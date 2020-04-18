package com;

import model.Appointment;

//For REST Service 
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON 
import com.google.gson.*;

//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Appointments")
public class AppointmentService {
	Appointment appointmentObj = new Appointment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readAppointments() {
		return appointmentObj.readAppointments();
	}

	//Insert
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertAppointment(@FormParam("appNo") String appNo,
				@FormParam("appType") String appType,
				@FormParam("appDate") String appDate, 
				@FormParam("appDescription") String appDescription)
		{
			String output = appointmentObj.insertAppointment(appNo, appType, appDate, appDescription);
			return output;
		}
	
	// update
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAppointment(String appData) {
		// Convert the input string to a JSON object
		JsonObject appObject = new JsonParser().parse(appData).getAsJsonObject();

		// Read the values from the JSON object
		String appID = appObject.get("appID").getAsString();
		String appNo = appObject.get("appNo").getAsString();
		String appType = appObject.get("appType").getAsString();
		String appDate = appObject.get("appDate").getAsString();
		String appDescription = appObject.get("appDescription").getAsString();

		String output = appointmentObj.updateAppointment(appID, appNo, appType, appDate, appDescription);

		return output;
	}

	// Delete
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteAppointment(String appData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(appData, "", Parser.xmlParser());

		// Read the value from the element <itemID>
		String appID = doc.select("appID").text();

		String output = appointmentObj.deleteAppointment(appID);

		return output;
	}
}
