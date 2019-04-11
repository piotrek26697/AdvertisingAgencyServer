package REST;

import EnterpriseJavaBeans.HandlingEJB;
import collections.Clients;
import entities.Client;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.xml.bind.JAXB;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;

@Path("/client")
public class ClientHandlingREST
{
    @EJB
    private HandlingEJB<Client> bean;

    @POST
    public void create(InputStream inputStream)
    {
        Client client = JAXB.unmarshal(inputStream, Client.class);
        bean.create(client);
    }

    @GET
    public String get(@QueryParam("name") String name,
                      @QueryParam("lastName") String lastName,
                      @QueryParam("address") String address)
    {
        String params = "where name like '" + name + "%' and lastName like '" + lastName + "%' and address like '" + address + "%'";
        List<Client> list = bean.get(params);
        Clients clients = new Clients();
        clients.setClientList(list);
        StringWriter writer = new StringWriter();
        JAXB.marshal(clients, writer);
        return writer.toString();
    }

    @DELETE
    public void delete(@QueryParam("id") int id)
    {
        bean.delete(id);
    }

    @PUT
    public void update(InputStream inputStream)
    {
        Client client = JAXB.unmarshal(inputStream, Client.class);
        bean.update(client);
    }
}
