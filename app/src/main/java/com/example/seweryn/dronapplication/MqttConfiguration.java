package com.example.seweryn.dronapplication;

import android.content.Context;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Created by Mateusz on 16.08.2017.
 */

public class MqttConfiguration
{
    /** */
    public static final String BROKER_IP_ADDRESS = "tcp://192.168.1.4:1883";

    /** */
    public static final String CLIENT_ID = "mobile";

    /** */
    public static final String DEFAULT_TOPIC = "command";

    /** */
    private final Context applicationContext;

    /** */
    private final DebugEventHandler debugEventHandler;

    /** */
    private MqttAndroidClient mqttAndroidClient;

    /** */
    public MqttConfiguration(final Context applicationContext, final DebugEventHandler debugEventHandler)
    {
        this.applicationContext = applicationContext;
        this.debugEventHandler = debugEventHandler;
    }

    /** */
    public void setUpConnection()
    {
        mqttAndroidClient = new MqttAndroidClient(
                applicationContext,
                MqttConfiguration.BROKER_IP_ADDRESS,
                MqttConfiguration.CLIENT_ID);

        mqttAndroidClient.setCallback(mqttCallbackExtended);

        final MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setAutomaticReconnect(true);
        mqttConnectOptions.setCleanSession(false);

        try
        {
            mqttAndroidClient.connect(mqttConnectOptions, null, mqttActionListener);
        }
        catch (final MqttException e)
        {
            debugEventHandler.changeDebugLabel("Connection failed");
        }
    }

    /** */
    private final MqttCallbackExtended mqttCallbackExtended = new MqttCallbackExtended()
    {
        @Override
        public void connectComplete(boolean reconnect, String serverURI)
        {
            debugEventHandler.changeDebugLabel("Connection complete");
        }

        @Override
        public void connectionLost(final Throwable cause)
        {
            debugEventHandler.changeDebugLabel("Connection lost");

            try
            {
                mqttAndroidClient.connect();
            }
            catch (final MqttException e)
            {
                debugEventHandler.changeDebugLabel("Reconnecting to broker failed");
            }
        }

        @Override
        public void messageArrived(final String topic, final MqttMessage message) throws Exception
        {

        }

        @Override
        public void deliveryComplete(final IMqttDeliveryToken token)
        {
            debugEventHandler.changeDebugLabel("Delivery complete");
        }
    };

    /** */
    private final IMqttActionListener mqttActionListener = new IMqttActionListener()
    {
        @Override
        public void onSuccess(final IMqttToken asyncActionToken)
        {
            debugEventHandler.changeDebugLabel("Successfully connected");
        }

        @Override
        public void onFailure(final IMqttToken asyncActionToken, final Throwable exception)
        {
            debugEventHandler.changeDebugLabel("Connection failed");
        }
    };

    /** */
    /*public void sendCommandMessage(final String message)
    {
        final MqttMessage commandMessage = new MqttMessage();
        commandMessage.setPayload(message.getBytes());
        try
        {
            mqttAndroidClient.publish(DEFAULT_TOPIC, commandMessage);
        }
        catch (final MqttException e)
        {
            debugEventHandler.changeDebugLabel("Publishing message failed");
        }
    }*/

    /** */
    public void reconect()
    {
        try
        {
            mqttAndroidClient.connect();
        }
        catch (final MqttException e)
        {
            debugEventHandler.changeDebugLabel("Reconnecting to broker failed");
        }
    }
}
