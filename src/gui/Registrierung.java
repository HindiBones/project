package gui;



import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.*;

import java.awt.event.*;


public class Registrierung extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JButton anmeldeButton;
	JPanel AnmeldePanel;
	JTextField textNBenutzer;
	JPasswordField passwortfeld;
	JPasswordField NeuesPasswortfeld;
	JButton registrierButton;
	JLabel benutzernameL;
	JLabel WiedPasswortL;
	JLabel passwortL;
	
	boolean anmeldung1=false;
	
	public static final int BOX = 32;
	public static final int WIDTH = 16, HEIGHT = 16;
	
	public Registrierung(){
	
	 AnmeldePanel = new JPanel();
	this.getContentPane().add(AnmeldePanel);
	
	 passwortfeld=new JPasswordField(15); //Erzeugen eines Passwortfeldes länge 15
	 passwortfeld.setBounds(200,150,230,35); //Größe + Koord. wird festgelegt
	 AnmeldePanel.add(passwortfeld);
	 
	 NeuesPasswortfeld=new JPasswordField(15); //Erzeugen eines Passwortfeldes länge 15
	 NeuesPasswortfeld.setBounds(200,200,230,35); //Größe + Koord. wird festgelegt
	 AnmeldePanel.add(NeuesPasswortfeld);
	 
	 textNBenutzer = new JTextField(15); //Erzeugen eines Textfeldes für Nicknamen
	 textNBenutzer.setBounds(200,100,230,35);//Größe+Koord. wird festgelegt 
	 AnmeldePanel.add(textNBenutzer);
	 
	 benutzernameL = new JLabel("Benutzername: "); //Erzeugen eines Labels heißt eines Textes
	 benutzernameL.setBounds(100,90,100,50); //Größe+ Koord. wird festgelegt
	 AnmeldePanel.add(benutzernameL);
	 
	 passwortL = new JLabel("Neues Passwort: ");// " das selbe vorgehen wie mit username "
	 passwortL.setBounds(90,140,120,50);
	 AnmeldePanel.add(passwortL);
	 
	 WiedPasswortL = new JLabel("Passwort Wiederholen: ");// " das selbe vorgehen wie mit username "
	 WiedPasswortL.setBounds(50,190,150,50);
	 AnmeldePanel.add(WiedPasswortL);
	 

	 registrierButton = new JButton("Registrieren");//Erzeugen eines Buttons "registrieren"
	 registrierButton.setBounds(200,250,230,35); //Größe+Koord. wird festgelegt 
	 registrierButton.addActionListener(this);
	 AnmeldePanel.add(registrierButton);



	 
	 setSize(640,400); // Größe ensprechend an das Bild angepasst
	 setLocation(550,320); //Zentrieren 
	 AnmeldePanel.setLayout (null);
	  
	 //Eingabe Grau gesetzt
	 benutzernameL.setForeground(Color.WHITE);
	 passwortL.setForeground(Color.WHITE);
	 WiedPasswortL.setForeground(Color.WHITE);
	 textNBenutzer.setForeground(Color.BLACK);
	 textNBenutzer.setText("Username");
	 
	
	 registrierButton.addActionListener(this);
	
	 //Standardaeinstellung
	 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	 setVisible(true);


	 //Bild einbinden
	 JLabel bildLabel = new JLabel("New label"); 
	 bildLabel.setIcon(new ImageIcon("img/regist2.jpg")); 
	 bildLabel.setBounds(0, 0, 640, 400); 
	 AnmeldePanel.add(bildLabel); 
	 repaint();
	
	}
	
	public void actionPerformed(ActionEvent e) {
		//Eingaben sollen später im Client gepspeichert werden
		//Registrierung vergleich Passwort und Wiederholtes Psw
		//auf gleichheit und das überhaupt ein Passwort eingegeben wurde
		if(e.getSource()==registrierButton){
			String nickname=textNBenutzer.getText();
			@SuppressWarnings("deprecation")
			String pwd= passwortfeld.getText();
			@SuppressWarnings("deprecation")
			String wpwd= NeuesPasswortfeld.getText();
			//Beispiel Versuch wird später mit client erweitert
			if(pwd.equals(wpwd)&& (pwd.isEmpty()==false )){
				anmeldung1= true;
				//Systemnachricht: Bestätigung
				JOptionPane.showMessageDialog(this, "Ihr Benutzername: " + nickname + " ,wurde registriert ");
			    dispose();
				
			}else { // Vergleiche!!!! zur richtigen Systemnachricht
				//Systemnachricht bei ungleichheit
				anmeldung1=false;
				JOptionPane.showMessageDialog(AnmeldePanel, "Falsches Passwort oder Falscher Benutzername");
			}
		}	     
	}



}