package pl.spot.dbk.faces;

import java.sql.Timestamp;

public class CommonUtil {
    public static Timestamp getCurrTimestamp() {
        return new Timestamp((new java.util.Date()).getTime());
    }
}
