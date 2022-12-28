package pl.reservationsystemtestcases.request.reservationConfirm;

import io.restassured.response.Response;
import pl.reservationsystemtestcases.request.BaseRequest;
import pl.reservationsystemtestcases.url.ReservationSystemURL;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class ReservationConfirmRequest {

    public static Response reservationConfirmRequest(Map<String, String> queryParams) {

        return given()
                .spec(BaseRequest.requestSetup())
                .queryParams(queryParams)
//                .body(create.toString())
                .when()
                .post(ReservationSystemURL.getReservationConfirmUrl())
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
