package server;

import java.io.IOException;

public class Start {
    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        if(args.length >= 2){
            TCPClient.main(args);
            return;
        }
        TCPServer.main(args);
    }
}
