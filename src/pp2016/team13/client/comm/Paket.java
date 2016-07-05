package pp2016.team13.client.comm;


import java.io.IOException;
import java.io.Serializable;

import pp2016.team13.client.engine.Nachricht;

public class Paket implements Serializable{

		private static final long serialVersionUID = 1;
		protected long time;
		public Nachricht inhalt;
		public String Test;
		public Paket(){
			this.time=System.currentTimeMillis();
			this.Test="Test vom Client";
		}
		public Paket(Nachricht n){
			this.time=System.currentTimeMillis();
			this.inhalt = n;
		}
		public Nachricht getMessage(){
			return inhalt;
		}
	    private void writeObject(java.io.ObjectOutputStream stream) throws IOException {
	        stream.defaultWriteObject();
	    }
	 
	    private void readObject(java.io.ObjectInputStream stream) throws IOException {
	        try {
	            stream.defaultReadObject();
	        } catch (ClassNotFoundException e) {
	            throw new IOException("No Class Found");
	        }
	}

	}



