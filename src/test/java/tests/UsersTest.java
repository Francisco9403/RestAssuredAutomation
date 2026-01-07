package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Feature("Gestión de Usuarios (JSONPlaceholder)")
public class UsersTest extends BaseTest {

    @Test(description = "Obtener lista de usuarios (GET)")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verifica que el endpoint devuelva código 200 y la lista de usuarios tenga 10 elementos.")
    public void getAllUsersTest() {
        given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0)) // Verifica que la lista no esté vacía
                .body("[0].name", equalTo("Leanne Graham")); // Validamos el primer usuario
    }

    @Test(description = "Obtener usuario único (GET)")
    @Severity(SeverityLevel.CRITICAL)
    public void getSingleUserTest() {
        given()
                .when()
                .get("/users/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("username", equalTo("Bret"))
                .body("email", equalTo("Sincere@april.biz"));
    }
    @Test(description = "Usuario no encontrado (404)")
    @Severity(SeverityLevel.NORMAL)
    public void userNotFoundTest() {
        given()
                .when()
                .get("/users/99999") // Un ID que seguro no existe
                .then()
                .statusCode(404); // Esperamos "Not Found"
    }

}