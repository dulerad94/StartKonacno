package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import controler.Kontroler;
import exception.LosaSifraException;
import exception.NepostojeciKorisnikException;
import model.Korisnik;

public class LogIn extends OsnovniProzor {

	private JPanel contentPane;
	private JLabel lblKorisnickoIme;
	private JLabel lblSifra;
	private JComboBox comboBox;
	private JPasswordField passwordField;
	private JButton btnUlogujSe;
	private JButton btnVratiSe;


	/**
	 * Create the frame.
	 */
	public LogIn(Kontroler kontrolor) {
		super(kontrolor);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblKorisnickoIme());
		contentPane.add(getLblSifra());
		contentPane.add(getComboBox());
		contentPane.add(getPasswordField());
		contentPane.add(getBtnUlogujSe());
		contentPane.add(getBtnVratiSe());
	}
	private JLabel getLblKorisnickoIme() {
		if (lblKorisnickoIme == null) {
			lblKorisnickoIme = new JLabel("Korisnicko ime:");
			lblKorisnickoIme.setBounds(77, 63, 94, 14);
		}
		return lblKorisnickoIme;
	}
	private JLabel getLblSifra() {
		if (lblSifra == null) {
			lblSifra = new JLabel("Sifra:");
			lblSifra.setBounds(77, 88, 46, 14);
		}
		return lblSifra;
	}
	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setBounds(166, 59, 94, 22);
			LinkedList<Korisnik> korisnici=kontrolor.dajListuKorisnika();
			for (int i = 0; i < korisnici.size(); i++) {
				comboBox.addItem(korisnici.get(i).getKorisnickoIme());
			}
		}
		return comboBox;
	}
	private JPasswordField getPasswordField() {
		if (passwordField == null) {
			passwordField = new JPasswordField();
			passwordField.setBounds(166, 88, 94, 20);
		}
		return passwordField;
	}
	private JButton getBtnUlogujSe() {
		if (btnUlogujSe == null) {
			btnUlogujSe = new JButton("Uloguj se");
			btnUlogujSe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String[] podaci=pokupiPodatke();
					String ime=podaci[0];
					String sifra=podaci[1];
	
					try {
						kontrolor.ulogujSe(ime,sifra);
						setVisible(false);
					} catch (LosaSifraException e1) {
						izbaciGresku(e1.getMessage());
					} catch (NepostojeciKorisnikException e1) {
						// TODO Auto-generated catch block
						izbaciGresku(e1.getMessage());
					}
					
				}
			});
			btnUlogujSe.setBounds(102, 147, 91, 23);
		}
		return btnUlogujSe;
	}
	private JButton getBtnVratiSe() {
		if (btnVratiSe == null) {
			btnVratiSe = new JButton("Vrati se");
			btnVratiSe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					kontrolor.predjiNaLogInSignUp();
				}
			});
			btnVratiSe.setBounds(102, 194, 91, 23);
		}
		return btnVratiSe;
	}
	private String[] pokupiPodatke(){
		String ime=(String) comboBox.getSelectedItem();
		char[] sifraC=passwordField.getPassword();
		String sifra="";
		for (int i = 0; i < sifraC.length; i++) {
			sifra+=sifraC[i];
		}
		return new String[]{ime,sifra};
	}
	public void primiObavestenje(String tekst){
		if(tekst.startsWith("K;")){
			comboBox.addItem(tekst.substring(2));
		}
	}
}
