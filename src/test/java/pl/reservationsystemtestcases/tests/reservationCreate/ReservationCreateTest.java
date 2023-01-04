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

import java.util.random.RandomGenerator;
import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReservationCreateTest {

    private static int reservationId;
    private final static String referrer = "PANEL";
    private final static String source = "item list - supplier change";
    private final static int operatorId = 69;
    private static int itemId = RandomGenerator.getDefault().nextInt();
    private static int partId = RandomGenerator.getDefault().nextInt();

    @DisplayName("Create a reservation with valid data")
    @ParameterizedTest
    @MethodSource("sampleCreateReservationData")
    void createReservationTest(int supplier, int product, int reservationQuantity) {

        JSONObject payload = new JSONObject();
        payload.put("referrer", referrer);
        payload.put("source", source);
        payload.put("supplierId", supplier);
        payload.put("productId", product);
        payload.put("quantity", reservationQuantity);
        payload.put("operatorId", operatorId);
//        payload.put("itemId", itemId);
//        payload.put("partId", partId);

        final Response createReservationResponse = ReservationCreateRequest.reservationCreateRequest(payload, partId , itemId);
        Assertions.assertThat(createReservationResponse.statusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath json = createReservationResponse.jsonPath();

        reservationId = json.getInt("id");
        supplier = json.getInt("supplierId");
        product = json.getInt("productId");
        reservationQuantity = json.getInt("quantity");

        Assertions.assertThat(json.getInt("id")).isEqualTo(reservationId);
        Assertions.assertThat(json.getInt("supplierId")).isEqualTo(supplier);
        Assertions.assertThat(json.getInt("productId")).isEqualTo(product);
        Assertions.assertThat(json.getInt("quantity")).isEqualTo(reservationQuantity);
    }

    private static Stream<Arguments> sampleCreateReservationData() {
        return Stream.of(
                Arguments.of(1133, 8365693, 2, partId, itemId, operatorId, referrer, source),
                Arguments.of(3323, 7887950, 2, partId, itemId, operatorId, referrer, source)
        );
    }
}
