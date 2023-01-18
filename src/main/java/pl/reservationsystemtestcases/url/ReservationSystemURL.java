package pl.reservationsystemtestcases.url;

public class ReservationSystemURL {

    private ReservationSystemURL() {
    }

    private static final String BASE_URL = "http://dev-inventory-app-2.apri.run/";
    private static final String RESERVATION_CREATE = "api/reservation";
    private static final String RESERVATION_ASSIGN = "api/reservation/assign";
    private static final String RESERVATION_FORCE = "api/reservation/create/force";
    private static final String RESERVATION_CANCEL = "api/reservation/cancel";
    private static final String RESERVATION_CONFIRM = "api/reservation/confirm";


    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getReservationCreateUrl(){
        return getBaseUrl() + RESERVATION_CREATE;
    }

    public static String getReservationAssignUrl(){
        return getBaseUrl() + RESERVATION_ASSIGN;
    }

    public static String getReservationForceUrl(){
        return getBaseUrl() + RESERVATION_FORCE;
    }

    public static String getReservationCancelUrl(){
        return getBaseUrl() + RESERVATION_CANCEL;
    }

    public static String getReservationConfirmUrl(){
        return getBaseUrl() + RESERVATION_CONFIRM;
    }
}
