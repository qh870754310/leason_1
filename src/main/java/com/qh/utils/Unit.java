package com.qh.utils;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/19.
 */
public class Unit {

    /**
     * 数据转换
     *
     * @param data
     * @return
     */
    public static byte[] convertByte(String data) {
        try {
            if (StringUtils.isEmpty(data.trim())) {
                throw new Exception("传输数据不能为空");
            }
            //剔除所有空格
            data = data.replace(" ", "");
            if (data.length() % 2 == 1) { //奇数个字符
                data = data.substring(0, data.length()-1); //去除末位字符
            }
            List<String> SendDataList = new ArrayList<>();
            for (int i = 0; i < data.length(); i = i + 2) {
                SendDataList.add(data.substring(i, 2));
            }
            byte[] SendBytes = new byte[SendDataList.size()];
            for (int j = 0; j < SendBytes.length; j++) {

            }
            return SendBytes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
