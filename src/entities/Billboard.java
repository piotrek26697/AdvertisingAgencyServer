package entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
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

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "billboard")
    private List<BillboardOccupation> billboardOccupationList;

    @Enumerated(EnumType.STRING)
    private BillboardSize billboardSize;

    public Billboard()
    {
        billboardOccupationList = new ArrayList<>();
    }

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

    @XmlTransient
    public List<BillboardOccupation> getBillboardOccupationList()
    {
        return billboardOccupationList;
    }

    public void setBillboardOccupationList(List<BillboardOccupation> billboardOccupationList)
    {
        this.billboardOccupationList = billboardOccupationList;
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
