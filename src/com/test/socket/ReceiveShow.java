package com.test.socket;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.test.protocol.ProtocolData;

public class ReceiveShow {
	
	byte[] receive;
	private String ZNAF;
	private byte cmd;
	private byte type;
	private byte opt;
	private byte sort;
	private int sid;
	private int seq;
	private int ack;
	private String DATA;
	public ReceiveShow(byte[] receive) {
		// TODO Auto-generated constructor stub
		this.receive = receive;
	}
	
	public byte[] getReceive() {
		return receive;
	}

	public String getZNAF() {
		return ZNAF;
	}

	public byte getCmd() {
		return cmd;
	}

	public byte getType() {
		return type;
	}

	public byte getOpt() {
		return opt;
	}

	public byte getSort() {
		return sort;
	}

	public int getSid() {
		return sid;
	}

	public int getSeq() {
		return seq;
	}

	public int getAck() {
		return ack;
	}

	public String getDATA() {
		return DATA;
	}

	public void show() {
		
		try {
			ByteArrayInputStream ips  = new ByteArrayInputStream(receive);
			DataInputStream dis = new DataInputStream(ips);
			byte[] znaf = new byte[4];
			for(int i=0;i<4;i++){
				znaf[i] = dis.readByte();
			}
			ZNAF = new String(znaf,"UTF-8");
			System.out.println("收到如下报文：");
			System.out.print(new String(znaf,"UTF-8"));
			cmd = dis.readByte();
			System.out.print(" cmd:"+cmd);
			type = dis.readByte();
			System.out.print(" type:"+type);
			opt = dis.readByte();
			System.out.print(" opt:"+opt);
			sort = dis.readByte();
			System.out.print(" sort:"+sort);
			sid = dis.readInt();
			System.out.print(" sid:"+sid);
			seq = dis.readInt();
			System.out.print(" seq:"+seq);
			ack = dis.readInt();
			System.out.print(" ack:"+ack);
			byte[] data = new byte[receive.length-20]; 
			for(int i=0;i<data.length;i++){
				data[i] = dis.readByte();
			}
			DATA = new String(data,"UTF-8");
			System.out.println(" DATA:"+DATA);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
