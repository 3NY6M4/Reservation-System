//package pl.reservationsystemtestcases.tests;
//
//import io.restassured.path.json.JsonPath;
//import org.apache.http.HttpStatus;
//import org.assertj.core.api.Assertions;
//import org.json.JSONObject;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.Arguments;
//import org.junit.jupiter.params.provider.MethodSource;
//import pl.reservationsystemtestcases.request.basketCreate.BasketCreateRequest;
//
//import java.util.Map;
//import java.util.stream.Stream;
//
//class AllStepsForBasketTest {
//
//    private String supplierId;
//    private String productId;
//    private String quantity;
//    private String itemId;
//    private String partId;
//    private String isComponent = "false";
//
//    private String reservationId;
//    private String basketId;
//
//    @DisplayName("Create a basket with valid data")
//    @ParameterizedTest
//    @MethodSource("sampleBasketData")
//    void createBasketTest() {
//        basketId = basketCreateStep();
//    }
//
//    private static Stream<Arguments> sampleBasketData() {
//        return Stream.of(
//                Arguments.of("3323", "111111", "1", "1", "0", "0"),
//                Arguments.of("3323", "222222", "0", "1", "1", "0"),
//                Arguments.of("1133", "111111", "1", "1", "0", "0"),
//                Arguments.of("1133", "222222", "0", "1", "1", "0")
//        );
//    }
//
//    private String basketCreateStep(Map<String, String> queryParams, JSONObject basketId) {
//        JSONObject basket = new JSONObject();
//        basket.put("info", "Rezerwacja produktu " + productId + " od dostawcy " + supplierId);
//
//        final var basketCreateResponse = BasketCreateRequest.basketCreateRequest(queryParams, basketId);
//        Assertions.assertThat(basketCreateResponse.statusCode()).isEqualTo(HttpStatus.SC_OK);
//
//        JsonPath jsonData = basketCreateResponse.jsonPath();
//        Assertions.assertThat(jsonData.getString("id")).isEqualTo("Rezerwacja produktu " + productId + " od dostawcy " + supplierId);
//
//        return jsonData.getString("id");
//    }
//}