package com.test.protocol;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import com.test.socket.ReceiveShow;
import com.test.tools.JsonAnalysis;
import com.test.workbean.SysInfo;
import net.sf.json.JSONObject;

public class ProtocolAccount {
    ProtocolData protocolData = new ProtocolData();
    ProtocolBase sendMessage = new ProtocolBase();
    DatagramPacket packetSend;
    DatagramPacket packetReceive;
    DatagramSocket socket;
    InetAddress address;
    ReceiveShow rShow;
    int port;

    public ProtocolAccount(DatagramSocket socket, InetAddress address, int port) {
        this.socket = socket;
        this.address = address;
        this.port = port;
    }

    public void userReg() throws Exception{//用户注册
        SysInfo.setSort((byte)0);

        System.out.println("用户注册--->");
        protocolData.setOpt((byte) 8);
        protocolData.setCmd((byte) 2);
        protocolData.setType((byte) 1);
        JSONObject ob = new JSONObject();
        JSONObject info = new JSONObject();
        JSONObject content = new JSONObject();
        content.put("SNUM", "88888888");
        content.put("SVER", "FFFFFF");
        content.put("USER", SysInfo.getUsername());
        content.put("PASSWORD", SysInfo.getPassworld());
        info.put("LIMIT", "1");
        info.put("NAME", "用户10");
        info.put("BIRTH", "1989-12-12");
        info.put("SEX", "0");
        info.put("EMAIL", "test@email.com");
        info.put("CLASS", "0");
        info.put("PHONE", "13382823224");
        content.put("INFO", info);
        ob.put("DATA", content);
        byte[] send = sendMessage.sendMessage(protocolData, ob);
        byte[] receive = new byte[1024 * 8];
        packetSend = new DatagramPacket(send, send.length, address, port);
        packetReceive = new DatagramPacket(receive, receive.length);
        socket.send(packetSend);
        int seq = SysInfo.getSeq();
        SysInfo.setSeq(++seq);
        socket.receive(packetReceive);
        int ack = SysInfo.getAck();
        SysInfo.setAck(++ack);
        rShow = new ReceiveShow(receive);
        Thread thread = new Thread(rShow);
        thread.start();
        Thread.sleep(1000);
        String data = rShow.getDATA();
        String data2 = JsonAnalysis.getValue(data,"DATA");
        String ret = JsonAnalysis.getValue(data2,"RET");
        if(ret.equals("0")){
            System.out.println("用户注册成功-->");
            String sid = JsonAnalysis.getValue(data2,"INFO");
            SysInfo.setSid(Integer.parseInt(sid));
        }else if(ret.equals("1")){
            System.out.println("账户已经存在-->");
            String sid = JsonAnalysis.getValue(data2,"INFO");
            SysInfo.setSid(Integer.parseInt(sid));
        }else{
            System.out.println("账户注册出错-->");
        }
    }

    public void deviceReg() throws Exception{
        SysInfo.setSort((byte)2);
        System.out.println("设备注册--->");
        protocolData.setOpt((byte) 8);
        protocolData.setCmd((byte) 2);
        protocolData.setType((byte) 1);
        JSONObject ob = new JSONObject();
        JSONObject info = new JSONObject();
        JSONObject content = new JSONObject();
        content.put("SNUM", "88888888");
        content.put("SVER", "FFFFFF");
        content.put("USER", SysInfo.getUsername());
        content.put("PASSWORD", SysInfo.getPassworld());
        info.put("SEQ", "MVBCQ-B3VPW-CT369-VM9TB-XXXXX");
        info.put("BARCODE", "DFGV-5544");
        info.put("LONGI", "106.2597345643");
        info.put("LATI", "35.9157456756");
        info.put("MAX", "10");
        info.put("CLASS", "0");
        info.put("PHONE", "13382823344");
        content.put("INFO", info);
        ob.put("DATA", content);
        byte[] send = sendMessage.sendMessage(protocolData, ob);
        byte[] receive = new byte[1024 * 8];
        packetSend = new DatagramPacket(send, send.length, address, port);
        packetReceive = new DatagramPacket(receive, receive.length);
        socket.send(packetSend);
        int seq = SysInfo.getSeq();
        SysInfo.setSeq(++seq);
        socket.receive(packetReceive);
        int ack = SysInfo.getAck();
        SysInfo.setAck(++ack);
        rShow = new ReceiveShow(receive);
//        Thread thread = new Thread(rShow);
//        thread.start();
        rShow.run();
        String data = rShow.getDATA();
        String data2 = JsonAnalysis.getValue(data,"DATA");
        String ret = JsonAnalysis.getValue(data2,"RET");
        if(ret.equals("0")){
            System.out.println("用户注册成功-->");
            String sid = JsonAnalysis.getValue(data2,"INFO");
            SysInfo.setSid(Integer.parseInt(sid));
        }else if(ret.equals("1")){
            System.out.println("账户已经存在-->");
            String sid = JsonAnalysis.getValue(data2,"INFO");
            SysInfo.setSid(Integer.parseInt(sid));
        }else{
            System.out.println("账户注册出错-->");
        }

    }
}

