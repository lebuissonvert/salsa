import app.DTO.NiveauDTO;
import app.entity.Niveau;
import app.repository.NiveauRepository;
import app.service.NiveauService;
import app.service.NiveauServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class NiveauRepositoryTest {
/*
    @Mock
    JdbcTemplate jdbcTemplate;

    @Test
    public void whenMockJdbcTemplate_thenReturnCorrectNiveauCount() {
        NiveauDAO niveauDAO = new NiveauDAO();
        ReflectionTestUtils.setField(niveauDAO, "jdbcTemplate", jdbcTemplate);
        Mockito.when(jdbcTemplate.queryForObject("SELECT COUNT(*) FROM REFNIVEAU", Integer.class))
                .thenReturn(3);

        assertEquals(java.util.Optional.of(3), niveauDAO.getCountOfNiveaux());
    }

//    private Integer getCountOfNiveaux() {
//        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM REFNIVEAU", Integer.class);
//    }

*/
}
