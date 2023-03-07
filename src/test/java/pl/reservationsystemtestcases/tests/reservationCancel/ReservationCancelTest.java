package pl.reservationsystemtestcases.tests.reservationCancel;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.reservationsystemtestcases.request.reservationConfirm.ReservationConfirmRequest;
import pl.reservationsystemtestcases.tests.reservationCreate.ReservationCreateTest;
import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReservationCancelTest {

    @DisplayName("Cancel a reservation with valid data")
    @ParameterizedTest
    @MethodSource("sampleConfirmReservationData")
    void confirmReservationTest() {

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

        final Response confirmReservationResponse = ReservationConfirmRequest.reservationConfirmRequest(payload, ReservationCreateTest.reservationId);
        Assertions.assertThat(confirmReservationResponse.statusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath json = confirmReservationResponse.jsonPath();

        Assertions.assertThat(json.getInt("id")).isEqualTo(ReservationCreateTest.reservationId);
        Assertions.assertThat(json.getInt("supplierId")).isEqualTo(ReservationCreateTest.supplier);
        Assertions.assertThat(json.getInt("productId")).isEqualTo(ReservationCreateTest.product);
        Assertions.assertThat(json.getInt("quantity")).isEqualTo(ReservationCreateTest.reservationQuantity);
        Assertions.assertThat(json.getInt("itemId")).isEqualTo(ReservationCreateTest.itemId);
        Assertions.assertThat(json.getInt("partId")).isEqualTo(ReservationCreateTest.partId);
        Assertions.assertThat(json.getString("state")).isEqualTo("CANCEL");

    }

    private static Stream<Arguments> sampleConfirmReservationData() {
        return Stream.of(
                Arguments.of(ReservationCreateTest.state, ReservationCreateTest.reservationId, ReservationCreateTest.referrer, ReservationCreateTest.source, ReservationCreateTest.supplier, ReservationCreateTest.product, ReservationCreateTest.reservationQuantity, ReservationCreateTest.itemId, ReservationCreateTest.partId, ReservationCreateTest.operatorId),
                Arguments.of(ReservationCreateTest.state, ReservationCreateTest.reservationId, ReservationCreateTest.referrer, ReservationCreateTest.source, ReservationCreateTest.supplier, ReservationCreateTest.product, ReservationCreateTest.reservationQuantity, ReservationCreateTest.itemId, ReservationCreateTest.partId, ReservationCreateTest.operatorId)
        );
    }
}
