import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    protected static RequestSpecification reqSpecJson;
    protected static RequestSpecification reqSpecForm;
    protected static RequestSpecification reqSpecGet;
    protected static ResponseSpecification resSpecOk;

    @BeforeAll
    public static void setUp() {
        reqSpecJson = Specifications.requestSpecJson();
        reqSpecForm = Specifications.requestSpecForm();
        reqSpecGet = Specifications.requestSpecGet();
        resSpecOk = Specifications.responseSpecOk200();
    }
}
