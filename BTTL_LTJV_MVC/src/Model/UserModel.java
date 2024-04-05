package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;


import View.Home;
import View.KhungLogin;
import View.Login;

public class UserModel {
	public String user;
	public String passwork;
	private Login login;
	private KhungLogin khung;

	public UserModel() {

	}

	public UserModel(Login login) {
		this.login = login;
	}

	public UserModel(String user, String passwork) {
		this.user = user;
		this.passwork = passwork;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPasswork() {
		return passwork;
	}

	public void setPasswork(String passwork) {
		this.passwork = passwork;
	}

	

}
