package pp2016.team13.client.comm;

import java.net.Socket;


public class Lebenszeichen extends Thread{
	 long jetzigeZeit;
		public void run(Socket S, long letztesLebenszeichen){
			try{
					jetzigeZeit = System.currentTimeMillis();
						if(jetzigeZeit- letztesLebenszeichen>10000)
						{
							System.out.println("Spieler 0 inaktiv");
//						    return true;
						}else{
//							return false;
						}
		     }catch (Exception e){
			    e.printStackTrace();
//			    return false;
		 }
	}
		Lebenszeichen(long Meldung){
			jetzigeZeit = Meldung;
		}
		
}