package com.enen.logcat;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Shell.exec("logcat -c", false);

        findViewById(R.id.clear).setOnClickListener(v -> {
            Shell.exec("logcat -c", false);
            Toast.makeText(this, "清除成功", Toast.LENGTH_SHORT).show();
        });
        findViewById(R.id.exe).setOnClickListener(v -> {
            String path = "sdcard/log.txt";
            Shell.exec("logcat -v time -d -f " + path, false);
            Toast.makeText(this, "获取日志成功 路劲" + path, Toast.LENGTH_LONG).show();
        });
    }
}
