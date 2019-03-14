package com.qh.utils;

import net.wimpi.modbus.ModbusException;
import net.wimpi.modbus.io.ModbusTCPTransaction;
import net.wimpi.modbus.msg.*;
import net.wimpi.modbus.net.TCPMasterConnection;

import java.net.InetAddress;

/**
 * @Author: qh
 * @Date: 2018/10/26 16:28
 * @Description:
 */
public class ModbusHelper {

    /**
     * 打开连接
     * @param ip
     * @param port
     * @return
     */
    public static TCPMasterConnection connectClient(String ip, int port) {
        try {
            InetAddress addr = InetAddress.getByName(ip);
            TCPMasterConnection con = new TCPMasterConnection(addr);
            con.setPort(port);
            con.connect();
            return con;
        } catch (Exception e) {
            System.out.print("connectClient");
            return null;
        }
    }

    /**
     * 关闭连接
     * @param con
     */
    public static void disconnectClient(TCPMasterConnection con){
        con.close();
    }

    public static int[] readHoldingRegister(int slaveId, int address, int count, TCPMasterConnection con){
        try {
            int[] data = new int[count];
            ReadMultipleRegistersRequest req = new ReadMultipleRegistersRequest(address, count);
            req.setUnitID(slaveId);
            ModbusTCPTransaction trans = new ModbusTCPTransaction(con);
            trans.setRequest(req);
            trans.execute();
            ReadMultipleRegistersResponse res = (ReadMultipleRegistersResponse) trans.getResponse();
            for(int i = 0; i < count; i++){
                data[i] = res.getRegisterValue(i);
            }
            return data;
        } catch (ModbusException e) {
            return null;
        }
    }

    /**
     * 读写保持寄存器
     * @param slaveId
     * @param address
     * @param count
     * @param con
     * @return
     */
    public static boolean readCoil(int slaveId, int address, int count, TCPMasterConnection con){
        try {
            boolean coil = false;
            ReadCoilsRequest req = new ReadCoilsRequest(address, count);
            req.setUnitID(slaveId);
            ModbusTCPTransaction trans = new ModbusTCPTransaction(con);
            trans.setRequest(req);
            trans.execute();
            ReadCoilsResponse res = (ReadCoilsResponse) trans.getResponse();
            for(int i = 0 ; i < count ; i ++) {
                coil = res.getCoilStatus(i);
            }
            return coil;
        } catch (ModbusException e) {
            return true;
        }
    }

    /**
     * 读写线圈
     * @param slaveId
     * @param address
     * @param value
     * @param con
     */
    public static void writeHoldingRegister(int slaveId, int address, int value, TCPMasterConnection con){
        try {
            ModbusTCPTransaction trans = new ModbusTCPTransaction(con);
            UnityRegister register = new UnityRegister(value);
            WriteSingleRegisterRequest req = new WriteSingleRegisterRequest(address, register);
            /*WriteSingleRegisterResponse response = new WriteSingleRegisterResponse();*/
            req.setUnitID(slaveId);
            trans.setRequest(req);
            System.out.println("ModbusSlave: FC" + req.getFunctionCode() + " ref=" + req.getReference() + " value=" + register.getValue());
            /*System.out.println();*/
            trans.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void writeCoil(int slaveId, int address, boolean value, TCPMasterConnection con){
        try {
            ModbusTCPTransaction trans = new ModbusTCPTransaction(con);
            WriteCoilRequest req = new WriteCoilRequest(address,value);
            /*WriteSingleRegisterResponse response = new WriteSingleRegisterResponse();*/
            req.setUnitID(slaveId);
            trans.setRequest(req);
            System.out.println("ModbusSlave: value=" + value);
            /*System.out.println();*/
            trans.execute();
        } catch (ModbusException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        String ip = "192.168.1.204";
        int port = 4196;
        //打开接口
        TCPMasterConnection tcpMasterConnection = connectClient(ip, port);
        System.out.println(tcpMasterConnection);
        String o = "01 05 00 13 ff 00";
        //写入数组
        //获取校验码
        String crc = SerialTool.Make_CRC(SerialTool.ConvertByte(o));
        System.out.println(crc);
     }
}
