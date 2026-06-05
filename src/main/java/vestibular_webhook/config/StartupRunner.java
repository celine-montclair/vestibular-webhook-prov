package vestibular_webhook.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StartupRunner implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) {

        try {

            Integer resultado = jdbcTemplate.queryForObject(
                    "SELECT 1",
                    Integer.class
            );

            System.out.println("CONEXÃO COM TOTVS OK: " + resultado);

            Integer usuarios = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM SPSUSUARIO",
                    Integer.class
            );

            System.out.println("TOTAL USUARIOS: " + usuarios);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}