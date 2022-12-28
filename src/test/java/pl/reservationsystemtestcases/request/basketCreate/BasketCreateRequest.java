package pl.reservationsystemtestcases.request.basketCreate;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.reservationsystemtestcases.properties.ReservationSystemProperties;
import pl.reservationsystemtestcases.request.BaseRequest;
import pl.reservationsystemtestcases.url.BasketReservationURL;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class BasketCreateRequest {

    public static Response basketCreateRequest(Map<String, String> queryParams) {

        return given()
//                .header("Authorization", ReservationSystemProperties.getToken())
                .spec(BaseRequest.requestSetup())
                .queryParams(queryParams)
//                .body(basketId.toString())
                .when()
                .post(BasketReservationURL.getBasketCreateUrl())
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}