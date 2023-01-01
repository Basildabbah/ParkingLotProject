package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.Parking;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import il.cshaifasweng.OCSFMediatorExample.entities.Price;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
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
        configuration.addAnnotatedClass(Parking.class);
        configuration.addAnnotatedClass(Price.class);
        ServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
    private static void initializeData(){
        Parking a1=new Parking("aaaa",3);
        Parking a2=new Parking("bbbb",5);
        Parking a3=new Parking("cccc",8);
        session.save(a1);
        session.save(a2);
        session.save(a3);
        session.flush();
        Price p1=new Price("mezdament","hours",8);
        Price p2=new Price("one time","hours",8);
        Price p3=new Price("client for one car","fixed for 60 hours",8);
        Price p4=new Price("client for more car","fixed for 54 hours* cars",8);
        Price p5=new Price("full subscribtion","fixed for 72 hours",8);

        session.save(p1);
        session.save(p2);
        session.save(p3);
        session.save(p4);
        session.save(p5);
        session.flush();
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
    public static void main( String[] args ) throws IOException
    {
        SessionFactory sessionFactory=getSessionFactory();
        session=sessionFactory.openSession();
        session.beginTransaction();
        initializeData();
        List<Parking> parks=getAll(Parking.class);
        System.out.println("parking list");
        for (Parking p:parks){
            System.out.format("Id: %d, Name: %s \n" ,p.getId(),p.getName());
        }
        if(session!=null) {
            session.close();
            session.getSessionFactory().close();
        }
        server = new SimpleServer(5555);
        server.listen();
    }
}
