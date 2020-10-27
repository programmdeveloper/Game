import app.TCPFileExchange;
import app.TCPFileExchangeImpl;

import java.io.IOException;

/**
 * UI file transfer
 * expected parameters:
 * 1) send : filename, hostname (recipient), port
 * 2) receive : filename, port
 */
public class TCPFileExchangerUI {
    public static void main(String[] args) throws IOException {
        if(args.length < 2){
            System.err.println("at least two arguments required");
            return;
        }

        String filename = args[0];
        String hostname = null;
        int port = -1;

        String portString = null;

        //1) send
        if(args.length == 3){
            hostname = args[1];
            portString = args[2];
        } else if(args.length == 2) {
            // 2 receive
            portString = args[1];
        }

        port = Integer.parseInt(portString);

        TCPFileExchange tcpFileExchanger = new TCPFileExchangeImpl();
        if(hostname == null){
            //receive
            tcpFileExchanger.recieveFile(filename, port);
        } else {
            //send
            tcpFileExchanger.sendFile2Host(filename, hostname, port);
        }

    }
}
