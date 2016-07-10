package pp2016.team13.client.gui;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import pp2016.team13.client.comm.Paket;
import pp2016.team13.client.engine.AusloggenNachricht;
import pp2016.team13.shared.Level;


public class MenuLeiste extends JMenuBar implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JMenu spiel;
	private JMenu anzeige;
	private JMenu hilfe;
	private JMenuItem ausloggen;

	private JMenuItem neuesSpiel;
	private JMenuItem highscore;
	private JMenuItem beenden;
	private JMenuItem karteaufdecken;
	private JMenuItem steuerung;

	private HindiBones fenster;
	boolean anmelde= false;

	public MenuLeiste(HindiBones fenster) {
		this.fenster = fenster;

		spiel = new JMenu("Spiel");
		anzeige = new JMenu("Anzeige");
		hilfe = new JMenu("Hilfe");
		ausloggen=new JMenuItem("Ausloggen");

		neuesSpiel = new JMenuItem("Neues Spiel starten");
		highscore = new JMenuItem("Highscore anzeigen");
		beenden = new JMenuItem("Beenden");
		karteaufdecken = new JMenuItem("Karte aufdecken");
		steuerung = new JMenuItem("Steuerung");
		
		//Einbinden Elemente an ActionListener
		neuesSpiel.addActionListener(this);
		highscore.addActionListener(this);
		beenden.addActionListener(this);
		karteaufdecken.addActionListener(this);
		steuerung.addActionListener(this);
		hilfe.addActionListener(this);
		ausloggen.addActionListener(this);

		spiel.add(neuesSpiel);
		spiel.add(beenden);
		anzeige.add(highscore);
		anzeige.add(karteaufdecken);
		hilfe.add(steuerung);

		
		this.add(spiel);
		this.add(anzeige);
		this.add(hilfe);
		this.add(ausloggen);
	}
	
	/**
	 * Ergänzt um Auslogg Item
	 * @author Seyma Keser
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == neuesSpiel) {
			fenster.levelNeustarten = true;
			fenster.spielZuruecksetzen();
			fenster.zeigeSpielfeld();
		} else if (e.getSource() == highscore) {
			if (fenster.highscoreAngezeigt) {
				fenster.zeigeSpielfeld();
				highscore.setText("Highscore anzeigen");
			} else {
				fenster.zeigeHighscore();
				highscore.setText("Spielfeld anzeigen");
			}

		} else if (e.getSource() == karteaufdecken) {
			if (fenster.nebelAn) {
				fenster.nebelAn = false;
				karteaufdecken.setText("Karte verdecken");
			} else {
				fenster.nebelAn = true;
				karteaufdecken.setText("Karte aufdecken");
			}
		} else if (e.getSource() == beenden) {
			fenster.client.socket.SendeLogout(new Paket(new AusloggenNachricht()));
			System.exit(0);
		} else if (e.getSource() == steuerung) {
			fenster.zeigeSteuerung();
		}else if (e.getSource()== ausloggen){
			
			
			try {
				
				Thread.sleep(100);
				fenster.Level = new Level(-1, null);
				fenster.spielZuruecksetzen();
				fenster.zeigeAnmeldung();
				
			} catch (InterruptedException e1) {
			
				e1.printStackTrace();
			
			}	 	
			
		}
	}

	public JMenuItem getHighscore() {
		return highscore;
	}

	
	
}
