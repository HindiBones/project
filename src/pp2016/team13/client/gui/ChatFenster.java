package pp2016.team13.client.gui;

import java.awt.BorderLayout; 
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import pp2016.team13.shared.Nachrichten.ChatNachricht;

/**
 * 
 * Ein Panel Chat Fenster wird in die Seitenleiste eingebaut
 * 
 * @author Keser, Seyma, 5979919
 *
 */
public class ChatFenster extends JPanel implements  MouseListener, KeyListener,ActionListener {

	private static final long serialVersionUID = 1L;
	public TextArea textumfeld=null;
	public TextField textfeld=null;
	public String benutzername= null;
	Button senden;
	Button loeschen;
	int i =1;
	
	

	SeitenLeiste m;
	
	/**
	 * 
	 * 
	 * 
	 * @author Keser, Seyma, 5979919
	 * @param s: Text eingabe 
	 * @param m: Um das Panel an die Seitenleiste anzubinden
	 */
	ChatFenster(String s, SeitenLeiste m ) {
			this.m= m;
			
			this.setLayout(new BorderLayout());
			this.setPreferredSize(new Dimension(170,132));
	
			textumfeld=new TextArea();

			this.add(textumfeld,"Center");
			textumfeld.setFont(new Font("Arial", Font.PLAIN,9));
			textumfeld.setEditable(false);
			
			Panel pane= new Panel();
			pane.setLayout(new BorderLayout());
			
			textfeld= new TextField(10);
			textfeld.addKeyListener(this);
			textfeld.setFont(new Font("Arial", Font.PLAIN,11));
			
			pane.add(textfeld,BorderLayout.CENTER);


			this.add(pane,"South");
			this.setVisible(true);
			
			//Provisorische Eingabe
			textfeld.setForeground(Color.GRAY);
			textfeld.setText("Cheats/ Text eingeben");
			textfeld.setEditable(true);			
			textfeld.requestFocus();
			

	}

	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Die Chat Fenster Funktion zum absenden von Nachrichten
	 * 
	 * @author Keser, Seyma, 5979919
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			
			String Text= this.textfeld.getText();
			//Verbindung zum Client
			ChatNachricht nachricht = new ChatNachricht(Text);
			boolean funktioniert = this.m.fenster.client.chatte(nachricht);
			
			//Prüfen von Cheats 
			if(funktioniert && !nachricht.istCheat()) {
				textumfeld.append(Text+"\n");
				textfeld.setText(null);	
			}
			else if(!nachricht.istCheat())
				textumfeld.append("Konnte Nachricht nicht senden!\n");
			textumfeld.requestFocusInWindow();
			textumfeld.setCaretPosition(i);
			this.disable();
			
			//Focus Aenderung zurueck auf das Spielfeld
			m.fenster.spielflaeche.setFocusable(true);
			m.fenster.spielflaeche.requestFocusInWindow();
			m.fenster.requestFocus();
			
			//Standardeinstellung
			textfeld.setText("Cheats/ Text eingeben");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
	}	
		
}
