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
import pl.reservationsystemtestcases.request.reservationCancel.ReservationCancelRequest;
import pl.reservationsystemtestcases.request.reservationConfirm.ReservationConfirmRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.random.RandomGenerator;
import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReservationCancelTest {

    private static int reservationId;
    private static String referrer = "PANEL";
    private static String source = "item list - supplier change";
    private static int supplierAMP;
    private static int supplierSMP;
    private static int productAMP;
    private static int productSMP;
    private static int reservationQuantity;
    private static int operatorId = 69;
    private static int itemId = RandomGenerator.getDefault().nextInt(9999);
    private static int partId = RandomGenerator.getDefault().nextInt(9999);

    @Order(1)
    @DisplayName("Create a reservation with valid data")
    @ParameterizedTest
    @MethodSource("sampleReservationData")
    void createReservationTest(String supplierId, String productId, String quantity, String itemId, String partId, String isComponent) {

        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("supplierId", supplierId);
        queryParams.put("productId", productId);
        queryParams.put("quantity", quantity);
        queryParams.put("itemId", itemId);
        queryParams.put("partId", partId);
        queryParams.put("isComponent", isComponent);

        final Response createReservationResponse = ReservationCancelRequest.reservationCancelRequest(queryParams);

        Assertions.assertThat(createReservationResponse.statusCode()).isEqualTo(HttpStatus.SC_OK);
        JsonPath json = createReservationResponse.jsonPath();
        Assertions.assertThat(json.getString("reservationId")).isEqualTo(reservationId);
    }

    private static Stream<Arguments> sampleReservationData() {
        return Stream.of(
                Arguments.of("3323", "696969", "69", "1", "0"),
                Arguments.of("1133", "131313", "96", "1", "0")
        );
    }
    @Order(2)
    @DisplayName("Cancel a reservation with valid data")
    @ParameterizedTest
    @MethodSource("sampleConfirmReservationData")
    void confirmReservationTest(String supplierId, String productId, String quantity, String itemId, String partId, String isComponent) {

        JSONObject payload = new JSONObject();
        payload.put("referrer", referrer);
        payload.put("source", source);
        payload.put("id", reservationId);
        payload.put("supplierId", supplierAMP);
        payload.put("supplierId", supplierSMP);
        payload.put("productId", productAMP);
        payload.put("productId", productSMP);
        payload.put("quantity", reservationQuantity);
        payload.put("operatorId", operatorId);
        payload.put("itemId", itemId);
        payload.put("partId", partId);

        final Response confirmReservationResponse = ReservationConfirmRequest.reservationConfirmRequest(payload, reservationId);

        Assertions.assertThat(confirmReservationResponse.statusCode()).isEqualTo(HttpStatus.SC_OK);
        JsonPath json = confirmReservationResponse.jsonPath();
        Assertions.assertThat(json.getString("reservationId")).isEqualTo(reservationId);
    }

    private static Stream<Arguments> sampleConfirmReservationData() {
        return Stream.of(
                Arguments.of("3323", "696969", "69", "1", "0"),
                Arguments.of("1133", "131313", "96", "1", "0")
        );
    }
}
