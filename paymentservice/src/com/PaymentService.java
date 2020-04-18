package com;

import model.Payment;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Payments")
public class PaymentService {
	Payment payObj = new Payment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayments() {
		return payObj.readPayments();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(@FormParam("pType") String pType,
	 @FormParam("Nic") String Nic,
	 @FormParam("PaymentPrice") String PaymentPrice,
	 @FormParam("pDate") String pDate)
	{
	 String output = payObj.insertPayment(pType, Nic, PaymentPrice, pDate);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(String paymentData)
	{
	//Convert the input string to a JSON object
	 JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject();
	//Read the values from the JSON object
	 String PayID = paymentObject.get("PayID").getAsString();
	 String pType = paymentObject.get("pType").getAsString();
	 String Nic = paymentObject.get("Nic").getAsString();
	 String PaymentPrice = paymentObject.get("PaymentPrice").getAsString();
	 String pDate = paymentObject.get("pDate").getAsString();
	 String output = payObj.updatePayment(PayID, pType, Nic, PaymentPrice, pDate);
	return output;
	} 
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String paymentData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String PayID = doc.select("PayID").text();
	 String output = payObj.deletePayment(PayID);
	return output;
	}


}
