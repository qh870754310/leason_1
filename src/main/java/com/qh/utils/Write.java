package com.qh.utils;

import gnu.io.SerialPort;

/**
 * Created by Administrator on 2018/6/20.
 */
public class Write {

    public static void main(String[] args) {
        SerialPort serialPort = null;                  //打开的端
        try {
            //打开串口
            serialPort = SerialTool.openPort("COM3", 115200);
            String o = "01 05 00 10 00 00";
            //写入数组
            //获取校验码
            String crc = SerialTool.Make_CRC(SerialTool.ConvertByte(o));
            SerialTool.sendToPort(serialPort, SerialTool.ConvertByte(o.concat(" " + crc.substring(0,2) + " " + crc.substring(2))));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //关闭串口
            SerialTool.closePort(serialPort);
        }
    }
}

/**
 *
 * 1、风扇
 * 打开：01 05 00 12 ff 00
 * 关闭：01 05 00 12 00 00
 *
 * 报警器
 * 打开：01 05 00 13 ff 00
 * 关闭：01 05 00 13 00 00
 *
 */
