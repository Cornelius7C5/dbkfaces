package pl.spot.dbk.faces.converters;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.spot.dbk.faces.HibernateUtil;
import pl.spot.dbk.faces.bean.CommonBean;
import pl.spot.dbk.faces.hib.SalePoint;

@ManagedBean(name = "spConverter")
public class SalePointConverter implements Converter {
    Logger log = LoggerFactory.getLogger(SalePointConverter.class);

    @ManagedProperty(value = "#{commonBean}")
    private CommonBean commonBean;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        log.info("## get As Object: " + value);
        Session session = HibernateUtil.getSessionFactory().openSession();
        SalePoint sp = (SalePoint) session.get(SalePoint.class, new Integer(value));
        session.close();
        return sp;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        try {
            log.info("## get As String: " + ((SalePoint) value).toString());
            return ((SalePoint) value).toString();
        } catch (NullPointerException e) {
            // e.printStackTrace();
            return null;
        }
    }

    public CommonBean getCommonBean() {
        return commonBean;
    }

    public void setCommonBean(CommonBean commonBean) {
        this.commonBean = commonBean;
    }

}
