package com.example.seweryn.dronapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityConfigurationDron extends AppCompatActivity {

    MessageConnectionHandler connectionHandler = new MessageConnectionHandler(this, "");
    TextView configurationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration_dron);

        configurationText = (TextView)findViewById(R.id.configurationText);

    }

    public void downloadConfiguration(View v){
        ActivityConnect.TOPIC_NAME = "CONFIG/ANDROID/GET";
        ActivityConnect.MESSAGES = " ";

        if(ActivityConnect.IP_ADDRESS != null)
        {
            MqttConnection conn = new MqttConnection(this);
            if(conn.Connect() == true)
            {
                //configurationText.setText(MessageConnectionHandler.CONF_TEXT);
            }
        }
        else
        {
            Toast.makeText( this, "You must set up ip address!", Toast.LENGTH_LONG).show();
        }

    }
}
