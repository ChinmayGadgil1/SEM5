import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudentFormAWT extends Frame implements ActionListener {

    Label lblId, lblName, lblGender, lblCourse, lblHobbies, lblAddress, lblSearch;
    TextField txtId, txtName, txtSearch;
    Checkbox rbMale, rbFemale;
    Checkbox cbSports, cbMusic, cbReading;
    Choice cmbCourse;
    TextArea txtAddress, txtDisplay;
    Button btnInsert, btnUpdate, btnDelete, btnSearch;

    Connection conn;
    Statement stmt;
    ResultSet rs;

    public StudentFormAWT() {
        super("Student Form (AWT Simplified)");

        setLayout(new FlowLayout());

        lblId = new Label("ID:");
        txtId = new TextField(10);
        lblName = new Label("Name:");
        txtName = new TextField(15);

        lblGender = new Label("Gender:");
        rbMale = new Checkbox("Male");
        rbFemale = new Checkbox("Female");

        lblCourse = new Label("Course:");
        cmbCourse = new Choice();
        cmbCourse.add("BCA");
        cmbCourse.add("B.Sc");
        cmbCourse.add("B.Tech");
        cmbCourse.add("MBA");
        cmbCourse.add("MCA");

        lblHobbies = new Label("Hobbies:");
        cbSports = new Checkbox("Sports");
        cbMusic = new Checkbox("Music");
        cbReading = new Checkbox("Reading");

        lblAddress = new Label("Address:");
        txtAddress = new TextArea(3, 20);

        btnInsert = new Button("Insert");
        btnUpdate = new Button("Update");
        btnDelete = new Button("Delete");
        btnSearch = new Button("Search");

        lblSearch = new Label("Search ID:");
        txtSearch = new TextField(10);

        txtDisplay = new TextArea(12, 50);
        txtDisplay.setEditable(false);

        add(lblId); add(txtId);
        add(lblName); add(txtName);

        add(lblGender);
        add(rbMale); add(rbFemale);

        add(lblCourse); add(cmbCourse);

        add(lblHobbies);
        add(cbSports); add(cbMusic); add(cbReading);

        add(lblAddress); add(txtAddress);

        add(btnInsert); add(btnUpdate); add(btnDelete);

        add(lblSearch); add(txtSearch); add(btnSearch);

        add(txtDisplay);

        btnInsert.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);
        btnSearch.addActionListener(this);

        setSize(600, 600);
        setVisible(true);

        loadDatabase();
    }

    void loadDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/demojdbc", "root", "qwerty6790"
            );
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM student");
            while (rs.next()) {
                txtDisplay.append(
                    rs.getInt(1) + "  " +
                    rs.getString(2) + "  " +
                    rs.getString(3) + "  " +
                    rs.getString(4) + "  " +
                    rs.getString(5) + "  " +
                    rs.getString(6) + "\n"
                );
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
        String id = txtId.getText().trim();
        String name = txtName.getText().trim();
        String gender = rbMale.getState() ? "Male" : rbFemale.getState() ? "Female" : "";
        String course = cmbCourse.getSelectedItem();

        String hobbies = "";
        if (cbSports.getState()) hobbies += "Sports ";
        if (cbMusic.getState()) hobbies += "Music ";
        if (cbReading.getState()) hobbies += "Reading ";

        String address = txtAddress.getText().trim();

        try {

            if (e.getSource() == btnInsert) {
                String q = "INSERT INTO student VALUES (" + id + ",'" + name + "','" +
                        gender + "','" + course + "','" + hobbies + "','" + address + "')";
                stmt.executeUpdate(q);

                txtDisplay.append(id + " " + name + " " + gender + " " + course + " " + hobbies + " " + address + "\n");
            }

            else if (e.getSource() == btnSearch) {
                String sid = txtSearch.getText().trim();
                ResultSet r = stmt.executeQuery("SELECT * FROM student WHERE id=" + sid);
                txtDisplay.setText("");

                if (!r.next()) {
                    txtDisplay.setText("Record Not Found");
                    return;
                }

                txtDisplay.append(
                    r.getInt(1) + " " + r.getString(2) + " " +
                    r.getString(3) + " " + r.getString(4) + " " +
                    r.getString(5) + " " + r.getString(6) + "\n"
                );
            }

            else if (e.getSource() == btnUpdate) {
                String q = "UPDATE student SET name='" + name + "', gender='" + gender +
                        "', course='" + course + "', hobbies='" + hobbies +
                        "', address='" + address + "' WHERE id=" + id;
                stmt.executeUpdate(q);

                txtDisplay.append("\nUpdated: " + id + "\n");
            }

            else if (e.getSource() == btnDelete) {
                stmt.executeUpdate("DELETE FROM student WHERE id=" + id);
                txtDisplay.append("\nDeleted: " + id + "\n");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new StudentFormAWT();
    }
}
