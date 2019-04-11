package collections;

import entities.Client;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Clients implements Serializable
{
    List<Client> clients = new ArrayList<>();

    public List<Client> getClients()
    {
        return clients;
    }

    public void setClients(List<Client> clients)
    {
        this.clients = clients;
    }
}
