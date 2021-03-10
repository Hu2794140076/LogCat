package  com.enen.logcat;


import android.content.Context;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Shell {

    private static final String COMMAND_SU = "su";
    private static final String COMMAND_EXIT = "exit\n";
    private static final String COMMAND_LINE_END = "\n";


    public static String exec(String commands, boolean isNeedResultMsg) {
        Process process = null;
        BufferedReader successResult = null;
        StringBuilder successMsg = null;
        DataOutputStream os = null;
        try {
            process = Runtime.getRuntime().exec(COMMAND_SU);
            os = new DataOutputStream(process.getOutputStream());
            os.write(commands.getBytes());
            os.writeBytes(COMMAND_LINE_END);
            os.writeBytes(COMMAND_EXIT);
            os.flush();
            process.waitFor();
            if (isNeedResultMsg) {
                successMsg = new StringBuilder();
                successResult = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String s;
                while ((s = successResult.readLine()) != null) {
                    successMsg.append(s).append("\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (successResult != null) {
                    successResult.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (process != null) {
                process.destroy();
            }
        }
        return successMsg == null ? "null" : successMsg.toString();
    }
}

