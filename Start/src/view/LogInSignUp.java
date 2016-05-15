package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controler.Kontroler;

public class LogInSignUp extends OsnovniProzor {

	private JPanel contentPane;
	private JButton btnLogin;
	private JButton btnSignup;

	

	/**
	 * Create the frame.
	 */
	public LogInSignUp(Kontroler kontrolor) {
		super(kontrolor);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnLogin());
		contentPane.add(getBtnSignup());
	}
	private JButton getBtnLogin() {
		if (btnLogin == null) {
			btnLogin = new JButton("LogIn");
			btnLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					kontrolor.predjiNaLogIn();
				}
			});
			btnLogin.setBounds(155, 44, 91, 23);
		}
		return btnLogin;
	}
	private JButton getBtnSignup() {
		if (btnSignup == null) {
			btnSignup = new JButton("SignUp");
			btnSignup.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					kontrolor.predjiNaSignUp();
				}
			});
			btnSignup.setBounds(155, 97, 91, 23);
		}
		return btnSignup;
	}
}
