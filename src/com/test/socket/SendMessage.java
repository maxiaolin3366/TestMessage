package com.test.socket;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SendMessage {

	public SendMessage() {
		// TODO Auto-generated constructor stub
	}
	public byte[] sendMessage() {
		byte[] send = null;
		try {
			byte [] znaf = "ZNAF".getBytes("utf-8");
			ByteArrayOutputStream ips  = new ByteArrayOutputStream();
			DataOutputStream dis = new DataOutputStream(ips);
			dis.write(znaf);
			byte cmd = 2;
			byte type = 1;
			byte opt = 8;
			byte sort = 0;
			int sid = 1;
			int seq = 12;
			int ack = 0;
			dis.writeByte(cmd);//CMD
			dis.writeByte(type);//TYPE
			dis.writeByte(opt);//OPT
			dis.writeByte(sort);//SORT 0是APP 2是前端设备
			dis.writeInt(sid);//SID
			dis.writeInt(seq);//SEQ
			dis.writeInt(ack);//ACK
			JSONObject ob = new JSONObject();
			JSONObject Data = new JSONObject();
			JSONArray infos = new JSONArray();
			JSONObject info = new JSONObject();
			JSONObject content = new JSONObject();
			content.put("SNUM", "88888888");
			content.put("SVER", "FFFFFF");
			content.put("USER", "13382824334");
			content.put("PASSWORD", "mima123");
			
			info.put("LIMIT", "1");
			info.put("NAME", "用户1");
			info.put("BIRTH", "1989-12-12");
			info.put("SEX", "0");
			info.put("EMAIL", "test@email.com");
			info.put("CLASS", "0");
			info.put("PHONE", "13382824334");
			content.put("INFO", info);
//			Data.put("NUM", 1);
//			Data.put("INFO", infos);
			ob.put("DATA", content);			
			String json = ob.toString();
			dis.write(json.getBytes("utf-8"));	
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
