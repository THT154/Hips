package Main;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import Controller.LoginController;
import Model.UserModel;
import View.KhungLogin;
import View.Login;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				KhungLogin khung = new KhungLogin();
				Login login = new Login(khung);
				
			}
		});
	}
}