package pl.spot.dbk.faces.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.spot.dbk.faces.CommonUtil;
import pl.spot.dbk.faces.HibernateUtil;
import pl.spot.dbk.faces.hib.Role;
import pl.spot.dbk.faces.hib.SalePoint;
import pl.spot.dbk.faces.hib.User;

@ManagedBean(name = "userBean")
@ViewScoped
public class UserBean {
    Logger log = LoggerFactory.getLogger(UserBean.class);

    private int id_u;
    private String name;
    private String surname;
    private String password;
    private int blocked_points;
    private Role role = new Role();
    private SalePoint registerPoint = new SalePoint();
    private int registerPoint_id;
    private SalePoint workSalePoint = new SalePoint();
    private int workSalePoint_id;

    private List<User> list;

    public UserBean() {}

    public int getId_u() {
        return id_u;
    }

    public void setId_u(int id_u) {
        this.id_u = id_u;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getBlocked_points() {
        return blocked_points;
    }

    public void setBlocked_points(int blocked_points) {
        this.blocked_points = blocked_points;
    }

    @SuppressWarnings("unchecked")
    public List<User> getList() {
        log.info("##getList");
        if (list == null) {
            log.warn("##Null list");
            Session session = HibernateUtil.getSessionFactory().openSession();

            List<User> newList = session.createCriteria(User.class).list();
            log.info("##Size of list:" + newList.size());
            this.setList(newList);
            session.close();
        }
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
        log.info("list set");
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        log.info("## setRole: " + role.toString());
        this.role = role;
    }

    public void validateRole(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (!(value instanceof Role)) { throw new ValidatorException(new FacesMessage("Zły obiekt",
                "Obiekt nie jest obiektem klasy Rola")); }
    }

    public SalePoint getRegisterPoint() {
        return registerPoint;
    }

    public void setRegisterPoint(SalePoint registerPoint) {
        log.info("## setRegPoint: " + registerPoint.toString());
        this.registerPoint = registerPoint;
    }

    public int getRegisterPoint_id() {
        return registerPoint_id;
    }

    public void setRegisterPoint_id(int registerPoint_id) {
        this.registerPoint_id = registerPoint_id;
    }

    public SalePoint getWorkSalePoint() {
        return workSalePoint;
    }

    public void setWorkSalePoint(SalePoint workSalePoint) {
        log.info("## setWorkPoint: " + workSalePoint.toString());
        this.workSalePoint = workSalePoint;
    }

    public int getWorkSalePoint_id() {
        return workSalePoint_id;
    }

    public void setWorkSalePoint_id(int workSalePoint_id) {
        this.workSalePoint_id = workSalePoint_id;
    }

    public boolean save() {
        log.info("## Saving user");
        Session session = HibernateUtil.getSessionFactory().openSession();
        User u = prepareNewUser();

        Transaction tx = null;
        boolean ret = false;
        try {
            tx = session.beginTransaction();
            session.save(u);
            tx.commit();
            ret = true;
        } catch (Exception e) {
            ret = false;
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        log.info("## Saved user");
        return ret;
    }

    private User prepareNewUser() {
        User u = new User();
        u.setLast_login(CommonUtil.getCurrTimestamp());
        u.setName(this.name);
        u.setPassword(this.password);
        u.setRole(this.role);
        u.setSurname(this.surname);
        u.setRegisterPoint(this.registerPoint);
        u.setWorkSalePoint(this.workSalePoint);

        return u;
    }
}
