package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/paf?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertPayment(String type, String nic, String price, String date)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into payment1(`PayID`,`pType`,`Nic`,`PaymentPrice`,`pDate`)"
	 + " values (?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, type);
	 preparedStmt.setString(3, nic);
	 preparedStmt.setDouble(4, Double.parseDouble(price));
	 preparedStmt.setString(5, date);
	// execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the payment.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }

	public String readPayments()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border=\"1\"><tr><th>Payment Type</th><th>NIC</th><th>Price</th><th>Date</th><th>Update</th><th>Remove</th></tr>";
	 String query = "select * from payment1";
	 Statement stmt = (Statement) con.createStatement();
	 ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String PayID = Integer.toString(rs.getInt("PayID"));
	 String pType = rs.getString("pType");
	 String Nic = rs.getString("Nic");
	 String PaymentPrice = Double.toString(rs.getDouble("PaymentPrice"));
	 String pDate = rs.getString("pDate");
	 // Add into the html table
	 output += "<tr><td>" + pType + "</td>";
	 output += "<td>" + Nic + "</td>";
	 output += "<td>" + PaymentPrice + "</td>";
	 output += "<td>" + pDate + "</td>";
	 // buttons
	 output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"+ "<td><form method=\"post\" action=\"items.jsp\">"+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
	 + "<input name=\"PayID\" type=\"hidden\" value=\"" + PayID
	 + "\">" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the payments.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }

	public String updatePayment(String ID, String type, String nic, String price, String date) 
	{
		String output = ""; 
	 
	  try   
	  {   
		  Connection con = connect(); 
	 
	   if (con == null)    
	   {
		   return "Error while connecting to the database for updating."; } 
	 
	   // create a prepared statement    
	   String query = "UPDATE payment1 SET pType=?,Nic=?,PaymentPrice=?,pDate=?"
	   				+ "WHERE PayID=?"; 
	 
	   PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	   // binding values    
	   preparedStmt.setString(1, type);
	   preparedStmt.setString(2, nic);
	   preparedStmt.setString(3, price);
	   preparedStmt.setString(4, date);
	   preparedStmt.setInt(5, Integer.parseInt(ID)); 
	 
	   // execute the statement    
	   preparedStmt.execute();    con.close(); 
	 
	   output = "Updated successfully";   
	   }   
	  catch (Exception e)   
	  {    
		  output = "Error while updating the payment.";    
		  System.err.println(e.getMessage());   
	 } 
	 
	  return output;  
	  } 
	 
	 public String deletePayment(String PayID)  
	 {   
		 String output = ""; 
	 
	  try   
	  {    
		  Connection con = connect(); 
	 
	   if (con == null)    
	   {
		   return "Error while connecting to the database for deleting."; } 
	 
	   // create a prepared statement    
	   String query = "delete from payment1 where PayID=?"; 
	 
	   PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	   // binding values    
	   preparedStmt.setInt(1, Integer.parseInt(PayID)); 
	 
	   // execute the statement    
	   preparedStmt.execute();    con.close(); 
	 
	   output = "Deleted successfully";   
	   }   
	  	catch (Exception e)   
	  {    output = "Error while deleting the patient.";    
	  System.err.println(e.getMessage());   } 
	 
	  return output;  }
}