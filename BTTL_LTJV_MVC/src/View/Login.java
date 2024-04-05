package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import Controller.LoginController;
import Model.UserModel;

public class Login extends JFrame {
	private Font font_name = new Font("Time new Roman", Font.BOLD, 40);

	public JButton jb_login;
	public JButton jb_register;

	public JPanel jp2 = new JPanel();
	public JTextField jt_user = new JTextField();
	public JPasswordField jt_pass = new JPasswordField();
	public JLabel jl_check = new JLabel("");
	public JCheckBox jcb = new JCheckBox("Ghi nhớ đăng nhập");

	private KhungLogin khung;

	public Login(KhungLogin khung) {
		this.khung = khung;
		addToMainFrame();

	}

	public void addToMainFrame() {

		// Panel chứa chữ Login
		JPanel jp_login = new JPanel();
		jp_login.setLayout(new FlowLayout());
		JLabel jl_login = new JLabel("Login");

		jl_login.setFont(font_name);
		jp_login.add(new Label());
		jp_login.add(jl_login);
		jp_login.add(new JLabel());

		// Panel chứa JLabel và Jtextfield để nhập

		JPanel jp_nhap = new JPanel();

		JLabel jl_user = new JLabel("Tài khoản");
		JLabel jl_pass = new JLabel("Mật khẩu ");

		Font font = new Font("Time New Roman", Font.ITALIC, 12);
		jl_check.setFont(font);
		jl_check.setForeground(Color.red);

		JPanel jp_user = new JPanel();
		jp_user.setLayout(new FlowLayout());
		jp_user.add(jl_user);
		jp_user.add(jt_user);

		JPanel jp_pass = new JPanel();
		jp_pass.setLayout(new FlowLayout());
		jp_pass.add(jl_pass);
		jp_pass.add(jt_pass);

		JPanel jp_save = new JPanel();
		jp_save.add(jcb);

		// Set kích thức cho JLabel và JTextfield
		jt_user.setColumns(20);
		jt_pass.setColumns(20);
		jl_user.setFont(new Font("Times New Roman", Font.BOLD, 23));
		jl_pass.setFont(new Font("Times New Roman", Font.BOLD, 23));

		jp_nhap.setLayout(new GridLayout(3, 1));
		jp_nhap.add(jp_user);
		jp_nhap.add(jp_pass);
		jp_nhap.add(jp_save);

		JPanel jp_nhap2 = new JPanel();

		JPanel jp_check = new JPanel();
		jp_check.setLayout(new GridLayout(3, 1));
		jp_check.add(new JLabel());
		jp_check.add(jl_check);
		jp_check.add(new JLabel());

		jp_nhap2.add(jp_check);

		// Panel chứa Button Login
		JPanel jp_button = new JPanel();
		jb_login = new JButton("Đăng nhập");
		LoginController controller = new LoginController(this, khung);
		jb_login.addActionListener(controller);

		jb_login.setPreferredSize(new Dimension(135, 35));
		jp_button.add(new JLabel());
		jp_button.add(new JLabel());
		jp_button.add(jb_login);

		// Phần Đăng ký
		JLabel jl_register = new JLabel("Bạn chưa có tài khoản?");
		jl_register.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				khung.frame_login.dispose();
				Register register = new Register(new KhungLogin());

			}
		});

		EmptyBorder kc_register = new EmptyBorder(0, 180, 0, 0);
		jl_register.setBorder(kc_register);

		// Thêm phần nhập dữ liệu vào jp2
		jp2.setLayout(new GridLayout(6, 1));
		jp2.add(new JLabel());
		jp2.add(jp_login);
		jp2.add(jp_nhap);
		jp2.add(jp_nhap2);
		jp2.add(jp_button);
		jp2.add(jl_register);

		khung.frame_login.add(jp2, BorderLayout.CENTER);
		
		controller.Loading_History();

	}

}
