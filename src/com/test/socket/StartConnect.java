package com.test.socket;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;

import com.test.protocol.ProtocolLogin;
import com.test.workbean.SysInfo;


public class StartConnect {

	public static void main(String[] args) throws IOException {
		int port=5678;
		InetAddress address = InetAddress.getByName("localhost");
		DatagramSocket socket =new DatagramSocket();
		ProtocolLogin login = new ProtocolLogin(socket, address, port);
		SysInfo.setSid(4);
		SysInfo.setSort((byte)2);
		try {
			login.login();
			login.keepAlive();
//			login.logout();
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			socket.close();
		}
	}
	
}
