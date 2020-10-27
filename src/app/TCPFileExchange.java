package app;

import java.io.IOException;

public interface TCPFileExchange {
    /**
     * Send a file from local entity to remote host via TCP
     * @param filename
     * @param hostname
     * @param port
     */
    void sendFile2Host(String filename, String hostname, int port) throws IOException;

    /**
     * receive content from a TCP client and write it to a local file
     * @param filename local file name
     * @param port to accept connections
     */
    void recieveFile(String filename, int port) throws IOException;
}
