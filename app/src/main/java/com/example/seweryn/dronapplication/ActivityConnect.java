package com.example.seweryn.dronapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;


public class ActivityConnect extends AppCompatActivity {

    static public String IP_ADDRESS;
    static public String TOPIC_NAME;
    static public String MESSAGES;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);
        Log.e("ip", "onCreate");

    }

    public void setParameters() {
        EditText ipText = (EditText) findViewById(R.id.ipAddress);
        EditText topicText = (EditText) findViewById(R.id.topicName);
        IP_ADDRESS = "tcp://" + ipText.getText().toString() + ":1883";
        TOPIC_NAME = topicText.getText().toString();
        MESSAGES = "connection test";
    }

    public void onButtonClick(View v) {

        setParameters();

        MqttConnection conn = new MqttConnection(this);
        conn.Connect();
    }
}
