package pl.reservationsystemtestcases.tests.reservationCreate;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.reservationsystemtestcases.fixtures.ReservationFixture;
import pl.reservationsystemtestcases.request.reservationCreate.ReservationCreateRequest;

import java.util.random.RandomGenerator;
import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReservationCreateTest {

    public static int reservationId;
    public static String state;

    @DisplayName("Create a reservation with valid data")
    @ParameterizedTest
    @MethodSource("sampleCreateReservationData")
    public void createReservationTest(int supplier, int product, int reservationQuantity) {

//        final Response createReservationResponse = ReservationCreateRequest.reservationCreateRequest(payload);
//        Assertions.assertThat(createReservationResponse.statusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath reservation = ReservationFixture.create(supplier, product, reservationQuantity);

        reservationId = reservation.getInt("id");
        supplier = reservation.getInt("supplierId");
        product = reservation.getInt("productId");
        reservationQuantity = reservation.getInt("quantity");
        state = reservation.getString("state");

        Assertions.assertThat(reservation.getInt("id")).isEqualTo(reservationId);
        Assertions.assertThat(reservation.getInt("supplierId")).isEqualTo(supplier);
        Assertions.assertThat(reservation.getInt("productId")).isEqualTo(product);
        Assertions.assertThat(reservation.getInt("quantity")).isEqualTo(reservationQuantity);
        Assertions.assertThat(reservation.getString("state")).isEqualTo(state);
    }

    public static Stream<Arguments> sampleCreateReservationData() {
        return Stream.of(
                Arguments.of(1133, 8365693, 2, state),
                Arguments.of(3323, 7887950, 2, state)
        );
    }
}
