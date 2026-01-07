package tests;

import io.qameta.allure.*;
import models.UserRequest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Feature("Ciclo de Vida (Update & Delete)")
public class LifecycleTest extends BaseTest {

    @Test(description = "Actualizar usuario existente (PUT)")
    @Severity(SeverityLevel.NORMAL)
    public void updateUserTest() {
        // Datos nuevos para actualizar al usuario ID 1
        UserRequest updatedData = new UserRequest("Francisco Updated", "fran_v2", "newemail@test.com");

        given()
                .body(updatedData)
                .when()
                .put("/users/1") // PUT requiere el ID en la URL
                .then()
                .statusCode(200)
                .body("name", equalTo("Francisco Updated"))
                .body("username", equalTo("fran_v2"));
    }

    @Test(description = "Borrar usuario (DELETE)")
    @Severity(SeverityLevel.CRITICAL)
    public void deleteUserTest() {
        given()
                .when()
                .delete("/users/1")
                .then()
                .statusCode(200); // JSONPlaceholder devuelve 200 OK (vacío) al borrar
    }
}