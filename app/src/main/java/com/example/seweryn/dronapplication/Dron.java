package com.example.seweryn.dronapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Dron extends AppCompatActivity {

    private static Button button_configuration;
    private static Button button_connect;
    private static Button button_remote_control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dron);
        OnClickButtonListener();
    }

    public void OnClickButtonListener() {
        button_configuration = (Button)findViewById(R.id.buttonConf);
        button_connect = (Button)findViewById(R.id.buttonConnect);
        button_remote_control = (Button)findViewById(R.id.buttonRemote);

        button_configuration.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent("com.example.seweryn.dronapplication.ActivityConfigurationDron");
                        startActivity(intent);
                    }
                }
        );

        button_connect.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent("com.example.seweryn.dronapplication.ActivityConnect");
                        startActivity(intent);
                    }
                }
        );

        button_remote_control.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent("com.example.seweryn.dronapplication.ActivityRemoteControl");
                        startActivity(intent);
                    }
                }
        );
    }

}
