package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    private final int port;
    private static final int PORTNUMMER = 3333;

    public static void main(String[] args) throws IOException, InterruptedException {

        TCPServer tcp = new TCPServer(PORTNUMMER);

        if(args.length == 1){
            tcp.readFile(args[0]);
        } else {
            //tcp.work();
            tcp.receiveSensorDate();
        }
    }

    private void receiveSensorDate() throws IOException {
        Socket socket = this.acceptSocket();
        InputStream is = socket.getInputStream();

        DataInputStream dais = new DataInputStream(is);
        long timeStamp = dais.readLong();
        float value = dais.readFloat();
        String sensorName = dais.readUTF();

        System.out.println("timeStamp == " + timeStamp);
        System.out.println("value == " + value);
        System.out.println("sensorName == " + sensorName);
    }

    private void readFile(String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        Socket socket = this.acceptSocket();
        InputStream is = socket.getInputStream();

        int read = 0;
        do{
            read = is.read();
            if(read != -1){
                fos.write(read);
            }
        } while (read != -1);
    }

    private Socket acceptSocket() throws IOException {
        ServerSocket server = new ServerSocket(this.port);
        System.out.println("Gestartet");
        return server.accept();
    }

    private void work() throws IOException, InterruptedException {
        Socket socket = this.acceptSocket();
        socket.getInputStream().read();
        System.out.println("read sth");
        OutputStream os = socket.getOutputStream();
        os.write(":)".getBytes());
        System.out.println("write sth");
        Thread.sleep(5000);
        System.out.println("woke up");
        os.close();
    }

    TCPServer(int port){
        this.port = port;
    }

}
