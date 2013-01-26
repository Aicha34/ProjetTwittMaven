package fmin362.resources;

import java.util.List;

import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fmin362.model.User;

@Path( "/users" )
public class ListeUsers {
	@GET
	@Produces( MediaType.APPLICATION_JSON )
	public List listeUsers() throws Exception{
		
		UserTransaction utx = null;
        try {

            // Lookup
            InitialContext ic = new InitialContext();
            utx = ( UserTransaction ) ic.lookup( "java:comp/UserTransaction" );
            EntityManager em = ( EntityManager ) ic.lookup( "java:comp/env/persistence/EntityManager" );
        
           // Transaction begin
           utx.begin();
           em.joinTransaction();

           List<User> users = em.createQuery( "select u from User u" ).getResultList();

           utx.commit();

           return users;
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
