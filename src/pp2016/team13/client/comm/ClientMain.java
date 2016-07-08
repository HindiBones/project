package pp2016.team13.client.comm;

	
	import pp2016.team13.client.comm.Client;
	import pp2016.team13.client.comm.Nachricht1;

	public class ClientMain {
	public static Nachricht1 Test = new Nachricht1("Hallo Welt");
		
		public static void main (String []args){
			Client TestenC= new Client("localhost",2345);
			TestenC.SendeAnServer(Test);
		}
	}





