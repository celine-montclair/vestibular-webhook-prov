    package vestibular_webhook.dto;

    public class VestibularWebhookRequest {

        private String cpf;
        private Integer idps;
        private Double nota;

        public String getCpf() {
            return cpf;
        }

        public void setCpf(String cpf) {
            this.cpf = cpf;
        }

        public Integer getIdps() {
            return idps;
        }

        public void setIdps(Integer idps) {
            this.idps = idps;
        }

        public Double getNota() {
            return nota;
        }

        public void setNota(Double nota) {
            this.nota = nota;
        }
    }