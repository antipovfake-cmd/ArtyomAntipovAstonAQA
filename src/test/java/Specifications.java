import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {
    private static final String BASE_URL = "https://postman-echo.com";

    public static RequestSpecification requestSpecJson() {
        return new RequestSpecBuilder().setBaseUri(BASE_URL).setContentType(ContentType.JSON).log(LogDetail.ALL).build();
    }

    public static RequestSpecification requestSpecForm() {
        return new RequestSpecBuilder().setBaseUri(BASE_URL).setContentType("application/x-www-form-urlencoded; charset=utf-8").log(LogDetail.ALL).build();
    }

    public static RequestSpecification requestSpecGet() {
        return new RequestSpecBuilder().setBaseUri(BASE_URL).log(LogDetail.ALL).build();
    }

    // Проверка статус-кода 200 на уровне спецификации RestAssured
    public static ResponseSpecification responseSpecOk200() {
        return new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).log(LogDetail.BODY).build();
    }
}
