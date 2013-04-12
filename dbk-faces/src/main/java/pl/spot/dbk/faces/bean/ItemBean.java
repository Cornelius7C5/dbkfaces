package pl.spot.dbk.faces.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.spot.dbk.faces.HibernateUtil;
import pl.spot.dbk.faces.hib.Item;

@ManagedBean(name = "itemBean", eager = true)
@ViewScoped
public class ItemBean implements Serializable {
    private static final long serialVersionUID = 6950897039685041858L;

    Logger log = LoggerFactory.getLogger(ItemBean.class);

    private int id_i;
    private String name = "Klik";
    private int cost;

    private List<Item> list;

    public int getId_i() {
        return id_i;
    }

    public void setId_i(int id_i) {
        this.id_i = id_i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @SuppressWarnings("unchecked")
    public List<Item> getList() {
        log.info("##getList");
        if (list == null) {
            log.warn("##Null list");
            Session session = HibernateUtil.getSessionFactory().openSession();

            List<Item> newList = session.createCriteria(Item.class).list();
            log.info("##Size of list:" + newList.size());
            this.setList(newList);
            session.close();
        }
        return list;
    }

    public void setList(List<Item> list) {
        this.list = list;
        log.info("list set");
    }

    public boolean save() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Item i = new Item();
        i.setCost(this.getCost());
        i.setName(this.getName());

        Transaction tx = null;
        boolean ret = false;
        try {
            tx = session.beginTransaction();
            session.save(i);
            tx.commit();
            ret = true;
        } catch (Exception e) {
            ret = false;
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        FacesContext.getCurrentInstance()
                .addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Przedmiot dodany:", "Nazwa: " + name + " Koszt: "
                                + cost));
        return ret;

    }

    public void onCellEdit(CellEditEvent event) {
        log.info("");
        log.info("## In event. Cont: " + event.getRowIndex());
        log.info("## name: " + this.name);
        log.info("## cost: " + this.cost);
        log.info("## cost: " + this.id_i);
        log.info("");
    }

    public void onRowEdit(RowEditEvent event) {
        Item i = (Item) event.getObject();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.update(i);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            log.error("Error", e);
        } finally {
            session.close();
        }
    }

    public void delete(Item item) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.delete(item);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            log.error("Error", e);
        } finally {
            session.close();
        }
    }
}
