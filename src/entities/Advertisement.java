package entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@Entity
@Table(name = "Advertisements")
public class Advertisement
{
    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String description;

    private double price;

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    @Transient
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Invoice> invoiceList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "advertisement")
    private List<BillboardOccupation> billboardOccupationList;

    public Advertisement()
    {
        billboardOccupationList = new ArrayList<>();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

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

    @XmlTransient
    public List<BillboardOccupation> getBillboardOccupationList()
    {
        return billboardOccupationList;
    }

    public void setBillboardOccupationList(List<BillboardOccupation> billboardOccupationList)
    {
        this.billboardOccupationList = billboardOccupationList;
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
