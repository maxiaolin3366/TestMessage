package com.test.protocol;

import com.test.workbean.SysInfo;
import net.sf.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public  class ProtocolBase implements Runnable{
	byte[] send = null;
	public byte[] sendMessage(ProtocolData protocolData, JSONObject obj) {
		// TODO Auto-generated method stub
		try {
			if(obj!=null){
				System.out.println("要发送的json-->"+obj.toString());
			}
			byte[] znaf = "ZNAF".getBytes("utf-8");
			ByteArrayOutputStream ips = new ByteArrayOutputStream();
			DataOutputStream dis = new DataOutputStream(ips);
			dis.write(znaf);
			System.out.print(new String(znaf));
			dis.writeByte(protocolData.getCmd());//CMD
			System.out.print(" cmd "+protocolData.getCmd());
			dis.writeByte(protocolData.getType());//TYPE
			System.out.print(" type "+protocolData.getType());
			dis.writeByte(protocolData.getOpt());//OPT
			System.out.print(" opt "+protocolData.getOpt());
			dis.writeByte(SysInfo.getSort());//SORT 0是APP 2是前端设备
			System.out.print(" sort "+SysInfo.getSort());
			dis.writeInt(SysInfo.getSid());//SID
			System.out.print(" sid "+SysInfo.getSid());
			dis.writeInt(SysInfo.getSeq());//SEQ
			System.out.print(" seq "+SysInfo.getSeq());
			dis.writeInt(SysInfo.getAck());//ACK
			System.out.println(" ack "+SysInfo.getAck());
			if(obj!=null){
				String json = obj.toString();
				dis.write(json.getBytes("utf-8"));
			}
			dis.writeBytes("\n");

			send = ips.toByteArray();

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return send;
	}

	@Override
	public void run() {

	}
}
