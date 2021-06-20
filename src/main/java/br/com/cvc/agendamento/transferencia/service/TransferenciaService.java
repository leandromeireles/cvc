package br.com.cvc.agendamento.transferencia.service;

import br.com.cvc.agendamento.transferencia.modelo.Taxacao;
import br.com.cvc.agendamento.transferencia.modelo.Transferencia;
import br.com.cvc.agendamento.transferencia.repository.TransferenciaRepository;
import br.com.cvc.agendamento.transferencia.util.ParseQuantidadeDiaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

@Service
public class TransferenciaService {

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private TaxaService taxaService;

    public void realizarTransferencia(Transferencia transferencia) {

        long quantidadeDia = ChronoUnit.DAYS.between(transferencia.getDataTransferencia(), transferencia.getDataAgendamento());

        quantidadeDia = ParseQuantidadeDiaUtil.converteQuantidadeDia(quantidadeDia);

        taxaService.realizarTaxacao(transferencia, Taxacao.procurarEnun(quantidadeDia));

        transferenciaRepository.save(transferencia);

    }

    public Page<Transferencia> consultarTransferencia() {
        Page<Transferencia> transferencias = transferenciaRepository.findAll(Pageable.ofSize(10));
        return transferencias;

    }
}
