package pl.reservationsystemtestcases.url;

public class BasketReservationURL {

    private BasketReservationURL() {
    }

    private static final String BASE_URL = "http://adress.run/";
    private static final String BASKET_CREATE = "api/reservation/basket";
    private static final String BASKET_ASSIGN = "api/reservation/basket/assign";
    private static final String BASKET_FORCE = "api/reservation/basket/force";
    private static final String BASKET_CANCEL = "api/reservation/basket/cancel";
    private static final String BASKET_DELETE = "api/reservation/basket/delete";


    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getBasketCreateUrl(){
        return getBaseUrl() + BASKET_CREATE;
    }

    public static String getBasketAssignUrl(){
        return getBaseUrl() + BASKET_ASSIGN;
    }

    public static String getBasketForceUrl(){
        return getBaseUrl() + BASKET_FORCE;
    }

    public static String getBasketCancelUrl(){
        return getBaseUrl() + BASKET_CANCEL;
    }

    public static String getBasketDeleteUrl(String basketId){
        return getBaseUrl() + BASKET_DELETE;
    }


}