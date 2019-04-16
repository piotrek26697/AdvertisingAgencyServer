package collections;

import entities.Advertisement;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Advertisements
{
    private List<Advertisement> adsList;

    public List<Advertisement> getAdsList()
    {
        return adsList;
    }

    public void setAdsList(List<Advertisement> adsList)
    {
        this.adsList = adsList;
    }
}
