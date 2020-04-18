package com;

import javax.swing.text.Document;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.hospital;

@Path("/Hospital")
public class hospitalService {

	hospital hosObj = new hospital();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readHospitalDetails() {

		return hosObj.readHospitalDetails();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertHospitalDetails(@FormParam("hName") String hName, @FormParam("hAddress") String address,
			@FormParam("contact") String contact, @FormParam("email") String email,
			@FormParam("charge") String charge) {
		String output = hosObj.insertHospitalDetails(hName, address, contact, email, charge);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateHospitalDetails(String hosData) {
		// Convert the input string to a JSON object
		JsonObject hosObj1 = new JsonParser().parse(hosData).getAsJsonObject();

		// Read the values from the JSON object
		String hId = hosObj1.get("hId").getAsString();
		String hName = hosObj1.get("hName").getAsString();
		String address = hosObj1.get("hAddress").getAsString();
		String contact = hosObj1.get("contact").getAsString();
		String email = hosObj1.get("email").getAsString();
		String charge = hosObj1.get("charges").getAsString();
		
		String output = hosObj.updateHospitalDetails( hId, hName,  address,  contact,  email, charge);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteHospitalDetails(String hosData)
	{
	//Convert the input string to an XML document
	 org.jsoup.nodes.Document doc = Jsoup.parse(hosData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String hosId = ((Element) doc).select("hId").text();
	 String output = hosObj.deleteHospitalDetails(hosId);
	return output;
	}

}
