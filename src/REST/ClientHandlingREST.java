package REST;

import EnterpriseJavaBeans.ClientHandlingEJB;
import entities.Client;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/client")
public class ClientHandlingREST
{
    @EJB
    private ClientHandlingEJB bean;

    @GET
    @Path("/test")
    public String test()
    {
        Client client = new Client();
        client.setName("imie1");
        client.setLastName("nazwisko1");
        client.setAddress("adres1");
        bean.create(client);
        return "dziala";
    }
}
