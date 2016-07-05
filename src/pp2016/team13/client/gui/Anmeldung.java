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
	
	public JButton anmeldeButton;

	JTextField textBenutzer;
	JPasswordField passwortfeld;
	JButton registrierButton;
	JLabel benutzernameL;
	JLabel passwortL;
	
	String Bn;
	String Pw;
	
//	public LoginNachricht lognachricht;
	
	HindiBones fenster;
	public Anmeldung(HindiBones fenster){
		this.fenster=fenster;
		//Bild einbinden
		 //Passwort Eingabe Feld
		 passwortfeld=new JPasswordField(15); //Erzeugen eines Passwortfeldes l�nge 15
		 passwortfeld.setBounds(370,250,230,35); //Gr��e + Koord. wird festgelegt
		 this.add(passwortfeld);
		 
		 //Benutzer Name Eingabe Feld
		 textBenutzer = new JTextField(15); //Erzeugen eines Textfeldes f�r Nicknamen
		 textBenutzer.setBounds(370,200,230,35);//Gr��e+Koord. wird festgelegt 
		 this.add(textBenutzer);
		 
		 //Label /Beschriftungen
		 benutzernameL = new JLabel("Benutzername: "); //Erzeugen eines Labels hei�t eines Textes
		 benutzernameL.setBounds(278,190,100,50); //Gr��e+ Koord. wird festgelegt
		 this.add(benutzernameL);
		 
		 passwortL = new JLabel("Passwort: ");// " das selbe vorgehen wie mit username "
		 passwortL.setBounds(310,240,100,50);
		 this.add(passwortL);
		 
		 //Buttons u.a einlogg+ registrierungs Button
		 anmeldeButton = new JButton("Einloggen"); //Erzeugen eines Buttons "login"
		 anmeldeButton.setBounds(370,320,100,50);//Gr��e+Koord. wird festgelegt 
		 this.add(anmeldeButton);

		 registrierButton = new JButton("Registrieren");//Erzeugen eines Buttons "registrieren"
		 registrierButton.setBounds(500,320,100,50); //Gr��e+Koord. wird festgelegt 
		 this.add(registrierButton);

		 //Standard Einstellungen f�r  das Anmelde Fenster
		 setSize(640,400); // Gr��e ensprechend an das Bild angepasst
		 setLocation(500,280); //Zentrieren 
		 this.setLayout (null);
		  
		 //Eingabe Grau gesetzt
		 benutzernameL.setForeground(Color.GRAY);
		 passwortL.setForeground(Color.GRAY);
		 textBenutzer.setForeground(Color.GRAY);
		 textBenutzer.setText("Username");
		 
			//Anbindung an ActionListener
		 anmeldeButton.addActionListener(this);
		 registrierButton.addActionListener(this);

		 //Standardaeinstellung
		 
		 

		 setVisible(true);


		
		
	JLabel bildLabel = new JLabel(); 
	 bildLabel.setIcon(new ImageIcon("img/Bild.png")); 
	 bildLabel.setBounds(0, 0, 640, 400); 
	 this.add(bildLabel); 
	 
//		lognachricht= new LoginNachricht("Peace", "12345");
//		Bn="Peace";
//		Pw="12345";
//	 
	}

	
	/**
	 * Beim Drücken auf den Button anmelden, sollen eingaben verglichen werden, anschließend (vor. richtige eingabe)
	 * soll das Spielfenster geöffnet werden.
	 * 
	 * Alternativ Button Registrierung soll Register Klasse öffnen siehe rest da
	 * 
	 * Verschlüsselungs Algorithmus 
	 * 
	 * @author Seyma Keser
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==anmeldeButton){
			String nickname=textBenutzer.getText();
			@SuppressWarnings("deprecation")
			String pwd= passwortfeld.getText();
//			lognachricht= new LoginNachricht("Peace", "12345");
			//Beispiel Versuch wird sp�ter mit client erweitert
			if(nickname.equals(fenster.GetBenutzername())&& pwd.equals(fenster.GetPasswort())){
				try {
					Thread.sleep(100);
					fenster.zeigeSpielfeld();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
//					
			}else {
				
//				anmeldung1=false;
				JOptionPane.showMessageDialog(null, "Falsches Passwort oder Falscher Benutzername");
			}
		//�ffnen der Registrierung 	
		}if(e.getSource()==registrierButton)	{
			try {
				Thread.sleep(50);	
				new Registrierung();

			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			
		}     
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Hier soll sp�ter mit Enter eingeloggt werden k�nnen
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}