package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Controller.HomeController;
import Controller.HomeController.GenderItemListener;

public class Home {
	public JFrame frame = new JFrame("Quản lý sinh viên");

	public JPanel panel = new JPanel();
	public JPanel panel2 = new JPanel();
	public JPanel panel3 = new JPanel();
	public JPanel panel4 = new JPanel();
	public JPanel panel5 = new JPanel();

	public JTextField masinhvien;
	public JTextField ten;
	public JTextField ngaysinh;
	public JTextField lop;

	public JCheckBox jck_male;
	public JCheckBox jck_female;

	public JLabel jl_ID;
	public JLabel jl_name;
	public JLabel jl_age;
	public JLabel jl_gender;
	public JLabel jl_class;

	public JButton jbAdd;
	public JButton jbRemove;
	public JButton jbUpdate;

	public JTable jtable;
	public JScrollPane jsc;

	public String[] columnNames = { "Mã SV", "Tên", "Ngày sinh", "Giới tính", "Lớp" };
	public Object[][] data = {};

	private HomeController controller = new HomeController(this);

	public Home() {
		Management_Student();
		jck_male.addItemListener(controller.new GenderItemListener());
		jck_female.addItemListener(controller.new GenderItemListener());

	}

	private void Management_Student() {
		frame.setTitle("Quản lí học sinh");
		frame.setSize(700, 350);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jl_ID = new JLabel("Mã SV");
		jl_name = new JLabel("Tên");
		jl_age = new JLabel("Ngày sinh");
		jl_gender = new JLabel("Giới tính");
		jl_class = new JLabel("Lớp");

		masinhvien = new JTextField();
		ten = new JTextField();
		ngaysinh = new JTextField();
		lop = new JTextField();

		jck_male = new JCheckBox("Nam");
		jck_female = new JCheckBox("Nữ");

		jbAdd = new JButton("Thêm");
		jbRemove = new JButton("Xóa");
		jbUpdate = new JButton("Cập nhật");

		jbAdd.addActionListener(controller);
		jbRemove.addActionListener(controller);
		jbUpdate.addActionListener(controller);

		panel2.add(jck_male);
		panel2.add(jck_female);

		panel3.add(jbAdd);
		panel3.add(jbRemove);
		panel3.add(jbUpdate);

		panel.setLayout(new GridLayout(5, 2));

		panel.add(jl_ID);
		panel.add(masinhvien);
		panel.add(jl_name);
		panel.add(ten);
		panel.add(jl_age);
		panel.add(ngaysinh);
		panel.add(jl_gender);
		panel.add(panel2);
		panel.add(jl_class);
		panel.add(lop);

		panel4.setLayout(new GridLayout(2, 1));
		panel4.add(panel);
		panel4.add(panel3);

		// Tạo JTable và JScrollPane để chứa JTable

		jtable = new JTable(data, columnNames);
		jsc = new JScrollPane(jtable);

		panel5.add(jsc);

		frame.getContentPane().add(panel4, BorderLayout.WEST);
		frame.getContentPane().add(panel5, BorderLayout.EAST);
		frame.setVisible(true);
		
		controller.FileToTable();

	}

}
