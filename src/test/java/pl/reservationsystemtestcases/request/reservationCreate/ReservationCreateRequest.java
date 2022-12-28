package pl.reservationsystemtestcases.request.reservationCreate;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.reservationsystemtestcases.request.BaseRequest;
import pl.reservationsystemtestcases.url.ReservationSystemURL;
import static io.restassured.RestAssured.given;

public class ReservationCreateRequest {

    public static Response reservationCreateRequest(JSONObject payload) {

        return given()
                .spec(BaseRequest.requestSetup())
//                .queryParams(queryParams)
                .body(payload.toString())
                .when()
                .post(ReservationSystemURL.getReservationCreateUrl())
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
