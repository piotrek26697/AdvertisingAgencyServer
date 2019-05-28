package EnterpriseJavaBeans;

import entities.Advertisement;
import entities.Billboard;
import entities.Client;
import entities.Invoice;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Stateless
public class AdvertisementHandlingEJB
{
    @PersistenceContext(name = "adAgencyPU")
    private EntityManager manager;

    public List<Advertisement> get(String params)
    {
        return null;
    }

    public List<Invoice> getInvoiceList(int id)
    {
        Advertisement ad = manager.find(Advertisement.class, id);
//        List<Invoice> list = Arrays.asList(new Invoice[ad.getInvoiceList().size()]);
//        Collections.copy(list, ad.getInvoiceList());
        ad.getInvoiceList().size();
        return ad.getInvoiceList();
    }

    public void create(Advertisement ad)
    {
        manager.persist(ad);
    }

    public void update(Advertisement element)
    {
        manager.merge(element);
    }

    public void delete(int id)
    {
        Advertisement ad = manager.find(Advertisement.class, id);
        manager.remove(ad);
    }

    /*public Client getClient(int id)
    {
        Advertisement ad = manager.find(Advertisement.class, id);
        return ad.getClient();
    }*/
}
