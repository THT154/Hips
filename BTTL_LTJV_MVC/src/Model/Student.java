package Model;

public class Student {
	private String ID;
	private String name;
	private int age;
	private String gender;
	private String classs;

	public Student(String iD, String name, int age, String gender, String classs) {
		ID = iD;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.classs = classs;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getClasss() {
		return classs;
	}

	public void setClasss(String classs) {
		this.classs = classs;
	}

}
