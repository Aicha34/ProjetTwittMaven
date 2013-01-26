package fmin362.resources;

import java.util.List;

import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import fmin362.model.User;

@Path( "/connexion" )
public class login {
	@POST
	@Produces( MediaType.APPLICATION_JSON )
	public User newUser(@FormParam("login") String login,
	          @FormParam("password") String password) throws Exception{
		
		User u=null;
		Response rep;
		
		UserTransaction utx = null;
        try {

            // Lookup
            InitialContext ic = new InitialContext();
            utx = ( UserTransaction ) ic.lookup( "java:comp/UserTransaction" );
            EntityManager em = ( EntityManager ) ic.lookup( "java:comp/env/persistence/EntityManager" );
        
           // Transaction begin
           utx.begin();
           em.joinTransaction();
           List<User> liste = em.createQuery("SELECT u FROM User u WHERE u.login='" + login + "'").getResultList();
           if(!liste.isEmpty()){
        	   if(((User) liste.get(0)).getPassword().equals(password)) {
        	          u = liste.get(0);
        	        } 
        	   else{
        		   rep=Response.status(Status.BAD_REQUEST).build();
        		   
        	   }
        	   
           }
           else {
        	   rep=Response.status(Status.BAD_REQUEST).build();
           }
           
           utx.commit();

           return u;
           } 
        catch ( Exception ex ) {

        try {
            if ( utx != null ) {
                utx.setRollbackOnly();
            }
        } catch ( Exception rollbackEx ) {
            // Impossible d'annuler les changements, vous devriez logguer une erreur,
            // voir envoyer un email à l'exploitant de l'application.
        }
        throw new Exception( ex );
	
			
	}
	}


}
