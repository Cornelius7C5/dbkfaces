package pl.spot.dbk.faces.hib;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BASKETS")
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_b;

    @ManyToOne
    @JoinColumn(name = "id_order", referencedColumnName = "id_o")
    private Order id_order;

    @ManyToOne
    @JoinColumn(name = "id_item", referencedColumnName = "id_i")
    private Item id_item;

    private int amount;

    public Basket() {}

    public int getId_b() {
        return id_b;
    }

    public void setId_b(int id_b) {
        this.id_b = id_b;
    }

    public Order getId_order() {
        return id_order;
    }

    public void setId_order(Order id_order) {
        this.id_order = id_order;
    }

    public Item getId_item() {
        return id_item;
    }

    public void setId_item(Item id_item) {
        this.id_item = id_item;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Basket [id_order=" + id_order + ", id_item=" + id_item + ", amount=" + amount + "]";
    }

}
