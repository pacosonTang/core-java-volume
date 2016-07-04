package com.xmpp.client;

import java.util.Locale;

public class ClientA {
	public static void main(String a[]) throws Exception {
		Locale.setDefault(Locale.CHINA);
		ClientBase client = new ClientBase(
				new String[]{"tangtang", "tangtang", "csdn.pacosonswjtu.com", "5222"});
		
		client.connectAndLogin();
		client.addChatListener(new MyChatListener(client));
		
		// create the chat with specified user.(startup a thread)
		client.createChat("pacoson@csdn.pacosonswjtu.com");
	}
}