package EnterpriseJavaBeans;

import entities.Advertisement;
import entities.Billboard;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class BillboardHandlingEJB
{
    @PersistenceContext(name = "adAgencyPU")
    private EntityManager manager;

    public List<Billboard> getBillboardList(String params)
    {
        String str = "select b from Billboard b " + params;
        Query query = manager.createQuery(str);
        @SuppressWarnings("unchecked")
        List<Billboard> list = query.getResultList();
        return list;
    }

    public void update(Billboard billboard)
    {
        manager.merge(billboard);
    }

    public List<Advertisement> find(int billboardID)
    {
        return null;
    }

    public void create(Billboard billboard)
    {
        manager.persist(billboard);
    }
}
