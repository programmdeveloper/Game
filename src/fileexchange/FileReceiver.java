package fileexchange;

import java.io.IOException;
import java.io.InputStream;

public interface FileReceiver {
    /**
     * received content from input stream and writes it to local file
     * @param filename local file name
     * @param is content provider
     */
    void receivedFile(String filename, InputStream is) throws IOException;
}
