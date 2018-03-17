package com.example.seweryn.dronapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class activity_connect extends AppCompatActivity implements CommandPublishEventHandler, DebugEventHandler {

    private MqttConfiguration mqttConfiguration;
    private CommandController commandController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        mqttConfiguration = new MqttConfiguration(getApplicationContext(), this);
        mqttConfiguration.setUpConnection();

        commandController = new CommandController(getDelegate(), this);
        commandController.setUpControls();
    }

    @Override
    public void sendNotepadCommand() {

    }

    @Override
    public void sendRebootCommand() {

    }

    @Override
    public void reconnect()
    {
        mqttConfiguration.reconect();
    }

    @Override
    public void changeDebugLabel(final String text)
    {
        Log.e("STATE",String.valueOf(commandController.getDebugLabel())) ;
        //commandController.getDebugLabel().setText(text);
    }
}
