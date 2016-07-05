package pp2016.team13.client.gui;

import java.awt.BorderLayout; 
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JFrame;
import javax.swing.JPanel;

import pp2016.team13.client.engine.ChatNachricht;
import pp2016.team13.client.engine.NachrichtenVerwaltung;

public class ChatFenster extends JPanel implements  MouseListener, KeyListener,ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public TextArea textumfeld=null;
	public TextField textfeld=null;
	public String benutzername= null;
	public NachrichtenVerwaltung client;
		Button senden;
	Button loeschen;
	int i =1;
	
	

	SeitenLeiste m;
	
	ChatFenster(String s, SeitenLeiste m ) {
			this.m= m;
			client = new NachrichtenVerwaltung(0);
			
			this.setLayout(new BorderLayout());
			this.setPreferredSize(new Dimension(170,112));

				
	
			textumfeld=new TextArea();


			this.add(textumfeld,"Center");
			textumfeld.setFont(new Font("Arial", Font.PLAIN,11));
			textumfeld.setEditable(false);
			
			Panel pane= new Panel();
			pane.setLayout(new BorderLayout());
			
			textfeld= new TextField(10);
			textfeld.addKeyListener(this);
			textfeld.setFont(new Font("Arial", Font.PLAIN,11));
			
			pane.add(textfeld,BorderLayout.CENTER);

			// Eventuell noch einbauen 
			senden= new Button("senden");
			senden.addMouseListener(this);
			senden.addActionListener(this);
//			pane.add(senden);

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

	@SuppressWarnings("deprecation")
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			String Text= this.textfeld.getText();
			textumfeld.append(Text+"\n");
			client.sende(new ChatNachricht(Text));
			textfeld.setText(null);
			
			textumfeld.requestFocusInWindow();
			textumfeld.setCaretPosition(i);
			
			this.disable();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		//Kein Button mehr vorhanden, Optional falls ich noch Buttons reinmache 
		
		
//		if(e.getSource()==this.senden){ 
//			
//			String Text= this.textfeld.getText();
//			
//			
//	
//			textumfeld.append(Text+"\n");
//			//client.sende(new ChatNachricht(Text));
//			textfeld.setText(null);
//			
//			textumfeld.requestFocusInWindow();
//			textumfeld.setCaretPosition(i);
//
//
//			
//		}

	}	
		

		
	
	

}
