package pl.spot.dbk.faces.hib;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Roles")
public class Role {
    @Id
    @GeneratedValue
    @Column(name = "id_r")
    private int id_r;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<User> specificRoleUsers;

    public Role() {}

    public int getId_r() {
        return id_r;
    }

    public void setId_r(int id_r) {
        this.id_r = id_r;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getSpecificRoleUsers() {
        return specificRoleUsers;
    }

    public void setSpecificRoleUsers(Set<User> specificRoleUsers) {
        this.specificRoleUsers = specificRoleUsers;
    }

    @Override
    public String toString() {
        return name;
    }

}
