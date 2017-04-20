

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import hei.devweb.wejog.managers.UserService;

public class test {

	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException{
		
		String recherche ="gui po";
		
		Long id = (long) 23;
		
		List<String> completeIdentity = new ArrayList<String>();
		
		if(recherche.contains(" ")){
			completeIdentity= Arrays.asList(recherche.split(" "));
		}
		
		System.out.println(completeIdentity);
		System.out.println(completeIdentity.size());
		
		
		List<Long> listUsersIdFound = new LinkedList<Long>();
		
		List<Long> listUsersIdFoundCompleteIdentity = new LinkedList<Long>();
		
		if(completeIdentity.size()==2){
			for (int i=0;i<2;i++){
				listUsersIdFoundCompleteIdentity = UserService.getInstance().listUsersIdFound(completeIdentity.get(i), id);
				
				int j = 0;
				while(j<listUsersIdFoundCompleteIdentity.size()){
					if(!listUsersIdFound.contains(listUsersIdFoundCompleteIdentity.get(j))){
						listUsersIdFound.add(listUsersIdFoundCompleteIdentity.get(j));
					}
					j++;
				}
				
				//listUsersIdFoundCompleteIdentity.addAll(listUsersIdFound);
			}
			// attention au doublons
		// envoyer dans context
			System.out.println(listUsersIdFound);
		}
		
		
		else{
			listUsersIdFound = UserService.getInstance().listUsersIdFound(recherche, id);
			// envoyer dans context
			System.out.println(listUsersIdFound);
		}
				
		
		/*System.out.println(UserService.getInstance().genererMotDePasse("0000"));
		
		System.out.println(generate(7));*/
		
		/*String test = " je teste ";
		test = test.replace(" ", "");
		System.out.println("La string :>>"+test+"<<");*/
		
		/*String firstName = "bruce wayne";
		
		String correctFirstName="";
		
		if(firstName.length()<2){
			correctFirstName = firstName.substring(0,1).toUpperCase();
		}
		else{
			correctFirstName = firstName.substring(0,1).toUpperCase()+firstName.substring(1).toLowerCase();
		}
		
		System.out.println("affichage correct : "+correctFirstName);*/
		
		/*String chaine ="  test  ";
		
		chaine = chaine.trim();
		
		System.out.println("affichage correct : >>"+chaine+"<<");*/
		
		/*LocalDate localDate = LocalDate.now();
		String dateAsString = localDate.toString();
		System.out.println("localDate : "+localDate+ " et DateAstring : "+dateAsString);*/
		
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
