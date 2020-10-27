package fileexchange;

import java.io.*;

public class FileExchanger implements FileSender, FileReceiver{

    @Override
    public void sendFile(String filename, OutputStream os) throws IOException {
        //open file
        FileInputStream fis = new FileInputStream(filename);
        //send to os
        streamData(fis, os);
        os.close();
    }

    @Override
    public void receivedFile(String filename, InputStream is) throws IOException{
        FileOutputStream fos = new FileOutputStream(filename);
        this.streamData(is, fos);
    }

    private void streamData(InputStream is, OutputStream os) throws IOException {
        int read = 0;
        do{
            read = is.read();
            if(read != -1){
                os.write(read);
            }
        } while (read != -1);
    }
}
