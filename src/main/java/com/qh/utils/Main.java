package com.qh.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Administrator on 2018/6/19.
 */
public class Main {

    public static void main(String[] args) {
        try {
            InetSocketAddress socketAddress  =  new InetSocketAddress(InetAddress.getByName("192.168.1.200"), 502);
            Socket socket = new Socket();
            socket.connect(socketAddress, 502);
            System.out.println(socket.isConnected());
            byte[] SendBytes = null;
            String SendData = ListCommand.command1;
            
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
