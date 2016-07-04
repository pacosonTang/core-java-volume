package com.xmpp.client;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.SimpleFormatter;

import javafx.scene.input.DataFormat;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatManagerListener;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.delay.packet.DelayInformation;

import com.xmpp.client.MessageHandler.Sender;

// 客户端基础类
public class ClientBase {
	private XMPPTCPConnectionConfiguration conf;
	private AbstractXMPPConnection connection;
	private ChatManager chatManager;
	private Chat chat;

	/**
	 * @param args refers to an array with ordered values as follows: user, password, host, port.
	 */
	public ClientBase(String... args) {
		String username = args[0];
		String password = args[1];
		String host = args[2];
		String port = args[3];

		conf = XMPPTCPConnectionConfiguration.builder().setUsernameAndPassword(username, password).setServiceName(host)
				.setHost(host).setPort(Integer.valueOf(port))
				.setSecurityMode(SecurityMode.disabled) // (attention of this line about SSL authentication.)
				.build();
		connection = new XMPPTCPConnection(conf);
		chatManager = ChatManager.getInstanceFor(connection);
	}
	
	/**
	 * connect to and login in openfire server.
	 */
	public void connectAndLogin() {
		System.out.println("executing connectAndLogin method.");
		try {
			connection.connect();
			System.out.println("successfully connection.");
			connection.login(); // client logins into openfire server.
			System.out.println("successfully login.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * create chat instance using ChatManager.(startup a thread)
	 */
	public Chat createChat(String toUser) {
		System.out.println("executing createChat method.");
		// create Chat chat instance with other clients.
		chat = chatManager.createChat(toUser, new ChatMessageListener() {
			@Override
			public void processMessage(Chat chat, Message message) {
				System.out.println("receiving " + message);
			}
		});
		
		// create the chat with specified user.(startup a thread)
//		Chat chat = client.createChat("pacoson@csdn.pacosonswjtu.com");
		MessageHandler handler = new MessageHandler(chat); 
		MessageHandler.Sender sender = handler.new Sender(); // creating inner class.
		new Thread(sender).start();
		return chat;
	}
	
	/**
	 * add ChatManagerListener to ChatManager.
	 * @param listener added into ChatManager instance.
	 */
	public void addChatListener(ChatManagerListener listener) {
		System.out.println("executing addChatListener method.");
		chatManager.addChatListener(listener);
	}
	
	/**
	 * get chat timestamp, also time recoded when the msg starts to send.
	 * @param msg 
	 * @return timestamp.
	 */
	public String getChatTimestamp(Message msg) {
		ExtensionElement delay = DelayInformation.from(msg);
		
		if(delay == null) {
			return null;
		}
		Date date = ((DelayInformation) delay).getStamp();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
		return format.format(date);
	}

	public XMPPTCPConnectionConfiguration getConf() {
		return conf;
	}

	public AbstractXMPPConnection getConnection() {
		return connection;
	}

	public ChatManager getChatManager() {
		return chatManager;
	}

	public Chat getChat() {
		return chat;
	}
}
