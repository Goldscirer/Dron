package com.example.seweryn.dronapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


public class activity_connect extends AppCompatActivity implements CommandPublishEventHandler, DebugEventHandler {

    private MqttConfiguration mqttConfiguration;
    private CommandController commandController;
    public String ipAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

    }

    @Override
    public void reconnect()
    {
        mqttConfiguration.reconect();
    }

    public void onButtonClick(View v){
        EditText e1 = (EditText) findViewById(R.id.ipAddress);
        ipAddress = e1.getText().toString();
        Log.e("ip w activity",ipAddress);

        commandController = new CommandController(getDelegate(), this);
        commandController.setUpControls();

        mqttConfiguration = new MqttConfiguration(getApplicationContext(), this);
        mqttConfiguration.setIpAddress(ipAddress);
        mqttConfiguration.setUpConnection();
    }

    @Override
    public void changeDebugLabel(final String text)
    {
       // if (commandController.getDebugLabel() != null) {
           // commandController.getDebugLabel().setText(text);
        //}
            commandController.getDebugLabel().setText(text);
    }
}
