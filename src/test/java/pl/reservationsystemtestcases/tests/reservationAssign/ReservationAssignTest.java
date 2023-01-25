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
import pl.reservationsystemtestcases.fixtures.ReservationFixture;
import pl.reservationsystemtestcases.request.reservationAssign.ReservationAssignRequest;
import pl.reservationsystemtestcases.tests.reservationCreate.ReservationCreateTest;
import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReservationAssignTest {

    @DisplayName("Assign a reservation with valid data")
    public void assignReservationTest() {

        JsonPath reservation = ReservationFixture.create(1, 18, 1);
        JsonPath reservationAssigned = ReservationFixture.assign(
                reservation.getInt("id"), reservation.getInt("supplierId"), reservation.getInt("productId"), reservation.getInt("quantity")
        );


        Assertions.assertThat(reservationAssigned.getInt("id")).isEqualTo(reservation.getInt("id"));
        Assertions.assertThat(reservationAssigned.getInt("supplierId")).isEqualTo(reservation.getInt("supplierId"));
        Assertions.assertThat(reservationAssigned.getInt("productId")).isEqualTo(reservation.getInt("productId"));
        Assertions.assertThat(reservationAssigned.getInt("quantity")).isEqualTo(reservation.getInt("quantity"));
        Assertions.assertThat(reservationAssigned.getInt("itemId")).isEqualTo(reservation.getInt("itemId"));
        Assertions.assertThat(reservationAssigned.getInt("partId")).isEqualTo(reservation.getInt("partId"));

    }
}
