import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class StudentForm2 extends JFrame implements ActionListener {

    // Labels
    JLabel lblId, lblName, lblGender, lblCourse, lblHobbies, lblAddress, lblSearch;

    // Text fields
    JTextField txtId, txtName, txtSearch;

    // Combo box
    JComboBox<String> cmbCourse;

    // Radio buttons
    JRadioButton rbMale, rbFemale;

    // Checkboxes
    JCheckBox cbSports, cbMusic, cbReading;

    // Text area
    JTextArea txtAddress;

    // Buttons
    JButton btnInsert, btnUpdate, btnSearch, btnDelete;

    // Table
    JTable table;
    DefaultTableModel model;

    // To group gender radio buttons
    ButtonGroup bgGender;
    
    //Components for database connectivity
    Connection conn;
    Statement stmt;
    ResultSet rs;

    public StudentForm2() {
        super("Student Details Form");

        // Initialize components
        lblId = new JLabel("Student ID:");
        lblName = new JLabel("Name:");
        lblGender = new JLabel("Gender:");
        lblCourse = new JLabel("Course:");
        lblHobbies = new JLabel("Hobbies:");
        lblAddress = new JLabel("Address:");
        lblSearch = new JLabel("Search by ID:");

        txtId = new JTextField(15);
        txtName = new JTextField(15);
        txtSearch = new JTextField(10);

        cmbCourse = new JComboBox<>(new String[]{"BCA", "B.Sc", "B.Tech", "MBA", "MCA"});

        rbMale = new JRadioButton("Male");
        rbFemale = new JRadioButton("Female");
        bgGender = new ButtonGroup();
        bgGender.add(rbMale);
        bgGender.add(rbFemale);

        cbSports = new JCheckBox("Sports");
        cbMusic = new JCheckBox("Music");
        cbReading = new JCheckBox("Reading");

        txtAddress = new JTextArea(3, 15);
        txtAddress.setLineWrap(true);
        txtAddress.setWrapStyleWord(true);

        btnInsert = new JButton("Insert");
        btnUpdate = new JButton("Update");
        btnSearch = new JButton("Search");
        btnDelete = new JButton("Delete");

        // Table
        model = new DefaultTableModel(new String[]{"ID", "Name", "Gender", "Course", "Hobbies", "Address"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Add action listeners
        btnInsert.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnSearch.addActionListener(this);
        btnDelete.addActionListener(this);

        // Layout setup
        setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;
        gbc.gridx = 0; gbc.gridy = y; panel.add(lblId, gbc);
        gbc.gridx = 1; panel.add(txtId, gbc);

        y++;
        gbc.gridx = 0; gbc.gridy = y; panel.add(lblName, gbc);
        gbc.gridx = 1; panel.add(txtName, gbc);

        y++;
        gbc.gridx = 0; gbc.gridy = y; panel.add(lblGender, gbc);
        gbc.gridx = 1;
        
        JPanel genderPanel = new JPanel();
        genderPanel.add(rbMale);
        genderPanel.add(rbFemale);
        panel.add(genderPanel, gbc);

        y++;
        gbc.gridx = 0; gbc.gridy = y; panel.add(lblCourse, gbc);
        gbc.gridx = 1; panel.add(cmbCourse, gbc);

        y++;
        gbc.gridx = 0; gbc.gridy = y; panel.add(lblHobbies, gbc);
        gbc.gridx = 1;
        JPanel hobbyPanel = new JPanel();
        hobbyPanel.add(cbSports);
        hobbyPanel.add(cbMusic);
        hobbyPanel.add(cbReading);
        panel.add(hobbyPanel, gbc);

        y++;
        gbc.gridx = 0; gbc.gridy = y; panel.add(lblAddress, gbc);
        gbc.gridx = 1; panel.add(new JScrollPane(txtAddress), gbc);

        y++;
        gbc.gridx = 0; gbc.gridy = y; panel.add(lblSearch, gbc);
        gbc.gridx = 1;
        JPanel searchPanel = new JPanel();
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);
        panel.add(searchPanel, gbc);

        y++;
        gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnInsert);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        panel.add(buttonPanel, gbc);

        // Add to frame
        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setSize(700, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        
        //Initialize the database components
        String userName = "root";
		String password = "qwerty6790";
		String url = "jdbc:mysql://localhost:3306/demojdbc";
		try {
			Class.forName ("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection (url, userName, password);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("Select * from Student");
			while(rs.next()) {
				
				model.addRow(new Object[]{	rs.getInt(1), rs.getString(2), 
											rs.getString(3), rs.getString(4), 
											rs.getString(5), rs.getString(6)});
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String id = txtId.getText().trim();
        String name = txtName.getText().trim();
        String gender = rbMale.isSelected() ? "Male" : rbFemale.isSelected() ? "Female" : "";
        String course = (String) cmbCourse.getSelectedItem();

        StringBuilder hobbies = new StringBuilder();
        if (cbSports.isSelected()) hobbies.append("Sports ");
        if (cbMusic.isSelected()) hobbies.append("Music ");
        if (cbReading.isSelected()) hobbies.append("Reading ");

        String address = txtAddress.getText().trim();

        if (e.getSource() == btnInsert) {
            if (id.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill ID and Name!");
                return;
            }
            
            String insQuery = "insert into student values ("+Integer.parseInt(id)+",'"+name+"','"+gender+"','"+course+"','"+hobbies.toString()+"','"+address+"');";
            try {
				int k = stmt.executeUpdate(insQuery);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            model.addRow(new Object[]{id, name, gender, course, hobbies.toString(), address});
            JOptionPane.showMessageDialog(this, "Record Inserted!");
        }

        else if (e.getSource() == btnUpdate) {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a row to update!");
                return;
            }
            model.setValueAt(id, row, 0);
            model.setValueAt(name, row, 1);
            model.setValueAt(gender, row, 2);
            model.setValueAt(course, row, 3);
            model.setValueAt(hobbies.toString(), row, 4);
            model.setValueAt(address, row, 5);
            JOptionPane.showMessageDialog(this, "Record Updated!");
        }

        else if (e.getSource() == btnSearch) {
            String searchId = txtSearch.getText().trim();
            if (searchId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter Student ID to search!");
                return;
            }
            boolean found = false;
            for (int i = 0; i < model.getRowCount(); i++) {
                if (model.getValueAt(i, 0).equals(searchId)) {
                    table.setRowSelectionInterval(i, i);
                    found = true;
                    break;
                }
            }
            if (!found)
                JOptionPane.showMessageDialog(this, "Record not found!");
        }

        else if (e.getSource() == btnDelete) {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a record to delete!");
                return;
            }
            model.removeRow(row);
            JOptionPane.showMessageDialog(this, "Record Deleted!");
        }
    }
    
    public static void main(String[] args) {
        new StudentForm2();
    }
}