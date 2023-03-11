package lt.viko.eif.stermen.project1.utility;

import lt.viko.eif.stermen.project1.domain.airport.Airport;
import org.h2.tools.Server;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

/**
 *
 */
public class HibernateUtility {
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;
    private static Transaction transaction = null;
    private static Server server = null;

    /**
     *
     */
    private HibernateUtility() {
        throw new java.lang.UnsupportedOperationException("This is a utility class and it cannot be instantiated.");
    }

    /**
     * @return
     */
    public static SessionFactory getConnectionHandler() {
        if (sessionFactory == null) {
            try {
                registry = new StandardServiceRegistryBuilder().configure().build();

                MetadataSources sources = new MetadataSources(registry);

                Metadata metadata = sources.getMetadataBuilder().build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                e.printStackTrace();
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }

    /**
     *
     */
    public static void clearDatabase() {
        List<Airport> items = loadObjects(Airport.class);
        try (Session session = HibernateUtility.getConnectionHandler().openSession()) {
            for (Airport item : items) {
                transaction = session.beginTransaction();
                session.delete(item);
                server = Server.createTcpServer().start();
                transaction.commit();
                server.shutdown();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * @param obj
     * @param <T>
     */
    public static <T> void saveObject(T obj) {
        try (Session session = HibernateUtility.getConnectionHandler().openSession()) {
            transaction = session.beginTransaction();
            session.save(obj);
            transaction.commit();
            System.out.println("Transaction committed successfully.");
        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("Transaction rolled back.");
                transaction.rollback();
            }
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @param classType
     * @param <T>
     * @return
     */
    public static <T> List<T> loadObjects(Class<T> classType) {
        try (Session session = HibernateUtility.getConnectionHandler().openSession()) {
            transaction = session.beginTransaction();
            String query = "from " + classType.getSimpleName() + " ";
            List<T> nl = session.createQuery(query, classType).list();
            Hibernate.initialize(nl = session.createQuery(query, classType).list());
            server = Server.createTcpServer().start();
            transaction.commit();
            server.shutdown();
            return nl;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     */
    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
