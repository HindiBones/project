package pp2016.team13.client.comm;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Lebenszeichen extends Thread{
	static long jetzigeZeit;
		public static boolean run(Socket S, long letztesLebenszeichen){
			try{
					jetzigeZeit = System.currentTimeMillis();
						if(jetzigeZeit- letztesLebenszeichen>10000)
						{
							System.out.println("Spieler 0 inaktiv");
						    return true;
						}else{
							return false;
						}
		     }catch (Exception e){
			    e.printStackTrace();
			    return false;
		 }
	}
		Lebenszeichen(long Meldung){
			jetzigeZeit = Meldung;
		}
		
}