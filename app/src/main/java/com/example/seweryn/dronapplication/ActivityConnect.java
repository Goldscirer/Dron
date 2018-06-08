package com.example.seweryn.dronapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


public class ActivityConnect extends AppCompatActivity {

    static public String IP_ADDRESS;
    static public String TOPIC_NAME = "CONFIG/ANDROID/POST";
    static public String MESSAGES;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);
        Log.e("ip", "onCreate");

    }

    public void setParameters() {
        EditText ipText = (EditText) findViewById(R.id.ipAddress);
        IP_ADDRESS = "tcp://" + ipText.getText().toString() + ":1883";
        MESSAGES = "connection test";
    }

    public void onButtonClick(View v) {

        setParameters();

        MqttConnection conn = new MqttConnection(this);
        conn.Connect();
    }
}
