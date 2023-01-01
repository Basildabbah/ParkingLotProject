package il.cshaifasweng.OCSFMediatorExample.server;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    static Session session;

    public static Session getSession() {
        return session;
    }

    private static SessionFactory getSessionFactory(){
        Configuration configuration=new Configuration();
        ServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
    private static void initializeData(){

    }
    public static <T> List<T> getAll(Class<T> object){
        CriteriaBuilder builder=session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery=builder.createQuery(object);
        Root<T> rootEntry=criteriaQuery.from(object);
        CriteriaQuery<T> allCriteriaQuery=criteriaQuery.select(rootEntry);

        TypedQuery<T> allQuery =session.createQuery(allCriteriaQuery);
        return allQuery.getResultList();
    }

	private static SimpleServer server;
    public static void main( String[] args ) throws IOException {
        try {
            server = new SimpleServer(3000);
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();

            initializeData();

        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }

            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
                session.getSessionFactory().close();
            }
        }
        server.listen();
    }
}
