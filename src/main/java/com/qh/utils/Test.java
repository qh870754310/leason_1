package com.qh.utils;

/**
 * @Author: qh
 * @Date: 2018/10/26 17:04
 * @Description:
 */
public class Test {
    public static int [] getCrc(byte[] data) {
        int flag;
        // 16位寄存器，所有数位均为1
        int wcrc = 0xffff;
        for (int i = 0; i < data.length; i++) {
            // 16 位寄存器的低位字节
            // 取被校验串的一个字节与 16 位寄存器的高位字节进行“异或”运算
            wcrc = wcrc ^ data[i];
            for (int j = 0; j < 8; j++) {
                flag = wcrc & 0x0001;
                // 把这个 16 寄存器向右移一位
                wcrc = wcrc >> 1;
                // 若向右(标记位)移出的数位是 1,则生成多项式 1010 0000 0000 0001 和这个寄存器进行“异或”运算
                if (flag == 1)
                    wcrc ^= 0xa001;
            }
        }
        //获取低八位
        int low = wcrc >> 8;//或者wcrc/256
        int up = wcrc % 256;//获取高八位
        int[] crc = {up, low};
        return crc;
        /*	return Integer.toHexString(wcrc);*/
    }
}
