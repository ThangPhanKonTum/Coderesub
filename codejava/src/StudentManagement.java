import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class StudentManagement extends JFrame {

	private JPanel contentPane;
	private JTextField textId;
	private JTextField textName;
	private JTextField textAge;
	private JTextField textad;
	private JTextField textg;
	private JTable table;

	/**
	 * Launch the application.
	 */
	
	ArrayList<Student> L = new ArrayList<>();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 StudentManagement  frame = new  StudentManagement ();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public  StudentManagement () {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 588, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student Management");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 255, 0));
		lblNewLabel.setFont(new Font("Segoe UI Symbol", Font.BOLD, 22));
		lblNewLabel.setBounds(79, 11, 480, 65);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(45, 104, 100, 25);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Name:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(45, 139, 100, 25);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Age:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(45, 211, 100, 25);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Address:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(45, 175, 100, 25);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Gender");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(45, 244, 100, 25);
		contentPane.add(lblNewLabel_5);
		
		textId = new JTextField();
		textId.setColumns(10);
		textId.setBounds(124, 104, 227, 20);
		contentPane.add(textId);
		
		textName = new JTextField();
		textName.setColumns(10);
		textName.setBounds(124, 139, 227, 20);
		contentPane.add(textName);
		
		textAge = new JTextField();
		textAge.setColumns(10);
		textAge.setBounds(124, 215, 227, 20);
		contentPane.add(textAge);
		
		textad = new JTextField();
		textad.setColumns(10);
		textad.setBounds(124, 175, 227, 20);
		contentPane.add(textad);
		
		textg = new JTextField();
		textg.setColumns(10);
		textg.setBounds(124, 244, 227, 20);
		contentPane.add(textg);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 279, 549, 149);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table);
		DefaultTableModel model = new DefaultTableModel();
		Object[] column = {"ID", "Name", "Age", "Address", "Gender"};
		Object[] row = new Object[5];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		
		JButton btnDataDisplay = new JButton("Import Data From Device");
		btnDataDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Note file Successfully");
				L = new ArrayList<Student>();
				try {
					FileInputStream fin = new FileInputStream("Student.txt");
					InputStreamReader isr = new InputStreamReader(fin);
					BufferedReader br = new BufferedReader(isr);
					String line;
					while ((line = br.readLine())!=null) {
						String[] words = line.split(", ");
						row [0] = words[0];
						row [1] = words[1];
						row [2] = words[2];
						row [3] = words[3];
						row [4] = words[4];
						model.addRow(row);
						int id = Integer.parseInt(words[0]);
						int age = Integer.parseInt(words[2]);
					
						Student s = new Student(id, words[1], age, words[3], words[4]);
						L.add(s);
					}
				} catch (Exception e2) {
					System.out.println("Errol!!" + e2);
				}
			}
		});
		btnDataDisplay.setForeground(new Color(0, 128, 255));
		btnDataDisplay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDataDisplay.setBackground(new Color(255, 255, 255));
		btnDataDisplay.setBounds(361, 244, 198, 25);
		contentPane.add(btnDataDisplay);
		
		JButton btnSave = new JButton("Add");
		btnSave.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
				JOptionPane.showMessageDialog(null, "Save Successfully");
				int id, age;
				String name, ad;
				String g;
				id = Integer.parseInt(textId.getText());
				name = textName.getText();
				age = Integer.parseInt(textAge.getText());
				if(id<=0 || age <=0)
				{
					JOptionPane.showMessageDialog(null, "Errorvalue", null,  JOptionPane.INFORMATION_MESSAGE);
				}else {
				ad = textad.getText();
				g =textg.getText();
				Student S = new Student(id, name, age, ad, g);
				L.add(S);
				row[0] = id;
				row[1] = name;
				row[2] = age;
				row[3] = ad;
				row[4] = g;
				model.addRow(row);
				textad.setText("");
				textId.setText("");
				textName.setText("");
				textAge.setText("");
				textg.setText("");
				}
			}catch(Exception e3) {
			 JOptionPane.showMessageDialog(null, "Error",null,JOptionPane.INFORMATION_MESSAGE);
			}
		   }
		});
		btnSave.setForeground(new Color(0, 128, 255));
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSave.setBackground(new Color(255, 255, 255));
		btnSave.setBounds(387, 81, 117, 48);
		contentPane.add(btnSave);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(table.getSelectedRow() !=-1)
				{
					model.removeRow(table.getSelectedRow());
					JOptionPane.showMessageDialog(null, "Delete Successfully");
				}
			}
		});
		btnDelete.setForeground(new Color(0, 128, 255));
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setBounds(387, 209, 117, 25);
		contentPane.add(btnDelete);
		
		JButton btnDownload = new JButton("Save data");
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Save DataSuccessfully");
				try {
					FileOutputStream fout = new FileOutputStream("Student.txt", true);
					for (Student s : L)
						s.Download(fout);
				} catch (Exception e1) {
					System.out.println("Errol!! " + e1);
				}
			}
		});
		btnDownload.setForeground(new Color(0, 128, 255));
		btnDownload.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDownload.setBackground(new Color(255, 255, 255));
		btnDownload.setBounds(387, 174, 117, 25);
		contentPane.add(btnDownload);
		
		JButton btnFind = new JButton("Find");
		btnFind.setForeground(new Color(0, 128, 255));
		btnFind.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFind.setBackground(new Color(255, 255, 255));
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Find Successfully");
				String name = textName.getText();
				model.setDataVector(null, column);
				if (name.compareToIgnoreCase("") ==0)
				{
					if(textId.getText().compareTo("")==0) {
						JOptionPane.showMessageDialog(null, "Please enter your name or ID to find", null, JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						int id = Integer.parseInt(textId.getText());
						for (Student s:L)
							if (s.id == id) {
								row[0] = s.id;
								row[1] = s.name;
								row[2] = s.age;
								row[3] = s.addess;
								row[4] = s.gender;
								model.addRow(row);
							}
					}
				}
				else
				{
					for (Student s:L)
						if(s.name.compareToIgnoreCase(name)==0) {
							row[0] = s.id;
							row[1] = s.name;
							row[2] = s.age;
							row[3] = s.addess;
							row[4] = s.gender;
							model.addRow(row);
						}
				}
			}
		});
		btnFind.setBounds(387, 139, 117, 25);
		contentPane.add(btnFind);
		

	}
}
