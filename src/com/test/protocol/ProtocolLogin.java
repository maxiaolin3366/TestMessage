package com.test.protocol;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import com.test.socket.ReceiveShow;
import com.test.tools.JsonAnalysis;

import com.test.workbean.SysInfo;
import net.sf.json.JSONObject;
import sun.misc.BASE64Encoder;

public class ProtocolLogin {
    ProtocolData protocolData = new ProtocolData();
    int ack;
    int seq;
    ProtocolBase sendMessage = new ProtocolBase();
    DatagramPacket packetSend;
    DatagramPacket packetReceive;
    DatagramSocket socket;
    InetAddress address;
    ReceiveShow rShow;
    int port;

    public ProtocolLogin(DatagramSocket socket, InetAddress address, int port) {
        this.socket = socket;
        this.address = address;
        this.port = port;
    }

    public void login() throws Exception{
        System.out.println("用户登陆");
        protocolData.setCmd((byte) 1);
        protocolData.setType((byte) 1);
        protocolData.setOpt((byte) 8);
        JSONObject ob = new JSONObject();
        JSONObject content = new JSONObject();
        content.put("SNUM", "88888888");
        content.put("SVER", "FFFFFF");
        content.put("USER", SysInfo.getUsername());
        content.put("LINK", SysInfo.getLink());
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
            rShow.show();
            String data = rShow.getDATA();
            data = JsonAnalysis.getValue(data, "DATA");
            String ret = JsonAnalysis.getValue(data, "RET");
            int rets = Integer.parseInt(ret);
            String info = null;
            if (rets == 1) {
                System.out.println("是登陆验证-->");
                info = JsonAnalysis.getValue(data, "INFO");

                byte[] passs = info.getBytes("utf-8");
                byte[] rondByte;
                String passwords = SysInfo.getPassworld();
                byte[] password = passwords.getBytes("utf-8");
                if (password.length <= passs.length) {
                    rondByte = passs;
                    int j = 0;
                    for (int i = 0; i < passs.length; i++) {
                        rondByte[i] = (byte) (passs[i] ^ password[j++]);
                        if (j == password.length) {
                            j = 0;
                        }
                    }
                } else {
                    rondByte = password;
                    int j = 0;
                    for (int i = 0; i < password.length; i++) {
                        rondByte[i] = (byte) (passs[j++] ^ password[i]);
                        if (j == passs.length) {
                            j = 0;
                        }
                    }
                }

                BASE64Encoder encoder = new BASE64Encoder();
                String ron = encoder.encode(rondByte);
                protocolData.setType((byte) 3);
                JSONObject obj = new JSONObject();
                JSONObject INFO = new JSONObject();
                INFO.put("CODE", ron);
                INFO.put("USER", SysInfo.getUsername());
                obj.put("DATA", INFO);

                send = sendMessage.sendMessage(protocolData, obj);
                packetSend = new DatagramPacket(send, send.length, address, port);
                receive = new byte[1024 * 8];

                packetReceive = new DatagramPacket(receive, receive.length);
                socket.send(packetSend);
                seq = SysInfo.getSeq();
                SysInfo.setSeq(++seq);
                socket.receive(packetReceive);
                ack = SysInfo.getAck();
                SysInfo.setAck(++ack);
                rShow = new ReceiveShow(receive);
                rShow.show();
                data = rShow.getDATA();
                data = JsonAnalysis.getValue(data, "DATA");
                ret = JsonAnalysis.getValue(data, "RET");
                rets = Integer.parseInt(ret);
                System.out.println("消息返回正确-->");
                if (JsonAnalysis.getValue(data, "LINK") != null) {
                    System.out.println("用户登陆成功-->");
                    String link = JsonAnalysis.getValue(data, "LINK");
                    int links = Integer.parseInt(link);
                    SysInfo.setLink(links);
                }
            }
    }

    public void logout() throws Exception {
        protocolData.setCmd((byte) 1);
        protocolData.setType((byte) 5);
        protocolData.setOpt((byte) 8);
        JSONObject object = new JSONObject();
        JSONObject json = new JSONObject();
        json.put("USER", SysInfo.getUsername());
        json.put("LINK", SysInfo.getLink());//
        object.put("DATA", json);
        byte[] send = sendMessage.sendMessage(protocolData, object);
        byte[] receive = new byte[1024 * 8];
        packetSend = new DatagramPacket(send, send.length, address, port);
        packetReceive = new DatagramPacket(receive, receive.length);
        socket.send(packetSend);
        seq = SysInfo.getSeq();
        SysInfo.setSeq(++seq);
        socket.receive(packetReceive);
        ack = SysInfo.getAck();
        SysInfo.setAck(++ack);
    }

    public void keepAlive() throws Exception{
        System.out.println("保活协议发送成功-->");
        protocolData.setCmd((byte) 1);
        protocolData.setType((byte) 6);
        protocolData.setOpt((byte) 8);
        JSONObject object = new JSONObject();
        JSONObject json = new JSONObject();
        json.put("USER", SysInfo.getUsername());
        json.put("LINK", SysInfo.getLink());
        object.put("DATA", json);
        byte[] send = sendMessage.sendMessage(protocolData, null);
        byte[] receive = new byte[1024 * 8];
        packetSend = new DatagramPacket(send, send.length, address, port);
        packetReceive = new DatagramPacket(receive, receive.length);
            socket.send(packetSend);
            seq = SysInfo.getSeq();
            SysInfo.setSeq(++seq);
            socket.receive(packetReceive);
            ack = SysInfo.getAck();
            SysInfo.setAck(++ack);
        rShow = new ReceiveShow(receive);
        rShow.show();


    }
}
