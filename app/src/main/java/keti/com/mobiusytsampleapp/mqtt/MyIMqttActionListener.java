package keti.com.mobiusytsampleapp.mqtt;

import android.util.Log;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Created by jaeyoung on 7/29/17.
 */

public class MyIMqttActionListener implements IMqttActionListener {
    private static final String TAG = "MyIMqttActionListener";
    private String MQTT_Req_Topic;

    @Override
    public void onSuccess(IMqttToken asyncActionToken) {

    }

    @Override
    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
        Log.d(TAG, "onFailure");
    }
}
