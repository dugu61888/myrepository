package com.apcompany.user.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MD5Util {
	private  static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	private static  MessageDigest messagedigest = null;
	static {
		try {
			messagedigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public static String getStringMD5String(String text){
		if(text==null){
			return "-1";
		}else{
			Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
	        Matcher m = p.matcher(text);
	        byte[] buffter = m.replaceAll("").getBytes();
			messagedigest.update(buffter);
			return bufferToHex(messagedigest.digest());
		}
	}
	
	public static String getFileMD5String(File file) throws IOException {
        InputStream fis;
        fis = new FileInputStream(file);
       return getFileMD5String(fis);
    }
		
	 public static String getFileMD5String(InputStream in) throws IOException {
	        byte[] buffer = new byte[1024];
	        int numRead = 0;
	        while ((numRead = in.read(buffer)) > 0) {
	            messagedigest.update(buffer, 0, numRead);
	        }
	        in.close();
	        return bufferToHex(messagedigest.digest());
	    }
	
	private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }
	private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }
	private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];// 取字节中高 4 位的数字转换
        // 为逻辑右移，将符号位一起右移,此处未发现两种符号有何不同
        char c1 = hexDigits[bt & 0xf];// 取字节中低 4 位的数字转换
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }
	
	public static void main(String[] args){
		System.out.println(MD5Util.getStringMD5String("123456"));
	}
}
