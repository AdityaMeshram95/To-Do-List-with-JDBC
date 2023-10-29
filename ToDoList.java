package project1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ToDoList {

	// instance variable
	private static String jdbcUrl = "jdbc:mysql://localhost:3306/unisoft";
	private static String username = "root";
	private static String password = "root";

	// Create a table
	public static void initializeDatabase() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection con = DriverManager.getConnection(jdbcUrl, username, password)) {
				Statement smt = con.createStatement();

				smt.execute("CREATE TABLE IF NOT EXISTS schedule(id int, description TEXT, reg_date timestamp)");
				System.out.println("Table created...");
				smt.close();
			} catch (SQLException s) {
				s.printStackTrace();
			}
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
	}

	// Create a method to add Task
	public static void addTask(int id, String task) {
		try {
			Connection con = DriverManager.getConnection(jdbcUrl, username, password);
			String query1 = "INSERT INTO schedule(id, description, reg_date) VALUES(?, ?, CURRENT_TIMESTAMP)";
			PreparedStatement ps = con.prepareStatement(query1);

			ps.setInt(1, id);
			ps.setString(2, task);
			ps.executeUpdate();

			System.out.println("Values added successfully");

			ps.close();
			con.close();
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	// Create a method to display the task
	public static void displayTask() {
		try {
			Connection con = DriverManager.getConnection(jdbcUrl, username, password);
			Statement smt = con.createStatement();
			ResultSet rs = smt.executeQuery("SELECT * FROM schedule");

			while (rs.next()) {
				System.out.println(
						rs.getInt("id") + " " + rs.getString("description") + " " + rs.getString("reg_Date"));
			}

			rs.close();
			smt.close();
			con.close();
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	// Create a method to remove the task
	public static void removeTask(int index) {
		try {
			Connection con = DriverManager.getConnection(jdbcUrl, username, password);
			String query2 = "DELETE FROM schedule WHERE id = ?";
			PreparedStatement ps = con.prepareStatement(query2);

			ps.setInt(1, index);
			ps.executeUpdate();
			System.out.println("Removed...");

			ps.close();
			con.close();

		} catch (SQLException s) {
			s.printStackTrace();
		}
	}
}
