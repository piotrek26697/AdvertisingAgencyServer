package EnterpriseJavaBeans;

import entities.BillboardOccupation;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class BillboardOccpupationHandlingEJB
{
    @PersistenceContext(name = "adAgencyPU")
    private EntityManager manager;

    public void create(BillboardOccupation billboardOccupation)
    {
        manager.persist(billboardOccupation);
    }

    public List<BillboardOccupation> getBillboardOccupationListForAd(int id)
    {
        String str = "select b from BillboardOccupation b where b.advertisement.id = "+id;
        Query query = manager.createQuery(str);
        @SuppressWarnings("unchecked")
        List<BillboardOccupation> list = query.getResultList();
        return list;
    }
}
