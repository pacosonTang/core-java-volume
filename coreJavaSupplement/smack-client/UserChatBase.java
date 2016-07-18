package com.xmpp.client;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Message.Body;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.delay.packet.DelayInformation;

// this class encapsulates some info for
// connection, login, creating chat.
public class UserChatBase {
	private XMPPTCPConnectionConfiguration conf;
	private AbstractXMPPConnection connection;
	private ChatManager chatManager;
	private Chat chat;
	
	/**
	 * @param args refers to an array with ordered values as follows: user, password, host, port.
	 */
	public UserChatBase(String... args) {
		String username = args[0];
		String password = args[1];

		conf = XMPPTCPConnectionConfiguration.builder().setUsernameAndPassword(username, password)
				.setServiceName(MyConstants.HOST)
				.setHost(MyConstants.HOST)
				.setPort(Integer.valueOf(MyConstants.PORT))
				.setSecurityMode(SecurityMode.disabled) // (attention of this line about SSL authentication.)
				.build();
		connection = new XMPPTCPConnection(conf);
		chatManager = ChatManager.getInstanceFor(connection);
		// differentiation ChatManagerListener from ChatMessageListener
		chatManager.addChatListener(new MyChatListener(this));
	}
	
	/**
	 * connect to and login in openfire server.
	 * @throws XMPPException 
	 * @throws IOException 
	 * @throws SmackException 
	 */
	public void connectAndLogin() throws SmackException, IOException, XMPPException {
		System.out.println("executing connectAndLogin method.");
		System.out.println("connection = " + connection);
		connection.connect();
		System.out.println("successfully connection.");
		connection.login(); // client logins into openfire server.
		System.out.println("successfully login.");
	}
	
	/**
	 * disconnect to and logout from openfire server.
	 * @throws XMPPException 
	 * @throws IOException 
	 * @throws SmackException 
	 */
	public void disconnect() {
		System.out.println("executing disconnect method.");
		try {
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * create chat instance using ChatManager.
	 */
	public Chat createChat(String toUser) {		
		toUser += "@" + MyConstants.HOST;
		chat = chatManager.createChat(toUser);		
		
		// create the chat with specified user.(startup a thread)
		MessageHandler handler = new MessageHandler(chat); 
		MessageHandler.Sender sender = handler.new Sender(); // creating inner class.
		new Thread(sender).start();
		// creating thread over.
		
		return chat;
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
