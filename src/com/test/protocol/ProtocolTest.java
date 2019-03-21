package com.test.protocol;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.test.workbean.SysInfo;
import net.sf.json.JSONObject;



public class ProtocolTest {

	public byte[] sendMessage(ProtocolData protocolData,JSONObject obj) {
		// TODO Auto-generated method stub
		byte[] send = null;
		try {
			byte [] znaf = "ZNAF".getBytes("utf-8");
			ByteArrayOutputStream ips  = new ByteArrayOutputStream();
			DataOutputStream dis = new DataOutputStream(ips);
			dis.write(znaf);
			byte cmd = 0;
			byte type = 1;
			byte opt = 8;
			byte sort = SysInfo.getSort();
			int sid = SysInfo.getSid();
			int seq = SysInfo.getSeq();
			int ack = SysInfo.getAck();
			dis.writeByte(cmd);//CMD
			dis.writeByte(type);//TYPE
			dis.writeByte(opt);//OPT
			dis.writeByte(sort);//SORT 0是APP 2是前端设备
			dis.writeInt(sid);//SID
			dis.writeInt(seq);//SEQ
			dis.writeInt(ack);//ACK
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
