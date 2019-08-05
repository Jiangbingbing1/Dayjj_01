package com.bawei.dayjj_01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        try {
            MyOkhttp.getInstance().RequestJson(new MyOkhttp.MyCallback() {
                @Override
                public void success(String json) {
                    name.setText(json);
                }

                @Override
                public void error(String error) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initView() {
        name = (TextView) findViewById(R.id.name);
    }
}
