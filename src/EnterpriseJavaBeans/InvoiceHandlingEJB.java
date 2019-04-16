package EnterpriseJavaBeans;

import entities.Advertisement;
import entities.Invoice;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class InvoiceHandlingEJB
{
    @PersistenceContext(name = "adAgencyPU")
    private EntityManager manager;


    public List<Advertisement> getAdvertisementList(int invoiceID)
    {
        Invoice invoice = manager.find(Invoice.class, invoiceID);
        return invoice.getAdvertisementList();
    }
}
