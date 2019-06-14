package REST;

import EnterpriseJavaBeans.AdvertisementHandlingEJB;
import EnterpriseJavaBeans.BillboardOccupationHandlingEJB;
import entities.Advertisement;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.xml.bind.JAXB;
import java.io.InputStream;

/**
 * Class which handles requests for Advertisement class instances.
 */
@Path("/advertisement")
public class AdvertisementHandlingREST
{
    @EJB
    private AdvertisementHandlingEJB advertisementBean;

    @EJB
    private BillboardOccupationHandlingEJB billboardOccupationHandlingEJB;

    /*@GET
    public String getAdvertisements(@QueryParam("title") String title,
                                    @QueryParam("priceFrom") String priceFrom,
                                    @QueryParam("priceTo") String priceTo)
    {
        String params = "where name like '" + title + "%' and price >" + priceFrom + "and price < '" + priceTo;
        List<Advertisement>
    }*/

    /**
     * Method used to delete Advertisement record in database if there is no display history.
     *
     * @param id - identification of the Advertisement to remove, provided by Client application
     * @return status whether the operation succeed or not
     */
    @DELETE
    public String deleteAdvertisement(@QueryParam("advertisementID") int id)
    {
        if (billboardOccupationHandlingEJB.getBillboardOccupationListFor("adID", id, true).size() == 0)
        {
            advertisementBean.delete(id);
            return "0";
        } else
            return "-1";
    }

    /**
     * Method invoked to create a new record in database based on Advertisement class.
     *
     * @param in - object to be inserted into database represented as InputStream
     */
    @POST
    public void createAdvertisement(InputStream in)
    {
        Advertisement ad = JAXB.unmarshal(in, Advertisement.class);
        advertisementBean.create(ad);
    }

    /**
     * Method invoked to update already existing record in database.
     *
     * @param in - object with updated fields represented as InputStream
     */
    @PUT
    public void updateAdvertisement(InputStream in)
    {
        Advertisement ad = JAXB.unmarshal(in, Advertisement.class);
        advertisementBean.update(ad);
    }

   /* @GET
    @Path("/invoiceList")
    public String getInvoiceList(@QueryParam("advertisementID") int advertisementID)
    {
        List<Invoice> list = advertisementBean.getInvoiceList(advertisementID);
        StringWriter stringWriter = new StringWriter();
        Invoices invoices = new Invoices();
        invoices.setInvoiceList(list);
        JAXB.marshal(invoices, stringWriter);
        return stringWriter.toString();
    }*/

    /*@GET
    @Path("/client")
    public String getClient(@QueryParam("advertisementID") int id)
    {
        Client client = advertisementBean.getClient(id);
        StringWriter sw = new StringWriter();
        JAXB.marshal(client, sw);
        return sw.toString();
    }*/
}
