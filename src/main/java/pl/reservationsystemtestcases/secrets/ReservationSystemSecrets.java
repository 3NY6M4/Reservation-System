package pl.reservationsystemtestcases.secrets;

public class ReservationSystemSecrets {

    private ReservationSystemSecrets() {
    }

    private static final String TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiIqIiwiYXVkIjoiKiIsImp0aSI6IlJOUnlNOXc3NG5VdzU0WGZTYThiWmdwU0ppQldJcUZzWldTaCIsImlhdCI6MTY2OTE5Mjg5NS4yMzUyMzIsIm5iZiI6MTY2OTE5Mjg5NS4yMzUyMzYsImV4cCI6MTY2OTI3OTI5NS4yMzUyMzh9.9mElrAkHIm7F4wJONza7vNjctjJW1ZirjZQXOmZzISc";
//    private static final String PANEL_SESSION_ID = "PANEL_SESSION_ID=4d477b43-33e1-30bf-8d7d-0a9f4e117942;";
//    private static final String PHP_SESSION_ID = "PHPSESSID=kdqm3g9ul9p9ch1mbqodlc4qtp";

    public static String getToken() {
        return TOKEN;
    }
//
//    public static String getPanelSessionId() {
//        return PANEL_SESSION_ID;
//    }
//
//    public static String getPhpSessionId() {
//        return PHP_SESSION_ID;
//    }
}
