package com.test.socket;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

import com.test.protocol.ProtocolAccount;
import com.test.protocol.ProtocolLogin;
import com.test.protocol.ProtocolMessage;
import com.test.workbean.SysInfo;
import com.test.workbean.Timer;


public class StartConnect {



	public static void main(String[] args) throws IOException {
		int port=9999;
		InetAddress address = InetAddress.getByName("localhost");
		DatagramSocket socket =new DatagramSocket();
		ProtocolLogin login = new ProtocolLogin(socket, address, port);
		ProtocolAccount account = new ProtocolAccount(socket, address, port);
		ProtocolMessage message = new ProtocolMessage(socket, address, port);
//		SysInfo.setSid(4);
		SysInfo.setSort((byte)0);
		System.out.println("请输入您需要注册的用户账户（8-20个字符构成字符串）：");
		Scanner sc = new Scanner(System.in);
		String user = sc.nextLine();
		SysInfo.setUsername(user);
		System.out.println("请输入您需要注册的用户密码：");
		Scanner sc2 = new Scanner(System.in);
		String pass= sc2.nextLine();
		SysInfo.setPassworld(pass);
		try {
			account.userReg();// 用户注册
//			account.deviceReg();
			login.login();
//			message.sendMessageOfChat();
			message.sendMessageOfNotice();
//			message.sendMessageOfAlarm();
//			if(SysInfo.getLink()!=0){
//			Timer timer = new Timer(port, address, socket);
//			Thread thread = new Thread(timer);
//			thread.start();
//			login.logout();
//			}
		}catch (Exception e){
			e.printStackTrace();
		}finally {
		}
	}
	
}
