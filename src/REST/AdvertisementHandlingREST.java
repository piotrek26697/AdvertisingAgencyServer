package REST;

import EnterpriseJavaBeans.AdvertisementHandlingEJB;
import collections.Billboards;
import collections.Invoices;
import entities.Advertisement;
import entities.Billboard;
import entities.Client;
import entities.Invoice;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.xml.bind.JAXB;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;

@Path("/advertisement")
public class AdvertisementHandlingREST
{
    @EJB
    private AdvertisementHandlingEJB advertisementBean;

    @POST
    public void createAdvertisement(InputStream in)
    {
        Advertisement ad = JAXB.unmarshal(in, Advertisement.class);
        advertisementBean.create(ad);
    }

    @GET
    @Path("/invoiceList")
    public String getInvoiceList(@QueryParam("advertisementID") int advertisementID)
    {
        List<Invoice> list = advertisementBean.getInvoiceList(advertisementID);
        StringWriter stringWriter = new StringWriter();
        Invoices invoices = new Invoices();
        invoices.setInvoiceList(list);
        JAXB.marshal(invoices, stringWriter);
        return stringWriter.toString();
    }

    @GET
    @Path("/billboardList")
    public String getBillboardList(@QueryParam("advertisementID") int advertisementID)
    {
        List<Billboard> list = advertisementBean.getBillboardList(advertisementID);
        Billboards billboards = new Billboards();
        billboards.setBillboardList(list);
        StringWriter sw = new StringWriter();
        JAXB.marshal(billboards, sw);
        return sw.toString();
    }

    @GET
    @Path("/client")
    public String getClient(@QueryParam("advertisementID") int id)
    {
        Client client = advertisementBean.getClient(id);
        StringWriter sw = new StringWriter();
        JAXB.marshal(client, sw);
        return sw.toString();
    }
}
