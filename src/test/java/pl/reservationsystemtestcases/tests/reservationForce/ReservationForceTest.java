package pl.reservationsystemtestcases.tests.reservationForce;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.reservationsystemtestcases.request.reservationForce.ReservationForceRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReservationForceTest {

    private String supplierId;
    private String productId;
    private String quantity;
    private String itemId;
    private String partId;
    private String isComponent = "false";

    private String reservationId;

    @DisplayName("Force a reservation with valid data")
    @ParameterizedTest
    @MethodSource("sampleForceReservationData")
    void forceReservationTest(String supplierId, String productId, String quantity, String itemId, String partId, String isComponent) {

        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("supplierId", supplierId);
        queryParams.put("productId", productId);
        queryParams.put("quantity", quantity);
        queryParams.put("itemId", itemId);
        queryParams.put("partId", partId);
        queryParams.put("isComponent", isComponent);


        final Response forceReservationResponse = ReservationForceRequest.reservationForceRequest(queryParams);

        Assertions.assertThat(forceReservationResponse.statusCode()).isEqualTo(HttpStatus.SC_OK);
        JsonPath json = forceReservationResponse.jsonPath();
        Assertions.assertThat(json.getString("reservationId")).isEqualTo(reservationId);
    }

    private static Stream<Arguments> sampleForceReservationData() {
        return Stream.of(
                Arguments.of("3323", "696969", "69", "1", "0"),
                Arguments.of("1133", "131313", "96", "1", "0")
        );
    }
}
