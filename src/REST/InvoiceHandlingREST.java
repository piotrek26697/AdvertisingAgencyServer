package REST;

import EnterpriseJavaBeans.InvoiceHandlingEJB;
import collections.Advertisements;
import entities.Advertisement;
import entities.Invoice;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.xml.bind.JAXB;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;

@Path("/invoice")
public class InvoiceHandlingREST
{
    @EJB
    private InvoiceHandlingEJB invoiceBean;

    @POST
    public void createInvoice(InputStream in)
    {
        Invoice invoice = JAXB.unmarshal(in, Invoice.class);
        invoiceBean.create(invoice);
    }

    @PUT
    public void updateInvoice(InputStream in)
    {
        Invoice invoice = JAXB.unmarshal(in, Invoice.class);
        invoiceBean.update(invoice);
    }

    @GET
    public String getAdvertisementList(@QueryParam("invoiceID") int invoiceID)
    {
        List<Advertisement> list = invoiceBean.getAdvertisementList(invoiceID);
        Advertisements ads = new Advertisements();
        StringWriter sw = new StringWriter();
        ads.setAdsList(list);
        JAXB.marshal(ads, sw);
        return sw.toString();
    }
}
