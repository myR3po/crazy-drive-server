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

public class CrazyDrive {
  public static HashMap<String, Vehicule> vehicules = new HashMap<String, Vehicule>();

  public CrazyDrive() {
  }

  private Vehicule getVehicule(String msg) {
    String identifiant = msg.matches("^\\p{Alnum}+$") ? msg : null;
    return vehicules.get(identifiant);
  }

  @GET
  @Path("/vehicule/join")
  public Response newPlayer(@PathParam("creation") String msg) {
    String id = getId();
    vehicules.put(id, new Vehicule(0, 0, 0, 0));

    return Response.status(200).entity("" + id).build();
  }

  private String getId() {
    String token = null;
    do {
      token = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
    } while (vehicules.containsKey(token));

    return token;
  }

  @GET
  @Path("/vehicule/{id}/quit")
  public Response out(@PathParam("id") String msg) {
    String identifiant = msg.matches("^\\p{Alnum}+$") ? msg : null;
    vehicules.remove(identifiant);

    return Response.status(200).build();
  }

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/vehicule/text/{id}/front")
  public Response Avancer(@PathParam("id") String msg) {
    Vehicule v = getVehicule(msg);
    if (v != null) {
      v.avancer();
      return Response.status(200).entity(v.toString()).build();
    }
    return Response.status(404).build();
  }

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/vehicule/text/{id}/left")
  public Response tournerGauche(@PathParam("id") String msg) {
    Vehicule v = getVehicule(msg);
    if (v != null) {
      v.tournerGauche();
      return Response.status(200).entity(v.toString()).build();
    }
    return Response.status(404).build();
  }

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/vehicule/text/{id}/right")
  public Response tournerDroite(@PathParam("id") String msg) {
    Vehicule v = getVehicule(msg);
    if (v != null) {
      v.tournerDroite();
      return Response.status(200).entity(v.toString()).build();
    }
    return Response.status(404).build();
  }

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/vehicule/text/{id}/back")
  public Response reculer(@PathParam("id") String msg) {
    Vehicule v = getVehicule(msg);
    if (v != null) {
      v.reculer();
      return Response.status(200).entity(v.toString()).build();
    }
    return Response.status(404).build();
  }

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/vehicule/text/{id}/none")
  public Response roueLibre(@PathParam("id") String msg) {
    Vehicule v = getVehicule(msg);
    if (v != null) {
      v.roueLibre();
      return Response.status(200).entity(v.toString()).build();
    }
    return Response.status(404).build();
  }

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/vehicule/text/{id}/opponents")
  public Response other(@PathParam("id") String msg) {
    String output = "";

    String identifiant = msg.matches("^\\p{Alnum}+$") ? msg : null;
    int i = 0;
    for (String key : vehicules.keySet()) {
      if (!key.equalsIgnoreCase(identifiant)) {
        Vehicule v = (Vehicule) vehicules.get(key);
        i++;
        output += "Voiture_" + i + v.toString() + ",\n";
      }
    }
    return Response.status(200).entity(output).build();
  }

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/vehicules/text/all")
  public Response others(@PathParam("id") String msg) {
    String output = "";

    int i = 0;
    for (String key : vehicules.keySet()) {
      Vehicule v = (Vehicule) vehicules.get(key);
      i++;
      output += "Voiture_" + i + v.toString() + ",\n";
    }
    return Response.status(200).entity(output).build();
  }

  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Path("/vehicule/{id}/front")
  public Vehicule jsonAvancer(@PathParam("id") String msg) {
    Vehicule v = getVehicule(msg);
    if (v != null) {
      v.avancer();
    }
    return v;
  }

  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Path("/vehicule/{id}/left")
  public Vehicule jsonTournerGauche(@PathParam("id") String msg) {
    Vehicule v = getVehicule(msg);
    if (v != null) {
      v.tournerGauche();
    }
    return v;
  }

  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Path("/vehicule/{id}/right")
  public Vehicule jsonTournerDroite(@PathParam("id") String msg) {
    Vehicule v = getVehicule(msg);
    if (v != null) {
      v.tournerDroite();
    }
    return v;
  }

  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Path("/vehicule/{id}/back")
  public Vehicule jsonReculer(@PathParam("id") String msg) {
    Vehicule v = getVehicule(msg);
    if (v != null) {
      v.reculer();
    }
    return v;
  }

  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Path("/vehicule/{id}/none")
  public Vehicule jsonRoueLibre(@PathParam("id") String msg) {
    Vehicule v = getVehicule(msg);
    if (v != null) {
      v.roueLibre();
    }
    return v;
  }

  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Path("/vehicule/{id}/opponents")
  public Cars jsonOther(@PathParam("id") String msg) {

    String identifiant = msg.matches("^\\p{Alnum}+$") ? msg.toUpperCase() : null;
    Cars c = new Cars();
    if (!identifiant.isEmpty() && vehicules.containsKey(identifiant)) {

      for (String key : vehicules.keySet()) {
        if (!key.equalsIgnoreCase(identifiant)) {
          c.addEntry((Vehicule) vehicules.get(key));
        }
      }
    }
    return c;
  }

  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Path("/vehicules/all")
  public Cars jsonAll(@PathParam("id") String msg) {
    Cars c = new Cars();
    for (String key : vehicules.keySet()) {
      c.addEntry((Vehicule) vehicules.get(key));
    }
    return c;
  }
}
