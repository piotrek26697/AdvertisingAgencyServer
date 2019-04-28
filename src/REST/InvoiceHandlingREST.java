package REST;

import EnterpriseJavaBeans.InvoiceHandlingEJB;

import javax.ejb.EJB;
import javax.ws.rs.Path;

@Path("/invoice")
public class InvoiceHandlingREST
{
    @EJB
    private InvoiceHandlingEJB invoiceBean;

    
}
