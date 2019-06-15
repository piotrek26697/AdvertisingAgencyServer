package REST;

import EnterpriseJavaBeans.BillboardHandlingEJB;
import collections.Billboards;
import entities.Billboard;

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
 * Class which handles requests for Billboard class instances.
 */
@Path("/billboard")
public class BillboardHandlingREST
{
    @EJB
    private BillboardHandlingEJB billboardBean;

    /**
     * Method invoked to create a new record in database based on Billboard class.
     *
     * @param in Billboard class instance represented as InputStream
     */
    @POST
    public void createBillboard(InputStream in)
    {
        Billboard billboard = JAXB.unmarshal(in, Billboard.class);
        billboardBean.create(billboard);
    }

    /**
     * Method invoked to download records from "Billboards" table.
     *
     * @param address filtering parameter
     * @param size filtering parameter
     * @return collection of Billboard instances
     */
    @GET
    public String getBillboardList(@QueryParam("address") String address, @QueryParam("size") String size)
    {
        if (size.isEmpty())
            size = "%";
        String params = "where address like '" + address + "%' and billboardSize like '" + size + "'";
        List<Billboard> list = billboardBean.getBillboardList(params);
        Billboards billboards = new Billboards();
        billboards.setBillboardList(list);
        StringWriter sw = new StringWriter();
        JAXB.marshal(billboards, sw);
        return sw.toString();
    }

    /*@GET
    @Path("/advertisementList")
    public String getAdvertisementList(@QueryParam("billboardID") int billboardID)
    {
        List<Advertisement> list = billboardBean.find(billboardID);
        Advertisements ads = new Advertisements();
        ads.setAdsList(list);
        StringWriter sw = new StringWriter();
        JAXB.marshal(ads, sw);
        return sw.toString();
    }*/
}
