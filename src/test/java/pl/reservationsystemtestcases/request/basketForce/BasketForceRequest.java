package pl.reservationsystemtestcases.request.basketForce;

import io.restassured.response.Response;
import pl.reservationsystemtestcases.request.BaseRequest;
import pl.reservationsystemtestcases.url.BasketReservationURL;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class BasketForceRequest {

    public static Response basketForceRequest(Map<String, String> queryParams) {

        return given()
                .spec(BaseRequest.requestSetup())
                .queryParams(queryParams)
//                .body(create.toString())
                .when()
                .post(BasketReservationURL.getBasketForceUrl())
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
