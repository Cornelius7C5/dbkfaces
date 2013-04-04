package pl.spot.dbk.faces.hib;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SALEPOINTS")
public class SalePoint {
    @Id
    @Column(name = "id_sp")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_sp;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "invoiceSalePoint", cascade = CascadeType.ALL)
    private Set<Invoice> invoices;

    @OneToMany(mappedBy = "registerPoint", cascade = CascadeType.ALL)
    private Set<User> registeredClients;

    @OneToMany(mappedBy = "workSalePoint", cascade = CascadeType.ALL)
    private Set<User> workers;

    @OneToMany(mappedBy = "salePoint", cascade = CascadeType.ALL)
    private Set<Order> orders;

    public SalePoint() {}

    public int getId_sp() {
        return id_sp;
    }

    public void setId_sp(int id_sp) {
        this.id_sp = id_sp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Set<User> getRegisteredClients() {
        return registeredClients;
    }

    public void setRegisteredClients(Set<User> registeredClients) {
        this.registeredClients = registeredClients;
    }

    public Set<User> getWorkers() {
        return workers;
    }

    public void setWorkers(Set<User> workers) {
        this.workers = workers;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return name;
    }

    public MetaObject getMetaObject() {
        return new MetaObject(this.id_sp, this.name);
    }

    public void setId_sp(String id) {
        this.setId_sp(new Integer(id));
    }

}
