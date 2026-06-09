package vestibular_webhook.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vestibular_webhook.dto.VestibularWebhookRequest;
import vestibular_webhook.service.VestibularWebhookService;

import java.util.Map;

@RestController
@RequestMapping("/webhook")
@RequiredArgsConstructor
public class VestibularWebhookController {

    private final VestibularWebhookService vestibularWebhookService;

    @Value("${webhook.token}")
    private String webhookToken;

    @PostMapping("/vestibular")
    public ResponseEntity<?> receberWebhook(

            @RequestHeader(value = "Authorization", required = false)
            String authorization,

            @RequestBody VestibularWebhookRequest request
    ) {

        if (authorization == null ||
                !authorization.equals("Bearer " + webhookToken)) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Token inválido"));
        }

        try {

            vestibularWebhookService.updateStatusandScore(request);

            return ResponseEntity.ok(
                    Map.of("message", "Webhook processado com sucesso")
            );

        } catch (RuntimeException e) {

            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }
}