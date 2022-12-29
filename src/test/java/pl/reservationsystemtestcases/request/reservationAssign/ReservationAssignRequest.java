package pl.reservationsystemtestcases.request.reservationAssign;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.reservationsystemtestcases.request.BaseRequest;
import pl.reservationsystemtestcases.url.ReservationSystemURL;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class ReservationAssignRequest {

    public static Response reservationAssignRequest(JSONObject payload) {

        return given()
                .spec(BaseRequest.requestSetup())
//                .queryParams(queryParams)
                .body(payload.toString())
                .when()
                .post(ReservationSystemURL.getReservationAssignUrl())
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}