package com.corejava.chapter9.cryption;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class RSATest
{
	private static final int KEYSIZE = 512;
	public static void main(String[] args) throws GeneralSecurityException, IOException, ClassNotFoundException 
	{
		if(args[0].equals("-genkey")) // 生成密钥对（公钥+私钥）
		{
			// 密钥对生成器
			KeyPairGenerator pairgen = KeyPairGenerator.getInstance("RSA");
			SecureRandom sr = new SecureRandom();
			pairgen.initialize(KEYSIZE, sr); // 密钥对生成器初始化
			KeyPair pair = pairgen.generateKeyPair(); // 生成密钥对（公钥+私钥）
			
			try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(args[1])))
			{
				out.writeObject(pair.getPublic()); // 写入公钥到文件
			}
			try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(args[2])))
			{
				out.writeObject(pair.getPrivate()); // 写入私钥到文件
			}
		}
		else if(args[0].equals("-encrypt")) // 加密模块
		{
			// 生成密钥
			KeyGenerator keygen = KeyGenerator.getInstance("AES");
			SecureRandom sr = new SecureRandom();
			keygen.init(sr);
			SecretKey key = keygen.generateKey();
			
			// wrap with RSA public key
			// args[3]==public.key,args[2]==encryptedFile,args[1]=inputFile
			try(ObjectInputStream keyIn = new ObjectInputStream(new FileInputStream(args[3]));
				DataOutputStream dataOut = new DataOutputStream(new FileOutputStream(args[2]));
					InputStream in = new FileInputStream(args[1]))
			{
				Key publicKey = (Key)keyIn.readObject();// 读入公钥
				Cipher cipher = Cipher.getInstance("RSA");// RSA密码对象
				cipher.init(Cipher.WRAP_MODE, publicKey); // 通过设置打包模式和公钥 来对RSA密码对象进行初始化
				
				byte[] wrappedKey = cipher.wrap(key);// 通过带有公钥的RSA算法对象给密钥加密
				dataOut.writeInt(wrappedKey.length); // 将加密后的密钥写入到输出流  dataOut
				dataOut.write(wrappedKey);
				
				cipher = Cipher.getInstance("AES"); // AES 密码对象
				cipher.init(Cipher.ENCRYPT_MODE, key); // 通过设置加密模式和密钥 来对 AES 密码对象进行初始化
				Util.crypt(in, dataOut, cipher); // 利用AES密码对象对inFile 进行加密并写入到输出流 dataOut
			} 
		}
		else // 解密模块
		{
			//args[1]==encryptedFile,args[3]=private.key,args[2]=decryptedFile;
			try(DataInputStream dataIn = new DataInputStream(new FileInputStream(args[1]));
					ObjectInputStream keyIn = new ObjectInputStream(new FileInputStream(args[3]));
						OutputStream out = new FileOutputStream(args[2]))
			{ 
				int length = dataIn.readInt();
				byte[] wrappedKey = new byte[length];
				dataIn.read(wrappedKey, 0, length); // 读入加密后的文件（经过公钥加密后的密钥 和 经过密钥加密后的文件内容）
				
				// unwrap with RSA private key
				Key privateKey = (Key)keyIn.readObject(); // 读入private.key 到 wrappedKey
				
				Cipher cipher = Cipher.getInstance("RSA");
				cipher.init(Cipher.UNWRAP_MODE, privateKey); // 通过设置解包模式和私钥 来对RSA密码对象进行初始化
				// 通过带有私钥的RSA算法对象给密钥解密
				Key key = cipher.unwrap(wrappedKey, "AES", Cipher.SECRET_KEY);
				
				cipher = Cipher.getInstance("AES"); // AES 密码对象
				cipher.init(Cipher.DECRYPT_MODE, key);	 // 通过设置解密模式和密钥 来对 AES 密码对象进行初始化				
				Util.crypt(dataIn, out, cipher); // 通过使用解密后的密钥 对 加密后的文件内容 进行解密并写入到输出流 out
			} 
		}
	}
}
