package keti.com.mobiusytsampleapp.mqtt;

/**
 * Created by jaeyoung on 7/29/17.
 */

public interface Config {
    interface CSE {
//        String HOST = "13.124.172.12";
        String HOST = "52.78.111.146";
        String PORT = "7579";
        String NAME = "Mobius";
        String SERVICE_URL = "http://"+ HOST +":"+PORT+"/"+NAME;
    }

    interface MQTT {
        String PORT = "1883";
    }

    interface AndroidClient {
        interface AE {
            String AEID = "ae-jibcon";
            String NAME = "ae-jibcon";
        }
    }

    interface Sensor {
        interface AE {
            String AEID = "ae-jyp";
            String NAME = "ae-jyp";
        }

        interface CNT {
            String NAME = "cnt-ultrasonic";
//            String NAME = "cnt-led";
        }
    }
}
