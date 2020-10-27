package app;

import fileexchange.FileExchanger;
import fileexchange.FileReceiver;
import fileexchange.FileSender;
import tcp.Client;
import tcp.Connection;
import tcp.Server;
import tcp.TCPConnector;

import java.io.IOException;

public class TCPFileExchangeImpl implements TCPFileExchange{

    @Override
    public void sendFile2Host(String filename, String hostname, int port) throws IOException {
        // need connection
        Client client = new TCPConnector();
        Connection connection = client.connect(hostname, port);
        //send file
        FileSender fileSender = new FileExchanger();
        fileSender.sendFile(filename, connection.getOutputStream());
    }

    @Override
    public void recieveFile(String filename, int port) throws IOException {
        Server server = new TCPConnector();
        Connection connection = server.acceptConnection(port);

        FileReceiver fileReceiver = new FileExchanger();
        fileReceiver.receivedFile(filename, connection.getInputStream());
    }
}
