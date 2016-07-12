package pp2016.team13.client.gui;

import java.awt.Color;  
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import pp2016.team13.client.engine.LoginNachricht;


public class Anmeldung extends JPanel implements ActionListener, KeyListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JButton anmeldeButton;

	JTextField textBenutzer;
	JPasswordField passwortfeld;
	JButton registrierButton;
	JLabel benutzernameL;
	JLabel passwortL;
	boolean einloggen= false;
	
	String Bn;
	String Pw;
	

	/**
	 * @author Seyma 
	 * @param fenster: Setzt das fenster fest, auf dem das Panel gezeichnet wird
	 * 
	 * Erstellt das Panel mit ihren Buttons, Textfeldern, Hintergrund und macht die Buttons Benutzbar
	 * 
	 * 
	 */
	HindiBones fenster;
	public Anmeldung(HindiBones fenster){
		this.fenster=fenster;
		
		 //Passwort Eingabe Feld
		 passwortfeld=new JPasswordField(15); //Erzeugen eines Passwortfeldes laenge 15
		 passwortfeld.setBounds(370,250,230,35); //Grosse + Koord. wird festgelegt
		 this.add(passwortfeld);
		 
		 //Benutzer Name Eingabe Feld
		 textBenutzer = new JTextField(15); //Erzeugen eines Textfeldes f�r Nicknamen
		 textBenutzer.setBounds(370,200,230,35);//Grosse+Koord. wird festgelegt 
		 this.add(textBenutzer);
		 
		 //Label /Beschriftungen
		 benutzernameL = new JLabel("Benutzername: "); //Erzeugen eines Labels heisst eines Textes
		 benutzernameL.setBounds(278,190,100,50); //Grosse+ Koord. wird festgelegt
		 this.add(benutzernameL);
		 
		 passwortL = new JLabel("Passwort: ");// " das selbe vorgehen wie mit username "
		 passwortL.setBounds(310,240,100,50);
		 this.add(passwortL);
		 
		 //Buttons u.a einlogg+ registrierungs Button
		 anmeldeButton = new JButton("Einloggen"); //Erzeugen eines Buttons "login"
		 anmeldeButton.setBounds(370,320,100,50);//Grosse+Koord. wird festgelegt 
		 this.add(anmeldeButton);

		 registrierButton = new JButton("Registrieren");//Erzeugen eines Buttons "registrieren"
		 registrierButton.setBounds(500,320,100,50); //Grosse+Koord. wird festgelegt 
		 this.add(registrierButton);

		 //Standard Einstellungen fuer  das Anmelde Fenster
		 setSize(640,400); // Grosse ensprechend an das Bild angepasst
		 setLocation(500,280); //Zentrieren 
		 this.setLayout (null);
		  
		 //Eingabe Grau gesetzt
		 benutzernameL.setForeground(Color.GRAY);
		 passwortL.setForeground(Color.GRAY);
		 textBenutzer.setForeground(Color.GRAY);
		 textBenutzer.setText("");
		 
		 //Anbindung an ActionListener
		 anmeldeButton.addActionListener(this);
		 registrierButton.addActionListener(this);
		 passwortfeld.addKeyListener(this);
		 //Standardaeinstellung
		 setVisible(true);
		
		
		JLabel bildLabel = new JLabel(); 
		 bildLabel.setIcon(new ImageIcon("img/Bild.png")); 
		 bildLabel.setBounds(0, 0, 640, 400); 
		 this.add(bildLabel); 
	 	 
	}

	
	/**
	 * 
	 * 
	 * Beim druecken auf den Button anmelden, sollen eingaben verglichen werden, anschliessend (vorr. richtige eingabe)
	 * soll das Spielfenster geoeffnet werden.
	 * 
	 * Alternativ Button Registrierung soll Register Klasse oeffnen siehe rest da kommt auch die Verschluesselung zum Einsatz.
	 * Hier wird die Eingabe im Server verschluesselt und verglichen mit den im Nutzertext aufgelisteten Passwoertern und Nutzernamen.
	 * 
	 * 
	 * @author Seyma Keser
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		
		// TODO Auto-generated method stub
		if(e.getSource()==anmeldeButton){
			anmelden();
			
		//Oeffnen der Registrierung 	
		}if(e.getSource()==registrierButton)	{
			try {
				Thread.sleep(50);	
				new Registrierung(fenster);

			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Hier soll spaeter mit Enter eingeloggt werden koennen
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			anmelden();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void anmelden(){
		String nickname=textBenutzer.getText();
		@SuppressWarnings("deprecation")
		String pwt= passwortfeld.getText();
	
	try {
		LoginNachricht registrierung = new LoginNachricht(nickname, pwt, 0);
		einloggen = fenster.client.einloggen(registrierung);
	} catch (Exception e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
				
				
	if(einloggen==true){
		try {
			Thread.sleep(100);
			fenster.zeigeSpielfeld();
			fenster.client.systemnachricht("Einloggen erfolgreich!");
			fenster.client.systemnachricht("Willkommen, " + nickname);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}else {
		JOptionPane.showMessageDialog(null, "Falsches Passwort oder Falscher Benutzername");
	}
	}


}
