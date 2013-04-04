package pl.spot.dbk.faces.hib;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class MetaObject implements Serializable {
    private static final long serialVersionUID = -1158931697209657065L;

    private String id;
    private String name;

    private HashMap<String, String> extra = new HashMap<String, String>();

    public boolean show_id = true;

    public boolean show_name = true;

    public MetaObject() {}

    public MetaObject(Object id, Object name) {
        this.id = id.toString();
        this.name = name.toString();
    }

    public MetaObject(boolean id, boolean name) {
        this.show_id = id;
        this.show_name = name;
    }

    public void setId(Object id_u) {
        this.id = id_u.toString();
    }

    public String getId() {
        return id;
    }

    public void setName(Object name) {
        this.name = name.toString();
    }

    public String getName() {
        return name;
    }

    public void addInfo(String key, String extra) throws Exception {
        if (!this.extra.keySet().contains(key)) {
            this.extra.put(key, extra);
        } else {
            throw new Exception("Key already exists");
        }
    }

    public ArrayList<String> getKeyList() {
        ArrayList<String> ret = new ArrayList<String>();
        ret.addAll(this.extra.keySet());

        return ret;
    }

    public HashMap<String, String> getExtra() {
        return extra;
    }

    public String getExtraItem(String key) {
        return this.extra.get(key);
    }

}
