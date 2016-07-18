package com.xmpp.client;

import java.util.Locale;

public class ClientB {
	public static void main(String a[]) throws Exception {
		Locale.setDefault(Locale.CHINA);
		UserChatBase client = new UserChatBase(
				new String[]{"pacoson", "pacoson"});
		
		client.connectAndLogin();
		System.out.println("building connection between pacoson as sender and tangtang as receiver.");
		// create the chat with specified user.(startup a thread)
		client.createChat("tangtang");
	}
}