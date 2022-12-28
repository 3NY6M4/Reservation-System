package pl.reservationsystemtestcases.tests.reservationCreate;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.reservationsystemtestcases.request.reservationCreate.ReservationCreateRequest;
import java.util.stream.Stream;

//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReservationCreateTest {

    private String referrer = "PANEL";
    private String source = "item list - supplier change";
    private int supplierId;
    private int productId;
    private int quantity;
    private int itemId;
    private int partId;
    private int operatorId;
//  private String isComponent = "false";

    private String reservationId;

    @DisplayName("Create a reservation with valid data")
    @ParameterizedTest
    @MethodSource("sampleReservationData")
    void createReservationTest() {

        JSONObject payload = new JSONObject();
        payload.put("referrer", referrer);
        payload.put("source", source);
        payload.put("supplierId", supplierId);
        payload.put("productId", productId);
        payload.put("quantity", quantity);
        payload.put("itemId", itemId);
        payload.put("partId", partId);
        payload.put("operatorId", operatorId);

        final Response createReservationResponse = ReservationCreateRequest.reservationCreateRequest(payload);
        Assertions.assertThat(createReservationResponse.statusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath json = createReservationResponse.jsonPath();
        Assertions.assertThat(json.getString("productId")).isEqualTo(productId);
        Assertions.assertThat(json.getString("supplierId")).isEqualTo(supplierId);
        Assertions.assertThat(json.getString("quantity")).isEqualTo(quantity);
        Assertions.assertThat(json.getString("reservationId")).isEqualTo(reservationId);

    }

    private static Stream<Arguments> sampleReservationData() {
        return Stream.of(
//                Arguments.of(referrer, source, supplierId, productId, quantity, itemId, partId, operatorId),
                Arguments.of("PANEL", "item list - supplier change", 3323, 7887950, 2, 1, 1, 69),
                Arguments.of("PANEL", "item list - supplier change", 1133, 8365693, 2, 1, 2, 69)
        );
    }
}
