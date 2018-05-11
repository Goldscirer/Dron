package com.example.seweryn.dronapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * Created by root on 23.03.18.
 */

public class MessageConnectionHandler implements IMqttActionListener {

    private  MqttAndroidClient client;

    private final AppCompatActivity view;
    private final String topic;
    static public  String CONF_TEXT = " ";
    private TextView configurationText;
    public static EditText tEdit;

    public static  String rotor_1_1;
    public ActivityConfigurationDron dornConf;
    static ConfigurationMessage JSON;

    public MessageConnectionHandler(MqttAndroidClient client, AppCompatActivity view, String topic) {
    this.client = client;
    this.view = view;
    this.topic = topic;
    }

    public MessageConnectionHandler(AppCompatActivity  view, String topic) {
        this.view = view;
        this.topic = topic;
    }

    @Override
    public void onSuccess(IMqttToken asyncActionToken) {
        Log.d("tag", "dupa3");
        Toast.makeText(view, "connected!", Toast.LENGTH_LONG).show();
        Log.d("id", "onSuccess");
        pub(client);
        sub();
    }

    @Override
    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
        Toast.makeText(view, "failed!", Toast.LENGTH_LONG).show();
        Log.d("id", "onFailure");

    }


    public void pub(MqttAndroidClient client){
        String message = ActivityConnect.MESSAGES;
        try {
            client.publish(topic, message.getBytes(),0,false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

     public void sub(){
        Log.d("tag", "dupa1");
        try{
            client.subscribe(Topics.SUBSCRIBE.toArray(new String[Topics.SUBSCRIBE.size()]), new int[Topics.SUBSCRIBE.size()]);
            for (String s : Topics.SUBSCRIBE.toArray(new String[Topics.SUBSCRIBE.size()])) {
                Log.d("tag", s);
            }
          //  client.subscribe("abc", 0);
        } catch (MqttException e) {
            Log.d("tag", "dupaX");
            e.printStackTrace();
        }
         client.setCallback(new MqttCallback() {
             @Override
             public void connectionLost(Throwable cause) {
                Log.d("tag", "dupa4");
             }

             @Override
             public void messageArrived(String topic, MqttMessage message) throws Exception {//potem beda rozne topici i if bedziesz sprawdzal jaka wiadomosc na jaki topic przyszla
                 Log.d("tag", "dupa");
                 CONF_TEXT = new String(message.getPayload());

                 /*configurationText = (TextView)view.findViewById(R.id.configurationText);
                 configurationText.setText(MessageConnectionHandler.CONF_TEXT);*/
                 getMessage(CONF_TEXT);
             }

             @Override
             public void deliveryComplete(IMqttDeliveryToken token) {

             }
         });
    }

    public void getMessage(String message) {


        try {
            ObjectMapper m = new ObjectMapper();
            ConfigurationMessage myClass = m.readValue(message,ConfigurationMessage.class);
            Log.d("tag","aa => " + myClass.Rotor_1_1);
            Log.d("tag","aa => " );
            setFieldConfiguration(myClass);
            JSON = myClass;


        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFieldConfiguration(ConfigurationMessage message) {

         /* Thrust */


         tEdit = (EditText)view.findViewById(R.id.editRotor_1_1);
        tEdit.setText(message.Rotor_1_1, TextView.BufferType.EDITABLE);

        tEdit = (EditText)view.findViewById(R.id.editRotor_1_2);
        tEdit.setText(message.Rotor_1_2, TextView.BufferType.EDITABLE);

        tEdit = (EditText)view.findViewById(R.id.editRotor_1_3);
        tEdit.setText(message.Rotor_1_3, TextView.BufferType.EDITABLE);

        tEdit = (EditText)view.findViewById(R.id.editRotor_1_4);
        tEdit.setText(message.Rotor_1_4, TextView.BufferType.EDITABLE);

        /* YAW */

        tEdit = (EditText)view.findViewById(R.id.editRotor_2_1);
        tEdit.setText(message.Rotor_1_1, TextView.BufferType.EDITABLE);

        tEdit = (EditText)view.findViewById(R.id.editRotor_2_2);
        tEdit.setText(message.Rotor_1_2, TextView.BufferType.EDITABLE);

        tEdit = (EditText)view.findViewById(R.id.editRotor_2_3);
        tEdit.setText(message.Rotor_1_3, TextView.BufferType.EDITABLE);

        tEdit = (EditText)view.findViewById(R.id.editRotor_2_4);
        tEdit.setText(message.Rotor_1_4, TextView.BufferType.EDITABLE);

        /* Roll */

        tEdit = (EditText)view.findViewById(R.id.editRotor_3_1);
        tEdit.setText(message.Rotor_1_1, TextView.BufferType.EDITABLE);

        tEdit = (EditText)view.findViewById(R.id.editRotor_3_2);
        tEdit.setText(message.Rotor_1_2, TextView.BufferType.EDITABLE);

        tEdit = (EditText)view.findViewById(R.id.editRotor_3_3);
        tEdit.setText(message.Rotor_1_3, TextView.BufferType.EDITABLE);

        tEdit = (EditText)view.findViewById(R.id.editRotor_3_4);
        tEdit.setText(message.Rotor_1_4, TextView.BufferType.EDITABLE);

        /* Pitch */

        tEdit = (EditText)view.findViewById(R.id.editRotor_4_1);
        tEdit.setText(message.Rotor_1_1, TextView.BufferType.EDITABLE);

        tEdit = (EditText)view.findViewById(R.id.editRotor_4_2);
        tEdit.setText(message.Rotor_1_2, TextView.BufferType.EDITABLE);

        tEdit = (EditText)view.findViewById(R.id.editRotor_4_3);
        tEdit.setText(message.Rotor_1_3, TextView.BufferType.EDITABLE);

        tEdit = (EditText)view.findViewById(R.id.editRotor_4_4);
        tEdit.setText(message.Rotor_1_4, TextView.BufferType.EDITABLE);
    }

    public ConfigurationMessage getJsonObject() {
         Log.d("tag", "getJSON =>" + JSON);
         return JSON;
    }

}
