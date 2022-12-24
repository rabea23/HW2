package com.example.hw_game1;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class StepDetector {

    public interface CallBack_steps {
        void oneStep();
        void bonos();

    }


    private SensorManager mSensorManager;
    private Sensor sensor;

    float step = 0;
    long timeStamp = 0;
    float step2 = 0;


    private CallBack_steps callBack_steps;

    /**
     * Step detector constructor
     * @param context the context of the activity or application or service
     * @param _callBack_steps the listener to steps
     */
    public StepDetector(Context context, CallBack_steps _callBack_steps) {
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        this.callBack_steps = _callBack_steps;
    }

    public float getStepCounter() {
        return step;
    }

    /**
     * register to the sensors
     */
    public void start() {
        mSensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    /**
     * unregister to the sensors
     */
    public void stop() {
        mSensorManager.unregisterListener(sensorEventListener);
    }

    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];

            calculateStep(x, y);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    public float getStep2() {
        return step2;
    }

    private void calculateStep(float x, float y) {

                timeStamp = System.currentTimeMillis();
                step =x;
                if (callBack_steps != null) {
                    callBack_steps.oneStep();
                }



                timeStamp = System.currentTimeMillis();
                step2=y;
                if (callBack_steps != null) {
                    callBack_steps.bonos();
                }





    }
}
