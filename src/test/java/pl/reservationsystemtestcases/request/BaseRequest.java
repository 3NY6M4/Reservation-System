package pl.reservationsystemtestcases.request;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pl.reservationsystemtestcases.properties.ReservationSystemProperties;

public class BaseRequest {

    protected static RequestSpecBuilder requestBuilder;

    public static RequestSpecification requestSetup() {
        requestBuilder = new RequestSpecBuilder();
        requestBuilder.setContentType(ContentType.JSON);
        requestBuilder.addHeader("Authorization", "Bearer " + ReservationSystemProperties.getToken());
//      requestBuilder.addQueryParam("token", ReservationSystemSecrets.getToken());
//        requestBuilder.addQueryParam("panelSessionId", ReservationSystemSecrets.getPanelSessionId());
//        requestBuilder.addQueryParam("phpSessionId", ReservationSystemSecrets.getPhpSessionId());
        requestBuilder.addFilter(new RequestLoggingFilter());
        requestBuilder.addFilter(new ResponseLoggingFilter());

        return requestBuilder.build();
    }
}
