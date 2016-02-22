package com.corejava.chapter9.cryption;

import java.io.*;
import java.security.*;
import javax.crypto.*;

public class AESTest
{
   public static void main(String[] args) 
      throws IOException, GeneralSecurityException, ClassNotFoundException
   {
      if (args[0].equals("-genkey")) // 产生密钥
      {
    	  // 获取密钥生成器
         KeyGenerator keygen = KeyGenerator.getInstance("AES");
         // 创建随机源
         SecureRandom random = new SecureRandom();
         // 用随机源来初始化密钥发生器
         keygen.init(random);
         // 生成密钥
         SecretKey key = keygen.generateKey();
         try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(args[1])))
         {
            out.writeObject(key); // 写出密钥到文件
         }
      }
      else // 加密或者解密
      {
         int mode;
         // 加密（解密）模式
         if (args[0].equals("-encrypt")) mode = Cipher.ENCRYPT_MODE;
         else mode = Cipher.DECRYPT_MODE;

         // 带资源的try 语句, args[3]==secret.key
         try (ObjectInputStream keyIn = new ObjectInputStream(new FileInputStream(args[3]));
            InputStream in = new FileInputStream(args[1]);
            OutputStream out = new FileOutputStream(args[2]))
         {
            Key key = (Key) keyIn.readObject();
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(mode, key); // 设置模式和密钥对其初始化
            Util.crypt(in, out, cipher);
         }
      }
   }
}

