package com.test.workbean;

import com.test.protocol.ProtocolLogin;

import java.net.DatagramSocket;
import java.net.InetAddress;

public class Timer implements Runnable {
    int port;InetAddress address; DatagramSocket socket;

    public Timer(int port, InetAddress address, DatagramSocket socket) {
        this.port = port;
        this.address = address;
        this.socket = socket;
    }

    @Override
    public void run(){
        try{
            while(true) {
                new ProtocolLogin(socket, address, port).keepAlive();
                Thread.sleep(3000);
            }
    }catch (Exception e){
        e.printStackTrace();}
    }

}
