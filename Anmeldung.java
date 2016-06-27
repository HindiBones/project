package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Anmeldung extends JPanel implements ActionListener{
	
	public JButton anmeldeButton;

	JTextField textBenutzer;
	JPasswordField passwortfeld;
	JButton registrierButton;
	JLabel benutzernameL;
	JLabel passwortL;
	
	HindiBones fenster;
	public Anmeldung(HindiBones fenster){
		this.fenster=fenster;
		//Bild einbinden
		 //Passwort Eingabe Feld
		 passwortfeld=new JPasswordField(15); //Erzeugen eines Passwortfeldes länge 15
		 passwortfeld.setBounds(370,250,230,35); //Größe + Koord. wird festgelegt
		 this.add(passwortfeld);
		 
		 //Benutzer Name Eingabe Feld
		 textBenutzer = new JTextField(15); //Erzeugen eines Textfeldes für Nicknamen
		 textBenutzer.setBounds(370,200,230,35);//Größe+Koord. wird festgelegt 
		 this.add(textBenutzer);
		 
		 //Label /Beschriftungen
		 benutzernameL = new JLabel("Benutzername: "); //Erzeugen eines Labels heißt eines Textes
		 benutzernameL.setBounds(278,190,100,50); //Größe+ Koord. wird festgelegt
		 this.add(benutzernameL);
		 
		 passwortL = new JLabel("Passwort: ");// " das selbe vorgehen wie mit username "
		 passwortL.setBounds(310,240,100,50);
		 this.add(passwortL);
		 
		 //Buttons u.a einlogg+ registrierungs Button
		 anmeldeButton = new JButton("Einloggen"); //Erzeugen eines Buttons "login"
		 anmeldeButton.setBounds(370,320,100,50);//Größe+Koord. wird festgelegt 
		 this.add(anmeldeButton);

		 registrierButton = new JButton("Registrieren");//Erzeugen eines Buttons "registrieren"
		 registrierButton.setBounds(500,320,100,50); //Größe+Koord. wird festgelegt 
		 this.add(registrierButton);

		 //Standard Einstellungen für  das Anmelde Fenster
		 setSize(640,400); // Größe ensprechend an das Bild angepasst
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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==anmeldeButton){
			String nickname=textBenutzer.getText();
			@SuppressWarnings("deprecation")
			String pwd= passwortfeld.getText();
			//Beispiel Versuch wird später mit client erweitert
			if(nickname.equals("Mann1")&& pwd.equals("12345")){
				try {
					Thread.sleep(50);
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
		//Öffnen der Registrierung 	
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
}
