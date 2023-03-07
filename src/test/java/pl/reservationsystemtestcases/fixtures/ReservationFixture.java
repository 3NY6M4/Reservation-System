package pl.reservationsystemtestcases.fixtures;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import pl.reservationsystemtestcases.request.reservationAssign.ReservationAssignRequest;
import pl.reservationsystemtestcases.request.reservationCreate.ReservationCreateRequest;

import java.util.random.RandomGenerator;

import static org.testng.AssertJUnit.assertEquals;

public class ReservationFixture {

    public static int itemId = RandomGenerator.getDefault().nextInt();
    public static int partId = RandomGenerator.getDefault().nextInt();

    public static JsonPath create(int supplier, int product, int reservationQuantity)
    {
        JSONObject payload = getJsonObject(supplier, product, reservationQuantity);

        return ReservationCreateRequest
                .reservationCreateRequest(payload)
                .jsonPath();
    }

    public static JsonPath assign(int reservationId, int supplier, int product, int reservationQuantity)
    {
        JSONObject payload = getJsonObject(supplier, product, reservationQuantity);
        payload.put("id", reservationId);

        Response response = ReservationAssignRequest.reservationAssignRequest(payload);
        assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        return response.jsonPath();
    }

    private static JSONObject getJsonObject(int supplier, int product, int reservationQuantity) {
        JSONObject payload = new JSONObject();
        payload.put("referrer", "SYSTEM");
        payload.put("source", "CHANGE");
        payload.put("supplierId", supplier);
        payload.put("productId", product);
        payload.put("quantity", reservationQuantity);
        payload.put("operatorId", 96);
//        payload.put("itemId", itemId);
//        payload.put("partId", partId);
        return payload;
    }

}
