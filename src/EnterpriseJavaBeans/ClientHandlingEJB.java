package EnterpriseJavaBeans;

import entities.Client;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ClientHandlingEJB implements HandlingEJB<Client>
{
    @PersistenceContext(name = "adAgencyPU")
    private EntityManager manager;

    @Override
    public List<Client> get(String params)
    {
        String str = "select c from Client c " + params;
        Query query = manager.createQuery(str);
        @SuppressWarnings("unchecked")
        List<Client> list = query.getResultList();
        return list;
    }

    @Override
    public Client find(int id)
    {
        return null;
    }

    @Override
    public void create(Client client)
    {
        manager.persist(client);
    }

    @Override
    public void update(Client element)
    {
        manager.merge(element);
    }

    @Override
    public void delete(int id)
    {
        Client client = manager.find(Client.class, id);
        manager.remove(client);
    }
}
