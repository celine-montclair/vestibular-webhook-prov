package vestibular_webhook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import vestibular_webhook.dto.VestibularWebhookRequest;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class VestibularWebhookService {

    private final JdbcTemplate jdbcTemplate;

    public void updateStatusandScore(VestibularWebhookRequest request) {

        System.out.println("=== WEBHOOK RECEBIDO ===");
        System.out.println("CPF: " + request.getCpf());
        System.out.println("IDPS: " + request.getIdps());
        System.out.println("NOTA: " + request.getNota());

        String buscaSql = """
                SELECT
                    SPI.IDPS,
                    SPI.CODUSUARIOPS,
                    SPI.NUMEROINSCRICAO
                FROM SPSOPCAOINSCRITO SPI
                JOIN SPSPROCESSOSELETIVO SPS
                    ON SPS.IDPS = SPI.IDPS
                JOIN SPSUSUARIO SPU
                    ON SPU.CODUSUARIOPS = SPI.CODUSUARIOPS
                WHERE SPU.CPF = ?
                  AND SPI.IDPS = ?
                  AND SPI.STATUS = 0
                """;

        Map<String, Object> candidato = jdbcTemplate.queryForMap(
                buscaSql,
                request.getCpf(),
                request.getIdps()
        );

        Integer idps = ((Number) candidato.get("IDPS")).intValue();

        Integer codUsuarioPs = ((Number) candidato.get("CODUSUARIOPS")).intValue();

        Integer numeroInscricao = ((Number) candidato.get("NUMEROINSCRICAO")).intValue();

        System.out.println("Candidato encontrado:");
        System.out.println("IDPS: " + idps);
        System.out.println("CODUSUARIOPS: " + codUsuarioPs);
        System.out.println("NUMEROINSCRICAO: " + numeroInscricao);

        String updateSql = """
                UPDATE SPSOPCAOINSCRITO
                SET
                    PONTUACAO = ?,
                    CLASSIFICACAO = 0,
                    STATUS = 5,
                    RECMODIFIEDBY = '000100012',
                    RECMODIFIEDON = GETDATE()
                WHERE IDPS = ?
                  AND NUMEROINSCRICAO = ?
                  AND CODUSUARIOPS = ?
                """;

        int linhasAfetadas = jdbcTemplate.update(
                updateSql,
                request.getNota(),
                idps,
                numeroInscricao,
                codUsuarioPs
        );

        System.out.println(
                "UPDATE realizado com sucesso. Linhas afetadas: "
                        + linhasAfetadas
        );
    }
}