import app.entity.Niveau;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class NiveauRepositoryTestV2 {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void saveNiveauTest() {
        Niveau niveau = new Niveau(4, "Test");
        entityManager.persist(niveau);
        //assertThat (true, equalTo(true));
    }
}
