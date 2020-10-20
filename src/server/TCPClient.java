package server;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {

    private static final String IRGENDWAS = "irgendwas";
    private final String hostname;
    private final int port;

    public static final String HOST = "localhost";
    public static final int PORT = 3333;

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        if(args.length<2){
            System.out.println("missing parameters: hostname portnumber");
        }

        String hostname = args[0];
        String portString = args[1];
        int portnumber = Integer.parseInt(portString);
        String fileName = null;

        if(args.length > 3){
            fileName = args[2];
        }

        TCPClient tcp = new TCPClient(hostname, portnumber);

        if(fileName != null){
            tcp.copyFile(fileName);
        } else {
            //tcp.work();
            long timeStamp = System.currentTimeMillis();
            float value = (float) 42.0;
            String sensorName = "Sensor A";
            tcp.sendSensorDate(timeStamp, value, sensorName);
        }
    }

    TCPClient(String hostname, int port){
        this.hostname = hostname;
        this.port = port;
    }

    private void sendSensorDate(long timeStamp, float value, String sensorName) throws IOException {
        Socket socket = new Socket(this.hostname, this.port);

        OutputStream os = socket.getOutputStream();

        DataOutputStream daos = new DataOutputStream(os);
        daos.writeLong(timeStamp);
        daos.writeFloat(value);
        daos.writeUTF(sensorName);

        os.close();

    }

    private void copyFile(String fileName) throws IOException {
        Socket socket = new Socket(this.hostname, this.port);
        FileInputStream fis = new FileInputStream(fileName);
        OutputStream os = socket.getOutputStream();

        int read = 0;
        do{
            read = fis.read();
            if(read != -1){
                os.write(read);
            }
        } while (read != -1);
        os.close();
    }

    private void work() throws IOException {
        Socket socket = new Socket(this.hostname, this.port);
        socket.getOutputStream().write(IRGENDWAS.getBytes());
        InputStream is = socket.getInputStream();
        byte[] buffer = new byte[1000];
        int i = 0;
        int read = 0;
        do{
           read = is.read();
           if(read != -1){
               byte readByte = (byte) read;
               buffer[i++] = readByte;
           }
        } while (read != -1);

        byte[] recievedBytes = new byte[i];
        for(int j=0; j < i; j++){
            recievedBytes[j] = buffer[j];
        }

        String receivedString = new String(recievedBytes);
        System.out.println(receivedString);
    }

//writeUTF
}
