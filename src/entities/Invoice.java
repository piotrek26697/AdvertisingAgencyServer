package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Calendar;
import java.util.List;

@XmlRootElement
@Entity
public class Invoice
{
    @Id
    @GeneratedValue
    private int id;

    private double amount;

    private int tax;

    private Calendar date;

    @ManyToMany
    private List<Advertisement> advertisementList;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    public int getTax()
    {
        return tax;
    }

    public void setTax(int tax)
    {
        this.tax = tax;
    }

    public Calendar getDate()
    {
        return date;
    }

    public void setDate(Calendar date)
    {
        this.date = date;
    }

    public List<Advertisement> getAdvertisementList()
    {
        return advertisementList;
    }

    public void setAdvertisementList(List<Advertisement> advertisementList)
    {
        this.advertisementList = advertisementList;
    }
}
