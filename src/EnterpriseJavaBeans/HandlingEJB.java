package EnterpriseJavaBeans;

import java.util.List;

public interface HandlingEJB<T>
{
    public List<T> get(String params);

    public T find(int id);

    public void create(T element);

    public void update(T element);

    public void delete(int id);
}
