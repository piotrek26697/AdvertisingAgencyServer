package EnterpriseJavaBeans;

import entities.Advertisement;
import entities.Billboard;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class BillboardHandlingEJB
{
    @PersistenceContext(name = "adAgencyPU")
    private EntityManager manager;

    public void update(Billboard billboard)
    {
        manager.merge(billboard);
    }

    public List<Advertisement> find(int billboardID)
    {
        Billboard billboard = manager.find(Billboard.class, billboardID);
        List<Advertisement> list = billboard.getAdvertisementDisplayed();
        return list;
    }

    public void create(Billboard billboard)
    {
        manager.persist(billboard);
    }
}
