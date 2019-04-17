package entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Calendar;
import java.util.List;

@XmlRootElement
@Entity
@Table(name = "Advertisements")
public class Advertisement
{
    @Id
    @GeneratedValue
    private int id;

    private Calendar dateFrom;

    private Calendar dateTo;

    private double price;

    @ManyToOne(fetch=FetchType.EAGER)
    private Client client;

    @ManyToMany(fetch=FetchType.LAZY)
    private List<Invoice> invoiceList;

    @ManyToMany(fetch=FetchType.LAZY)
    private List<Billboard> billboardList;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Calendar getDateFrom()
    {
        return dateFrom;
    }

    public void setDateFrom(Calendar dateFrom)
    {
        this.dateFrom = dateFrom;
    }

    public Calendar getDateTo()
    {
        return dateTo;
    }

    public void setDateTo(Calendar dateTo)
    {
        this.dateTo = dateTo;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public Client getClient()
    {
        return client;
    }

    public void setClient(Client client)
    {
        this.client = client;
    }

    public List<Invoice> getInvoiceList()
    {
        return invoiceList;
    }

    public void setInvoiceList(List<Invoice> invoice)
    {
        this.invoiceList = invoice;
    }

    public List<Billboard> getBillboardList()
    {
        return billboardList;
    }

    public void setBillboardList(List<Billboard> billboardList)
    {
        this.billboardList = billboardList;
    }
}
