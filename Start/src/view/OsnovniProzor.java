package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controler.Kontroler;

public class OsnovniProzor extends JFrame {

	private JPanel contentPane;
	protected Kontroler kontrolor;
	

	/**
	 * Create the frame.
	 */
	public OsnovniProzor(Kontroler kontrolor) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.kontrolor=kontrolor;
	}
	
	protected void izbaciGresku(String poruka){
		JOptionPane.showMessageDialog(contentPane,
				poruka, "START",
				JOptionPane.ERROR_MESSAGE);
	}
	public void primiObavestenje(String tekst){
	}

}
