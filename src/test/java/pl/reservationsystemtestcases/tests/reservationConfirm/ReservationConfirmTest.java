package pl.reservationsystemtestcases.tests.reservationConfirm;

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
import pl.reservationsystemtestcases.fixtures.ReservationFixture;
import pl.reservationsystemtestcases.request.reservationConfirm.ReservationConfirmRequest;
import pl.reservationsystemtestcases.tests.reservationCreate.ReservationCreateTest;
import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReservationConfirmTest {

    public static String state;

    @DisplayName("Confirm a reservation with valid data")
    public void confirmReservationTest() {

        JsonPath reservation = ReservationFixture.create(1, 18, 1);
        JsonPath reservationAssigned = ReservationFixture.assign(reservation.getInt("id"),
                reservation.getInt("supplierId"),
                reservation.getInt("productId"),
                reservation.getInt("quantity")
        );


    }
}
