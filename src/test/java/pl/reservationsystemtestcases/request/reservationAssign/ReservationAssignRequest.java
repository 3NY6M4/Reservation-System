package pl.reservationsystemtestcases.request.reservationAssign;

import io.restassured.response.Response;
import pl.reservationsystemtestcases.request.BaseRequest;
import pl.reservationsystemtestcases.url.ReservationSystemURL;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class ReservationAssignRequest {

    public static Response reservationAssignRequest(Map<String, String> queryParams) {

        return given()
                .spec(BaseRequest.requestSetup())
                .queryParams(queryParams)
//                .body(create.toString())
                .when()
                .post(ReservationSystemURL.getReservationAssignUrl())
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}