package com.qh.modbus;

import com.qh.utils.SerialTool;
import junit.framework.Assert;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @Author: qh
 * @Date: 2018/10/27 14:23
 * @Description:
 */
public class demo {

    public static void main(String[] args) throws Exception {
        String strOutput = "";
        // 要连接的服务端IP地址和端口
        String host = "192.168.1.204";
        // 监听指定的端口
        int port = 4196;
        // 与服务端建立连接
        Socket socket = new Socket(host, port);
        //待发送数据
        String message = "01 01 00 00 00 01";
        //校验码
        String crc = SerialTool.Make_CRC(SerialTool.ConvertByte(message));
        socket.setKeepAlive(true);
        //发送数据,客户端输出流作为服务器的输入
        OutputStream socketWriter = socket.getOutputStream();
        socketWriter.write(SerialTool.ConvertByte(message.concat(" " + crc.substring(0,2) + " " + crc.substring(2))));
        socketWriter.flush();
        Thread.sleep(1000);

        //服务器的输出即为客户端的输入，这里主要是为了把服务器输出的字节流报文转化成字符串，方便进行解析，最终测试报文的正确性
        InputStream socketReader = socket.getInputStream();
        byte[] temp = new byte[6];
        int bytes = 0;
        /* read从输入流socketReader中读取temp（6）数量的字节数，并将它们存储到缓冲区数组temp。实际读取的字节数作为一个整数6返回。
         * 此方法块，直到输入数据可用，检测到文件结束，或抛出异常。如果B的长度为零，则没有读取字节数和返回0；
         * 否则，将有一个至少一个字节的尝试。如果没有可用的字节，因为流是在文件的结尾，值- 1返回；否则，至少一个字节被读取和存储到temp。
         */
        bytes = socketReader.read(temp);
        if (bytes != 6) {
            throw new Exception("报错");
        }
        strOutput += ByteUtil.BinaryToHexString(temp);
        //读取报文体的内容
        String[] order = strOutput.split(" ");
        int state = 0;
        if (order.length > 2) {

            state = (int) Long.parseLong(order[2], 16);
        }
        System.out.println(state);
        //把字符串中“ ”去掉
        strOutput = strOutput.replaceAll(" ", "");
        socket.close();
        Assert.assertEquals(strOutput.substring(0,4), "0101");
        System.out.println(strOutput);
    }



    /**
     * 将十六进制的字符串转换成字节数组
     *
     * @param hexString
     * @return
     */
    public static byte[] hexStrToBinaryStr(String hexString) {
        if (StringUtils.isEmpty(hexString)) {
            return null;
        }
        hexString = hexString.replaceAll(" ", "");
        int len = hexString.length();
        int index = 0;
        byte[] bytes = new byte[len / 2];
        while (index < len) {
            String sub = hexString.substring(index, index + 2);
            bytes[index/2] = (byte)Integer.parseInt(sub,16);
            index += 2;
        }
        return bytes;
    }
}
