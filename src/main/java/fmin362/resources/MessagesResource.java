package fmin362.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fmin362.model.Message;


@Path( "/messages" )
public class MessagesResource {
	@GET
@Produces( MediaType.APPLICATION_JSON )
public List<Message> listeMessage(){

List<Message> liste=new ArrayList<Message>();

Message m1=new Message(1,"bonjour toufik", new Date());
Message m2=new Message(1,"bonjour aicha", new Date(12/06/2012));
Message m3=new Message(1,"bonjour ubuntu", new Date(8/11/2012));

liste.add(m1);
liste.add(m2);
liste.add(m3);
return liste;


//TODO


}

}
