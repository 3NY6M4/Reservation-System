package pl.reservationsystemtestcases.tests.reservationForce;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.reservationsystemtestcases.request.reservationForce.ReservationForceRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReservationForceTest {

    @DisplayName("Force a reservation with valid data")
    @ParameterizedTest
    @MethodSource("sampleForceReservationData")
    void forceReservationTest(String referrer, String source, int supplierId, int productId, int quantity, int itemId, int partId, int operatorId) {

        JSONObject payload = new JSONObject();
        payload.put("referrer", referrer);
        payload.put("source", source);
        payload.put("supplierId", supplierId);
        payload.put("productId", productId);
        payload.put("quantity", quantity);
        payload.put("itemId", itemId);
        payload.put("partId", partId);
        payload.put("operatorId", operatorId);

        final Response forceReservationResponse = ReservationForceRequest.reservationForceRequest(payload);
        Assertions.assertThat(forceReservationResponse.statusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath json = forceReservationResponse.jsonPath();
        Assertions.assertThat(json.getInt("productId")).isEqualTo(productId);
        Assertions.assertThat(json.getInt("supplierId")).isEqualTo(supplierId);
        Assertions.assertThat(json.getInt("quantity")).isEqualTo(quantity);
//        Assertions.assertThat(json.getInt("id")).isEqualTo(reservationId);
    }

    private static Stream<Arguments> sampleForceReservationData() {
        return Stream.of(
                Arguments.of("PANEL", "item list - supplier change", 3323, 7887950, 2, 1, 41, 69),
                Arguments.of("PANEL", "item list - supplier change", 1133, 8365693, 2, 1, 42, 69),
                Arguments.of("PANEL", "item list - supplier change", 1133, 374083, 1001, 1, 43, 69),
                Arguments.of("PANEL", "item list - supplier change", 3323, 1689571, 1001, 1, 44, 69)
        );
    }
}
