package com.xmpp.client;

import java.util.Locale;

public class ClientA {
	public static void main(String a[]) throws Exception {
		Locale.setDefault(Locale.CHINA);
		UserChatBase client = new UserChatBase(
				new String[]{"tangtang", "tangtang"});
		
		client.connectAndLogin();
		System.out.println("building connection between tangtang as sender and pacoson as receiver.");
		// create the chat with specified user.(startup a thread)
		client.createChat("pacoson");
	}
}