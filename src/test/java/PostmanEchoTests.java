import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostmanEchoTests {

    @Test
    @DisplayName("Check response fields of GET request")
    public void testGetRequest() {
        given()
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .when()
                .get("https://postman-echo.com/get")
                .then()
                .log()
                .body().statusCode(200)
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-request-start", notNullValue())
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.x-amzn-trace-id", notNullValue())
                .body("headers.user-agent", equalTo("Apache-HttpClient/4.5.13 (Java/11.0.21)"))
                .body("headers.accept", equalTo("*/*"))
                .body("headers.postman-token", anyOf(nullValue(), notNullValue()))
                .body("headers.accept-encoding", notNullValue())
                .body("headers.cookie", anyOf(nullValue(), notNullValue()))
                .body("url", equalTo("https://postman-echo.com/get?foo1=bar1&foo2=bar2"));
    }

    @Test
    @DisplayName("Check response fields of POST RawText request")
    public void testPostRawTextRequest() {
        given()
                .contentType("text/plain; charset=UTF-8")
                .body("This is expected to be sent back as part of response body.")
                .when()
                .post("https://postman-echo.com/post")
                .then()
                .log()
                .body().statusCode(200)
                .body("args", equalTo(Collections.emptyMap()))
                .body("data", equalTo("This is expected to be sent back as part of response body."))
                .body("files", equalTo(Collections.emptyMap()))
                .body("form", equalTo(Collections.emptyMap()))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.'x-request-start'", notNullValue())
                .body("headers.'content-length'", equalTo("58"))
                .body("headers.'x-forwarded-proto'", equalTo("https"))
                .body("headers.'x-forwarded-port'", equalTo("443"))
                .body("headers.'x-amzn-trace-id'", notNullValue())
                .body("headers.'content-type'", equalTo("text/plain; charset=UTF-8"))
                .body("headers.'user-agent'", equalTo("Apache-HttpClient/4.5.13 (Java/11.0.21)"))
                .body("headers.accept", equalTo("*/*"))
                .body("headers.'postman-token'", anyOf(nullValue(), notNullValue()))
                .body("headers.'accept-encoding'", equalTo("gzip,deflate"))
                .body("headers.cookie", anyOf(nullValue(), notNullValue()))
                .body("json", nullValue())
                .body("url", equalTo("https://postman-echo.com/post"));
    }

    @Test
    @DisplayName("Check response fields of POST FormData request")
    public void testPostFormDataRequest() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("foo1", "bar1")
                .formParam("foo2", "bar2")
                .when()
                .post("https://postman-echo.com/post")
                .then()
                .log()
                .body().statusCode(200)
                .body("args", equalTo(Collections.emptyMap()))
                .body("data", equalTo(""))
                .body("files", equalTo(Collections.emptyMap()))
                .body("form.foo1", equalTo("bar1"))
                .body("form.foo2", equalTo("bar2"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.'x-request-start'", notNullValue())
                .body("headers.'content-length'", equalTo("19"))
                .body("headers.'x-forwarded-proto'", equalTo("https"))
                .body("headers.'x-forwarded-port'", equalTo("443"))
                .body("headers.'x-amzn-trace-id'", notNullValue())
                .body("headers.'user-agent'", equalTo("Apache-HttpClient/4.5.13 (Java/11.0.21)"))
                .body("headers.accept", equalTo("*/*"))
                .body("headers.'postman-token'", anyOf(nullValue(), notNullValue()))
                .body("headers.'accept-encoding'", equalTo("gzip,deflate"))
                .body("headers.'content-type'", equalTo("application/x-www-form-urlencoded; charset=UTF-8"))
                .body("headers.cookie", anyOf(nullValue(), notNullValue()))
                .body("json.foo1", equalTo("bar1"))
                .body("json.foo2", equalTo("bar2"))
                .body("url", equalTo("https://postman-echo.com/post"));
    }

    @Test
    @DisplayName("Check response fields of PUT request")
    public void testPutRequest() {
        given()
                .contentType("text/plain; charset=UTF-8")
                .body("This is expected to be sent back as part of response body.")
                .when()
                .put("https://postman-echo.com/put")
                .then()
                .log()
                .body().statusCode(200)
                .contentType("application/json")
                .body("args", equalTo(Collections.emptyMap()))
                .body("data", equalTo("This is expected to be sent back as part of response body."))
                .body("files", equalTo(Collections.emptyMap()))
                .body("form", equalTo(Collections.emptyMap()))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.'x-request-start'", notNullValue())
                .body("headers.'content-length'", equalTo("58"))
                .body("headers.'x-forwarded-proto'", equalTo("https"))
                .body("headers.'x-forwarded-port'", equalTo("443"))
                .body("headers.'x-amzn-trace-id'", notNullValue())
                .body("headers.'content-type'", equalTo("text/plain; charset=UTF-8"))
                .body("headers.'user-agent'", equalTo("Apache-HttpClient/4.5.13 (Java/11.0.21)"))
                .body("headers.accept", equalTo("*/*"))
                .body("headers.'postman-token'", anyOf(nullValue(), notNullValue()))
                .body("headers.'accept-encoding'", equalTo("gzip,deflate"))
                .body("headers.cookie", anyOf(nullValue(), notNullValue()))
                .body("json", nullValue())
                .body("url", equalTo("https://postman-echo.com/put"));
        //.log().all();
    }

    @Test
    @DisplayName("Check response fields of PATCH request")
    public void testPatchRequest() {
        given()
                .contentType("text/plain; charset=UTF-8")
                .body("This is expected to be sent back as part of response body.")
                .when()
                .patch("https://postman-echo.com/patch")
                .then()
                .log()
                .body().statusCode(200)
                .body("args", equalTo(Collections.emptyMap()))
                .body("data", equalTo("This is expected to be sent back as part of response body."))
                .body("files", equalTo(Collections.emptyMap()))
                .body("form", equalTo(Collections.emptyMap()))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.'x-request-start'", notNullValue())
                .body("headers.'content-length'", equalTo("58"))
                .body("headers.'x-forwarded-proto'", equalTo("https"))
                .body("headers.'x-forwarded-port'", equalTo("443"))
                .body("headers.'x-amzn-trace-id'", notNullValue())
                .body("headers.'content-type'", equalTo("text/plain; charset=UTF-8"))
                .body("headers.'user-agent'", equalTo("Apache-HttpClient/4.5.13 (Java/11.0.21)"))
                .body("headers.accept", equalTo("*/*"))
                .body("headers.'postman-token'", anyOf(nullValue(), notNullValue()))
                .body("headers.'accept-encoding'", equalTo("gzip,deflate"))
                .body("headers.cookie", anyOf(nullValue(), notNullValue()))
                .body("json", nullValue())
                .body("url", equalTo("https://postman-echo.com/patch"));
    }

    @Test
    @DisplayName("Check response fields of DELETE request")
    public void testDeleteRequest() {
        given()
                .contentType("text/plain; charset=UTF-8")
                .body("This is expected to be sent back as part of response body.")
                .when()
                .delete("https://postman-echo.com/delete")
                .then()
                .log()
                .body().statusCode(200)
                .body("args", equalTo(Collections.emptyMap()))
                .body("data", equalTo("This is expected to be sent back as part of response body."))
                .body("files", equalTo(Collections.emptyMap()))
                .body("form", equalTo(Collections.emptyMap()))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.'x-request-start'", notNullValue())
                .body("headers.'content-length'", equalTo("58"))
                .body("headers.'x-forwarded-proto'", equalTo("https"))
                .body("headers.'x-forwarded-port'", equalTo("443"))
                .body("headers.'x-amzn-trace-id'", notNullValue())
                .body("headers.'content-type'", equalTo("text/plain; charset=UTF-8"))
                .body("headers.'user-agent'", equalTo("Apache-HttpClient/4.5.13 (Java/11.0.21)"))
                .body("headers.accept", equalTo("*/*"))
                .body("headers.'postman-token'", anyOf(nullValue(), notNullValue()))
                .body("headers.'accept-encoding'", equalTo("gzip,deflate"))
                .body("headers.cookie", anyOf(nullValue(), notNullValue()))
                .body("json", nullValue())
                .body("url", equalTo("https://postman-echo.com/delete"));
    }
}