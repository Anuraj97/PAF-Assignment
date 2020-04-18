package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class hospital {

	
	// A common method to connect to the DB
	
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hospital?useTimezone=true&serverTimezone=UTC",
				"root", "");
	 System.out.println("Connection Success");
	 
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 } 

	public String insertHospitalDetails(String hName, String address, String contact, String email,
			String charge) {
		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into hospital1(`hId`,`hName`,`hAddress`,`contact`,`email`,`charges`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
     		preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, hName);
			preparedStmt.setString(3, address);
			preparedStmt.setString(4, contact);
			preparedStmt.setString(5, email);
			preparedStmt.setDouble(6, Double.parseDouble(charge));
			

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String readHospitalDetails() {
	 
		String output = "";
	 
	try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border=\"1\"><tr><th>Hospital ID</th><th>Hospital Name</th><th>Address</th><th>Contact</th><th>Email</th><th>Charges</th><th>UPDATE</th><th>DELETE</th></tr>";
	 String query = "select * from hospital1";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String hID = Integer.toString(rs.getInt("hId"));
	 String hName = rs.getString("hName");
	 String address = rs.getString("hAddress");
	 String contact = rs.getString("contact");
	 String email = rs.getString("email");
	 String charges = Double.toString(rs.getDouble("charges"));
	
	 // Add into the html table
	 output += "<tr><td>" + hID + "</td>";
	 output += "<td>" + hName + "</td>";
	 output += "<td>" + address + "</td>";
	 output += "<td>" + contact + "</td>";
	 output += "<td>" + email + "</td>";
	 output += "<td>" + charges + "</td>";
	 
	 // buttons
	 output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>" + "<td><form method=\"post\" action=\"items.jsp\">"+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\" class=\"btn btn-danger\">" + "<input name=\"itemID\" type=\"hidden\" value=\"" + hID + "\">" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the items.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	public String updateHospitalDetails(String hId,String hName, String address, String contact, String email,String charge)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE hospital1 SET hName=?,hAddress =?,contact=?,email=?,charges=? WHERE hId=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, hName);
	 preparedStmt.setString(2, address);
	 preparedStmt.setString(3, contact);
	 preparedStmt.setString(4, email);
	 preparedStmt.setDouble(5, Double.parseDouble(charge));
	 preparedStmt.setInt(6, Integer.parseInt(hId));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Updated successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while updating the item.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
	public String deleteHospitalDetails(String hId)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from hospital1 where hId=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(hId));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the item.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 

	
}
