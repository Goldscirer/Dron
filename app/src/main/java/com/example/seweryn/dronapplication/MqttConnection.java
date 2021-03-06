package com.example.seweryn.dronapplication;

import android.support.v7.app.AppCompatActivity;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;



public class MqttConnection {

    private final AppCompatActivity  view;

    public MqttConnection(AppCompatActivity view){
        this.view = view;
    }

    public boolean Connect() {
        String clientId = MqttClient.generateClientId();
        MqttAndroidClient client =
                new MqttAndroidClient(view, ActivityConnect.IP_ADDRESS,
                        clientId);

        try {
            IMqttToken token = client.connect();
            MessageConnectionHandler.build(client, view);
            token.setActionCallback(MessageConnectionHandler.getHandler(view));
            return true;
        } catch (MqttException e) {
            e.printStackTrace();
            return false;
        }
    }
}
