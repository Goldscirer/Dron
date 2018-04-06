package com.example.seweryn.dronapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Created by root on 23.03.18.
 */

public class MessageConnectionHandler implements IMqttActionListener {

    private  MqttAndroidClient client;

    private final AppCompatActivity view;
    private final String topic;
    static public  String CONF_TEXT = " ";
    private TextView configurationText;


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

                 configurationText = (TextView)view.findViewById(R.id.configurationText);
                 configurationText.setText(MessageConnectionHandler.CONF_TEXT);

             }

             @Override
             public void deliveryComplete(IMqttDeliveryToken token) {

             }
         });
    }
}
