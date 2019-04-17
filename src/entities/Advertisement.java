package entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@Entity
@Table(name = "Advertisements")
public class Advertisement
{
    @Id
    @GeneratedValue
    private int id;

    private String description;

    private double price;

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    @Transient
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Invoice> invoiceList;

    @Transient
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Billboard> billboardList;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
