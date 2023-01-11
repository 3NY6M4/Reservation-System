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
import pl.reservationsystemtestcases.tests.reservationCreate.ReservationCreateTest;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReservationForceTest {

    @DisplayName("Force a reservation with valid data")
    @ParameterizedTest
    @MethodSource("sampleForceReservationData")
    void forceReservationTest() {

        JSONObject payload = new JSONObject();
        payload.put("referrer", ReservationCreateTest.referrer);
        payload.put("source", ReservationCreateTest.source);
        payload.put("id", ReservationCreateTest.reservationId);
        payload.put("supplierId", ReservationCreateTest.supplier);
        payload.put("productId", ReservationCreateTest.product);
        payload.put("quantity", ReservationCreateTest.reservationQuantity);
        payload.put("operatorId", ReservationCreateTest.operatorId);
        payload.put("itemId", ReservationCreateTest.itemId);
        payload.put("partId", ReservationCreateTest.partId);
        payload.put("state", ReservationCreateTest.state);

        final Response forceReservationResponse = ReservationForceRequest.reservationForceRequest(payload);
        Assertions.assertThat(forceReservationResponse.statusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath json = forceReservationResponse.jsonPath();

        Assertions.assertThat(json.getInt("id")).isEqualTo(ReservationCreateTest.reservationId);
        Assertions.assertThat(json.getInt("productId")).isEqualTo(ReservationCreateTest.product);
        Assertions.assertThat(json.getInt("supplierId")).isEqualTo(ReservationCreateTest.supplier);
        Assertions.assertThat(json.getInt("quantity")).isEqualTo(ReservationCreateTest.reservationQuantity);
    }

    private static Stream<Arguments> sampleForceReservationData() {
        return Stream.of(
                Arguments.of("PANEL", "item list - supplier change", 3323, 7887950, 2, 1, 41, 69),
                Arguments.of("PANEL", "item list - supplier change", 1133, 8365693, 2, 1, 42, 69),
                Arguments.of("PANEL", "item list - supplier change", 1133, 374083, 1001, 1, 43, 69),
                Arguments.of("PANEL", "item list - supplier change", 3323, 1689571, 1001, 1, 44, 69),
                Arguments.of(ReservationCreateTest.state, ReservationCreateTest.reservationId, ReservationCreateTest.referrer, ReservationCreateTest.source, ReservationCreateTest.supplier, ReservationCreateTest.product, ReservationCreateTest.reservationQuantity, ReservationCreateTest.itemId, ReservationCreateTest.partId, ReservationCreateTest.operatorId),
                Arguments.of(ReservationCreateTest.state, ReservationCreateTest.reservationId, ReservationCreateTest.referrer, ReservationCreateTest.source, ReservationCreateTest.supplier, ReservationCreateTest.product, ReservationCreateTest.reservationQuantity, ReservationCreateTest.itemId, ReservationCreateTest.partId, ReservationCreateTest.operatorId)
        );
    }
}
