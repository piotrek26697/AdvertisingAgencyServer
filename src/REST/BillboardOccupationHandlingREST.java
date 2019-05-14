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

@Path("/billboardOccupation")
public class BillboardOccupationHandlingREST
{
    @EJB
    private BillboardOccupationHandlingEJB billboardOccupationBean;

    @POST
    public void createBillboardOccupation(InputStream in)
    {
        BillboardOccupation billboardOccupation = JAXB.unmarshal(in, BillboardOccupation.class);
        billboardOccupationBean.create(billboardOccupation);
    }

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
