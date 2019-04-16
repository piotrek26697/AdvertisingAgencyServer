package REST;

import EnterpriseJavaBeans.AdvertisementHandlingEJB;
import EnterpriseJavaBeans.ClientHandlingEJB;
import collections.Advertisements;
import collections.Clients;
import collections.Invoices;
import entities.Advertisement;
import entities.Client;
import entities.Invoice;

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
    private ClientHandlingEJB clientBean;

    @EJB
    private AdvertisementHandlingEJB advertisementEJB;

    @POST
    public void createClient(InputStream inputStream)
    {
        Client client = JAXB.unmarshal(inputStream, Client.class);
        clientBean.create(client);
    }

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

    @GET
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
    }

    @DELETE
    public void deleteClient(@QueryParam("id") int id)
    {
        clientBean.delete(id);
    }

    @PUT
    public void updateClient(InputStream inputStream)
    {
        Client client = JAXB.unmarshal(inputStream, Client.class);
        clientBean.update(client);
    }
}
