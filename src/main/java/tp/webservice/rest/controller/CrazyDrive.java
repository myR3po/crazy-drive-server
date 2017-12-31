package tp.webservice.rest.controller;

import java.util.HashMap;
import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import tp.webservice.rest.model.Cars;
import tp.webservice.rest.model.Vehicule;

@Path("/server")
public class CrazyDrive {
	public static int nombrePlayer = 0;
	public String id=null;

	public static HashMap<String, Vehicule> vehicules = new HashMap<String, Vehicule>();
	public Vehicule v;
	
	public CrazyDrive(){
	}

	@GET
	@Path("/creation")
	public Response newPlayer(@PathParam("creation") String msg){
		nombrePlayer++;
		id=getId();
		v = new Vehicule(0,0,0,0);
		vehicules.put(id, v);
		
		return Response.status(200).entity(""+id).build();
	}
	
	private String getId(){
		String token=null;
		do{
			token = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
		}while(vehicules.containsKey(token));
		
		return token;
	}

	@GET
	@Path("/{id}/quit")
	public Response out(@PathParam("id") String msg){
		String identifiant = msg.matches("^\\p{Alnum}+$")? msg : null;
		nombrePlayer--;
		vehicules.remove(identifiant);
	
		return Response.status(200).entity(""+id).build();
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/text/{id}/up")
	public Response Avancer( @PathParam("id") String msg){
		String identifiant = msg.matches("^\\p{Alnum}+$")? msg : null;
		Vehicule v = (Vehicule) vehicules.get(identifiant);
		v.avancer();
		String output = "angle:"+ v.getTheta() + ",X:"+ v.getX()+ ",Y:"+v.getY() + ",vitesse:"+v.getVitesse();
		return Response.status(200).entity(output).build();
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/text/{id}/left")
	public Response tournerGauche(@PathParam("id") String msg){
		String identifiant = msg.matches("^\\p{Alnum}+$")? msg : null;
		Vehicule v = (Vehicule) vehicules.get(identifiant);
		v.tournerGauche();
		
		String output = "angle:"+ v.getTheta() + ",X:"+ v.getX()+ ",Y:"+v.getY() + ",vitesse:"+v.getVitesse();
		return Response.status(200).entity(output).build();
		
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/text/{id}/right")
	public Response tournerDroite(@PathParam("id") String msg){
		String identifiant = msg.matches("^\\p{Alnum}+$")? msg : null;
		Vehicule v = (Vehicule) vehicules.get(identifiant);
		v.tournerDroite();
		
		String output = "angle:"+ v.getTheta() + ",X:"+ v.getX()+ ",Y:"+v.getY() + ",vitesse:"+v.getVitesse();
		return Response.status(200).entity(output).build();
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/text/{id}/down")
	public Response reculer(@PathParam("id") String msg){
		String identifiant = msg.matches("^\\p{Alnum}+$")? msg : null;
		Vehicule v = (Vehicule) vehicules.get(identifiant);
		v.reculer();
		String output =  "angle:"+ v.getTheta() + ",X:"+ v.getX()+ ",Y:"+v.getY() + ",vitesse:"+v.getVitesse();
		return Response.status(200).entity(output).build();
		
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/text/{id}/notouch")
	public Response roueLibre(@PathParam("id") String msg){
		String identifiant = msg.matches("^\\p{Alnum}+$")? msg : null;
		Vehicule v = (Vehicule) vehicules.get(identifiant);
		v.roueLibre();
		String output =  "angle:"+ v.getTheta() + ",X:"+ v.getX()+ ",Y:"+v.getY() + ",vitesse:"+v.getVitesse();
		return Response.status(200).entity(output).build();
	}
	
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/text/{id}/other")
	public Response other(@PathParam("id") String msg){
		String output= "";
		
		String identifiant = msg.matches("^\\p{Alnum}+$")? msg : null;
		int i=0;
		for(String key : vehicules.keySet()){
			if (!key.equalsIgnoreCase(identifiant)){
				Vehicule v = (Vehicule) vehicules.get(key);
				i++;
				output += "Voiture_"+i+";angle:"+ v.getTheta() + ",X:"+ v.getX()+ ",Y:"+v.getY() + ",vitesse:"+v.getVitesse()+",\n";
			}
		}
		return Response.status(200).entity(output).build();
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/text/cars")
	public Response others(@PathParam("id") String msg){
		String output= "";
		
		int i=0;
		for(String key : vehicules.keySet()){
				Vehicule v = (Vehicule) vehicules.get(key);
				i++;
				output += "Voiture_"+i+";angle:"+ v.getTheta() + ",X:"+ v.getX()+ ",Y:"+v.getY() + ",vitesse:"+v.getVitesse()+",\n";
		}
		return Response.status(200).entity(output).build();
	}
	
/****************************************JSON*********************************/
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/json/{id}/up")
	public Vehicule jsonAvancer( @PathParam("id") String msg){
		String identifiant = msg.matches("^\\p{Alnum}+$")? msg : null;
		Vehicule v = (Vehicule) vehicules.get(identifiant);
		v.avancer();
		
		return v;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/json/{id}/left")
	public Vehicule jsonTournerGauche(@PathParam("id") String msg){
		String identifiant = msg.matches("^\\p{Alnum}+$")? msg : null;
		Vehicule v = (Vehicule) vehicules.get(identifiant);
		v.tournerGauche();
		
		return v;
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/json/{id}/right")
	public Vehicule jsonTournerDroite(@PathParam("id") String msg){
		String identifiant = msg.matches("^\\p{Alnum}+$")? msg : null;
		Vehicule v = (Vehicule) vehicules.get(identifiant);
		v.tournerDroite();
		
		return v;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/json/{id}/down")
	public Vehicule jsonReculer(@PathParam("id") String msg){
		String identifiant = msg.matches("^\\p{Alnum}+$")? msg : null;
		Vehicule v = (Vehicule) vehicules.get(identifiant);
		v.reculer();
		
		return v;
		
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/json/{id}/notouch")
	public Vehicule jsonRoueLibre(@PathParam("id") String msg){
		String identifiant = msg.matches("^\\p{Alnum}+$")? msg : null;
		Vehicule v = (Vehicule) vehicules.get(identifiant);
		v.roueLibre();
		return v;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/json/{id}/other")
	public Cars jsonOther(@PathParam("id") String msg){
		
		String identifiant = msg.matches("^\\p{Alnum}+$")? msg.toUpperCase() : null;
		Cars c = new Cars();
		if(!identifiant.isEmpty() && vehicules.containsKey(identifiant)){

			for(String key : vehicules.keySet()){
				if (!key.equalsIgnoreCase(identifiant)){
					
					c.addEntry( (Vehicule) vehicules.get(key));
				}
			}
		}
		return c;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/json/cars")
	public Cars jsonOthers(@PathParam("id") String msg){
		Cars c = new Cars();

		for(String key : vehicules.keySet()){
					
			c.addEntry( (Vehicule) vehicules.get(key));
			
		}
		return c;
	}

	/************************************XML*****************************/
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/xml/{id}/up")
	public Vehicule xmlAvancer( @PathParam("id") String msg){
		String identifiant = msg.matches("^\\p{Alnum}+$")? msg : null;
		Vehicule v = (Vehicule) vehicules.get(identifiant);
		v.avancer();
		
		return v;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/xml/{id}/left")
	public Vehicule xmlTournerGauche(@PathParam("id") String msg){
		String identifiant = msg.matches("^\\p{Alnum}+$")? msg : null;
		Vehicule v = (Vehicule) vehicules.get(identifiant);
		v.tournerGauche();
		
		return v;
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/xml/{id}/right")
	public Vehicule xmlTournerDroite(@PathParam("id") String msg){
		String identifiant = msg.matches("^\\p{Alnum}+$")? msg : null;
		Vehicule v = (Vehicule) vehicules.get(identifiant);
		v.tournerDroite();
		
		return v;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/xml/{id}/down")
	public Vehicule xmlReculer(@PathParam("id") String msg){
		String identifiant = msg.matches("^\\p{Alnum}+$")? msg : null;
		Vehicule v = (Vehicule) vehicules.get(identifiant);
		v.reculer();
		
		return v;
		
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/xml/{id}/notouch")
	public Vehicule xmlRoueLibre(@PathParam("id") String msg){
		String identifiant = msg.matches("^\\p{Alnum}+$")? msg : null;
		Vehicule v = (Vehicule) vehicules.get(identifiant);
		v.roueLibre();
		return v;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/xml/{id}/other")
	public Cars xmlOther(@PathParam("id") String msg){
		
		String identifiant = msg.matches("^\\p{Alnum}+$")? msg.toUpperCase() : null;
		Cars c = new Cars();
		if(!identifiant.isEmpty() && vehicules.containsKey(identifiant)){

			for(String key : vehicules.keySet()){
				if (!key.equalsIgnoreCase(identifiant)){
					
					c.addEntry( (Vehicule) vehicules.get(key));
				}
			}
		}
		return c;
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/xml/cars")
	public Cars xmlOthers(@PathParam("id") String msg){
		Cars c = new Cars();

		for(String key : vehicules.keySet()){
					
			c.addEntry( (Vehicule) vehicules.get(key));
			
		}
		return c;
	}

}
