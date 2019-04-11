package collections;

import entities.Client;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement
public class Clients implements Serializable
{
    List<Client> clientList;

    public Clients(List<Client> clientList)
    {
        this.clientList = clientList;
    }

    public List<Client> getClientList()
    {
        return clientList;
    }

    public void setClientList(List<Client> clientList)
    {
        this.clientList = clientList;
    }
}
