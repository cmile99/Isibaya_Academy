package com.threeklines.isibayaacademyclient;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CodeHolder {
    private static String TAG = "Tag";

    private void displayAddress() {
        final InetAddress inetAddress = getAddress();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        final Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                ServerSocket serverSocket = null;
                try {
                    serverSocket = new ServerSocket(1234, 0, inetAddress);
                    Log.d(TAG, "run: " + serverSocket.getInetAddress().getHostAddress() + ":" + serverSocket.getLocalPort());
                    new StartServer(serverSocket.accept(), 1234).start();

                } catch (IOException ioException) {
                    Log.d(TAG, "onCreate: Serversocket failed to create: " + ioException.getMessage());
                }

                final ServerSocket finalServerSocket = serverSocket;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (finalServerSocket != null) {
                            System.out.println();
                        }
                    }
                });
            }
        });
    }

    private InetAddress getAddress() {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress();

                        boolean isIPv4 = sAddr.indexOf(':') < 0;

                        if (isIPv4) {
                            return addr;
                        }
                    }
                }
            }
            return null;
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }
}









