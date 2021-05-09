package com.threeklines.isibayaacademyclient;

import android.util.Log;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class StartServer extends Thread{
    private static final String TAG = "StartServer";
    Socket socket;
    int port;
    public StartServer(Socket socket, int port){
        this.socket = socket;
        this.port = port;
    }

    @Override
    public void run() {

        try {
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            int filenameLength = dataInputStream.readInt();
            byte[] filenameBytes = new byte[filenameLength];
            dataInputStream.readFully(filenameBytes, 0, filenameLength);
            String filename = new String(filenameBytes);
            Log.d(TAG, "run: Filename: " + filename);
            File file = new File(filename);
            Log.d(TAG, "run: File path: " + file.getAbsolutePath());

        }catch (SocketException socketException){
            Log.d(TAG, "run: sockect exception");
        }catch (IOException ioException){
            Log.d(TAG, "run: I/O Exception");
        }

    }
}
