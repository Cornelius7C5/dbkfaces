package pl.spot.dbk.faces.converters;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.spot.dbk.faces.HibernateUtil;
import pl.spot.dbk.faces.hib.Role;

@ManagedBean(name = "roleConverter")
public class RoleConverter implements Converter {
    Logger log = LoggerFactory.getLogger(RoleConverter.class);
    List<Role> roles;

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Role> roles = session.createCriteria(Role.class).list();
        session.close();
        this.roles = roles;
        log.info("##Roles: " + roles.size());
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        log.info("## get As Object: " + value);
        for (Role r : this.roles) {
            if (r.getName().equals(value)) { return r; }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        try {
            log.info("## get As String: " + ((Role) value));
            return ((Role) value).toString();
        } catch (NullPointerException e) {
            return null;
        }
    }

}
