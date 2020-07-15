import app.DTO.NiveauDTO;
import app.DTO.PasseDTO;
import app.service.NiveauService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.io.IOException;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Le but de cette classe est uniquement de tester que les points d'accès fonctionnent
 * Aucun test métier n'a sa place ici, c'est purement du testing des endpoints
 */

public class RestControllerEndPointTest {

    @Test
    public void givenNothing_whenShowAllNiveau_then200IsReceived()
            throws IOException {
        // Given
        HttpUriRequest request = new HttpGet( "http://localhost:8080/showAllNiveau");
        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
        // Then
        assertThat(
                httpResponse.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.OK.value()));
    }
}
