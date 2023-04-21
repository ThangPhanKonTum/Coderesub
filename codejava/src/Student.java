import java.io.FileOutputStream;

public class Student{
	int id;
	String name;
	int age;
	String addess;
	String gender;
 Student(int i, String n, int ag, String ad, String g) {
		id = i;
		name = n;
		age = ag;
		addess = ad;
	    gender=g;
	}
  
 Student(){
	 
 }
	void Download (FileOutputStream fout)
	{
		try {
			String s = id + ", " + name + ", " + age + ", " + addess + ", " + gender+ "\n";
			byte b[] = s.getBytes(); 
			fout.write(b); 
		} catch (Exception e) {
			System.out.println("\nErrol!!" + e);
		}
	}

}


