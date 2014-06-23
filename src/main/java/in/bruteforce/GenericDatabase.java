package in.bruteforce;

import org.hibernate.*;

import java.util.*;

public class GenericDatabase {

    private Session session;

    private Transaction transaction;

    public <T> void save( T aToBeSaved)
    {
        session = DatabaseUtil.getSession();
        transaction = session.beginTransaction();
        session.save(aToBeSaved);
        transaction.commit();
        session.close();
    }

    public <T> void persist(T aToBeSaved)
    {
        session = DatabaseUtil.getSession();
        transaction = session.beginTransaction();
        session.persist(aToBeSaved);
        transaction.commit();
        session.close();
    }

    @SuppressWarnings({"List conversion", "unchecked"})
    public <T> List<T> retrieve(
            String queryName,
            Map<String, String> parameters)
    {
        session = DatabaseUtil.getSession();
        transaction = session.beginTransaction();
        Query myQuery = session.getNamedQuery(queryName);
        for (String parameter : parameters.keySet())
        {
            myQuery.setString(parameter, parameters.get(parameter));
        }
        List<T> recordSet = myQuery.list();
        session.close();

        return recordSet;
    }

    @SuppressWarnings({"List conversion", "unchecked"})
    public <T> List<T> retrieve(String queryName)
    {
        session = DatabaseUtil.getSession();
        transaction = session.beginTransaction();
        Query myQuery = session.getNamedQuery(queryName);
        List<T> recordSet = myQuery.list();
        session.close();

        return recordSet;
    }

    public <T> List<T> retrieve(String queryName,
                                String parameter,
                                String value)
    {
        session = DatabaseUtil.getSession();
        transaction = session.beginTransaction();
        Query myQuery = session.getNamedQuery(queryName);
        myQuery.setString(parameter, value);

        List<T> recordSet = myQuery.list();
        session.close();

        return recordSet;
    }

    public <T> void saveOrUpdate( T aToBeSaved,
                                 Class<T> aClass)
    {
        session = DatabaseUtil.getSession();
        transaction = session.beginTransaction();
        session.saveOrUpdate(aToBeSaved);
        transaction.commit();
        session.close();
    }

    public <T> void saveOrUpdate( Collection<T> aToBeSaved,
                                 Class<T> aClass)
    {
        session = DatabaseUtil.getSession();
        transaction = session.beginTransaction();
        for(T aObject : aToBeSaved)
        {
            session.saveOrUpdate(aObject);
            session.flush();
            session.clear();
        }
        transaction.commit();
        session.close();
    }

    public <T> void delete( T aToBeSaved,
                           Class<T> aClass)
    {
        session = DatabaseUtil.getSession();
        transaction = session.beginTransaction();
        session.delete(aToBeSaved);
        transaction.commit();
        session.close();
    }

}
