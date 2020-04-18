package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

//import java.sql.*;

public class doctor {

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

	public String insertDoctorDetails(String docName, String specilization, String qualification, String chanelTime,
			String chanelDate, String visitHospital) {
		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into doctor(`docId`,`docName`,`specilization`,`qualification`,`chanelTime`,`chanelDate`,`visitHospital`)"
					+ " values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
     		preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, docName);
			preparedStmt.setString(3, specilization);
			preparedStmt.setString(4, qualification);
			preparedStmt.setString(5, chanelTime);
			preparedStmt.setString(6, chanelDate);
			preparedStmt.setString(7, visitHospital);

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

	public String readDoctorDetails() {
	 
		String output = "";
	 
	try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border=\"1\"><tr><th>Doctor ID</th><th>Doctor Name</th><th>Specilization</th><th>Qualification</th><th>Chanel Time</th><th>Chanel Date</th><th>Visit Hospital</th><th>UPDATE</th><th>DELETE</th></tr>";
	 String query = "select * from doctor";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String docID = Integer.toString(rs.getInt("docId"));
	 String doctorName = rs.getString("docName");
	 String specilization = rs.getString("specilization");
	 String qualification = rs.getString("qualification");
	 String time = rs.getString("chanelTime");
	 String date = rs.getString("chanelDate");
	 String visitHospital = rs.getString("visitHospital");
	 // Add into the html table
	 output += "<tr><td>" + docID + "</td>";
	 output += "<td>" + doctorName + "</td>";
	 output += "<td>" + specilization + "</td>";
	 output += "<td>" + qualification + "</td>";
	 output += "<td>" + time + "</td>";
	 output += "<td>" + date + "</td>";
	 output += "<td>" + visitHospital + "</td>";
	 // buttons
	 output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>" + "<td><form method=\"post\" action=\"items.jsp\">"+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\" class=\"btn btn-danger\">" + "<input name=\"itemID\" type=\"hidden\" value=\"" + docID + "\">" + "</form></td></tr>";
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
	
	public String updateDoctorDetails(String docID,String docName, String specilization, String qualification, String chanelTime,String chanelDate, String visitHospital)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE doctor SET docName=?,specilization =?,qualification=?,chanelTime=?,chanelDate=?,visitHospital=? WHERE docId=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, docName);
	 preparedStmt.setString(2, specilization);
	 preparedStmt.setString(3, qualification);
	 preparedStmt.setString(4, chanelTime);
	 preparedStmt.setString(5, chanelDate);
	 preparedStmt.setString(6, visitHospital);
	 preparedStmt.setInt(7, Integer.parseInt(docID));
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
	
	public String deleteDoctorDetails(String docID)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from doctor where docId=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(docID));
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
