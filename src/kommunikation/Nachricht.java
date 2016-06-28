package kommunikation;


import java.io.IOException;
import java.io.Serializable;

public class Nachricht implements Serializable{

		private static final long serialVersionUID = 1;
		protected long time;
		public String Test;
		public Nachricht(){
			this.time=System.currentTimeMillis();
			this.Test="Test vom Client";
		}
		public Nachricht(String Test){
			this.time=System.currentTimeMillis();
			this.Test=Test;
		}
		public String getMessage(Nachricht m){
			return m.Test;
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



