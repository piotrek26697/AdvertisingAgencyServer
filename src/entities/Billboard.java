package entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@Entity
@Table(name="Billboards")
public class Billboard
{
    @Id
    @GeneratedValue
    private int id;

    private String address;

    @Transient
    @ManyToMany(fetch=FetchType.LAZY)
    private List<Advertisement> advertisementDisplayed;

    private BillboardSize billboardSize;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public List<Advertisement> getAdvertisementDisplayed()
    {
        return advertisementDisplayed;
    }

    public void setAdvertisementDisplayed(List<Advertisement> advertisementDisplayed)
    {
        this.advertisementDisplayed = advertisementDisplayed;
    }

    public BillboardSize getBillboardSize()
    {
        return billboardSize;
    }

    public void setBillboardSize(BillboardSize billboardSize)
    {
        this.billboardSize = billboardSize;
    }
}
