package fmin362.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path( "/menu" )
public class MenuResource {
	@GET
	@Produces( MediaType.APPLICATION_JSON )
	public String menu(){
		
					return "hello";
		
		
		
	}

}
