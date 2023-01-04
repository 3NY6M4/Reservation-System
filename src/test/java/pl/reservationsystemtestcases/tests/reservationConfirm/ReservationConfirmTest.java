package pl.reservationsystemtestcases.tests.reservationConfirm;

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
import pl.reservationsystemtestcases.request.reservationAssign.ReservationAssignRequest;
import pl.reservationsystemtestcases.request.reservationConfirm.ReservationConfirmRequest;
import pl.reservationsystemtestcases.request.reservationCreate.ReservationCreateRequest;
import java.util.random.RandomGenerator;
import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReservationConfirmTest {

    private static int reservationId;
    private final static String referrer = "PANEL";
    private final static String source = "item list - supplier change";
    private static int supplier;
    private static int product;
    private static int reservationQuantity;
    private final static int operatorId = 69;
    private static int itemId = RandomGenerator.getDefault().nextInt(9999);
    private static int partId = RandomGenerator.getDefault().nextInt(9999);
    private final static String state = "CONFIRMED";

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
//                Arguments.of(referrer, source, 1133, 8365693, 2, operatorId),
                Arguments.of(referrer, source, 3323, 7887950, 2, operatorId)
        );
    }
    @Order(2)
    @DisplayName("Assign a reservation with valid data")
    @ParameterizedTest
    @MethodSource("sampleAssignReservationData")
    void assignReservationTest() {

        JSONObject payload = new JSONObject();
        payload.put("referrer", referrer);
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

        reservationId = json.getInt("id");
    }
    private static Stream<Arguments> sampleAssignReservationData() {
        return Stream.of(
//                Arguments.of(referrer, source, supplier, product, reservationQuantity, itemId, partId, operatorId, reservationId),
                Arguments.of(reservationId, referrer, source, supplier, product, reservationQuantity, itemId, partId, operatorId)
        );
    }

    @Order(3)
    @DisplayName("Confirm a reservation with valid data")
    @ParameterizedTest
    @MethodSource("sampleConfirmReservationData")
    void confirmReservationTest() {

        JSONObject payload = new JSONObject();
        payload.put("referrer", referrer);
        payload.put("source", source);
        payload.put("id", reservationId);
        payload.put("supplierId", supplier);
        payload.put("productId", product);
        payload.put("quantity", reservationQuantity);
        payload.put("operatorId", operatorId);
        payload.put("itemId", itemId);
        payload.put("partId", partId);

        final Response confirmReservationResponse = ReservationConfirmRequest.reservationConfirmRequest(payload, reservationId);
        Assertions.assertThat(confirmReservationResponse.statusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath json = confirmReservationResponse.jsonPath();
        Assertions.assertThat(json.getInt("id")).isEqualTo(reservationId);
        Assertions.assertThat(json.getInt("supplierId")).isEqualTo(supplier);
        Assertions.assertThat(json.getInt("productId")).isEqualTo(product);
        Assertions.assertThat(json.getInt("quantity")).isEqualTo(reservationQuantity);
        Assertions.assertThat(json.getInt("itemId")).isEqualTo(itemId);
        Assertions.assertThat(json.getInt("partId")).isEqualTo(partId);
        Assertions.assertThat(json.getString("state")).isEqualTo(state);

        reservationId = json.getInt("id");
    }

    private static Stream<Arguments> sampleConfirmReservationData() {
        return Stream.of(
//                Arguments.of(referrer, source, supplier, product, reservationQuantity, itemId, partId, operatorId, reservationId),
                Arguments.of(referrer, source, supplier, product, reservationQuantity, itemId, partId, operatorId)
        );
    }
}
