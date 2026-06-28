import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EchoContent {
    // Поля для GET, POST, PUT, PATCH, DELETE методов
    private String test;
    private String foo;
    private String foo1;
    private String foo2;
    private String status;
    private Integer id;

    // Поля для Form Data метода
    private String formFoo1;

    // Геттеры и сеттеры
    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }

    public String getFoo1() {
        return foo1;
    }

    public void setFoo1(String foo1) {
        this.foo1 = foo1;
    }

    public String getFoo2() {
        return foo2;
    }

    public void setFoo2(String foo2) {
        this.foo2 = foo2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFormFoo1() {
        return formFoo1;
    }

    public void setFormFoo1(String formFoo1) {
        this.formFoo1 = formFoo1;
    }
}
