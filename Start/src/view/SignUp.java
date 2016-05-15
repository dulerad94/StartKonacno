package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controler.Kontroler;
import exception.LosaSifraException;
import exception.PostojeciKorisnikException;

public class SignUp extends OsnovniProzor {

	private JPanel contentPane;
	private JLabel lblKorisnickoIme;
	private JTextField txtKorisnickoIme;
	private JLabel lblSifra;
	private JPasswordField passSifra;
	private JLabel lblPonovljenaSifra;
	private JPasswordField passPonovljenaSifra;
	private JComboBox comboBox;
	private JLabel lblTip;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JButton btnRegistrujSe;
	private JButton btnVratiSe;

	

	/**
	 * Create the frame.
	 */
	public SignUp(Kontroler kontrolor) {
		super(kontrolor);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblKorisnickoIme());
		contentPane.add(getTxtKorisnickoIme());
		contentPane.add(getLblSifra());
		contentPane.add(getPassSifra());
		contentPane.add(getLblPonovljenaSifra());
		contentPane.add(getPassPonovljenaSifra());
		contentPane.add(getComboBox());
		contentPane.add(getLblTip());
		contentPane.add(getScrollPane());
		contentPane.add(getBtnRegistrujSe());
		contentPane.add(getBtnVratiSe());
		scrollPane.setVisible(false);;
	}

	private JLabel getLblKorisnickoIme() {
		if (lblKorisnickoIme == null) {
			lblKorisnickoIme = new JLabel("Korisnicko ime:");
			lblKorisnickoIme.setBounds(60, 61, 97, 14);
		}
		return lblKorisnickoIme;
	}
	private JTextField getTxtKorisnickoIme() {
		if (txtKorisnickoIme == null) {
			txtKorisnickoIme = new JTextField();
			txtKorisnickoIme.setBounds(182, 58, 86, 20);
			txtKorisnickoIme.setColumns(10);
		}
		return txtKorisnickoIme;
	}
	private JLabel getLblSifra() {
		if (lblSifra == null) {
			lblSifra = new JLabel("Sifra:");
			lblSifra.setBounds(60, 90, 61, 14);
		}
		return lblSifra;
	}
	private JPasswordField getPassSifra() {
		if (passSifra == null) {
			passSifra = new JPasswordField();
			passSifra.setBounds(182, 87, 86, 20);
		}
		return passSifra;
	}
	private JLabel getLblPonovljenaSifra() {
		if (lblPonovljenaSifra == null) {
			lblPonovljenaSifra = new JLabel("Ponovljena sifra:");
			lblPonovljenaSifra.setBounds(58, 120, 86, 14);
		}
		return lblPonovljenaSifra;
	}
	private JPasswordField getPassPonovljenaSifra() {
		if (passPonovljenaSifra == null) {
			passPonovljenaSifra = new JPasswordField();
			passPonovljenaSifra.setBounds(182, 117, 86, 20);
		}
		return passPonovljenaSifra;
	}
	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"ne placa", "placa"}));
			comboBox.setBounds(182, 148, 86, 22);
			comboBox.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					String tip=(String) comboBox.getSelectedItem();
					if(tip.equals("placa")){
						textArea.setText("");
						scrollPane.setVisible(true);
					}else {
						textArea.setText("");
						scrollPane.setVisible(false);
					}
					
				}
			});
		}
		return comboBox;
	}
	private JLabel getLblTip() {
		if (lblTip == null) {
			lblTip = new JLabel("Tip");
			lblTip.setBounds(60, 152, 46, 14);
		}
		return lblTip;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(60, 187, 212, 58);
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
	private JButton getBtnRegistrujSe() {
		if (btnRegistrujSe == null) {
			btnRegistrujSe = new JButton("Registruj se");
			btnRegistrujSe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String[] podaci=pokupiPodatke();
					String korisnickoIme=podaci[0];
					String sifra=podaci[1];
					String ponovljenaSifra=podaci[2];
					String biografija=podaci[3];
					String tip=podaci[4];
					try {
	
						kontrolor.registrujSe(korisnickoIme,sifra,ponovljenaSifra,biografija,tip);
						
						JOptionPane.showMessageDialog(contentPane, "Uspeno ste se registrovali","Start",JOptionPane.INFORMATION_MESSAGE);
					} catch (LosaSifraException e1) {
						// TODO Auto-generated catch block
						izbaciGresku(e1.getMessage());
					} catch (PostojeciKorisnikException e1) {
						// TODO Auto-generated catch block
						izbaciGresku(e1.getMessage());
					}
				}
			});
			btnRegistrujSe.setBounds(302, 57, 91, 23);
		}
		return btnRegistrujSe;
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
			btnVratiSe.setBounds(302, 100, 91, 23);
		}
		return btnVratiSe;
	}
	private String[] pokupiPodatke(){
		String ime=txtKorisnickoIme.getText();
		char[] sifraC=passSifra.getPassword();
		String sifra="";
		for (int i = 0; i < sifraC.length; i++) {
			sifra+=sifraC[i];
		}
		char[] ponovljenaSifraC=passPonovljenaSifra.getPassword();
		String ponovljenaSifra="";
		for (int i = 0; i < ponovljenaSifraC.length; i++) {
			ponovljenaSifra+=ponovljenaSifraC[i];
		}
		String biografija=textArea.getText();
		String tip=(String) comboBox.getSelectedItem();
		return new String[]{ime,sifra,ponovljenaSifra,biografija,tip};
	}
}
