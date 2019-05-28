package EnterpriseJavaBeans;

import entities.Advertisement;
import entities.Invoice;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Stateless
public class InvoiceHandlingEJB
{
    @PersistenceContext(name = "adAgencyPU")
    private EntityManager manager;

    public void create(Invoice invoice)
    {
        manager.persist(invoice);
    }

    public List<Invoice> getInvoiceList(int id)
    {
        String str = "select distinct i from Invoice i inner join i.advertisementList ad " +
                "inner join ad.client c where c.id="+id;

        Query query = manager.createQuery(str);
        @SuppressWarnings("unchecked")
        List<Invoice> list = query.getResultList();
        return list;
    }

    public List<Advertisement> getAds(int id)
    {
        Invoice invoice = manager.find(Invoice.class, id);
        List<Advertisement> list = Arrays.asList(new Advertisement[invoice.getAdvertisementList().size()]);
        Collections.copy(list, invoice.getAdvertisementList());
        return list;
    }
}
