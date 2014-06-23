package in.bruteforce;

import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.service.*;

public class DatabaseUtil {
    private static final SessionFactory sessionFactory;

    static
    {
        try
        {

            Configuration myConfiguration = new Configuration().configure();
            ServiceRegistry myServiceRegistry = new ServiceRegistryBuilder().applySettings(myConfiguration.getProperties())
                    .buildServiceRegistry();
            sessionFactory = myConfiguration.buildSessionFactory(myServiceRegistry);

        }
        catch (Throwable ex)
        {
            // Log exception!
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession()
            throws HibernateException
    {
        return sessionFactory.openSession();
    }

    public static void close()
    {
        sessionFactory.close();
    }
}
