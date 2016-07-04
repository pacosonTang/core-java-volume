package com.xmpp.client;

import java.util.Set;

import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManagerListener;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Message.Body;

// 监听器
public class MyChatListener implements ChatManagerListener{
	private ClientBase client;
	
	public MyChatListener(ClientBase client) {
		this.client = client;
	}
	
	@Override
	public void chatCreated(Chat chat, boolean createdLocally) {
		if (!createdLocally) {
			chat.addMessageListener(new ChatMessageListener() {
				@Override
				public void processMessage(Chat chat, Message message) {
					String from = message.getFrom();
					Set<Body> bodies = message.getBodies();
					String timestamp = client.getChatTimestamp(message);
					
					if(timestamp != null) {
						System.out.println(timestamp);
					}
					for(Body b : bodies) {
						System.out.println(from + ":" + b.getMessage());
					}
				}
			});
		}
	}
}
