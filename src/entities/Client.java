package entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement
@Entity
@Table(name = "Clients")
public class Client implements Serializable
{
    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String lastName;

    private String address;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Advertisement> adsList;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public List<Advertisement> getAdsList()
    {
        return adsList;
    }

    public void setAdsList(List<Advertisement> adsList)
    {
        this.adsList = adsList;
    }
}
