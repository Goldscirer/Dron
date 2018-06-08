package com.example.seweryn.dronapplication;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;

public class ActivityRemoteControl extends AppCompatActivity implements SensorEventListener {

    private static Button button_stop_send;

    private SensorManager sensorMenager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_control);
        if (ActivityConnect.IP_ADDRESS != null) {
            pilot();
        } else {
            Toast.makeText(this, "You must set up ip address!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ActivityRemoteControl.this, ActivityConnect.class);
            startActivity(intent);
        }
    }

    public void onButtonClickStop() {
        Log.d("tag", "BUTTON CLICKED!!!!!!!!!");
        sensorMenager.unregisterListener(this);
        finish();
    }

    void pilot() {
        sensorMenager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);


        Sensor sensor_1 = sensorMenager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);


        sensorMenager.registerListener(this, sensor_1, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        MessageConnectionHandler connectionHandler = MessageConnectionHandler.getHandler(this);
        String JSON;

        RemoteControlMessage message = new RemoteControlMessage();

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
