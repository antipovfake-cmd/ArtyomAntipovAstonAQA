import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PostmanEchoTest extends BaseTest {
    @Test
    public void testGetRequest() {
        PostmanEchoResponse response = given()
                .spec(reqSpecGet)
                .queryParam("test", "123")
                .queryParam("foo", "bar")
                .when()
                .get("/get")
                .then()
                .spec(resSpecOk) // Проверка кода 200
                .extract().as(PostmanEchoResponse.class);

        // Проверка значений всех полей в блоке args и url
        assertEquals("123", response.getArgs().getTest());
        assertEquals("bar", response.getArgs().getFoo());
        assertEquals("https://postman-echo.com/get?test=123&foo=bar", response.getUrl());
    }

    @Test
    public void testPostRawBodyRequest() {
        String jsonBody = "{\"foo1\":\"bar1\",\"foo2\":\"bar2\"}";

        PostmanEchoResponse response = given()
                .spec(reqSpecJson)
                .body(jsonBody)
                .when()
                .post("/post")
                .then()
                .spec(resSpecOk) // Проверка кода 200
                .extract().as(PostmanEchoResponse.class);

        // Проверка значений всех переданных полей
        assertEquals("bar1", response.getData().getFoo1());
        assertEquals("bar2", response.getData().getFoo2());
        assertEquals("https://postman-echo.com/post", response.getUrl());
    }

    @Test
    public void testPostFormDataRequest() {
        PostmanEchoResponse response = given()
                .spec(reqSpecForm)
                .formParam("formFoo1", "formBar1")
                .when()
                .post("/post")
                .then()
                .spec(resSpecOk) // Проверка кода 200
                .extract().as(PostmanEchoResponse.class);

        // Проверка полей формы
        assertEquals("formBar1", response.getForm().getFormFoo1());
        assertEquals("https://postman-echo.com/post", response.getUrl());
    }

    @Test
    public void testPutRequest() {
        Map<String, String> putBody = new HashMap<>();
        putBody.put("status", "updated");

        PostmanEchoResponse response = given()
                .spec(reqSpecJson)
                .body(putBody)
                .when()
                .put("/put")
                .then()
                .spec(resSpecOk) // Проверка кода 200
                .extract().as(PostmanEchoResponse.class);

        // Проверка измененных полей
        assertEquals("updated", response.getData().getStatus());
        assertEquals("https://postman-echo.com/put", response.getUrl());
    }

    @Test
    public void testPatchRequest() {
        Map<String, String> patchBody = new HashMap<>();
        patchBody.put("status", "patched");

        PostmanEchoResponse response = given()
                .spec(reqSpecJson)
                .body(patchBody)
                .when()
                .patch("/patch")
                .then()
                .spec(resSpecOk) // Проверка кода 200
                .extract().as(PostmanEchoResponse.class);

        // Проверка частичного обновления полей
        assertEquals("patched", response.getData().getStatus());
        assertEquals("https://postman-echo.com/patch", response.getUrl());
    }

    @Test
    public void testDeleteRequest() {
        Map<String, Integer> deleteBody = new HashMap<>();
        deleteBody.put("id", 99);

        PostmanEchoResponse response = given()
                .spec(reqSpecJson)
                .body(deleteBody)
                .when()
                .delete("/delete")
                .then()
                .spec(resSpecOk) // Проверка кода 200
                .extract().as(PostmanEchoResponse.class);

        // Проверка удаления
        assertEquals(99, response.getData().getId());
        assertEquals("https://postman-echo.com/delete", response.getUrl());
        assertNotNull(response.getHeaders().get("x-forwarded-proto")); // Проверка системного поля заголовков
    }
}
