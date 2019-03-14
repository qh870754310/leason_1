package com.qh.utils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static com.qh.utils.StringUtil.byteArray2HexString;

/**
 * @Author: qh
 * @Date: 2018/10/26 17:00
 * @Description:
 */
public class Bus {

    public static void main(String[] args) {
        try {
            // 要连接的服务端IP地址和端口
            String host = "192.168.1.204";
            // 监听指定的端口
            int port = 4196;
            // 与服务端建立连接
            Socket socket = new Socket(host, port);
            // 建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
            OutputStream out=socket.getOutputStream();
            DataOutputStream data=new DataOutputStream(out);
            String message = "01 01 00 00 00 01";
            String crc = SerialTool.Make_CRC(SerialTool.ConvertByte(message));
            data.write(SerialTool.ConvertByte(message.concat(" " + crc.substring(0,2) + " " + crc.substring(2))));
            //通过shutdownOutput高速服务器已经发送完数据，后续只能接受数据
            socket.shutdownOutput();

            //接受数据
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[20] ;
            StringBuilder sb = new StringBuilder();
            int len;
            while ((len = inputStream.read(bytes)) != -1) {
                //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
                sb.append(new String(bytes, 0, len,"UTF-8"));
            }
            String hex = byteArray2HexString(bytes);
            System.out.println(hex);
            System.out.println("get message from server: " + sb.toString());
            inputStream.close();
            out.flush();
            out.close();
            data.flush();
            data.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
