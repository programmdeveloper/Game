package sensordata;

import java.io.*;
import java.util.Date;

public class SensorDataExchanger implements SensorDataSender, SensorDataReceiver{

    @Override
    public void sendSensorData(SensorData data, OutputStream os) throws IOException {
        DataOutputStream daos = new DataOutputStream(os);

        daos.writeLong(data.getTimeStamp());
        daos.writeFloat(data.getValue());
        daos.writeUTF(data.getSensorName());

        os.close();
    }

    @Override
    public SensorData receiveSensorData(InputStream is) throws IOException {
        DataInputStream dais = new DataInputStream(is);

        long timeStamp = dais.readLong();
        float value = dais.readFloat();
        String sensorName = dais.readUTF();

        Date date = new Date(timeStamp);
        return new SensorDataImpl(timeStamp, value, sensorName);

        /*return new SensorData() {
            @Override
            public long getTimeStamp() {
                return timeStamp;
            }

            @Override
            public float getValue() {
                return value;
            }

            @Override
            public String getSensorName() {
                return sensorName;
            }
        }*/
    }
}
