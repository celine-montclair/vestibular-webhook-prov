package vestibular_webhook.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vestibular_webhook.dto.VestibularWebhookRequest;
import vestibular_webhook.service.VestibularWebhookService;

@RestController
@RequestMapping("/webhook")
@RequiredArgsConstructor
public class VestibularWebhookController {

    private final VestibularWebhookService vestibularWebhookService;

    @PostMapping("/vestibular")
    public ResponseEntity<String> receberWebhook(
            @RequestBody VestibularWebhookRequest request
    ) {

        vestibularWebhookService.updateStatusandScore(request);

        return ResponseEntity.ok("Webhook processado com sucesso");
    }
}