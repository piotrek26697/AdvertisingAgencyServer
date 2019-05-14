package EnterpriseJavaBeans;

import entities.BillboardOccupation;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class BillboardOccupationHandlingEJB
{
    @PersistenceContext(name = "adAgencyPU")
    private EntityManager manager;

    public void create(BillboardOccupation billboardOccupation)
    {
        manager.persist(billboardOccupation);
    }

    public List<BillboardOccupation> getBillboardOccupationListFor(String type, int id, boolean enableHistory)
    {
        String str;
        if (type.equals("adID"))
            str = "select b from BillboardOccupation b where b.advertisement.id = " + id;
        else if (type.equals("billboardID"))
        {
            if (enableHistory)
                str = "select b from BillboardOccupation b where b.billboard.id = " + id;
            else
                str = "select b from BillboardOccupation b where b.billboard.id =" + id+
                        " and b.dateTo >=" + LocalDate.now();
        } else return new ArrayList<>();
        Query query = manager.createQuery(str);
        @SuppressWarnings("unchecked")
        List<BillboardOccupation> list = query.getResultList();
        return list;
    }
}
