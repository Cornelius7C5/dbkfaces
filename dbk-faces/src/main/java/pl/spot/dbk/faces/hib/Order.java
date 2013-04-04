package pl.spot.dbk.faces.hib;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "id_o")
    private int id_o;

    @Column(name = "date")
    private Timestamp date;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_u")
    private User buyer;

    @ManyToOne
    @JoinColumn(name = "id_seller", referencedColumnName = "id_u")
    private User seller;

    @ManyToOne
    @JoinColumn(name = "id_o_salepoint", referencedColumnName = "id_sp")
    private SalePoint salePoint;

    @ManyToOne
    @JoinColumn(name = "id_status", referencedColumnName = "id_s")
    private Status status;

    @OneToMany(mappedBy = "id_order", cascade = CascadeType.ALL)
    private List<Basket> basketItems;

    @Transient
    private int sum;

    public Order() {}

    public int getId_o() {
        return id_o;
    }

    public void setId_o(int id_o) {
        this.id_o = id_o;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public SalePoint getSalePoint() {
        return salePoint;
    }

    public void setSalePoint(SalePoint salePoint) {
        this.salePoint = salePoint;
    }

    public List<Basket> getBasketItems() {
        return basketItems;
    }

    public void setBasketItems(List<Basket> basketItems) {
        this.basketItems = basketItems;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order [id_o=" + id_o + ", date=" + date + ", buyer=" + buyer + "]";
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

}
