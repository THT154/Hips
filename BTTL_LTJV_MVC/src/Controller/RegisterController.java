package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Model.UserModel;
import View.Login;
import View.Register;

public class RegisterController implements ActionListener {
	private Register register;

	public RegisterController(Register register) {
		this.register = register;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		UserModel model = new UserModel();
		AddAcountToFile();
		Reset();
	}

	private void Reset() {
		register.jtRegister_user.setText("");
		register.jtRegister_pass.setText("");
		register.jtRegister_name.setText("");
		register.jtRegister_phone.setText("");
	}

	private void AddAcountToFile() {

		try (BufferedWriter writer = new BufferedWriter(new FileWriter("Account.txt", true))) {
			writer.write(register.jtRegister_user.getText());
			writer.write(",");
			String password = new String(register.jtRegister_pass.getPassword());
			writer.write(password);
			writer.write(",");
			writer.write(register.jtRegister_name.getText());
			writer.write(",");
			writer.write(register.jtRegister_phone.getText());
			writer.write("\n");
			JOptionPane.showMessageDialog(register, "Đăng ký thành công");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
