import app.DTO.NiveauDTO;
import app.DTO.PaginatedPassesDTO;
import app.DTO.PasseDTO;
import app.DTO.TypePasseDTO;
import app.entity.Niveau;
import app.entity.TypePasse;
import app.repository.NiveauRepository;
import app.service.NiveauService;
import app.service.NiveauServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class RestControllerDataTest {

    private ObjectMapper jacksonMapper = new ObjectMapper();

    @InjectMocks
    private NiveauService niveauService = new NiveauServiceImpl();

    @Mock
    private NiveauRepository mockNiveauRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenNothing_whenShowAllNiveau_thenAllValuesAreReceived()
            throws IOException {
        // Given
        initMockNiveauRepository();
        HttpUriRequest request = new HttpGet( "http://localhost:8080/showAllNiveau");
        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
        // Then
        String json = EntityUtils.toString(httpResponse.getEntity());
        List<NiveauDTO> niveauxDTO = jacksonMapper.readValue(json, new TypeReference<List<NiveauDTO>>(){});
        assertThat(niveauxDTO.size(), equalTo(niveauService.findAllOrderByIdNiveauAsc().size()));
    }

    @Test
    public void givenNothing_whenCreatePasse_thenPasseIsCreated()
            throws IOException {
        // Given (état initial, on récupère le nombre de passe)
        HttpUriRequest requestGet1 = new HttpGet( "http://localhost:8080/showAllPassePaginated?page=0&size=5");
        HttpResponse httpResponseGet1 = HttpClientBuilder.create().build().execute( requestGet1 );
        String jsonGet1 = EntityUtils.toString(httpResponseGet1.getEntity());
        PaginatedPassesDTO passesPagineesDTO = jacksonMapper.readValue(jsonGet1, new TypeReference<PaginatedPassesDTO>(){});
        Long nbPassesInitial = passesPagineesDTO.getTotalRecords();

        // When (on insere la nouvelle passe)
        HttpPost requestPost = new HttpPost( "http://localhost:8080/createPasse");
            // init du DTO envoyé par post
            PasseDTO passeDTO = new PasseDTO();
            passeDTO.setId(null);
            passeDTO.setCavalier("cavalier");
            passeDTO.setCavaliere("cavalière");
            passeDTO.setNiveau(new NiveauDTO(new Niveau(1, "Débutant")));
            passeDTO.setNom("nom");
            passeDTO.setTypepasse(new TypePasseDTO(new TypePasse(1, "Comparsa")));
            passeDTO.setVideo("");
            // FIN init du DTO envoyé par post
        requestPost.setEntity(new StringEntity(jacksonMapper.writeValueAsString(passeDTO), "UTF-8"));
        requestPost.setHeader("Data-type", "application/json");
        requestPost.setHeader("Content-type", "application/json;charset=ISO-8859-1");
        HttpResponse httpResponsePost = HttpClientBuilder.create().build().execute( requestPost );
        assertThat(httpResponsePost.getStatusLine().getStatusCode(), equalTo(200));
        String jsonPost = EntityUtils.toString(httpResponsePost.getEntity());
        PasseDTO passeInseree = jacksonMapper.readValue(jsonPost, new TypeReference<PasseDTO>(){});

        // Then (le nombre de passe a du augmenté de 1)
        HttpUriRequest requestGet2 = new HttpGet( "http://localhost:8080/showAllPassePaginated?page=0&size=5");
        HttpResponse httpResponseGet2 = HttpClientBuilder.create().build().execute( requestGet2 );
        String jsonGet2 = EntityUtils.toString(httpResponseGet2.getEntity());
        PaginatedPassesDTO passesPagineesDTO_apres = jacksonMapper.readValue(jsonGet2, new TypeReference<PaginatedPassesDTO>(){});
        Long nbPassesApres = passesPagineesDTO_apres.getTotalRecords();
        assertThat(nbPassesApres, equalTo(nbPassesInitial+1));
    }

    private void initMockNiveauRepository() {
        List<Niveau> dummyNiveauList = new ArrayList<>();
        dummyNiveauList.add(new Niveau(1, "Débutant"));
        dummyNiveauList.add(new Niveau(2, "Intermédiaire"));
        dummyNiveauList.add(new Niveau(3, "Avancé"));
        when(mockNiveauRepository.findAllByOrderByIdniveauAsc()).thenReturn(dummyNiveauList);
    }

}
