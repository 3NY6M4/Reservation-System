package pl.reservationsystemtestcases.tests.reservationAssign;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.reservationsystemtestcases.request.reservationAssign.ReservationAssignRequest;
import pl.reservationsystemtestcases.tests.reservationCreate.ReservationCreateTest;
import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReservationAssignTest {

    private final static String referrer = ReservationCreateTest.referrer;
    private final static String source = ReservationCreateTest.source;
    private final static int operatorId = ReservationCreateTest.operatorId;
    private static int reservationId = ReservationCreateTest.reservationId;
    private static int supplier;
    private static int product;
    private static int reservationQuantity;
    private static int itemId = ReservationCreateTest.itemId;
    private static int partId = ReservationCreateTest.partId;

    @DisplayName("Assign a reservation with valid data")
    @ParameterizedTest
    @MethodSource("sampleAssignReservationData")
    void assignReservationTest(int supplier, int product, int reservationQuantity) {

        JSONObject payload = new JSONObject();
        payload.put("referrer", ReservationCreateTest.referrer);
        payload.put("source", source);
        payload.put("id", reservationId);
        payload.put("supplierId", supplier);
        payload.put("productId", product);
        payload.put("quantity", reservationQuantity);
        payload.put("operatorId", operatorId);
        payload.put("itemId", itemId);
        payload.put("partId", partId);

        final Response assignReservationResponse = ReservationAssignRequest.reservationAssignRequest(payload, reservationId);
        Assertions.assertThat(assignReservationResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath json = assignReservationResponse.jsonPath();

        Assertions.assertThat(json.getInt("id")).isEqualTo(reservationId);
        Assertions.assertThat(json.getInt("supplierId")).isEqualTo(supplier);
        Assertions.assertThat(json.getInt("productId")).isEqualTo(product);
        Assertions.assertThat(json.getInt("quantity")).isEqualTo(reservationQuantity);
        Assertions.assertThat(json.getInt("itemId")).isEqualTo(itemId);
        Assertions.assertThat(json.getInt("partId")).isEqualTo(partId);

    }
    private static Stream<Arguments> sampleAssignReservationData() {
        return Stream.of(
                Arguments.of(reservationId, referrer, source, supplier, product, reservationQuantity, itemId, partId, operatorId),
                Arguments.of(referrer, source, supplier, product, reservationQuantity, itemId + 1, partId + 1, operatorId, reservationId)
        );
    }
}
