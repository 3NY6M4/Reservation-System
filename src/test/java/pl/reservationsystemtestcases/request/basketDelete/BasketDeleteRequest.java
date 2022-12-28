package pl.reservationsystemtestcases.request.basketDelete;

import io.restassured.response.Response;
import pl.reservationsystemtestcases.request.BaseRequest;
import pl.reservationsystemtestcases.url.BasketReservationURL;

import static io.restassured.RestAssured.given;

public class BasketDeleteRequest {

    public static Response basketDeleteRequest(String basketId) {

        return given()
//                .header("Authorization", ReservationSystemProperties.getToken())
                .spec(BaseRequest.requestSetup())
//                .queryParams(queryParams)
                .when()
                .delete(BasketReservationURL.getBasketDeleteUrl(basketId))
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
