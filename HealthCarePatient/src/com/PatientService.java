package com;


import model.Patient1;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


@Path("/Patient12")
public class PatientService {

	Patient1 P1 = new Patient1();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPatient1()
	 {
	 return P1.readPatient1();
	 } 
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPatient1
	
	( @FormParam("fName") String fName,
	 @FormParam("lName") String lName,
	 @FormParam("NIC") String NIC,
	 @FormParam("age") String age,
	 @FormParam("contactNo") String contactNo)
	{
	 String output = P1.insertPatient1(fName,lName,NIC,age,contactNo);
	return output;
	}

	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePatient1(String PatientData)
	{
	//Convert the input string to a JSON object
	 JsonObject pObject = new JsonParser().parse(PatientData).getAsJsonObject();
	//Read the values from the JSON object
	 String PID = pObject.get("PID").getAsString();
	 String fName = pObject.get("fName").getAsString();
	 String lName = pObject.get("lName").getAsString();
	 String NIC = pObject.get("NIC").getAsString();
	 String age = pObject.get("age").getAsString();
	 String contactNo = pObject.get("contactNo").getAsString();
	 
	 
	 String output = P1.updatePatient1(PID, fName, lName, NIC, age,contactNo);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String  deletePatient1(String PatientData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(PatientData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String PID = doc.select("PID").text();
	 String output = P1. deletePatient1(PID);
	return output;
	}

}
