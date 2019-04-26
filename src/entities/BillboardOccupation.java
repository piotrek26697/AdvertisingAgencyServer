package entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@Entity
@XmlRootElement
public class BillboardOccupation
{
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Billboard billboard;

    @ManyToOne(fetch = FetchType.EAGER)
    private Advertisement advertisement;

    private LocalDate dateFrom;

    private LocalDate dateTo;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Billboard getBillboard()
    {
        return billboard;
    }

    public void setBillboard(Billboard billboard)
    {
        this.billboard = billboard;
    }

    public Advertisement getAdvertisement()
    {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement)
    {
        this.advertisement = advertisement;
    }

    public LocalDate getDateFrom()
    {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom)
    {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo()
    {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo)
    {
        this.dateTo = dateTo;
    }
}
