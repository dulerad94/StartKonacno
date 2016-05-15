package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import controler.Kontroler;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Profil extends OsnovniProzor {
	private JTextField txtIme;
	private JLabel lblKorisnickoIme;
	private JLabel lblSifra;
	private JTextField txtSifra;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JButton btnVratiSe;

	

	/**
	 * Create the frame.
	 */
	public Profil(Kontroler kontrolor) {
		super(kontrolor);
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().add(getTxtIme());
		getContentPane().add(getLblKorisnickoIme());
		getContentPane().add(getLblSifra());
		getContentPane().add(getTxtSifra());
		getContentPane().add(getScrollPane());
		getContentPane().add(getBtnVratiSe());
		postaviPodatke();

	}
	private void postaviPodatke(){
		String[] podaciOKorisniku=kontrolor.dajPodatkeOKorisniku();
		txtIme.setText(podaciOKorisniku[0]);
		txtSifra.setText(podaciOKorisniku[1]);
		textArea.setText(podaciOKorisniku[2]);
	}
	private JTextField getTxtIme() {
		if (txtIme == null) {
			txtIme = new JTextField();
			txtIme.setBounds(161, 38, 86, 20);
			txtIme.setColumns(10);
		}
		return txtIme;
	}
	private JLabel getLblKorisnickoIme() {
		if (lblKorisnickoIme == null) {
			lblKorisnickoIme = new JLabel("Korisnicko ime:");
			lblKorisnickoIme.setBounds(161, 11, 85, 14);
		}
		return lblKorisnickoIme;
	}
	private JLabel getLblSifra() {
		if (lblSifra == null) {
			lblSifra = new JLabel("Sifra:");
			lblSifra.setBounds(161, 76, 46, 14);
		}
		return lblSifra;
	}
	private JTextField getTxtSifra() {
		if (txtSifra == null) {
			txtSifra = new JTextField();
			txtSifra.setBounds(161, 103, 86, 20);
			txtSifra.setColumns(10);
		}
		return txtSifra;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 21, 118, 102);
			scrollPane.setViewportView(getTextArea());
		}
		return scrollPane;
	}
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
		}
		return textArea;
	}
	private JButton getBtnVratiSe() {
		if (btnVratiSe == null) {
			btnVratiSe = new JButton("Vrati se");
			btnVratiSe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					kontrolor.predjiNaGlavniProzor();
				}
			});
			btnVratiSe.setBounds(85, 157, 91, 23);
		}
		return btnVratiSe;
	}
}
