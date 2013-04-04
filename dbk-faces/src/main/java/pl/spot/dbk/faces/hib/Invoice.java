package pl.spot.dbk.faces.hib;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "INVOICES")
public class Invoice {
    @Override
    public String toString() {
        return "Invoice [id_i=" + id_i + ", invoiceSalePoint=" + invoiceSalePoint + ", user=" + user + "]";
    }

    @Id
    @GeneratedValue
    @Column(name = "id_i")
    private int id_i;

    @Column(name = "amount")
    private int amount;

    @Column(name = "extra")
    private int extra = 0;

    @Column(name = "order_registration")
    private Timestamp order_registration;

    @ManyToOne
    @JoinColumn(name = "id_i_salepoint", referencedColumnName = "id_sp")
    private SalePoint invoiceSalePoint;
    @Transient
    private int invoiceSalePoint_id;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_u")
    private User user;
    @Transient
    private int user_id;

    @ManyToOne
    @JoinColumn(name = "id_seller", referencedColumnName = "id_u")
    private User seller;
    @Transient
    private int seller_id;

    public Invoice() {}

    public int getId_i() {
        return id_i;
    }

    public void setId_i(int id_i) {
        this.id_i = id_i;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    /** @return the extra */
    public int getExtra() {
        return extra;
    }

    /** @param extra the extra to set */
    public void setExtra(int extra) {
        this.extra = extra;
    }

    public Timestamp getOrder_registration() {
        return order_registration;
    }

    public void setOrder_registration(Timestamp order_registration) {
        this.order_registration = order_registration;
    }

    public SalePoint getSalePoint() {
        return invoiceSalePoint;
    }

    public void setSalePoint(SalePoint salePoint) {
        this.invoiceSalePoint = salePoint;
    }

    /** @return the invoiceSalePoint_id */
    public int getInvoiceSalePoint_id() {
        return invoiceSalePoint_id;
    }

    /** @param invoiceSalePoint_id the invoiceSalePoint_id to set */
    public void setInvoiceSalePoint_id(int invoiceSalePoint_id) {
        this.invoiceSalePoint_id = invoiceSalePoint_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /** @return the user_id */
    public int getUser_id() {
        return user_id;
    }

    /** @param user_id the user_id to set */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public MetaObject getMetaObject() throws Exception {
        MetaObject mo = new MetaObject(true, false);
        mo.setId(this.id_i);
        mo.addInfo("Data sprzedaży", this.order_registration.toString());
        mo.addInfo("Uczestnik", this.user.getName() + " " + this.user.getSurname());
        mo.addInfo("Nr karty", this.user.getId_u() + "");
        mo.addInfo("Rejestracja karty", this.user.getRegisterPoint().getName());
        mo.addInfo("Miejsce sprzedaży", this.invoiceSalePoint.getName());
        mo.addInfo("Pracownik", this.seller.getName() + " " + this.seller.getSurname());
        mo.addInfo("Kwota", this.amount + "");
        mo.addInfo("Punkty", Math.round(this.amount / 10.0) + "");
        mo.addInfo("Ekstra punkty", this.extra + "");

        return mo;
    }

}
