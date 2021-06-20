package br.com.cvc.agendamento.transferencia.modelo;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

public enum Taxacao {

    A(0) {
        @Override
        public BigDecimal taxacao(Transferencia transferencia) {
            BigDecimal taxa = transferencia.getValor().multiply(new BigDecimal("0.03"));
            taxa = taxa.add(new BigDecimal("3"));
            return taxa;
        }
    }, B(1) {
        @Override
        public BigDecimal taxacao(Transferencia transferencia) {
            long quantidadeDia = ChronoUnit.DAYS.between(transferencia.getDataTransferencia(), transferencia.getDataAgendamento());
            BigDecimal taxa = new BigDecimal("12").multiply(BigDecimal.valueOf(quantidadeDia));
            return taxa;
        }
    }, C(2) {
        @Override
        public BigDecimal taxacao(Transferencia transferencia) {

            long quantidadeDia = ChronoUnit.DAYS.between(transferencia.getDataTransferencia(), transferencia.getDataAgendamento());
            BigDecimal taxa = new BigDecimal("0.00");
            BigDecimal valor = new BigDecimal("100.000");
            int i = transferencia.getValor().compareTo(valor);

            if (quantidadeDia > 10 && quantidadeDia <= 20) {
                taxa = transferencia.getValor().multiply(new BigDecimal("0.08"));
                return taxa;
            }else if(quantidadeDia > 20 && quantidadeDia <= 30){
                taxa = transferencia.getValor().multiply(new BigDecimal("0.06"));
                return taxa;
            }else if(quantidadeDia > 30 && quantidadeDia <= 40){
                taxa = transferencia.getValor().multiply(new BigDecimal("0.04"));
                return taxa;
            }else if(quantidadeDia > 40 && i == 1){
                taxa = transferencia.getValor().multiply(new BigDecimal("0.02"));
                return taxa;
            }else {

                throw new IllegalArgumentException("Não é possivel a realização da transferência");
            }

        }
    };

    private Taxacao(long quantidadeDiaEscolhido) {
        quantidadeDia = quantidadeDiaEscolhido;
    }

    public long getQuantidadeDia() {
        return quantidadeDia;
    }

    private long quantidadeDia;

    public static Taxacao procurarEnun(long id) {
        for (Taxacao e : values()) {
            if (e.getQuantidadeDia() == id)
                return e;
        }
        return null;
    }


    public abstract BigDecimal taxacao(Transferencia transferencia);
}
