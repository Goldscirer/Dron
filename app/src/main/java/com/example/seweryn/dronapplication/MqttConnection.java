package com.example.seweryn.dronapplication;

import android.content.Context;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;



public class MqttConnection {

    private final Context view;

    public MqttConnection(Context view){
        this.view = view;
    }

    public boolean Connect() {
        String clientId = MqttClient.generateClientId();
        MqttAndroidClient client =
                new MqttAndroidClient(view, ActivityConnect.IP_ADDRESS,
                        clientId);

        try {
            IMqttToken token = client.connect();
            token.setActionCallback(new MessageConnectionHandler(client, view, ActivityConnect.TOPIC_NAME));
            return true;
        } catch (MqttException e) {
            e.printStackTrace();
            return false;
        }
    }
}
