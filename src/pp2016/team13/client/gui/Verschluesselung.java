package pp2016.team13.client.gui;

import java.security.MessageDigest; 
import java.util.Arrays;
 
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
 
import sun.misc.BASE64Encoder;
 

public class Verschluesselung {
	    // Das Passwort bzw der Schluesseltext hier kommt der Benutzername rein

	

	    public String verschluesseln(String passwort, String benutzername)throws Exception{
	    	//mein schlüssel zum entschlüsseln 
	    	String keyStr = benutzername;
	    	// byte-Array erzeugen
	    	byte[] key = (keyStr).getBytes("UTF-8");
	    	
	    	  // aus dem Array einen Hash-Wert erzeugen mit MD5 oder SHA
		    MessageDigest sha = MessageDigest.getInstance("SHA-256");
		    key = sha.digest(key);
		    // nur die ersten 128 bit nutzen
		    key = Arrays.copyOf(key, 16); 
		    // der fertige Schluessel
		    SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
		     

		    // der zu verschl. Text
		    String text = passwort;

		    // Verschluesseln
		    Cipher cipher = Cipher.getInstance("AES");
		    cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
		    byte[] encrypted = cipher.doFinal(text.getBytes());

		    // bytes zu Base64-String konvertieren (dient der Lesbarkeit)
		    BASE64Encoder myEncoder = new BASE64Encoder();
		    String geheim = myEncoder.encode(encrypted);
		    
	    	
	    	 return geheim;
	    }
	    
	   

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		  

	    
	  
	}

}
