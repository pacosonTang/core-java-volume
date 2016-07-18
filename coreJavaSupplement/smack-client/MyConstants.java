package com.xmpp.client;

public class MyConstants {
	public static final int PORT = 5222;
	public static final String HOST = "lenovo-pc";
	public static final String PLUGIN_PRESENT_URL
		= "http://lenovo-pc:9090/plugins/presence/status?";
		//= "http://lenovo-pc:9090/plugins/presence/status?jid=pacoson@lenovo-pc&type=text&req_jid=tangtang@lenovo-pc";
	
	public static final String buildPresenceURL(String from, String to, String type) {
		return PLUGIN_PRESENT_URL 
				+ "jid=" + to + "@" + HOST + "&"
				+ "req_jid=" + from + "@" + HOST + "&"
				+ "type=" + type;
	}
}
