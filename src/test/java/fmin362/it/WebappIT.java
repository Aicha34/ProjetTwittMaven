package fmin362.it;

import junit.framework.TestCase;
import java.util.*;
import java.net.URL;
import java.net.HttpURLConnection;
import org.junit.*;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class WebappIT extends TestCase
{
    private String baseUrl;
    
    public void setUp() throws Exception
    {
        super.setUp();
        String port = System.getProperty("servlet.port");
        this.baseUrl = "http://localhost:" + port + "/ProjetTwittMaven";
    }

    /*@Test
    public void testUser() throws Exception
    {
    	//Test de la connextion de l'utilisateur
        URL url = new URL(this.baseUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
//        assertEquals(200, connection.getResponseCode());
        
      //Test des attributs de User
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put( JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE );
        Client client = Client.create( clientConfig );
        WebResource webResource = client.resource( url.toURI() );

        List<Map<String,?>> result = webResource.get( List.class );
        System.out.println( result );

        assertEquals( true, result.size()>1 );//on suppose qu'il y ait plus d'un compte
        assertNotNull(result.get(0).get("login"));//on suppose que le premier elt du compte est non null
        assertNotNull(result.get(0).get("password"));//on suppose que le premier elt de la table est non null
        assertNotNull(result.get(0).get("firstName"));//on suppose que le premier elt de la table est non null
        assertNotNull(result.get(0).get("lastName"));//on suppose que le premier elt de la table est non null
        assertNotNull(result.get(0).get("email"));
        assertNotNull(result.get(0).get("dateOfBirth"));
        assertNotNull(result.get(0).get("age"));
    }*/
    
    @Test
    public void testMessage() throws Exception
    {
    	//Test de la connextion
        URL url = new URL(this.baseUrl + "/messages");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        assertEquals(200, connection.getResponseCode());
        
        
      //Test du contenu du message
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put( JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE );
        Client client = Client.create( clientConfig );
        WebResource webResource = client.resource( url.toURI() );

        List<Map<String,?>> result = webResource.get(List.class);
        System.out.println( result );

        assertEquals( true, result.size()>1 );//on suppose qu'il y ait plus d'1 message dans le compte
        assertNotNull(result.get(0).get("label"));//on suppose qu'il existe au moins un message     
        assertNotNull(result.get(0).get("date"));
        assertNull(result.get(0).get("time"));
    }
}
