package REST;

import EnterpriseJavaBeans.AdvertisementHandlingEJB;
import EnterpriseJavaBeans.InvoiceHandlingEJB;
import collections.Advertisements;
import entities.Advertisement;
import entities.Invoice;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.xml.bind.JAXB;
import java.io.InputStream;
import java.io.StringReader;
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
        for(Advertisement ad : ads.getAdsList())
        {
            amount+= ad.getPrice();
        }
        invoice.setAmountBrutto(amount);
        invoice.setAmountNetto((100 - invoice.getTax()) * amount / 100);
        invoice.setAdvertisementList(ads.getAdsList());
        invoice.setDate(LocalDate.now());
        invoiceBean.create(invoice);
        for(Advertisement ad : ads.getAdsList())
        {
            List<Invoice> invoiceList = adBean.getInvoiceList(ad.getId());
            ad.setInvoiceList(invoiceList);
            ad.getInvoiceList().add(invoice);
            adBean.update(ad);
        }
    }

}
