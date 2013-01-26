package fmin362.resources;

import java.util.ArrayList;

import java.util.List;
import java.util.Date;

import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fmin362.model.Message;


@Path( "/messages" )

public class MessagesResource {
	
	@GET
	@Produces( MediaType.APPLICATION_JSON )
	public List listeMessage() throws Exception{
		
		/*List<Message> liste=new ArrayList<Message>();
		Message m1=new Message("bonjour toufik", new Date());
		Message m2=new Message("bonjour aicha", new Date(12/06/2012));
		Message m3=new Message("bonjour ubuntu", new Date(8/11/2012));
		
		liste.add(m1);
		liste.add(m2);
		liste.add(m3);
		return liste;*/
		
		UserTransaction utx = null;
        try {

            // Lookup
            InitialContext ic = new InitialContext();
            utx = ( UserTransaction ) ic.lookup( "java:comp/UserTransaction" );
            EntityManager em = ( EntityManager ) ic.lookup( "java:comp/env/persistence/EntityManager" );
        
           // Transaction begin
           utx.begin();
           em.joinTransaction();

           Message msg = new Message( "Hello World!", new Date() );
           Message m1=new Message("bonjour aicha", new Date(2012));
           em.persist( msg );
           em.persist(m1);

           List<Message> messages = em.createQuery( "select m from Message m" ).getResultList();

           utx.commit();

           return messages;
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

