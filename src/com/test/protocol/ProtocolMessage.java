package com.test.protocol;

import com.test.socket.ReceiveShow;
import com.test.tools.JsonAnalysis;
import com.test.workbean.SysInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ProtocolMessage {
    ProtocolData protocolData = new ProtocolData();
    ProtocolBase sendMessage = new ProtocolBase();
    DatagramPacket packetSend;
    DatagramPacket packetReceive;
    DatagramSocket socket;
    InetAddress address;
    ReceiveShow rShow;
    int port;

    public ProtocolMessage(DatagramSocket socket, InetAddress address, int port) {
        this.socket = socket;
        this.address = address;
        this.port = port;
    }

    public void sendMessageOfAlarm() throws Exception { //发送消息
        protocolData.setCmd((byte) 5);
        protocolData.setType((byte) 1);
        protocolData.setOpt((byte) 8);
        JSONObject ob = new JSONObject();
        JSONObject info = new JSONObject();
        JSONArray infos = new JSONArray();
        JSONObject data = new JSONObject();
        JSONObject content = new JSONObject();
        data.put("NUM", "1");
        data.put("LINK", SysInfo.getLink());
        info.put("DEST", 0);
        info.put("CLASS", 1);
        info.put("USER",SysInfo.getUsername());
        info.put("DATE", System.currentTimeMillis());
        content.put("SORT",1);
        content.put("LEVEL",1);
        content.put("ATTACH","");
        info.put("CONTENT", content);
        infos.add(info);
        data.put("INFO", infos);
        ob.put("DATA", data);
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
        String datas = rShow.getDATA();
        String data2 = JsonAnalysis.getValue(datas, "DATA");
        String ret = JsonAnalysis.getValue(data2, "RET");
        if (ret.equals("0")) {
            System.out.println("消息请求发送成功-->");
            String sid = JsonAnalysis.getValue(data2, "INFO");
            System.out.println(sid);
        } else if (ret.equals("-1")) {
            System.out.println("消息请求失败");
            String sid = JsonAnalysis.getValue(data2, "INFO");
            System.out.println(sid);
        } else {
            System.out.println("消息发送请求出错-->");
        }
    }

    public void sendMessageOfNotice() throws Exception { //发送消息
        protocolData.setCmd((byte) 5);
        protocolData.setType((byte) 1);
        protocolData.setOpt((byte) 8);
        JSONObject ob = new JSONObject();
        JSONObject info = new JSONObject();
        JSONArray infos = new JSONArray();
        JSONObject data = new JSONObject();
        JSONObject content = new JSONObject();
        data.put("NUM", "1");
        data.put("LINK", SysInfo.getLink());
        info.put("DEST", 0);
        info.put("CLASS", 4);
        info.put("USER",SysInfo.getUsername());
        info.put("DATE", System.currentTimeMillis());
        content.put("TITLE","hello");
        content.put("MSG","这是一条测试通知消息");
        content.put("SET","utf8");
        info.put("CONTENT", content);
        infos.add(info);
        data.put("INFO", infos);
        ob.put("DATA", data);
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
        String datas = rShow.getDATA();
        String data2 = JsonAnalysis.getValue(datas, "DATA");
        String ret = JsonAnalysis.getValue(data2, "RET");
        if (ret.equals("0")) {
            System.out.println("消息请求发送成功-->");
            String sid = JsonAnalysis.getValue(data2, "INFO");
            System.out.println(sid);
        } else if (ret.equals("-1")) {
            System.out.println("消息请求失败");
            String sid = JsonAnalysis.getValue(data2, "INFO");
            System.out.println(sid);
        } else {
            System.out.println("消息发送出错-->");
        }
    }
    public void sendMessageOfChat() throws Exception { //发送消息
        protocolData.setCmd((byte) 5);
        protocolData.setType((byte) 1);
        protocolData.setOpt((byte) 8);
        JSONObject ob = new JSONObject();
        JSONObject info = new JSONObject();
        JSONArray infos = new JSONArray();
        JSONObject data = new JSONObject();
        JSONObject content = new JSONObject();
        data.put("NUM", "1");
        data.put("LINK", SysInfo.getLink());
        info.put("DEST", 10001);
        info.put("CLASS", 2);
        info.put("USER",SysInfo.getUsername());
        info.put("DATE", System.currentTimeMillis());
        content.put("MSG","hello");
        content.put("TYPE",1);
        content.put("COLOR","red");
        info.put("CONTENT", content);
        infos.add(info);
        data.put("INFO", infos);
        ob.put("DATA", data);
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
        String datas = rShow.getDATA();
        String data2 = JsonAnalysis.getValue(datas, "DATA");
        String ret = JsonAnalysis.getValue(data2, "RET");
        if (ret.equals("0")) {
            System.out.println("消息请求发送成功-->");
            String sid = JsonAnalysis.getValue(data2, "INFO");
            System.out.println(sid);
        } else if (ret.equals("-1")) {
            System.out.println("消息请求失败");
            String sid = JsonAnalysis.getValue(data2, "INFO");
            System.out.println(sid);
        } else {
            System.out.println("账户注册出错-->");
        }
    }
}
