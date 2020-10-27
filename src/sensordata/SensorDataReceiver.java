package sensordata;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface SensorDataReceiver {
    /**
     * receive data from stream and create new sensor data object
     * @param is
     * @return
     */
    SensorData receiveSensorData(InputStream is) throws IOException;
}
