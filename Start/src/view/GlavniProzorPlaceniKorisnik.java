package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedList;

import javax.swing.JFrame;

import controler.Kontroler;
import exception.NepostojeciKorisnikException;
import model.Korisnik;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class GlavniProzorPlaceniKorisnik extends GlavniProzor {
	private JButton btnProfil;
	private JComboBox comboBox;

	 

	/**
	 * Create the frame.
	 */
	public GlavniProzorPlaceniKorisnik(Kontroler kontrolor) {
		super(kontrolor);
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panelEast.add(getBtnProfil());
		panelEast.add(getComboBox());

	}

	private JButton getBtnProfil() {
		if (btnProfil == null) {
			btnProfil = new JButton("Profil");
			btnProfil.setBounds(10, 229, 91, 23);
			btnProfil.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					kontrolor.predjiNaProfil();
					
				}
			});
		}
		return btnProfil;
	}
	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setBounds(10, 128, 86, 22);
			LinkedList<Korisnik> korisnici=kontrolor.dajListuKorisnika();
			for (int i = 0; i < korisnici.size(); i++) {
				comboBox.addItem(korisnici.get(i).getKorisnickoIme());
			}
			comboBox.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					String korisnickoIme=(String) comboBox.getSelectedItem();
					try {
						textArea.setText(kontrolor.listaAutomobila(korisnickoIme));
					} catch (NepostojeciKorisnikException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return comboBox;
	}
	protected JButton getBtnDodajAuto() {
		if (btnDodajAuto == null) {
			btnDodajAuto = new JButton("Dodaj auto");
			btnDodajAuto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String[] podaci=pokupiPodatke();
					String model=podaci[0];
					int konjskaSnaga=Integer.parseInt(podaci[1]);
					String korisnickoIme=podaci[2];
					try {
						kontrolor.dodajAuto(model,konjskaSnaga,korisnickoIme);
						textArea.setText(kontrolor.listaAutomobila(korisnickoIme));
					} catch (NepostojeciKorisnikException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnDodajAuto.setBounds(10, 161, 91, 23);
		}
		return btnDodajAuto;
	}
	protected String[] pokupiPodatke(){
		String[] polja=super.pokupiPodatke();
		String korisnickoIme=(String) comboBox.getSelectedItem();
		String[] podaci=new String[polja.length+1];
		for (int i = 0; i < polja.length; i++) {
			podaci[i]=polja[i];
		}
		podaci[podaci.length-1]= korisnickoIme;
		return podaci;
	}
	public void primiObavestenje(String tekst){
		if(tekst.startsWith("K;"))
			comboBox.addItem(tekst.substring(2));
		else {
			String korisnik=(String) comboBox.getSelectedItem();
			try {
				textArea.setText(kontrolor.listaAutomobila(korisnik));
			} catch (NepostojeciKorisnikException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
