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

    
}
