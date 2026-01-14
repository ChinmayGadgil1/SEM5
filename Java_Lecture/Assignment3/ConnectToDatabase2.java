import java.sql.*;

public class ConnectToDatabase2 {

	public ConnectToDatabase2() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
			Connection conn = null;
			Statement stmt = null; 
			ResultSet rs = null;
			String tableName;
			try  {
				String userName = "root";
				String password = "qwerty6790";
				String url = "jdbc:mysql://localhost:3306/demojdbc";
				Class.forName ("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection (url, userName, password);
				System.out.println("Connection Successful");
				stmt = conn.createStatement();
				//Creating table
				//stmt.executeUpdate("create table JavaCourse(Roll Integer primary key, Name Varchar(30), Marks Integer not null, Grade Varchar(2))");
				stmt.executeUpdate("insert into JavaCourse values (15,'Deba', 75, 'A')");
				stmt.execute("insert into JavaCourse values(16,'Nila', 85, 'EX')");
				stmt.execute("insert into JavaCourse values(17,'Heer', 85, 'B')");
				stmt.execute("insert into JavaCourse values(18,'Priya', 78, 'A')");
				
				rs = stmt.executeQuery("Select * from javacourse");
				while(rs.next()) {
					System.out.print(rs.getInt(1)+"\t|  ");
					System.out.print(rs.getString("name")+"\t|  ");
					System.out.print(rs.getString(3)+"\t|  ");
					System.out.print(rs.getString(4)+"\t|  ");
					System.out.println();
				}
			} catch (SQLException ex){
				System.out.println("SQLException: " + ex.getMessage());
				System.out.println("SQLState: " + ex.getSQLState());
				System.out.println("VendorError: " + ex.getErrorCode());
			}
			catch (Exception e){
				System.err.println ("Cannot connect to database server");
			}
			finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException sqlEx) {}
					rs = null;
				}
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException sqlEx) {}
					stmt = null; 
				}
				if (conn != null) {
					try {
						conn.close ();
					} catch (Exception e) { /* Ignore code for closing errors */ }
				}	   
			} 
		

	}

}