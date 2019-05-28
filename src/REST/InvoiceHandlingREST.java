package REST;

import EnterpriseJavaBeans.AdvertisementHandlingEJB;
import EnterpriseJavaBeans.InvoiceHandlingEJB;
import collections.Advertisements;
import collections.Invoices;
import entities.Advertisement;
import entities.Invoice;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.xml.bind.JAXB;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Path("/invoice")
public class InvoiceHandlingREST
{
    @EJB
    private InvoiceHandlingEJB invoiceBean;

    @EJB
    private AdvertisementHandlingEJB adBean;

    @POST
    public void createInvoice(InputStream inputStream)
    {
        Advertisements ads = JAXB.unmarshal(inputStream, Advertisements.class);
        Invoice invoice = new Invoice();
        double amount = 0;
        for (Advertisement ad : ads.getAdsList())
        {
            amount += ad.getPrice();
        }
        invoice.setAmountBrutto(amount);
        invoice.setAmountNetto((100 - invoice.getTax()) * amount / 100);
        invoice.setAdvertisementList(ads.getAdsList());
        invoice.setDate(LocalDate.now());
        invoiceBean.create(invoice);
        for (Advertisement ad : ads.getAdsList())
        {
            List<Invoice> invoiceList = adBean.getInvoiceList(ad.getId());
            ad.setInvoiceList(invoiceList);
            ad.getInvoiceList().add(invoice);
            adBean.update(ad);
        }
    }

    @GET
    public String getInvoices(@QueryParam("ID") int id)
    {
        List<Invoice> list = invoiceBean.getInvoiceList(id);
        StringWriter sw = new StringWriter();
        Invoices invoices = new Invoices();
        invoices.setInvoiceList(list);
        JAXB.marshal(invoices, sw);
        return sw.toString();
    }

    @GET
    @Path("/ads")
    public String getAdsForInvoice(@QueryParam("ID") int id)
    {
        List<Advertisement> list = invoiceBean.getAds(id);
        Advertisements advertisements = new Advertisements();
        advertisements.setAdsList(list);
        StringWriter sw = new StringWriter();
        JAXB.marshal(advertisements, sw);
        return sw.toString();
    }
}
