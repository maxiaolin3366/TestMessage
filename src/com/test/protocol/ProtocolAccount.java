package com.test.protocol;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.test.workbean.SysInfo;
import net.sf.json.JSONObject;

public class ProtocolAccount{

	public byte[] sendMessage(ProtocolData protocolData,JSONObject obj) {
		byte[] send = null;
		if(protocolData.getCmd()==2&&protocolData.getType()==1&&SysInfo.getSort()==2) {  //注册协议
		try {
			byte [] znaf = "ZNAF".getBytes("utf-8");
			ByteArrayOutputStream ips  = new ByteArrayOutputStream();
			DataOutputStream dis = new DataOutputStream(ips);
			dis.write(znaf);			
			byte opt = 8;
			int sid = 1;
			int seq = 12;
			int ack = 0;
			dis.writeByte(protocolData.getCmd());//CMD
			dis.writeByte(protocolData.getType());//TYPE
			dis.writeByte(opt);//OPT
			dis.writeByte(SysInfo.getSort());//SORT 0是APP 2是前端设备
			dis.writeInt(sid);//SID
			dis.writeInt(seq);//SEQ
			dis.writeInt(ack);//ACK
			JSONObject ob = new JSONObject();			
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
			ob.put("DATA", content);			
			String json = ob.toString();
			dis.write(json.getBytes("utf-8"));	
			dis.writeBytes("\n");
			send = ips.toByteArray();
			for(int i=0;i<send.length;i++) {
				System.out.print(Integer.toHexString(send[i] & 0xFF)+" ");
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		if(protocolData.getCmd()==2&&protocolData.getType()==1&& SysInfo.getSort()==2) {
			try {
				byte [] znaf = "ZNAF".getBytes("utf-8");
				ByteArrayOutputStream ips  = new ByteArrayOutputStream();
				DataOutputStream dis = new DataOutputStream(ips);
				dis.write(znaf);			
				byte opt = 8;
				int sid = 1;
				int seq = 12;
				int ack = 0;
				dis.writeByte(protocolData.getCmd());//CMD
				dis.writeByte(protocolData.getType());//TYPE
				dis.writeByte(opt);//OPT
				dis.writeByte(SysInfo.getSort());//SORT 0是APP 2是前端设备
				dis.writeInt(sid);//SID
				dis.writeInt(seq);//SEQ
				dis.writeInt(ack);//ACK
				JSONObject ob = new JSONObject();			
				JSONObject info = new JSONObject();
				JSONObject content = new JSONObject();
				content.put("SNUM", "88888888");
				content.put("SVER", "FFFFFF");
				content.put("USER", "13382824334");
				content.put("PASSWORD", "mima123");			
				info.put("SEQ", "MVBCQ-B3VPW-CT369-VM9TB-YFGBP");
				info.put("BARCODE", "4455-5544");
				info.put("LONGI", "106.2597479643");
				info.put("LATI", "35.9157381056");
				info.put("MAX", "10");
				info.put("CLASS", "0");
				info.put("PHONE", "13382824334");
				content.put("INFO", info);
				ob.put("DATA", content);			
				String json = ob.toString();
				dis.write(json.getBytes("utf-8"));	
				dis.writeBytes("\n");
				send = ips.toByteArray();
				for(int i=0;i<send.length;i++) {
					System.out.print(Integer.toHexString(send[i] & 0xFF)+" ");
				}
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return send;
	}
}
