package entities;

import model.LocalDateAdapter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "Invoices")
public class Invoice
{
    @Id
    @GeneratedValue
    private int id;

    private double amount;

    private int tax;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate date;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "invoiceList")
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

    public LocalDate getDate()
    {
        return date;
    }

    public void setDate(LocalDate date)
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
