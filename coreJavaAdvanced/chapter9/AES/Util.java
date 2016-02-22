package com.corejava.chapter9.cryption;

import java.io.*;
import java.security.*;
import javax.crypto.*;

public class Util
{
   public static void crypt(InputStream in, OutputStream out, Cipher cipher) throws IOException,
         GeneralSecurityException
   {
      int blockSize = cipher.getBlockSize();
      int outputSize = cipher.getOutputSize(blockSize);
      byte[] inBytes = new byte[blockSize];
      byte[] outBytes = new byte[outputSize];

      int inLength = 0;
      boolean more = true;
      while (more)
      {
    	  // inBytes 就是一个缓冲区
         inLength = in.read(inBytes);
         if (inLength == blockSize)
         {
        	// 只要输入数据块具有全长度（长度能够被8整除）： 就要调用update方法； 
            int outLength = cipher.update(inBytes, 0, blockSize, outBytes);
            out.write(outBytes, 0, outLength);
         }
         else more = false;
      }
      // 而如果输入数据块不具有全长度（长度不能被8整除，此时需要填充）: 就要调用 doFinal 方法；
      if (inLength > 0) 
    	  outBytes = cipher.doFinal(inBytes, 0, inLength);
      else
    	  outBytes = cipher.doFinal();
      out.write(outBytes);
   }
}
