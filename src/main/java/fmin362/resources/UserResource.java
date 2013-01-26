package fmin362.resources;

import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fmin362.model.User;

@Path( "/newUser" )
public class UserResource {
	@POST
	@Produces( MediaType.APPLICATION_JSON )
	public User newUser(@FormParam("login") String login,
	          @FormParam("mail") String mail,
	          @FormParam("password") String password) throws Exception{
		
		UserTransaction utx = null;
        try {

            // Lookup
            InitialContext ic = new InitialContext();
            utx = ( UserTransaction ) ic.lookup( "java:comp/UserTransaction" );
            EntityManager em = ( EntityManager ) ic.lookup( "java:comp/env/persistence/EntityManager" );
        
           // Transaction begin
           utx.begin();
           em.joinTransaction();
           
           
           User user=new User(login,password,mail);
          // User user2=new User("snoppy","pass","snoopy@mail.com");
           
           em.persist( user );
           //em.persist( user2 );
           
           //List<User> users = em.createQuery( "select u from User u" ).getResultList();
           utx.commit();

           return user;
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
