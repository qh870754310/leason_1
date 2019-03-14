package com.qh.utils;

/**
 * Created by Administrator on 2018/6/19.
 */
public class ListCommand {

    public static String command1 = "00 00 00 00 00 06 01 01 00 00 00 08";       //查询8个DI

    public static String command2 = "00 00 00 00 00 06 04 01 00 00 00 08";       //查询8个AI

    public static String command3 = "00 00 00 00 00 06 01 01 00 10 00 08";       //查询8个DO

    public static String command4 = "00 00 00 00 00 06 01 05 00 10 ff 00";       //控制第1个DO打开
    public static String command5 = "00 00 00 00 00 06 01 05 00 10 00 00";       //控制第1个DO关闭

    public static String command6 = "00 00 00 00 00 06 01 05 00 11 ff 00";       //控制第2个DO打开
    public static String command7 = "00 00 00 00 00 06 01 05 00 11 00 00";       //控制第2个DO关闭

    public static String command8 = "00 00 00 00 00 06 01 05 00 12 ff 00";       //控制第3个DO打开
    public static String command9 = "00 00 00 00 00 06 01 05 00 12 00 00";       //控制第3个DO关闭

    public static String command10 = "00 00 00 00 00 06 01 05 00 13 ff 00";      //控制第4个DO打开
    public static String command11 = "00 00 00 00 00 06 01 05 00 13 00 00";      //控制第4个DO关闭

    public static String command12 = "00 00 00 00 00 06 01 05 00 14 ff 00";      //控制第5个DO打开
    public static String command13 = "00 00 00 00 00 06 01 05 00 14 00 00";      //控制第5个DO关闭

    public static String command14 = "00 00 00 00 00 06 01 05 00 15 ff 00";      //控制第6个DO打开
    public static String command15 = "00 00 00 00 00 06 01 05 00 15 00 00";      //控制第6个DO关闭

    public static String command16 = "00 00 00 00 00 06 01 05 00 16 ff 00";      //控制第7个DO打开
    public static String command17 = "00 00 00 00 00 06 01 05 00 16 00 00";      //控制第7个DO关闭

    public static String command18 = "00 00 00 00 00 06 01 05 00 17 ff 00";      //控制第8个DO打开
    public static String command19 = "00 00 00 00 00 06 01 05 00 17 00 00";      //控制第8个DO关闭
}
