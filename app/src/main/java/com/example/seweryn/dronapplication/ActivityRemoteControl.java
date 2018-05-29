package com.example.seweryn.dronapplication;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;

public class ActivityRemoteControl extends AppCompatActivity implements SensorEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_control);

        pilot();
    }

    void pilot() {
        SensorManager sensorMenager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);




        Sensor sensor_1 = sensorMenager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


        sensorMenager.registerListener(this, sensor_1, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        MessageConnectionHandler connectionHandler = MessageConnectionHandler.getHandler(this);
        String JSON;

        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        RemoteControlMessage message= new RemoteControlMessage();

        message.x_axis = sensorEvent.values[0];
        message.y_axis = sensorEvent.values[1];
        message.z_axis = sensorEvent.values[2];


        ObjectMapper mapper = new ObjectMapper();
        try {
            JSON = mapper.writeValueAsString(message);
            connectionHandler.pub(JSON, Topics.REMOTE);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Log.d("tag", Arrays.toString(sensorEvent.values));

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
