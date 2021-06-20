package br.com.cvc.agendamento.transferencia.service;

import br.com.cvc.agendamento.transferencia.modelo.Taxacao;
import br.com.cvc.agendamento.transferencia.modelo.Transferencia;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
public class TaxaService {
    public void realizarTaxacao(Transferencia transferencia, Taxacao taxacao) {

        BigDecimal taxa = taxacao.taxacao(transferencia);
        transferencia.inserirValorTaxado(taxa);

    }
}
