package collections;

import entities.Invoice;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Invoices
{
    List<Invoice> invoiceList;

    public List<Invoice> getInvoiceList()
    {
        return invoiceList;
    }

    public void setInvoiceList(List<Invoice> invoiceList)
    {
        this.invoiceList = invoiceList;
    }
}
