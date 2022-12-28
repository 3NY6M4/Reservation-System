package pl.reservationsystemtestcases.tests.basketAssign;

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
import pl.reservationsystemtestcases.request.basketAssign.BasketAssignRequest;
import pl.reservationsystemtestcases.request.basketCreate.BasketCreateRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BasketAssignTest
{
    private String supplierId;
    private String productId;
    private static String quantity;
    private static String itemId;
    private static String partId;
    private String isComponent = "false";

    private String reservationId;

    @Order(1)
    @DisplayName("Create unassign reservation with valid data in basket")
    @ParameterizedTest
    @MethodSource("sampleReservationBasketData")
    void createNewReservationBasket(String supplierId, String productId, String quantity, String isComponent) {

        JSONObject basket = new JSONObject();
        basket.put("name", "Koszyk rezerwacji");

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("supplierId", supplierId);
        queryParams.put("productId", productId);
        queryParams.put("quantity", quantity);
        queryParams.put("isComponent", isComponent);

        final Response createReservationBasketResponse = BasketCreateRequest.basketCreateRequest(queryParams);
        Assertions.assertThat(createReservationBasketResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath json = createReservationBasketResponse.jsonPath();
        Assertions.assertThat(json.getString("supplierId")).isEqualTo(supplierId);
        Assertions.assertThat(json.getString("productId")).isEqualTo(productId);
        Assertions.assertThat(json.getString("quantity")).isEqualTo(quantity);

    }

    private static Stream<Arguments> sampleReservationBasketData() {
        return Stream.of(
                Arguments.of("3323", "696969", "69"),
                Arguments.of("1133", "131313", "96")
        );
    }
    @Order(2)
    @DisplayName("Assign a reservation with valid data in basket")
    @ParameterizedTest
    @MethodSource("sampleReservationBasketAssignData")
    void assignReservationBasket(String supplierId, String productId, String quantity, String itemId, String partId, String isComponent) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("supplierId", supplierId);
        queryParams.put("productId", productId);
        queryParams.put("quantity", quantity);
        queryParams.put("itemId", itemId);
        queryParams.put("partId", partId);
        queryParams.put("isComponent", isComponent);

        final Response assignReservationBasketResponse = BasketAssignRequest.basketAssignRequest(queryParams);
        Assertions.assertThat(assignReservationBasketResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath json = assignReservationBasketResponse.jsonPath();
        Assertions.assertThat(json.getString("supplierId")).isEqualTo(supplierId);
        Assertions.assertThat(json.getString("productId")).isEqualTo(productId);
        Assertions.assertThat(json.getString("quantity")).isEqualTo(quantity);
        Assertions.assertThat(json.getString("itemId")).isEqualTo(itemId);
        Assertions.assertThat(json.getString("partId")).isEqualTo(partId);
    }
    private static Stream<Arguments> sampleReservationBasketAssignData() {
        return Stream.of(
                Arguments.of("3323", "696969", "69", "1", "0"),
                Arguments.of("1133", "131313", "96", "1", "0")
        );
    }
}

