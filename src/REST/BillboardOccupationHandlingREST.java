package REST;

import EnterpriseJavaBeans.BillboardOccpupationHandlingEJB;
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
    private BillboardOccpupationHandlingEJB billboardOccupationBean;

    @POST
    public void createBillboardOccupation(InputStream in)
    {
        BillboardOccupation billboardOccupation = JAXB.unmarshal(in, BillboardOccupation.class);
        billboardOccupationBean.create(billboardOccupation);
    }
    @GET
    public String getBillboardOccupationListForAd(@QueryParam("id") int id)
    {
        List<BillboardOccupation> list = billboardOccupationBean.getBillboardOccupationListForAd(id);
        BillboardOccupations billboardOccupations = new BillboardOccupations();
        billboardOccupations.setList(list);
        StringWriter sw = new StringWriter();
        JAXB.marshal(billboardOccupations, sw);
        return sw.toString();
    }
}
