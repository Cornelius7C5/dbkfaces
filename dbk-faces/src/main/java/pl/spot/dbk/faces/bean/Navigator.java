package pl.spot.dbk.faces.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "navigator")
@SessionScoped
public class Navigator {
    private String pageToNavigate = "";
    Logger log = LoggerFactory.getLogger(Navigator.class);

    public String navigateTo() {
        log.info("in navigate to: " + pageToNavigate);
        if ("ToSecure".equalsIgnoreCase(pageToNavigate)) {
            return "Secured";
        } else if ("ToUnSecure".equalsIgnoreCase(pageToNavigate)) {
            return "UnSecured";
        } else if("ToItems".equalsIgnoreCase(pageToNavigate)){
            return "Items";
        }
        return "none";
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