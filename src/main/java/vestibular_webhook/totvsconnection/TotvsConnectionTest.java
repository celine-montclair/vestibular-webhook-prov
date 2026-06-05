package vestibular_webhook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TotvsConnectionTest {

    private final JdbcTemplate jdbcTemplate;

    public void testar() {

        try {

            Integer result = jdbcTemplate.queryForObject(
                    "SELECT 1",
                    Integer.class
            );

            System.out.println("CONEXÃO TOTVS TESTADA COM SUCESSO: " + result);

        } catch (Exception e) {

            System.out.println("FALHA NA CONEXÃO TOTVS");
            e.printStackTrace();
        }
    }
}