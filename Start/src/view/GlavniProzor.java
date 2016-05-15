package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import controler.Kontroler;
import exception.NepostojeciKorisnikException;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GlavniProzor extends OsnovniProzor {
	
	
	protected JPanel panelEast;
	protected JScrollPane scrollPane;
	protected JTextArea textArea;
	protected JTextField txtModel;
	protected JLabel lblModel;
	protected JLabel lblKonjskaSnaga;
	protected JTextField txtKonjskaSnaga;
	protected JButton btnDodajAuto;
	protected JButton btnIzlogujSe;



	/**
	 * Create the frame.
	 */
	public GlavniProzor(Kontroler kontrolor) {
		super(kontrolor);
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(getPanelEast(), BorderLayout.EAST);
		getContentPane().add(getScrollPane(), BorderLayout.CENTER);

	}

	protected JPanel getPanelEast() {
		if (panelEast == null) {
			panelEast = new JPanel();
			panelEast.setPreferredSize(new Dimension(150, 10));
			panelEast.setLayout(null);
			panelEast.add(getTxtModel());
			panelEast.add(getLblModel());
			panelEast.add(getLblKonjskaSnaga());
			panelEast.add(getTxtKonjskaSnaga());
			panelEast.add(getBtnDodajAuto());
			panelEast.add(getBtnIzlogujSe());
		}
		return panelEast;
	}
	protected JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTextArea());
		}
		return scrollPane;
	}
	protected JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			try {
				textArea.setText(kontrolor.listaAutomobila());
			} catch (NepostojeciKorisnikException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return textArea;
	}
	protected JTextField getTxtModel() {
		if (txtModel == null) {
			txtModel = new JTextField();
			txtModel.setBounds(10, 41, 86, 20);
			txtModel.setColumns(10);
		}
		return txtModel;
	}
	protected JLabel getLblModel() {
		if (lblModel == null) {
			lblModel = new JLabel("Model:");
			lblModel.setBounds(10, 16, 46, 14);
		}
		return lblModel;
	}
	protected JLabel getLblKonjskaSnaga() {
		if (lblKonjskaSnaga == null) {
			lblKonjskaSnaga = new JLabel("Konjska snaga:");
			lblKonjskaSnaga.setBounds(10, 72, 86, 14);
		}
		return lblKonjskaSnaga;
	}
	protected JTextField getTxtKonjskaSnaga() {
		if (txtKonjskaSnaga == null) {
			txtKonjskaSnaga = new JTextField();
			txtKonjskaSnaga.setBounds(10, 97, 86, 20);
			txtKonjskaSnaga.setColumns(10);
		}
		return txtKonjskaSnaga;
	}
	protected JButton getBtnDodajAuto() {
		if (btnDodajAuto == null) {
			btnDodajAuto = new JButton("Dodaj auto");
			btnDodajAuto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String[] podaci=pokupiPodatke();
					String model=podaci[0];
					int konjskaSnaga=Integer.parseInt(podaci[1]);
					try {
						kontrolor.dodajAuto(model,konjskaSnaga);
						textArea.setText(kontrolor.listaAutomobila());
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
	protected JButton getBtnIzlogujSe() {
		if (btnIzlogujSe == null) {
			btnIzlogujSe = new JButton("Izloguj se");
			btnIzlogujSe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					kontrolor.izlogujSe();
				}
			});
			btnIzlogujSe.setBounds(10, 195, 91, 23);
		}
		return btnIzlogujSe;
	}
	protected String[] pokupiPodatke(){
		String model=txtModel.getText();
		String konjskaSnaga=txtKonjskaSnaga.getText();
		return new String[]{model,konjskaSnaga};
	}
	public void primiObavestenje(String tekst){
		if(tekst.startsWith("K;")) return;
		try {
			textArea.setText(kontrolor.listaAutomobila());
		} catch (NepostojeciKorisnikException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
