package tests;

import io.qameta.allure.*;
import models.UserRequest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;

@Feature("Creación de Recursos (POST)")
public class CreateUserTest extends BaseTest {

    @Test(description = "Crear un nuevo usuario")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Envía un objeto Java serializado como JSON y valida la respuesta 201 Created.")
    public void createNewUserTest() {
        // 1. Preparamos los datos usando nuestro Objeto Java
        UserRequest newUser = new UserRequest("Francisco Martinez", "fran_qa", "fran@test.com");

        given()
                .body(newUser) // convierte esto a JSON automáticamente
                .when()
                .post("/users")
                .then()
                .statusCode(201) // 201 significa "Created"
                .body("id", notNullValue()) // La API debe devolvernos un ID nuevo
                .body("name", equalTo("Francisco Martinez"))
                .body("email", equalTo("fran@test.com"));
    }
}