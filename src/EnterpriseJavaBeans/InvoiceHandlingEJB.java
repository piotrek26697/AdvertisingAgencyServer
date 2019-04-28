package EnterpriseJavaBeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class InvoiceHandlingEJB
{
    @PersistenceContext(name = "adAgencyPU")
    private EntityManager manager;

}
