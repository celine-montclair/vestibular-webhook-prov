package vestibular_webhook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TesteService {

    private final JdbcTemplate jdbcTemplate;

    public void testarConexao() {

        try {

            Integer result = jdbcTemplate.queryForObject(
                    "SELECT 1",
                    Integer.class
            );

            System.out.println("TESTE BANCO OK: " + result);

        } catch (Exception e) {

            System.out.println("ERRO NO TESTE DO BANCO");
            e.printStackTrace();
        }
    }
}