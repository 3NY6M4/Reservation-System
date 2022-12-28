package pl.reservationsystemtestcases.request.reservationCancel;

import io.restassured.response.Response;
import pl.reservationsystemtestcases.request.BaseRequest;
import pl.reservationsystemtestcases.url.ReservationSystemURL;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class ReservationCancelRequest {

    public static Response reservationCancelRequest(Map<String, String> queryParams) {

        return given()
                .spec(BaseRequest.requestSetup())
                .queryParams(queryParams)
//                .body(create.toString())
                .when()
                .post(ReservationSystemURL.getReservationCancelUrl())
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
