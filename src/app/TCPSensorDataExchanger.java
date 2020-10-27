package app;

import sensordata.SensorData;

import java.io.IOException;

public interface TCPSensorDataExchanger {
    /**
     * send sensor data to a specified host
     * @param data
     * @param hostname
     * @param port
     */
    void sendSensorData(SensorData data, String hostname, int port) throws IOException;

    /**
     * listen on a port and received sensor data
     * @param port
     */
    void receiveSensorData(int port) throws IOException;
}
