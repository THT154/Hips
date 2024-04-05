package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import View.Home;

public class HomeController implements ActionListener {

	private Home home;

	public HomeController(Home home) {
		this.home = home;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == home.jbAdd) {
			AddStudentToFile();
			FileToTable();
		}
		if (e.getSource() == home.jbUpdate) {
			UpdateStudentToFile();
			FileToTable();
		}
		if (e.getSource() == home.jbRemove) {
			RemoveAtFile();
			FileToTable();
		}
	}

	private void AddStudentToFile() {
		String newID = home.masinhvien.getText();

		try (BufferedReader reader = new BufferedReader(new FileReader("Student.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts.length > 0 && parts[0].equals(newID)) {
					JOptionPane.showMessageDialog(home.frame, "Thêm sinh viên không hợp lệ do ID đã tồn tại");
					return; // Ngăn việc thêm sinh viên nếu ID đã tồn tại
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter("Student.txt", true))) {
			writer.write(newID);
			writer.write(",");
			writer.write(home.ten.getText());
			writer.write(",");
			writer.write(home.ngaysinh.getText());
			writer.write(",");
			String gender = home.jck_male.isSelected() ? "Nam" : "Nữ";
			writer.write(gender);
			writer.write(",");
			writer.write(home.lop.getText());
			writer.write("\n");
			JOptionPane.showMessageDialog(home.frame, "Thêm sinh viên thành công");

			Reset();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void UpdateStudentToFile() {
		String newID = home.masinhvien.getText();
		List<String> lines = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader("Student.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts.length > 0 && parts[0].equals(newID)) {
					String updatedLine = newID + "," + home.ten.getText() + "," + home.ngaysinh.getText() + ",";
					String gender = home.jck_male.isSelected() ? "Nam" : "Nữ";
					updatedLine += gender + "," + home.lop.getText();
					lines.add(updatedLine);
					FileToTable();
				} else {
					lines.add(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Ghi lại toàn bộ dữ liệu từ danh sách vào tệp
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("Student.txt"))) {
			for (String line : lines) {
				writer.write(line);
				writer.newLine(); // Thêm dấu xuống dòng sau mỗi dòng dữ liệu
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		JOptionPane.showMessageDialog(home.frame, "Cập nhật thông tin sinh viên thành công");
		Reset();
	}

	private void RemoveAtFile() {
		String newID = home.masinhvien.getText();
		List<String> lines = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader("Student.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts.length > 0 && parts[0].equals(newID)) {
					continue;
				} else {
					lines.add(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Ghi lại toàn bộ dữ liệu từ danh sách vào tệp
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("Student.txt"))) {
			for (String line : lines) {
				writer.write(line);
				writer.newLine(); // Thêm dấu xuống dòng sau mỗi dòng dữ liệu
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		JOptionPane.showMessageDialog(home.frame, "Xóa thông tin sinh viên thành công");
		Reset();
	}

	public void FileToTable() {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("Student.txt"));
			String line;
			int lineNumber = 0;
			List<Object[]> dataList = new ArrayList<>(); // Tạo một danh sách để lưu trữ dữ liệu
			while ((line = reader.readLine()) != null) {
				lineNumber++;
				String[] parts = line.split(",");
				if (parts.length == 5) { // Sửa parts.length từ 4 thành 5
					String ID = parts[0].trim();
					String name = parts[1].trim();
					String ngaysinh = parts[2].trim();
					String gender = parts[3].trim();
					String lop = parts[4].trim();
					Object[] rowData = { ID, name, ngaysinh, gender, lop };
					dataList.add(rowData); // Thêm mảng này vào danh sách
				} else {
					System.out.println("Lỗi ở dòng " + lineNumber + ": Dòng dữ liệu không đúng định dạng.");
					System.out.println("Dòng lỗi: " + line);
				}
			}
			// Chuyển danh sách sang mảng hai chiều
			Object[][] dataArray = new Object[dataList.size()][5];
			for (int i = 0; i < dataList.size(); i++) {
				dataArray[i] = dataList.get(i);
			}
			// Gán mảng hai chiều cho biến data của lớp Home
			home.data = dataArray;
			// Cập nhật dữ liệu trên bảng
			home.jtable.setModel(new DefaultTableModel(home.data, home.columnNames));
			home.jtable.getSelectionModel().addListSelectionListener(e -> LayDuLieu());
		} catch (IOException e) {
			System.out.println("Đã xảy ra lỗi khi đọc dữ liệu từ tệp Student.txt.");
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void Reset() {
		home.masinhvien.setText("");
		home.ten.setText("");
		home.lop.setText("");
		home.ngaysinh.setText("");
		home.jck_female.setSelected(false);
		home.jck_male.setSelected(false);
	}

	private void LayDuLieu() {
		int selectedRow = home.jtable.getSelectedRow();
		if (selectedRow != -1) {
			home.masinhvien.setText((String) home.jtable.getValueAt(selectedRow, 0));
			home.masinhvien.setEditable(false);
			home.ten.setText((String) home.jtable.getValueAt(selectedRow, 1));
			home.ngaysinh.setText((String) home.jtable.getValueAt(selectedRow, 2));
			String bn = (String) home.jtable.getValueAt(selectedRow, 3);
			home.jck_male.setSelected("Nam".equals(bn));
			home.jck_female.setSelected("Nữ".equals(bn));
			home.lop.setText((String) home.jtable.getValueAt(selectedRow, 4));

		}
	}

	public class GenderItemListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == home.jck_male && home.jck_male.isSelected()) {
				home.jck_female.setSelected(false);
			}
			if (e.getSource() == home.jck_female && home.jck_female.isSelected()) {
				home.jck_male.setSelected(false);
			}
		}
	}
}
