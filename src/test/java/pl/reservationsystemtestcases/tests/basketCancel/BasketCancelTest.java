package pl.reservationsystemtestcases.tests.basketCancel;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.reservationsystemtestcases.request.basketCancel.BasketCancelRequest;
import pl.reservationsystemtestcases.request.basketCreate.BasketCreateRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class BasketCancelTest {

    private String supplierId;
    private String productId;
    private String quantity;
    private String itemId;
    private String partId;
    private String isComponent = "false";

    private String reservationId;

    @Order(1)
    @DisplayName("Create a reservation basket with valid data")
    @ParameterizedTest
    @MethodSource("sampleCreateReservationBasketData")
    void createCancelTest(String supplierId, String productId, String quantity, String itemId, String partId, String isComponent) {

        JSONObject basket = new JSONObject();
        basket.put("name", "Koszyk rezerwacji");

        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("supplierId", supplierId);
        queryParams.put("productId", productId);
        queryParams.put("quantity", quantity);
        queryParams.put("itemId", itemId);
        queryParams.put("partId", partId);
        queryParams.put("isComponent", isComponent);

        final Response response = BasketCreateRequest.basketCreateRequest(queryParams);

        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        JsonPath json = response.jsonPath();
        Assertions.assertThat(json.getString("reservationId")).isEqualTo(reservationId);
    }

    private static Stream<Arguments> sampleCreateReservationBasketData() {
        return Stream.of(
                Arguments.of("3323", "696969", "69", "1", "0"),
                Arguments.of("1133", "131313", "96", "1", "0")
        );
    }
    @Order(2)
    @DisplayName("Cancel a reservation basket with valid data")
    @ParameterizedTest
    @MethodSource("sampleReservationBasketCancelData")
    void reservationConfirmTest(String supplierId, String productId, String quantity, String itemId, String partId, String isComponent) {

        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("supplierId", supplierId);
        queryParams.put("productId", productId);
        queryParams.put("quantity", quantity);
        queryParams.put("itemId", itemId);
        queryParams.put("partId", partId);
        queryParams.put("isComponent", isComponent);

        final Response response = BasketCancelRequest.basketCancelRequest(queryParams);

        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        JsonPath json = response.jsonPath();
        Assertions.assertThat(json.getString("reservationId")).isEqualTo(reservationId);
    }

    private static Stream<Arguments> sampleReservationBasketCancelData() {
        return Stream.of(
                Arguments.of("3323", "696969", "69", "1", "0"),
                Arguments.of("1133", "131313", "96", "1", "0")
        );
    }
}
