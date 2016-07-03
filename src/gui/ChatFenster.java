package gui;

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

import Client.ChatNachricht;
import Client.Client;

public class ChatFenster extends JPanel implements WindowListener, MouseListener, KeyListener,ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public TextArea textumfeld=null;
	public TextField textfeld=null;
	public String benutzername= null;
	public Client client;
		Button senden;
	Button loeschen;
	int i =1;
	
	

	Minimap m;
	
	ChatFenster(String s, Minimap m ) {
			this.m= m;
//			this.setBounds(0, 500, 170, 115);
//			this.setLocation(900,900);

			client = new Client(0);
			
//			this.addWindowListener(this);
//				this.setSize(170	,105);
//				this.setResizable(true);
				this.setLayout(new BorderLayout());
//				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.setPreferredSize(new Dimension(170,112));

				
	
			textumfeld=new TextArea();

//			this.setBackground(new Color(111,111,111));
			this.add(textumfeld,"Center");
			textumfeld.setFont(new Font("Arial", Font.PLAIN,11));
			textumfeld.setEditable(false);
//			textumfeld.setSize(150,90);
//			textumfeld.setBackground(new Color(111,111,111));
			
			
			Panel pane= new Panel();
			pane.setLayout(new BorderLayout());
			
			textfeld= new TextField(10);
			textfeld.addKeyListener(this);
			textfeld.setFont(new Font("Arial", Font.PLAIN,11));
//			textfeld.setBackground(new Color(111,111,111));
			pane.add(textfeld,BorderLayout.CENTER);
//			pane.add(textfeld);
//			this.add(textfeld);
//			pane.setBackground(new Color(111,111,111));
			senden= new Button("senden");
			senden.addMouseListener(this);
			senden.addActionListener(this);
			loeschen= new Button("löschen");
			loeschen.addMouseListener(this);
			loeschen.addActionListener(this);
//			pane.add(senden);
//			pane.add(loeschen);
			this.add(pane,"South");
			this.setVisible(true);
				
				textfeld.setForeground(Color.GRAY);
				 textfeld.setText("Cheats/ Text eingeben");
				 textfeld.setEditable(true);
			
				 textfeld.requestFocus();
			
			//this.add(m);
			
			

	}
	//		
	
	
	
	
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
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
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
			
<<<<<<< HEAD
=======
			
>>>>>>> branch 'master' of https://github.com/HindiBones/project.git
			textumfeld.append(Text+"\n");
			client.sende(new ChatNachricht(Text));
			textfeld.setText(null);
			
			textumfeld.requestFocusInWindow();
//			textumfeld.setCaretPosition(i);
			
			this.disable();
		}
	}
//	public static void main (String [] args){
//		ChatFenster c= new ChatFenster("Chat");
//		
//		
//		
//	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		
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
		// TODO Auto-generated method stub
	}	
		

		
	
	

}
