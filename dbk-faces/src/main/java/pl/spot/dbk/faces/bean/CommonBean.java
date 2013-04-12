package pl.spot.dbk.faces.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.spot.dbk.faces.HibernateUtil;
import pl.spot.dbk.faces.hib.Role;
import pl.spot.dbk.faces.hib.SalePoint;

@ManagedBean(name = "commonBean")
@RequestScoped
public class CommonBean {
    Logger log = LoggerFactory.getLogger(Navigator.class);

    private List<SalePoint> listSP;
    private List<Role> listRoles;

    public SalePoint getSalePoint(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        SalePoint sp = (SalePoint) session.get(SalePoint.class, new Integer(id));
        session.close();
        return sp;
    }

    public Role getRole(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Role role = (Role) session.get(Role.class, new Integer(id));
        session.close();
        return role;
    }

    @SuppressWarnings("unchecked")
    public List<SalePoint> getListSP() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<SalePoint> sps = session.createCriteria(SalePoint.class).list();
        session.close();
        this.listSP = sps;
        log.info("##SalePoints: " + sps.size());
        return this.listSP;
    }

    public void setListSP(List<SalePoint> listSP) {
        this.listSP = getListSP();
    }

    @SuppressWarnings("unchecked")
    public List<Role> getListRoles() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Role> roles = session.createCriteria(Role.class).list();
        session.close();
        this.listRoles = roles;
        log.info("##Roles: " + roles.size());
        return this.listRoles;
    }

    public void setListRoles(List<Role> listRoles) {
        this.listRoles = getListRoles();
    }

}
