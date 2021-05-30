/**
 * 
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * @author Gavriel Hason
 *
 */
public class JDBC {
	String uname = "root";
	String password = "gavriel2908";
	String insertQuery = "insert into recordsTable (score, name)" + " values(?, ?)";
	String query = "select * from recordsTable";
	String url = "jdbc:mysql://localhost:3306/try";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new JDBC().printRecords();

	}
	
	public void connect(String name, int score) {

		try(			
			Connection con = DriverManager.getConnection(url,uname,password);
			PreparedStatement st = con.prepareStatement(insertQuery)) {

			st.setInt(1, score);
			st.setString(2, name);
			st.execute();
			st.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void printRecords() {
		try {
			Connection con = DriverManager.getConnection(url,uname,password);
			Statement statment = con.createStatement();
			ResultSet result = statment.executeQuery(query);

			while (result.next()) {
				String data ="";
				for (int i = 1; i < 3 ;i++) {
					data += result.getString(i) + " ";
				}
				System.out.println(data);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
