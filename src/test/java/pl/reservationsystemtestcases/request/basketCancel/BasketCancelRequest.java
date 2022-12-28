package pl.reservationsystemtestcases.request.basketCancel;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.reservationsystemtestcases.properties.ReservationSystemProperties;
import pl.reservationsystemtestcases.request.BaseRequest;
import pl.reservationsystemtestcases.url.BasketReservationURL;
import pl.reservationsystemtestcases.url.ReservationSystemURL;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class BasketCancelRequest {

    public static Response basketCancelRequest(Map<String, String> queryParams) {

        JSONObject basket = new JSONObject();
        basket.put("name", "Koszyk rezerwacji");

        return given()
                .header("Authorization", ReservationSystemProperties.getToken())
                .spec(BaseRequest.requestSetup())
                .queryParams(queryParams)
                .body(basket.toString())
                .when()
                .post(BasketReservationURL.getBasketCancelUrl())
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}

