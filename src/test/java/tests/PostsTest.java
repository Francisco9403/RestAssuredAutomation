package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Feature("Validación de Posts y Filtros")
public class PostsTest extends BaseTest {

    @Test(description = "Filtrar posts por User ID")
    @Severity(SeverityLevel.NORMAL)
    @Description("Obtiene los posts del usuario 1 y valida que TODOS pertenezcan a ese ID.")
    public void getPostsByUserIdTest() {
        given()
                .queryParam("userId", 1) // Esto genera: /posts?userId=1
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                // Valida que CADA item de la lista tenga userId = 1
                .body("userId", everyItem(equalTo(1)));
    }
}