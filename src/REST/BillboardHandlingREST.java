package REST;

import EnterpriseJavaBeans.BillboardHandlingEJB;
import collections.Advertisements;
import entities.Advertisement;
import entities.Billboard;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.xml.bind.JAXB;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;

@Path("/billboard")
public class BillboardHandlingREST
{
    @EJB
    private BillboardHandlingEJB billboardBean;

    @POST
    public void createBillboard(InputStream in)
    {
        Billboard billboard = JAXB.unmarshal(in, Billboard.class);
        billboardBean.create(billboard);
    }

    @PUT
    public void billboardUpdate(InputStream inputStream)
    {
        Billboard billboard = JAXB.unmarshal(inputStream, Billboard.class);
        billboardBean.update(billboard);
    }

    @GET
    public String getAdvertisementList(@QueryParam("billboardID") int billboardID)
    {
        List<Advertisement> list = billboardBean.find(billboardID);
        Advertisements ads = new Advertisements();
        ads.setAdsList(list);
        StringWriter sw = new StringWriter();
        JAXB.marshal(ads, sw);
        return sw.toString();
    }
}
