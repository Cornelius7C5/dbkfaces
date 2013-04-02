package pl.spot.dbk.faces;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class KeyboardBean {
    private String value;

    public String getValue() {
        System.out.println("");
        System.out.println("KB::reading value: " + value);
        System.out.println("");
        return value;
    }

    public void setValue(String value) {
        System.out.println("");
        System.out.println("KB::setting value: " + value);
        System.out.println("KB::previous " + this.value);
        System.out.println("");
        this.value = value;
    }
}
