package com;

import model.doctor;

import javax.swing.text.Document;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/Doctor")
public class doctorService {
	doctor docObj = new doctor();

	// Read doctor details
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readDoctorDetails() {
		return docObj.readDoctorDetails();
	}

	// Insert doctor details
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertDoctorDetails(@FormParam("docName") String docName,
			@FormParam("specilization") String specilization, @FormParam("qualification") String qualification,
			@FormParam("chanelTime") String chanelTime, @FormParam("chanelDate") String chanelDate,
			@FormParam("visitHospital") String visitHospital) {
		String output = docObj.insertDoctorDetails(docName, specilization, qualification, chanelTime, chanelDate,
				visitHospital);
		return output;
	}

	// Update doctor details
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateDoctorDetails(String docData) {
		// Convert the input string to a JSON object
		JsonObject docObj1 = new JsonParser().parse(docData).getAsJsonObject();

		// Read the values from the JSON object
		String docID = docObj1.get("docId").getAsString();
		String docName = docObj1.get("docName").getAsString();
		String specilization = docObj1.get("specilization").getAsString();
		String qualification = docObj1.get("qualification").getAsString();
		String chanelTime = docObj1.get("chanelTime").getAsString();
		String chanelDate = docObj1.get("chanelDate").getAsString();
		String visitHospital = docObj1.get("visitHospital").getAsString();
		String output = docObj.updateDoctorDetails(docID, docName, specilization, qualification, chanelTime, chanelDate,
				visitHospital);
		return output;
	}

	// Delete doctor details
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteDoctorDetails(String docData) {
		// Convert the input string to an XML document
		org.jsoup.nodes.Document doc = Jsoup.parse(docData, "", Parser.xmlParser());

		// Read the value from the element <itemID>
		String docId = ((Element) doc).select("docId").text();
		String output = docObj.deleteDoctorDetails(docId);
		return output;
	}

}
