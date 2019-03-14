package com.qh.modbus;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @Author: qh
 * @Date: 2018/10/27 14:14
 * @Description: 接收数据
 */
public class TcpReceiverThread {

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        String serial = "*00007VERSION\\n1$";//串口字符串
        String hex = HexConvert.convertStringToHex(serial);//转化为十六进制字符串：2a303030303756455253494f4e5c6e3124
        byte[] buf = HexConvert.hexStringToBytes( hex );//将十六进制字符串转为字节数组
        //将数据打包
        DatagramPacket packet = new DatagramPacket(buf, buf.length, InetAddress.getByName("192.168.1.204"), 4196);
        socket.send(packet);
        socket.close();
    }
}
