package EnterpriseJavaBeans;

import entities.Advertisement;
import entities.Client;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.Collections;
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
//        List<Advertisement> list = Arrays.asList(new Advertisement[client.getAdsList().size()]);
//        Collections.copy(list, client.getAdsList());
        client.getAdsList().size();
        return client.getAdsList();
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

    public Client find(int id)
    {
        return manager.find(Client.class, id);
    }
}
