package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class ChatFenster extends JFrame implements WindowListener, MouseListener, KeyListener {

	private TextArea textumfeld=null;
	private TextField textfeld=null;
	private String benutzername= null;
	
	
	//***Julius*** Bitte anpassen:
	
	ChatFenster(String s) {
			this.addWindowListener(this);
				this.setSize(490,380);
				this.setResizable(true);
				this.setLayout(new BorderLayout());
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
	
			textumfeld=new TextArea();
			textumfeld.setEditable(false);
			this.add(textumfeld,"Center");
			textumfeld.setFont(new Font("Arial", Font.PLAIN,16));
			
			Panel pane= new Panel();
			pane.setLayout(new FlowLayout());
			
			textfeld= new TextField(30);
			textfeld.addKeyListener(this);
			textfeld.setFont(new Font("Arial", Font.PLAIN,16));
			
			pane.add(textfeld);
			pane.setBackground(new Color(222,221,221));
			Button senden= new Button("senden");
			senden.addMouseListener(this);
			Button löschen= new Button("löschen");
			löschen.addMouseListener(this);
			pane.add(senden);
			pane.add(löschen);
			this.add(pane,"South");
			this.setVisible(true);
			
			textfeld.requestFocus();
			
			

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

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args){
		ChatFenster c= new ChatFenster("Chat client");
	}
	

}
