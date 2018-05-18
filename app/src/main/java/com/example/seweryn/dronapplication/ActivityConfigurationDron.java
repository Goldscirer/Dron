package com.example.seweryn.dronapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ActivityConfigurationDron extends AppCompatActivity implements ConfigurationMessageListener {
    
    MessageConnectionHandler connectionHandler = MessageConnectionHandler.getHandler(this);
    ConfigurationMessage currentConfigrationMessage;

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
            connectionHandler.setConfigurationMessageListener(this);
            connectionHandler.pub(" ", Topics.GET);

        }
        else
        {
            Toast.makeText( this, "You must set up ip address!", Toast.LENGTH_LONG).show();
        }
    }

    public void uploadDownloadConfiguration(View v) throws IOException {

        ActivityConnect.TOPIC_NAME = Topics.UPDATE;
        Log.d("tag", ActivityConnect.TOPIC_NAME);

        if(ActivityConnect.IP_ADDRESS != null)
        {
            getFieldConfiguration();

            ObjectMapper mapper = new ObjectMapper();

            String jsonInString = mapper.writeValueAsString(currentConfigrationMessage);
            Log.d("tag", jsonInString);

            connectionHandler.pub(jsonInString, Topics.UPDATE);
        }


        else
        {
            Toast.makeText( this, "You must set up ip address!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void handleMessage(String message) {
        getMessage(message);
        
    }
    public void getMessage(String message) {


        try {
            ObjectMapper m = new ObjectMapper();
            currentConfigrationMessage = m.readValue(message, ConfigurationMessage.class);
            Log.d("tag","aa => " + currentConfigrationMessage.Rotor_1_1);
            Log.d("tag","aa => " );
            setFieldConfiguration(currentConfigrationMessage);


        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getFieldConfiguration() {

        /* Thrust */

        currentConfigrationMessage.Rotor_1_1 = ((EditText)findViewById(R.id.editRotor_1_1)).getText().toString();
        currentConfigrationMessage.Rotor_1_2 = ((EditText)findViewById(R.id.editRotor_1_2)).getText().toString();
        currentConfigrationMessage.Rotor_1_3 = ((EditText)findViewById(R.id.editRotor_1_3)).getText().toString();
        currentConfigrationMessage.Rotor_1_4 = ((EditText)findViewById(R.id.editRotor_1_4)).getText().toString();

        /* YAW */

        currentConfigrationMessage.Rotor_2_1 = ((EditText)findViewById(R.id.editRotor_2_1)).getText().toString();
        currentConfigrationMessage.Rotor_2_2 = ((EditText)findViewById(R.id.editRotor_2_2)).getText().toString();
        currentConfigrationMessage.Rotor_2_3 = ((EditText)findViewById(R.id.editRotor_2_3)).getText().toString();
        currentConfigrationMessage.Rotor_2_4 = ((EditText)findViewById(R.id.editRotor_2_4)).getText().toString();
        /* Roll */

        currentConfigrationMessage.Rotor_3_1 = ((EditText)findViewById(R.id.editRotor_3_1)).getText().toString();
        currentConfigrationMessage.Rotor_3_2 = ((EditText)findViewById(R.id.editRotor_3_2)).getText().toString();
        currentConfigrationMessage.Rotor_3_3 = ((EditText)findViewById(R.id.editRotor_3_3)).getText().toString();
        currentConfigrationMessage.Rotor_3_4 = ((EditText)findViewById(R.id.editRotor_3_4)).getText().toString();

        /* Pitch */

        currentConfigrationMessage.Rotor_4_1 = ((EditText)findViewById(R.id.editRotor_4_1)).getText().toString();
        currentConfigrationMessage.Rotor_4_2 = ((EditText)findViewById(R.id.editRotor_4_2)).getText().toString();
        currentConfigrationMessage.Rotor_4_3 = ((EditText)findViewById(R.id.editRotor_4_3)).getText().toString();
        currentConfigrationMessage.Rotor_4_4 = ((EditText)findViewById(R.id.editRotor_4_4)).getText().toString();

    }

    public void setFieldConfiguration(ConfigurationMessage message) {
        
        
         /* Thrust */
        
        ((EditText)findViewById(R.id.editRotor_1_1)).setText(currentConfigrationMessage.Rotor_1_1);

        ((EditText)findViewById(R.id.editRotor_1_2)).setText(currentConfigrationMessage.Rotor_1_2);

        ((EditText)findViewById(R.id.editRotor_1_3)).setText(currentConfigrationMessage.Rotor_1_3);

        ((EditText)findViewById(R.id.editRotor_1_4)).setText(currentConfigrationMessage.Rotor_1_4);

        /* YAW */

        ((EditText)findViewById(R.id.editRotor_2_1)).setText(currentConfigrationMessage.Rotor_2_1);

        ((EditText)findViewById(R.id.editRotor_2_2)).setText(currentConfigrationMessage.Rotor_2_2);

        ((EditText)findViewById(R.id.editRotor_2_3)).setText(currentConfigrationMessage.Rotor_2_3);

        ((EditText)findViewById(R.id.editRotor_2_4)).setText(currentConfigrationMessage.Rotor_2_4);

        /* Roll */

        ((EditText)findViewById(R.id.editRotor_3_1)).setText(currentConfigrationMessage.Rotor_3_1);

        ((EditText)findViewById(R.id.editRotor_3_2)).setText(currentConfigrationMessage.Rotor_3_2);

        ((EditText)findViewById(R.id.editRotor_3_3)).setText(currentConfigrationMessage.Rotor_3_3);

        ((EditText)findViewById(R.id.editRotor_3_4)).setText(currentConfigrationMessage.Rotor_3_4);

        /* Pitch */

        ((EditText)findViewById(R.id.editRotor_4_1)).setText(currentConfigrationMessage.Rotor_4_1);

        ((EditText)findViewById(R.id.editRotor_4_2)).setText(currentConfigrationMessage.Rotor_4_2);

        ((EditText)findViewById(R.id.editRotor_4_3)).setText(currentConfigrationMessage.Rotor_4_3);

        ((EditText)findViewById(R.id.editRotor_4_4)).setText(currentConfigrationMessage.Rotor_4_4);
    }
}
