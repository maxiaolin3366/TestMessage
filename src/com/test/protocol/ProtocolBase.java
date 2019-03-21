package com.test.protocol;

import com.test.workbean.SysInfo;
import net.sf.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public  class ProtocolBase {
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
			dis.writeByte(protocolData.getCmd());//CMD
			dis.writeByte(protocolData.getType());//TYPE
			dis.writeByte(protocolData.getOpt());//OPT
			dis.writeByte(SysInfo.getSort());//SORT 0是APP 2是前端设备
			dis.writeInt(SysInfo.getSid());//SID
			dis.writeInt(SysInfo.getSeq());//SEQ
			dis.writeInt(SysInfo.getAck());//ACK
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
}
