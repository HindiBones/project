package pp2016.team13.server.engine;
import pp2016.team13.client.gui.Verschluesselung;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Einloggen {
	 static String[] passworte;
	 static String[] benutzer;
	
 static String Pwt;
	
		public static boolean LogIn(String benutzername, String passwort) throws Exception{
			Verschluesselung Ver= new Verschluesselung();
			try{
				boolean fertig = false;
				FileInputStream fileinptstrm = new FileInputStream("./src/pp2016/team13/server/engine/NutzerDaten.txt");
				InputStreamReader inptstrmreader = new InputStreamReader(fileinptstrm);
				BufferedReader buffreader = new BufferedReader(inptstrmreader);
				String rLine = buffreader.readLine();
				int i = 0;
				String[] Nutzerdaten = new String [10];
				passworte= new String[10];
				benutzer= new String[10];
				while(rLine != null)
				{	//Passwort und Bentzername wird getrennt	
					Nutzerdaten [i] = rLine;
					String[] a= Nutzerdaten[i].split(" "); 
					passworte[i]=a[1];
					System.out.println("Psw  "+passworte[i]);
					System.out.println("Eingabe  "+passwort);
					
					Pwt=passworte[i];
					
					System.out.println("Psw  "+ Ver.verschluesseln(passwort, benutzername));

//					benutzer[i]= a[0];
					
					if (Ver.verschluesseln(passwort, benutzername).equals(Pwt)){
						System.out.println("Willkommen zur�ck");
						fertig = true;
						
						//Nutzerdaten [i] = rLine;

						break;
					}else{
						System.out.println("Falsche eingabe");
//						a= Nutzerdaten[i].split(" "); 
//						passworte[i]= a[1];
						Nutzerdaten [i] = rLine;
						rLine = buffreader.readLine();
						i++; 
						fertig = false;
					}
					
				}
				buffreader.close();
	        	return fertig;
			}catch(IOException e){
			System.out.println("Fehler " + e);
			return false;
		}
	}
		
		public static boolean RegIn(String Benutzername, String Passwort)
		{			
			try{
			boolean fertig = false;
			FileInputStream fileinptstrm = new FileInputStream("./src/pp2016/team13/server/engine/NutzerDaten.txt");
			InputStreamReader inptstrmreader = new InputStreamReader(fileinptstrm);
			BufferedReader buffreader = new BufferedReader(inptstrmreader);
			String rLine = buffreader.readLine();
			int i = 0;
			String[] Nutzerdaten = new String [10];
			
			while(rLine!= null)
			{
				if (rLine.equals(Benutzername+" " +Passwort)){
				
						System.out.println("Benutzername ist schon Vergeben");
						fertig = true;
						Nutzerdaten [i] = rLine;
						
						fertig=true;
						break;

				}else{		
			
				}
					Nutzerdaten [i] = rLine;
				
					rLine = buffreader.readLine();
					i++;
					fertig= false;
				}
			
			if (!fertig)
			{
				buffreader.close();  
				System.out.println("Hallo neuer Spieler");
				BufferedWriter buffwriter = new BufferedWriter(new FileWriter("./src/pp2016/team13/server/engine/NutzerDaten.txt", false));
				for(int j = 0; j<i;j++)
				{
					buffwriter.write(Nutzerdaten[j]);
					buffwriter.write(System.getProperty("line.separator"));
				}
				buffwriter.write(Benutzername+" " +Passwort);
				buffwriter.flush();
	        	buffwriter.close();
	        	fertig = true;
			}
        	return true;
		}catch(IOException e){
		System.out.println("Fehler " + e);
		return false;
	}
			
			


}	
		
//	public static String[] gibmirpasswort(){
//		
//		return passworte;
//	}	
					
			
//		public static String gibmirdasPasswort(String passwort){
//			try{
//				boolean fertig = false;
//				FileInputStream fileinptstrm = new FileInputStream("./src/pp2016/team13/server/engine/NutzerDaten.txt");
//				InputStreamReader inptstrmreader = new InputStreamReader(fileinptstrm);
//				BufferedReader buffreader = new BufferedReader(inptstrmreader);
//				String rLine = buffreader.readLine();
//				int i = 0;
//				String[] Nutzerdaten = new String [10];
//				while(rLine != null)
//				{
//					if (rLine.equals(" " +passwort)){
//						System.out.println("Willkommen zur�ck");
//						fertig = true;
//						Nutzerdaten [i] = rLine;
//						
//						break;
//					}else{
//						System.out.println("Falsche eingabe");
//						
//						Nutzerdaten [i] = rLine;
//						rLine = buffreader.readLine();
//						i++; 
//						fertig = false;
//					}
//					
//				}
//
////	        	return fertig;
//			}catch(IOException e){
//			System.out.println("Fehler " + e);
//			return "OK";
//		}
//		}
}

