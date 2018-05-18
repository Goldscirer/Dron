package com.example.seweryn.dronapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityConfigurationDron extends AppCompatActivity {

    ConfigurationMessage message;
    MessageConnectionHandler connectionHandler = new MessageConnectionHandler(this, "");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration_dron);

    }

    public void downloadConfiguration(View v){
        ActivityConnect.TOPIC_NAME = "CONFIG/ANDROID/GET";
        ActivityConnect.MESSAGES = " ";

        if(ActivityConnect.IP_ADDRESS != null)
        {
            MqttConnection conn = new MqttConnection(this);
            if(conn.Connect() == true)
            {
                Log.d("tag", "Connection and sub works");
            }
        }
        else
        {
            Toast.makeText( this, "You must set up ip address!", Toast.LENGTH_LONG).show();
        }
    }

    public void uploadDownloadConfiguration(View v) {
        ActivityConnect.TOPIC_NAME = Topics.UPDATE;
        Log.d("tag", "TO jest update => " + MessageConnectionHandler.JSON.Rotor_1_1);
        Log.d("tag", ActivityConnect.TOPIC_NAME);

        if(ActivityConnect.IP_ADDRESS != null)
        {
        }
        else
        {
            Toast.makeText( this, "You must set up ip address!", Toast.LENGTH_LONG).show();
        }


    }


}
