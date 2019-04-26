package EnterpriseJavaBeans;

import entities.Advertisement;
import entities.Billboard;
import entities.Client;
import entities.Invoice;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    public List<Billboard> getBillboardList(int id)
    {
        Advertisement ad = manager.find(Advertisement.class, id);
        return null;
    }

    public Client getClient(int id)
    {
        Advertisement ad = manager.find(Advertisement.class, id);
        return ad.getClient();
    }
}
