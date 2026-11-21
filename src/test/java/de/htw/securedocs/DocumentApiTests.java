package de.htw.securedocs;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = "app.cors.allowed-origins=http://localhost:5173")
class DocumentApiTests {
    @Value("${local.server.port}")
    private int port;

    private HttpResponse<String> request(String origin) throws Exception {
        var request = HttpRequest.newBuilder(URI.create("http://localhost:" + port + "/documents"));
        if (origin != null) request.header("Origin", origin);
        try (var client = HttpClient.newHttpClient()) {
            return client.send(request.GET().build(), HttpResponse.BodyHandlers.ofString());
        }
    }

    @Test
    void returnsDocumentsAsJson() throws Exception {
        var response = request(null);
        assertEquals(200, response.statusCode());
        assertTrue(response.headers().firstValue("Content-Type").orElse("").startsWith("application/json"));
        assertTrue(response.body().contains("studienbescheinigung.pdf"));
        assertTrue(response.body().contains("projektbeschreibung.pdf"));
    }

    @Test
    void allowsConfiguredFrontendOrigin() throws Exception {
        var response = request("http://localhost:5173");
        assertEquals(200, response.statusCode());
        assertEquals("http://localhost:5173", response.headers().firstValue("Access-Control-Allow-Origin").orElse(""));
    }

    @Test
    void rejectsOtherBrowserOrigins() throws Exception {
        var response = request("https://other.example");
        assertEquals(403, response.statusCode());
        assertTrue(response.headers().firstValue("Access-Control-Allow-Origin").isEmpty());
    }
}
