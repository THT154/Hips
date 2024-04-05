package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class KhungLogin extends JFrame {
	public JFrame frame_login = new JFrame();

	private Font font_name = new Font("Time new Roman", Font.BOLD, 40);
	private ImageIcon icon = new ImageIcon("logo.png");
	private Image image = icon.getImage();

	public KhungLogin() {

		frame_login.setTitle("Đăng nhập");
		frame_login.setSize(800, 550);
		frame_login.setLocationRelativeTo(null);
		frame_login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel jp1 = new JPanel();

		// Tạo Icon và Tên bệnh viện
		JLabel jl_icon = new JLabel(icon);
		EmptyBorder kc_logo = new EmptyBorder(50, 10, 10, 10);
		jl_icon.setBorder(kc_logo);
		jl_icon.setBackground(Color.pink);
		jl_icon.setOpaque(true);

		JLabel jl_name = new JLabel("PINK DRAGON");
		jl_name.setFont(font_name);
		jl_name.setOpaque(true);
		jl_name.setBackground(Color.pink);
		MatteBorder kc_name = new MatteBorder(0, 5, 5, 5, Color.PINK);
		jl_name.setBorder(kc_name);

		// Thêm Icon và Tên bệnh viện
		JPanel jp_phu = new JPanel();
		jp_phu.setLayout(new BorderLayout());
		jp_phu.add(jl_icon, BorderLayout.CENTER);
		jp_phu.add(jl_name, BorderLayout.SOUTH);

		jp1.add(jp_phu);

		frame_login.add(jp1, BorderLayout.WEST);
		frame_login.setIconImage(image); // Set icon cho cửa sổ mở lên
		jp1.setBackground(Color.pink);

		frame_login.setVisible(true);

	}

	public JFrame getFrame() {
		return frame_login;
	}

}
