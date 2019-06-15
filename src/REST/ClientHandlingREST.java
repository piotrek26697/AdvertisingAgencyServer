package REST;

import EnterpriseJavaBeans.AdvertisementHandlingEJB;
import EnterpriseJavaBeans.ClientHandlingEJB;
import collections.Advertisements;
import collections.Clients;
import entities.Advertisement;
import entities.Client;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.xml.bind.JAXB;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;

/**
 * Class which handles requests for Client class instances.
 */
@Path("/client")
public class ClientHandlingREST
{
    @EJB
    private ClientHandlingEJB clientBean;

    @EJB
    private AdvertisementHandlingEJB advertisementEJB;

    /**
     * Method invoked to create a new record in database based on Client class.
     *
     * @param inputStream Client class instance represented as InputStream
     */
    @POST
    public void createClient(InputStream inputStream)
    {
        Client client = JAXB.unmarshal(inputStream, Client.class);
        clientBean.create(client);
    }

    /**
     * Method invoked to get collection of Client class instances from database.
     *
     * @param name filtering parameter
     * @param lastName filtering parameter
     * @param address filtering parameter
     * @return collection of Client class instances
     */
    @GET
    public String getClients(@QueryParam("name") String name,
                             @QueryParam("lastName") String lastName,
                             @QueryParam("address") String address)
    {
        String params = "where name like '" + name + "%' and lastName like '" + lastName + "%' and address like '" + address + "%'";
        List<Client> list = clientBean.get(params);
        Clients clients = new Clients();
        clients.setClients(list);
        StringWriter writer = new StringWriter();
        JAXB.marshal(clients, writer);
        return writer.toString();
    }

    /**
     * Method invoked to get collection of Advertisement class instances for specified Client entity.
     *
     * @param id Client entity identifier
     * @return collection of Advertisement class instances
     */
    @GET
    @Path("/advertisementList")
    public String getAdvertisementList(@QueryParam("clientID") int id)
    {
        List<Advertisement> list = clientBean.getAdvertisementList(id);
        Advertisements ads = new Advertisements();
        ads.setAdsList(list);
        StringWriter writer = new StringWriter();
        JAXB.marshal(ads, writer);
        return writer.toString();
    }


    /*@GET
    @Path("/invoiceList")
    public String getIncoiveList(@QueryParam("clientID") int id)
    {
        List<Advertisement> adsList = clientBean.getAdvertisementList(id);
        List<Invoice> invoiceList = null;
        for (Advertisement ad : adsList)
        {
            invoiceList.addAll(advertisementEJB.getInvoiceList(ad.getId()));
        }
        StringWriter sw = new StringWriter();
        Invoices invoices = new Invoices();
        invoices.setInvoiceList(invoiceList);
        JAXB.marshal(invoices, sw);
        return sw.toString();
    }*/

    /*@PUT
    @Path("/advertisementAdd")
    public void updateAdvertisementList(@QueryParam("clientID") int id, InputStream inputStream)
    {
        Advertisement ad = JAXB.unmarshal(inputStream, Advertisement.class);
        List<Advertisement> list = clientBean.getAdvertisementList(id);
        Client client = clientBean.find(id);
        list.add(ad);
        client.setAdsList(list);
        clientBean.update(client);
    }*/

    /**
     * Method invoked to delete Client.
     *
     * @param id record identifier
     * @return status whether the removal succeed or not
     */
    @DELETE
    public String deleteClient(@QueryParam("id") int id)
    {
        List<Advertisement> list = clientBean.getAdvertisementList(id);
        if (list.size() == 0)
        {
            clientBean.delete(id);
            return "0";
        } else
        {
            return "-1";
        }
    }

    /**
     * Method invoked to update existing record of Client in database.
     *
     * @param inputStream Object with updated fields represented as InputStream
     */
    @PUT
    public void updateClient(InputStream inputStream)
    {
        Client client = JAXB.unmarshal(inputStream, Client.class);
        clientBean.update(client);
    }
}
