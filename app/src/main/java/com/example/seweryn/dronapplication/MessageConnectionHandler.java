package com.example.seweryn.dronapplication;

import android.content.Context;
import android.util.Log;
import android.view.View;
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

    private final Context view;
    private final String topic;
    static public  String CONF_TEXT = " ";


    public MessageConnectionHandler(MqttAndroidClient client, Context view, String topic) {
    this.client = client;
    this.view = view;
    this.topic = topic;
    }

    public MessageConnectionHandler(Context view, String topic) {
        this.view = view;
        this.topic = topic;
    }

    @Override
    public void onSuccess(IMqttToken asyncActionToken) {
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
        try{
            client.subscribe(ActivityConnect.TOPIC_NAME, 0);
        } catch (MqttException e) {
            e.printStackTrace();
        }
         client.setCallback(new MqttCallback() {
             @Override
             public void connectionLost(Throwable cause) {

             }

             @Override
             public void messageArrived(String topic, MqttMessage message) throws Exception {
                 CONF_TEXT = new String(message.getPayload());
             }

             @Override
             public void deliveryComplete(IMqttDeliveryToken token) {

             }
         });
    }
}
