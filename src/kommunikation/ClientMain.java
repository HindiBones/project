package kommunikation;

<<<<<<< HEAD
import kommunikation.Client; 
import kommunikation.Paket;
=======
import pp2016.team13.client.comm.Client;
import pp2016.team13.client.engine.*;
>>>>>>> branch 'master' of https://github.com/HindiBones/project.git

public class ClientMain {
public static Paket Test = new Paket("Hallo Welt");
	
	public static void main (String []args){
		Client TestenC= new Client("localhost",2345);
		TestenC.SendeAnServer(Test);
	}
}






