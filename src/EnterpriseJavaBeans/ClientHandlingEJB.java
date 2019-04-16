package EnterpriseJavaBeans;

import entities.Advertisement;
import entities.Client;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ClientHandlingEJB
{
    @PersistenceContext(name = "adAgencyPU")
    private EntityManager manager;

    public List<Client> get(String params)
    {
        String str = "select c from Client c " + params;
        Query query = manager.createQuery(str);
        @SuppressWarnings("unchecked")
        List<Client> list = query.getResultList();
        return list;
    }

    public List<Advertisement> getAdvertisementList(int id)
    {
        Client client = manager.find(Client.class, id);
        List<Advertisement> list = client.getAdsList();
        return list;
    }

    public void create(Client client)
    {
        manager.persist(client);
    }

    public void update(Client element)
    {
        manager.merge(element);
    }

    public void delete(int id)
    {
        Client client = manager.find(Client.class, id);
        manager.remove(client);
    }
}
