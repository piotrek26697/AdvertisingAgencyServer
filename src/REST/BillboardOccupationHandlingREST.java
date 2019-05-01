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
    public String getBillboardOccupationListForAd(@QueryParam("ID") int id,
                                                  @QueryParam("type") String type)
    {
        List<BillboardOccupation> list;
        if (type.equals("adID"))
            list = billboardOccupationBean.getBillboardOccupationListFor("adID", id);
        else if(type.equals("billboardID"))
            list = billboardOccupationBean.getBillboardOccupationListFor("billboardID",id);
        else
            return null;
        BillboardOccupations billboardOccupations = new BillboardOccupations();
        billboardOccupations.setList(list);
        StringWriter sw = new StringWriter();
        JAXB.marshal(billboardOccupations, sw);
        return sw.toString();
    }
}
