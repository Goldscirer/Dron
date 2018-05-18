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

    private AppCompatActivity view;
    static public  String CONF_TEXT = " ";


    private static MessageConnectionHandler handler;

    private ConfigurationMessageListener configurationMessageListener;

    private  MessageConnectionHandler(MqttAndroidClient client, AppCompatActivity view) {
        this.client = client;
        this.view = view;
    }

    public void setView(AppCompatActivity view) {
        this.view = view;
    }

    public void setConfigurationMessageListener(ConfigurationMessageListener configurationMessageListener) {
        this.configurationMessageListener = configurationMessageListener;
    }

    public static MessageConnectionHandler getHandler(AppCompatActivity view) {
        return handler;
    }

    public static void build(MqttAndroidClient client, AppCompatActivity view)
    {
        if (handler == null)
        {
            handler = new MessageConnectionHandler(client, view);
        }
    }

    @Override
    public void onSuccess(IMqttToken asyncActionToken) {
        Log.d("tag", "dupa3");
        Toast.makeText(view, "connected!", Toast.LENGTH_LONG).show();
        Log.d("id", "onSuccess");
        sub();
    }

    @Override
    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
        Toast.makeText(view, "failed!", Toast.LENGTH_LONG).show();
        Log.d("id", "onFailure");

    }

    public void pub(final String message, final String topic2){
        try {
            Log.d("guzik", topic2);
            client.publish(topic2, message.getBytes(),0,false);
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
                 configurationMessageListener.handleMessage(new String(message.getPayload()));
             }

             @Override
             public void deliveryComplete(IMqttDeliveryToken token) {

             }
         });
    }
}
