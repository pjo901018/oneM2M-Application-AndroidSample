package keti.com.mobiusytsampleapp.mqtt;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import keti.com.mobiusytsampleapp.R;

/**
 * Created by jaeyoung on 7/28/17.
 */

public class MqttActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private static final String TAG = "MqttActivity";

    private MqttAndroidClient mqttClient = null;
    private MyMqttCallback mainMqttCallback = new MyMqttCallback();
    private IMqttActionListener mainIMqttActionListener;
    private TextView textViewData;
    private Handler handler = new Handler();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Switch Switch_MQTT = (Switch) findViewById(R.id.switch_mqtt);
        textViewData = (TextView) findViewById(R.id.textViewData);
        Switch_MQTT.setOnCheckedChangeListener(this);
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            Log.d(TAG, "MQTT Create");
            MQTT_Create(true);
        } else {
            Log.d(TAG, "MQTT Close");
            MQTT_Create(false);
        }
    }
    /* MQTT Subscription */
    public void MQTT_Create(boolean mtqqStart) {
        if (mtqqStart && mqttClient == null) {
            /* Subscription Resource Create to Yellow Turtle */
            SubscribeResource subcribeResource = new SubscribeResource();
            subcribeResource.setReceiver(new IReceived() {
                public void getResponseBody(final String msg) {
                    handler.post(new Runnable() {
                        public void run() {
                            textViewData.setText("**** Subscription Resource Create 요청 결과 ****\r\n\r\n" + msg);
                        }
                    });
                }
            });
            subcribeResource.start();

            /* MQTT Subscribe */
            mqttClient = new MqttAndroidClient(this.getApplicationContext(), "tcp://" + Config.MQTT.HOST + ":" + Config.MQTT.PORT, MqttClient.generateClientId());
            mainIMqttActionListener = new MyIMqttActionListener(mqttClient);


            mainMqttCallback.setListener(
                    new Consumer<String>() {
                        @Override
                        public void accept(@NonNull String s) throws Exception {
                            textViewData.setText(s);
                        }
                    }
            );
            mqttClient.setCallback(mainMqttCallback);
            try {
                IMqttToken token = mqttClient.connect();
                token.setActionCallback(mainIMqttActionListener);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        } else {
            /* MQTT unSubscribe or Client Close */
            mqttClient.setCallback(null);
            mqttClient.close();
            mqttClient = null;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

    }
    @Override
    public void onStop() {
        super.onStop();

    }
}
