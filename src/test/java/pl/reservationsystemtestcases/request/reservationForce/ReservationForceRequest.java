package pl.reservationsystemtestcases.request.reservationForce;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.reservationsystemtestcases.request.BaseRequest;
import pl.reservationsystemtestcases.url.ReservationSystemURL;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class ReservationForceRequest {

    public static Response reservationForceRequest(JSONObject payload) {

        return given()
                .spec(BaseRequest.requestSetup())
//                .queryParams(queryParams)
                .body(payload.toString())
                .when()
                .post(ReservationSystemURL.getReservationForceUrl())
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
