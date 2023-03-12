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
 * A simple utility class that wraps over the org.hibernate library, also utilizes some org.h2 library functionality.
 * This utility is the main way to interact with the database.
 * It can save an object, return a list of objects, initialize the primary connection, or clear all records in the database.
 */
public class HibernateUtility {
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;
    private static Transaction transaction = null;
    private static Server server = null;

    /**
     * Utility class cannot have an instance. Throw exception if instantiated.
     */
    private HibernateUtility() {
        throw new java.lang.UnsupportedOperationException("This is a utility class and it cannot be instantiated.");
    }

    /**
     * Initializes the connection to the database.
     * @return the sessionFactory, which can be used if needed for further processing, like transactions.
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
     * Removes all records in the database.
     * DOES NOT RESET SEQUENCE COUNTER.
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
     * A methods that provides a way to save an object to the database.
     * Ideally the object that will be saved should be mapped within the code-base, and listed in the hibernate.cfg file.
     * @param obj the object to be saved to database.
     * @param <T> the type of the object to be saved to database.
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
     * Ideally the object that will be loaded should be mapped within the code-base, and listed in the hibernate.cfg file.
     * @param classType the object class type.
     * @param <T> the object type list.
     * @return the object list that will be returned based on the classType provided.
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
     * Closes the connection to the database.
     */
    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
