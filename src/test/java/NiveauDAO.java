import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class NiveauDAO {
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int getCountOfNiveaux() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM REFNIVEAU", Integer.class);
    }
}
