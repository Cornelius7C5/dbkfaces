package pl.spot.dbk.faces.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "navigator")
@SessionScoped
public class Navigator {
    private String pageToNavigate = "ToUsers";
    Logger log = LoggerFactory.getLogger(Navigator.class);

    public String navigateTo() {
        log.info("in navigate to: " + pageToNavigate);
        if ("ToSecure".equalsIgnoreCase(pageToNavigate)) {
            return "Secured";
        } else if ("ToUnSecure".equalsIgnoreCase(pageToNavigate)) {
            return "UnSecured";
        } else if ("ToItems".equalsIgnoreCase(pageToNavigate)) {
            return "Items";
        } else if ("ToAddItems".equalsIgnoreCase(pageToNavigate)) {
            return "AddItems";
        } else if ("ToUsers".equalsIgnoreCase(pageToNavigate)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Uwaga:", "Wszystkie pola są wymagane."));
            return "ToUsers";
        }
        return "none";
    }

    public String goToAddItems() {
        log.info("navigate to additems");
        return "AddItems";
    }

    public String goToListItems() {
        log.info("navigate to listitems");
        return "Items";
    }

    public String getPageToNavigate() {
        log.info("getPageToNavigate");
        return pageToNavigate;
    }

    public void setPageToNavigate(String option) {
        log.info("setPageToNavigate");
        this.pageToNavigate = option;
    }
}