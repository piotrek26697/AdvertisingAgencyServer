package EnterpriseJavaBeans;

import entities.Client;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ClientHandlingEJB
{
    @PersistenceContext(name = "adAgencyPU")
    EntityManager manager;

    public void create(Client client)
    {
        manager.persist(client);
    }
}
