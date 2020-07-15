import app.DTO.NiveauDTO;
import app.entity.Niveau;
import app.repository.NiveauRepository;
import app.service.NiveauService;
import app.service.NiveauServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NiveauServiceTest {

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
    public void testFindAllOrderByIdNiveauAsc() {
        // Given
        initMockNiveauRepository();

        // When
        List<NiveauDTO> dummyNiveauListFromService = niveauService.findAllOrderByIdNiveauAsc();

        // Then
        assertThat("result", dummyNiveauListFromService.size(), equalTo(3));
    }

    private void initMockNiveauRepository() {
        List<Niveau> dummyNiveauList = new ArrayList<>();
        dummyNiveauList.add(new Niveau(1, "Débutant"));
        dummyNiveauList.add(new Niveau(2, "Intermédiaire"));
        dummyNiveauList.add(new Niveau(3, "Avancé"));
        when(mockNiveauRepository.findAllByOrderByIdniveauAsc()).thenReturn(dummyNiveauList);
    }

}
