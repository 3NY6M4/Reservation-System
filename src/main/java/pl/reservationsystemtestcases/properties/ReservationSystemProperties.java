package pl.reservationsystemtestcases.properties;

import java.util.ResourceBundle;

public class ReservationSystemProperties {

    private static final String TOKEN = "inventory.api.token";

    public static String getToken(){
        return getProperty(TOKEN);
    }

    private static String getProperty(String key){
        return ResourceBundle.getBundle("reservation").getString(key);
    }
}

