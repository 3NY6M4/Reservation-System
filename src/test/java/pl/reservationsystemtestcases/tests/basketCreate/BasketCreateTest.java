package pl.reservationsystemtestcases.tests.basketCreate;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.reservationsystemtestcases.request.basketCreate.BasketCreateRequest;
import pl.reservationsystemtestcases.request.basketDelete.BasketDeleteRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class BasketCreateTest {

    private String supplierId;
    private String productId;
    private String quantity;
    private String itemId;
    private String partId;
    private String isComponent = "false";

    private String reservationId;

    @DisplayName("Create a basket with valid data")
    @ParameterizedTest
    @MethodSource("sampleBasketData")
    void createBasketTest(String supplierId, String productId, String quantity, String itemId, String partId, String isComponent) {

//        JSONObject basket = new JSONObject();
//        basket.put("name", "Rezerwacja produktu " + productId + " od dostawcy " + supplierId);

        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("supplierId", supplierId);
        queryParams.put("productId", productId);
        queryParams.put("quantity", quantity);
        queryParams.put("itemId", itemId);
        queryParams.put("partId", partId);
        queryParams.put("isComponent", isComponent);

        final Response basketCreateRequest = BasketCreateRequest.basketCreateRequest(queryParams);

        Assertions.assertThat(basketCreateRequest.statusCode()).isEqualTo(HttpStatus.SC_OK);
        JsonPath json = basketCreateRequest.jsonPath();
        Assertions.assertThat(json.getString("reservationId")).isEqualTo(reservationId);

//        final var basketId = json.getString("id");
//        BasketDeleteRequest.basketDeleteRequest(basketId);

//        final var basketDeleteResponse = BasketDeleteRequest.basketDeleteRequest(basketId);
//        Assertions.assertThat(basketDeleteResponse.statusCode()).isEqualTo(HttpStatus.SC_OK);

    }

    private static Stream<Arguments> sampleBasketData() {
        return Stream.of(
                Arguments.of("3323", "696969", "69", "1", "0", "0"),
                Arguments.of("1133", "131313", "96", "1", "0", "0")
        );
    }
}
