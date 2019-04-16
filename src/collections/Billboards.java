package collections;

import entities.Billboard;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Billboards
{
    private List<Billboard> billboardList;

    public List<Billboard> getBillboardList()
    {
        return billboardList;
    }

    public void setBillboardList(List<Billboard> billboardList)
    {
        this.billboardList = billboardList;
    }
}
