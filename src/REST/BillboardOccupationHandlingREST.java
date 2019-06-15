package REST;

import EnterpriseJavaBeans.BillboardOccupationHandlingEJB;
import collections.BillboardOccupations;
import entities.BillboardOccupation;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.xml.bind.JAXB;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;

/**
 * Class which handles requests for BillboardOccupation class instances.
 */
@Path("/billboardOccupation")
public class BillboardOccupationHandlingREST
{
    @EJB
    private BillboardOccupationHandlingEJB billboardOccupationBean;

    /**
     * Method invoked to create a new record in database based on BillboardOccupation class.
     *
     * @param in BillboardOccupation class instance represented as InputStream
     */
    @POST
    public void createBillboardOccupation(InputStream in)
    {
        BillboardOccupation billboardOccupation = JAXB.unmarshal(in, BillboardOccupation.class);
        billboardOccupationBean.create(billboardOccupation);
    }

    /**
     * Method invoked to download collection of BillboardOccupation class instances from database.
     *
     * @param id billboard or advertisement identifier
     * @param type specifies the class that parameter id belongs to
     * @param enableHistory allows to skip records which dates before current date
     * @return collection of BillboardOccupation class instances
     */
    @GET
    public String getBillboardOccupationListFor(@QueryParam("ID") int id,
                                                @QueryParam("type") String type,
                                                @QueryParam("enableHistory") boolean enableHistory)
    {
        List<BillboardOccupation> list;
        list = billboardOccupationBean.getBillboardOccupationListFor(type, id, enableHistory);

        BillboardOccupations billboardOccupations = new BillboardOccupations();
        billboardOccupations.setList(list);
        StringWriter sw = new StringWriter();
        JAXB.marshal(billboardOccupations, sw);
        return sw.toString();
    }
}
