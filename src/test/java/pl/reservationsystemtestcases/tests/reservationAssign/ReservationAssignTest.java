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
import pl.reservationsystemtestcases.request.reservationCreate.ReservationCreateRequest;

import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReservationAssignTest {

    private static int reservationId;
    private static String referrer = "PANEL";
    private static String source = "item list - supplier change";
    private static int supplier;
    private static int product;
    private static int reservationQuantity;
    private static int operatorId = 69;
    private static int itemId;
    private static int partId;

    @Order(1)
    @DisplayName("Create unassign reservation with valid data")
    @ParameterizedTest
    @MethodSource("sampleCreateReservationData")
    void createUnAssignReservationTest(String referrer, String source, int supplierId, int productId, int quantity, int operatorId) {

        JSONObject payload = new JSONObject();
        payload.put("referrer", referrer);
        payload.put("source", source);
        payload.put("supplierId", supplierId);
        payload.put("productId", productId);
        payload.put("quantity", quantity);
        payload.put("operatorId", operatorId);

        final Response createReservationResponse = ReservationCreateRequest.reservationCreateRequest(payload);
        Assertions.assertThat(createReservationResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath json = createReservationResponse.jsonPath();
        Assertions.assertThat(json.getInt("supplierId")).isEqualTo(supplierId);
        Assertions.assertThat(json.getInt("productId")).isEqualTo(productId);
        Assertions.assertThat(json.getInt("quantity")).isEqualTo(quantity);

        reservationId = json.getInt("id");
        supplier = json.getInt("supplierId");
        product = json.getInt("productId");
        reservationQuantity = json.getInt("quantity");

    }

    private static Stream<Arguments> sampleCreateReservationData() {
        return Stream.of(
                Arguments.of("PANEL", "item list - supplier change", 3323, 7887950, 2, 69)
//                Arguments.of("PANEL", "item list - supplier change", 1133, 8365693, 2, 69)
        );
    }
    @Order(2)
    @DisplayName("Assign a reservation with valid data")
    @ParameterizedTest
    @MethodSource("sampleAssignReservationData")
    void assignReservationTest(String referrer, String source, int supplierId, int productId, int quantity, int itemId, int partId, int operatorId) {

        JSONObject payload = new JSONObject();
        payload.put("referrer", this.referrer);
        payload.put("source", this.source);
        payload.put("id", reservationId);
        payload.put("supplierId", supplier);
        payload.put("productId", product);
        payload.put("quantity", reservationQuantity);
        payload.put("operatorId", this.operatorId);
        payload.put("itemId", itemId);
        payload.put("partId", partId);

        final Response assignReservationResponse = ReservationAssignRequest.reservationAssignRequest(payload);
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
                Arguments.of(referrer, source, supplier, product, reservationQuantity, 9, 90, operatorId, reservationId)
//                Arguments.of(referrer, source, supplier, product, reservationQuantity, 10, 81, operatorId, reservationId)
        );
    }
}
