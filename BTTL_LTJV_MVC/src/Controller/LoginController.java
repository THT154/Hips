package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Model.UserModel;
import View.Home;
import View.KhungLogin;
import View.Login;

public class LoginController implements ActionListener {
	private Login login;
	private KhungLogin khung;
	private UserModel User;
	private ArrayList<UserModel> users;

	public LoginController(Login login, KhungLogin khung) {
		this.login = login;
		this.khung = khung;
		this.users = new ArrayList<>();
		loadAccountsFromFile();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == login.jb_login) {
			String username = login.jt_user.getText();
			char[] password = login.jt_pass.getPassword();
			String pass = new String(password);

			History_Login();
			Check(username, pass, login.jl_check);
		}

	}

	public void loadAccountsFromFile() {
		Path path = Paths.get("Account.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts.length == 4) {
					String username = parts[0].trim();
					String password = parts[1].trim();
					UserModel user = new UserModel(username, password);
					users.add(user);
				}
			}
		} catch (IOException e) {
			// Xử lý lỗi cụ thể hoặc hiển thị thông báo lỗi cho người dùng
			e.printStackTrace();
		}
	}

	public void Check(String username, String password, JLabel jl_check) {
		if (username.isEmpty() || password.isEmpty()) {
			jl_check.setText("Tài khoản và mật khẩu không được để trống");
			return;
		}

		boolean isAuthenticated = false;
		for (UserModel user : users) {
			if (user.getUser().equals(username) && user.getPasswork().equals(password)) {
				isAuthenticated = true;
				break;
			}
		}

		if (isAuthenticated) {
			History_Login();
			khung.frame_login.dispose();
			Home home = new Home();

			// Điều hướng tới trang chính sau khi đăng nhập thành công
		} else {
			jl_check.setText("Tài khoản hoặc mật khẩu không chính xác");
		}
	}

	private void History_Login() {
		boolean isSelect = login.jcb.isSelected();
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("History_Login.txt"))) {
			if (isSelect) {
				writer.write(login.jt_user.getText());
				writer.write(",");
				// Chuyển đổi mảng ký tự thành chuỗi
				String password = new String(login.jt_pass.getPassword());
				writer.write(password);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void Loading_History() {
		BufferedReader reader = null; // Khai báo BufferedReader ngoài try để đảm bảo bạn có thể đóng nó ở finally
		try {
			reader = new BufferedReader(new FileReader("History_Login.txt"));
			String line;
			int lineNumber = 0;
			while ((line = reader.readLine()) != null) {
				lineNumber++;
				String[] parts = line.split(",");
				if (parts.length == 2) {
					String user = parts[0].trim();
					String pass = parts[1].trim();
					
					login.jt_user.setText(user);
					login.jt_pass.setText(pass);
					login.jcb.setSelected(true);
					
				} else {
					System.out.println("Lỗi ở dòng " + lineNumber + ": Dòng dữ liệu không đúng định dạng.");
					System.out.println("Dòng lỗi: " + line);
				}
			}
		} catch (IOException e) {
			System.out.println("Đã xảy ra lỗi khi đọc dữ liệu từ tệp History_Login.txt.");
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close(); // Đảm bảo rằng bạn đóng BufferedReader ở trong finally block
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}



}
