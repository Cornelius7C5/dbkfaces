package pl.spot.dbk.faces;
 
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
 
/**
 * @author onlinetechvision.com
 * @since 3 Oct 2011
 * @version 1.0.0
 *
 */
public class HibernateUtil {
 
    private static SessionFactory sessionFactory = null;
 
    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
 
    public static void setSessionFactory(SessionFactory sessionFactory) {
        HibernateUtil.sessionFactory = sessionFactory;
    }
 
}