package pl.reservationsystemtestcases.request.basketAssign;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.reservationsystemtestcases.request.BaseRequest;
import pl.reservationsystemtestcases.request.basketCreate.BasketCreateRequest;
import pl.reservationsystemtestcases.url.BasketReservationURL;
import pl.reservationsystemtestcases.url.ReservationSystemURL;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;

public class BasketAssignRequest {

    public static Response basketAssignRequest(Map<String, String> queryParams) {

        return given()
                .spec(BaseRequest.requestSetup())
                .queryParams(queryParams)
//                .body(create.toString())
                .when()
                .post(BasketReservationURL.getBasketAssignUrl())
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}