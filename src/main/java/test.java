

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import hei.devweb.wejog.managers.UserService;

public class test {

	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException{
		
		/*System.out.println(UserService.getInstance().genererMotDePasse("0000"));
		
		System.out.println(generate(7));*/
		
		/*String test = " je teste ";
		test = test.replace(" ", "");
		System.out.println("La string :>>"+test+"<<");*/
		
		String firstName = "bruce wayne";
		
		String correctFirstName="";
		
		if(firstName.length()<2){
			correctFirstName = firstName.substring(0,1).toUpperCase();
		}
		else{
			correctFirstName = firstName.substring(0,1).toUpperCase()+firstName.substring(1).toLowerCase();
		}
		
		System.out.println("affichage correct : "+correctFirstName);
	}
	
	/*public static String generate(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; 
        StringBuffer pass = new StringBuffer();
        for(int x=0;x<length;x++)   {
           int i = (int)Math.floor(Math.random() * (chars.length() -1));
           pass.append(chars.charAt(i));
        }
        return pass.toString();
	}*/

}
