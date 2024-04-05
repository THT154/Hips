package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controller.RegisterController;

public class Register extends JFrame {
	public JButton jb_register;
	private Font font_name = new Font("Time new Roman", Font.BOLD, 40);
	public JPanel jp2 = new JPanel();
	
	public JTextField jtRegister_user = new JTextField();
	public JPasswordField jtRegister_pass = new JPasswordField();
	public JTextField jtRegister_name = new JTextField();
	public JTextField jtRegister_phone = new JTextField();

	private KhungLogin khung;

	public Register(KhungLogin khung) {
		this.khung = khung;
		Register();
	}

	private void Register() {

		// Panel chứa chữ Login
		JPanel jp_register = new JPanel();
		jp_register.setLayout(new FlowLayout());
		JLabel jl_register = new JLabel("Register");

		jl_register.setFont(font_name);
		jp_register.add(new Label());
		jp_register.add(jl_register);
		jp_register.add(new JLabel());

		// Panel chứa JLabel và Jtextfield để nhập

		JPanel jp_nhap = new JPanel();

		JLabel jl_user = new JLabel("Tài khoản             ");
		JLabel jl_pass = new JLabel("Mật khẩu             ");
		JLabel jl_name = new JLabel("Tên người dùng: ");
		JLabel jl_phone = new JLabel("Số điện thoại:      ");

		

		JPanel jp_user = new JPanel();
		jp_user.setLayout(new FlowLayout());
		jp_user.add(jl_user);
		jp_user.add(jtRegister_user);

		JPanel jp_pass = new JPanel();
		jp_pass.setLayout(new FlowLayout());
		jp_pass.add(jl_pass);
		jp_pass.add(jtRegister_pass);

		// Set kích thức cho JLabel và JTextfield
		jtRegister_user.setColumns(20);
		jtRegister_pass.setColumns(20);

		jl_user.setFont(new Font("Times New Roman", Font.BOLD, 23));
		jl_pass.setFont(new Font("Times New Roman", Font.BOLD, 23));

		jp_nhap.setLayout(new GridLayout(2, 1));
		jp_nhap.add(jp_user);
		jp_nhap.add(jp_pass);

		JPanel jp_nhap2 = new JPanel();

		JPanel jp_name = new JPanel();
		jp_name.setLayout(new FlowLayout());
		jp_name.add(jl_name);
		jp_name.add(jtRegister_name);

		JPanel jp_phone = new JPanel();
		jp_phone.setLayout(new FlowLayout());
		jp_phone.add(jl_phone);
		jp_phone.add(jtRegister_phone);

		jp_nhap2.add(jp_name);
		jp_nhap2.add(jp_phone);

		jtRegister_name.setColumns(20);
		jtRegister_phone.setColumns(20);

		jl_name.setFont(new Font("Times New Roman", Font.BOLD, 23));
		jl_phone.setFont(new Font("Times New Roman", Font.BOLD, 23));

		// Panel chứa Button Login
		JPanel jp_button = new JPanel();
		jb_register = new JButton("Đăng ký");
		jb_register.addActionListener(new RegisterController(this));
		jb_register.setPreferredSize(new Dimension(135, 35));
		jp_button.add(new JLabel());
		jp_button.add(new JLabel());
		jp_button.add(jb_register);

		// Phần Quên mật khẩu
		JLabel jl_dn = new JLabel("Đăng nhập");

		jl_dn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				khung.frame_login.dispose();
				Login login = new Login(new KhungLogin());
				

			}
		});

		EmptyBorder kc_qmk = new EmptyBorder(0, 230, 0, 0);
		jl_dn.setBorder(kc_qmk);

		// Thêm phần nhập dữ liệu vào jp2
		jp2.setLayout(new GridLayout(6, 1));
		jp2.add(new JLabel());
		jp2.add(jp_register);
		jp2.add(jp_nhap);
		jp2.add(jp_nhap2);
		jp2.add(jp_button);
		jp2.add(jl_dn);

		khung.frame_login.add(jp2, BorderLayout.CENTER);

	}
}
