

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import hei.devweb.wejog.managers.UserService;

public class test {

	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException{
		
		System.out.println(UserService.getInstance().genererMotDePasse("0000"));
		
		System.out.println(generate(7));

	}
	
	public static String generate(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; 
        StringBuffer pass = new StringBuffer();
        for(int x=0;x<length;x++)   {
           int i = (int)Math.floor(Math.random() * (chars.length() -1));
           pass.append(chars.charAt(i));
        }
        return pass.toString();
	}

}
