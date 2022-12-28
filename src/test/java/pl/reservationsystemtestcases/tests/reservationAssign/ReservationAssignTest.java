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
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReservationAssignTest {

    private String supplierId;
    private String productId;
    private static String quantity;
    private static String itemId;
    private static String partId;
    private String isComponent = "false";

    private String reservationId;

    @Order(1)
    @DisplayName("Create unassign reservation with valid data")
    @ParameterizedTest
    @MethodSource("sampleReservationData")
    void createUnAssignReservationTest(String supplierId, String productId, String quantity, String isComponent) {
        JSONObject payload = new JSONObject();
        payload.put("supplierId", supplierId);
        payload.put("productId", productId);
        payload.put("quantity", quantity);
        payload.put("isComponent", isComponent);

        final Response creteReservationResponse = ReservationCreateRequest.reservationCreateRequest(payload);
        Assertions.assertThat(creteReservationResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath json = creteReservationResponse.jsonPath();
        Assertions.assertThat(json.getString("supplierId")).isEqualTo(supplierId);
        Assertions.assertThat(json.getString("productId")).isEqualTo(productId);
        Assertions.assertThat(json.getString("quantity")).isEqualTo(quantity);

    }

    private static Stream<Arguments> sampleReservationData() {
        return Stream.of(
                Arguments.of("3323", "696969", "69"),
                Arguments.of("1133", "131313", "96")
        );
    }
    @Order(2)
    @DisplayName("Assign a reservation with valid data")
    @ParameterizedTest
    @MethodSource("sampleAssignReservationData")
    void assignReservationTest(String supplierId, String productId, String quantity, String itemId, String partId, String isComponent) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("supplierId", supplierId);
        queryParams.put("productId", productId);
        queryParams.put("quantity", quantity);
        queryParams.put("itemId", itemId);
        queryParams.put("partId", partId);
        queryParams.put("isComponent", isComponent);

        final Response assignReservationResponse = ReservationAssignRequest.reservationAssignRequest(queryParams);
        Assertions.assertThat(assignReservationResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath json = assignReservationResponse.jsonPath();
        Assertions.assertThat(json.getString("supplierId")).isEqualTo(supplierId);
        Assertions.assertThat(json.getString("productId")).isEqualTo(productId);
        Assertions.assertThat(json.getString("quantity")).isEqualTo(quantity);
        Assertions.assertThat(json.getString("itemId")).isEqualTo(itemId);
        Assertions.assertThat(json.getString("partId")).isEqualTo(partId);
    }
    private static Stream<Arguments> sampleAssignReservationData() {
        return Stream.of(
                Arguments.of("3323", "696969", "69", "1", "0"),
                Arguments.of("1133", "131313", "96", "1", "0")
        );
    }
}