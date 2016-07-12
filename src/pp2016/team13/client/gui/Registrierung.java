package pp2016.team13.client.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.*;
import pp2016.team13.shared.Nachrichten.LoginNachricht;
import java.awt.event.*;

/**
 * Getrenntes Fenster fuer die Registrierung
 * 
 * @author <Keser, Seyma, 5979919>
 *
 */
public class Registrierung extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	// Fenster Elemente
	public JButton anmeldeButton;
	JPanel anmeldePanel;
	JTextField textNBenutzer;
	JPasswordField passwortfeld;
	JPasswordField neuesPasswortfeld;
	JButton registrierButton;
	JLabel benutzernameL;
	JLabel wiedPasswortL;
	JLabel passwortL;
	HindiBones fenster;

	boolean anmeldung1 = false;

	public static final int BOX = 32;
	public static final int WIDTH = 16, HEIGHT = 16;

	public Registrierung(HindiBones f) {

		this.fenster = f;
		anmeldePanel = new JPanel();
		this.getContentPane().add(anmeldePanel);

		// Einstellungen zu den Elementen
		passwortfeld = new JPasswordField(15); // Erzeugen eines Passwortfeldes
												// Laenge 15
		passwortfeld.setBounds(200, 150, 230, 35); // Groesse + Koord. wird
													// festgelegt
		anmeldePanel.add(passwortfeld);

		neuesPasswortfeld = new JPasswordField(15); // Erzeugen eines
													// Passwortfeldes Laenge 15
		neuesPasswortfeld.setBounds(200, 200, 230, 35); // Groesse + Koord. wird
														// festgelegt
		anmeldePanel.add(neuesPasswortfeld);

		textNBenutzer = new JTextField(15); // Erzeugen eines Textfeldes fuer
											// Nicknamen
		textNBenutzer.setBounds(200, 100, 230, 35);// Groesse+Koord. wird
													// festgelegt
		anmeldePanel.add(textNBenutzer);

		benutzernameL = new JLabel("Benutzername: "); // Erzeugen eines Labels
														// heisst eines Textes
		benutzernameL.setBounds(100, 90, 100, 50); // Groesse+ Koord. wird
													// festgelegt
		anmeldePanel.add(benutzernameL);

		passwortL = new JLabel("Neues Passwort: ");// " das selbe vorgehen wie
													// mit username "
		passwortL.setBounds(90, 140, 120, 50);
		anmeldePanel.add(passwortL);

		wiedPasswortL = new JLabel("Passwort Wiederholen: ");// " das selbe
																// vorgehen wie
																// mit username
																// "
		wiedPasswortL.setBounds(50, 190, 150, 50);
		anmeldePanel.add(wiedPasswortL);

		registrierButton = new JButton("Registrieren");// Erzeugen eines Buttons
														// "registrieren"
		registrierButton.setBounds(200, 250, 230, 35); // Groesse+Koord. wird
														// festgelegt
		registrierButton.addActionListener(this);
		anmeldePanel.add(registrierButton);

		setSize(640, 400); // Groesse ensprechend an das Bild angepasst
		setLocation(550, 320); // Zentrieren
		anmeldePanel.setLayout(null);

		// Eingabe Grau gesetzt
		benutzernameL.setForeground(Color.WHITE);
		passwortL.setForeground(Color.WHITE);
		wiedPasswortL.setForeground(Color.WHITE);
		textNBenutzer.setForeground(Color.BLACK);
		textNBenutzer.setText("Username");

		registrierButton.addActionListener(this);

		// Standardaeinstellung
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);

		// Bild einbinden
		JLabel bildLabel = new JLabel("New label");
		bildLabel.setIcon(new ImageIcon("img/regist2.jpg"));
		bildLabel.setBounds(0, 0, 640, 400);
		anmeldePanel.add(bildLabel);
		repaint();

	}

	/**
	 * Funktioniert im Grunde wie bei der Anmeldung. Unterschied: Fuer die
	 * Sicherheit, wird das Passwort 2 mal abgefragt (Wahl eines Passwort und
	 * Wiederholung
	 * 
	 * @author <Keser, Seyma, 5979919>
	 */
	public void actionPerformed(ActionEvent e) {
		// Eingaben sollen spaeter im Client gepspeichert werden
		// Registrierung vergleich Passwort und Wiederholtes Psw
		// auf Gleichheit und das ueberhaupt ein Passwort eingegeben wurde

		Verschluesselung verschluesseln = new Verschluesselung();
		if (e.getSource() == registrierButton) {
			String nickname = textNBenutzer.getText();
			@SuppressWarnings("deprecation")
			String pwd = passwortfeld.getText();
			@SuppressWarnings("deprecation")
			String wpwd = neuesPasswortfeld.getText();

			// Verschluesseln der Passwort Eingabe
			try {
				pwd = verschluesseln.verschluesseln(pwd, nickname);
				System.out.println(pwd + " " + nickname);
				wpwd = verschluesseln.verschluesseln(wpwd, nickname);
				System.out.println(wpwd + " " + nickname);
			} catch (Exception e2) {
				e2.printStackTrace();
			}

			// Beispiel Versuch wird spaeter mit client erweitert
			if ((pwd.isEmpty() == false) && (pwd.equals(wpwd))) {
				anmeldung1 = true;
				// Systemnachricht: Bestaetigung
				LoginNachricht registrierung = new LoginNachricht(nickname, pwd, 1);
				boolean funktioniert = fenster.client.einloggen(registrierung);
				if (funktioniert)
					JOptionPane.showMessageDialog(this, "Ihr Benutzername: " + nickname + " ,wurde registriert ");
				else
					JOptionPane.showMessageDialog(anmeldePanel, "Falsches Passwort oder Falscher Benutzername");
				dispose();

			} else {
				// Systemnachricht bei Ungleichheit
				anmeldung1 = false;
				JOptionPane.showMessageDialog(anmeldePanel, "Falsches Passwort oder Falscher Benutzername");
			}
		}
	}

}