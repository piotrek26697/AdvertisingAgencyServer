package REST;

import EnterpriseJavaBeans.InvoiceHandlingEJB;
import collections.Advertisements;
import entities.Advertisement;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.xml.bind.JAXB;
import java.io.StringWriter;
import java.util.List;

@Path("/invoice")
public class InvoiceHandlingREST
{
    @EJB
    private InvoiceHandlingEJB invoiceBean;

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
