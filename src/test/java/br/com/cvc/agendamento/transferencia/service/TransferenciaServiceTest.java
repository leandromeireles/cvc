package br.com.cvc.agendamento.transferencia.service;

import br.com.cvc.agendamento.transferencia.modelo.Taxacao;
import br.com.cvc.agendamento.transferencia.modelo.Transferencia;
import br.com.cvc.agendamento.transferencia.util.ParseQuantidadeDiaUtil;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TransferenciaServiceTest {

    @Test
    public void transferenciaNoMesmoDiaDeveriaSerDeTresReaisAMaisETresPorcentoDoValorASerTransferido() {

        TransferenciaService transferenciaService = new TransferenciaService();
        TaxaService taxaservice = new TaxaService();

        Transferencia transferencia = new Transferencia("12345-1", "12345-2", new BigDecimal("10"), new BigDecimal("10"), LocalDate.now(), LocalDate.now());

        long quantidadeDia = ChronoUnit.DAYS.between(transferencia.getDataTransferencia(), transferencia.getDataAgendamento());

        taxaservice.realizarTaxacao(transferencia, Taxacao.procurarEnun(quantidadeDia));


        assertEquals(new BigDecimal("13.30"), transferencia.getValor());


    }

    @Test
    public void transferenciaAteDezDiasDeveriaSerDeDozeReaisAMaisEMultiplicadoPelaQuantidadeDeDiasDaDataDeAgendamentoAteDataDeTransferencia() {

        TransferenciaService transferenciaService = new TransferenciaService();
        TaxaService taxaservice = new TaxaService();
        Transferencia transferencia = new Transferencia("12345-1", "12345-2", new BigDecimal("10"), new BigDecimal("10"), LocalDate.now(), LocalDate.now().plusDays(3));

        long quantidadeDia = ChronoUnit.DAYS.between(transferencia.getDataTransferencia(), transferencia.getDataAgendamento());

        quantidadeDia = ParseQuantidadeDiaUtil.converteQuantidadeDia(quantidadeDia);

        taxaservice.realizarTaxacao(transferencia, Taxacao.procurarEnun(quantidadeDia));


        assertEquals(new BigDecimal("46.00"), transferencia.getValor());


    }

    @Test
    public void transferenciaComTaxaRegressivaAcimaDeDezAteVinteDias() {

        TransferenciaService transferenciaService = new TransferenciaService();
        TaxaService taxaservice = new TaxaService();
        Transferencia transferencia = new Transferencia("12345-1", "12345-2", new BigDecimal("10"), new BigDecimal("10"), LocalDate.now(), LocalDate.now().plusDays(11));

        long quantidadeDia = ChronoUnit.DAYS.between(transferencia.getDataTransferencia(), transferencia.getDataAgendamento());

        quantidadeDia = ParseQuantidadeDiaUtil.converteQuantidadeDia(quantidadeDia);

        taxaservice.realizarTaxacao(transferencia, Taxacao.procurarEnun(quantidadeDia));


        assertEquals(new BigDecimal("10.80"), transferencia.getValor());


    }

    @Test
    public void transferenciaComTaxaRegressivaAcimaDeVinteAteTrintaDias() {

        TransferenciaService transferenciaService = new TransferenciaService();
        TaxaService taxaservice = new TaxaService();
        Transferencia transferencia = new Transferencia("12345-1", "12345-2", new BigDecimal("10"), new BigDecimal("10"), LocalDate.now(), LocalDate.now().plusDays(21));

        long quantidadeDia = ChronoUnit.DAYS.between(transferencia.getDataTransferencia(), transferencia.getDataAgendamento());

        quantidadeDia = ParseQuantidadeDiaUtil.converteQuantidadeDia(quantidadeDia);

        taxaservice.realizarTaxacao(transferencia, Taxacao.procurarEnun(quantidadeDia));


        assertEquals(new BigDecimal("10.60"), transferencia.getValor());


    }

    @Test
    public void transferenciaComTaxaRegressivaAcimaDeTrintaAteQuarentaDias() {

        TransferenciaService transferenciaService = new TransferenciaService();
        TaxaService taxaservice = new TaxaService();
        Transferencia transferencia = new Transferencia("12345-1", "12345-2", new BigDecimal("10"), new BigDecimal("10"), LocalDate.now(), LocalDate.now().plusDays(31));

        long quantidadeDia = ChronoUnit.DAYS.between(transferencia.getDataTransferencia(), transferencia.getDataAgendamento());

        quantidadeDia = ParseQuantidadeDiaUtil.converteQuantidadeDia(quantidadeDia);

        taxaservice.realizarTaxacao(transferencia, Taxacao.procurarEnun(quantidadeDia));


        assertEquals(new BigDecimal("10.40"), transferencia.getValor());


    }

    @Test
    public void transferenciaComTaxaRegressivaAcimaDeQuarentaEValorSuperiorACemMil() {

        TransferenciaService transferenciaService = new TransferenciaService();
        TaxaService taxaservice = new TaxaService();
        Transferencia transferencia = new Transferencia("12345-1", "12345-2", new BigDecimal("100.001"), new BigDecimal("10"), LocalDate.now(), LocalDate.now().plusDays(41));

        long quantidadeDia = ChronoUnit.DAYS.between(transferencia.getDataTransferencia(), transferencia.getDataAgendamento());

        quantidadeDia = ParseQuantidadeDiaUtil.converteQuantidadeDia(quantidadeDia);

        taxaservice.realizarTaxacao(transferencia, Taxacao.procurarEnun(quantidadeDia));


        assertEquals(new BigDecimal("102.00"), transferencia.getValor());


    }

    @Test
    public void transferenciaComException() {

        TransferenciaService transferenciaService = new TransferenciaService();
        TaxaService taxaservice = new TaxaService();
        Transferencia transferencia = new Transferencia("12345-1", "12345-2", new BigDecimal("10"), new BigDecimal("10"), LocalDate.now(), LocalDate.now().plusDays(41));

        long quantidadeDia = ChronoUnit.DAYS.between(transferencia.getDataTransferencia(), transferencia.getDataAgendamento());

        quantidadeDia = ParseQuantidadeDiaUtil.converteQuantidadeDia(quantidadeDia);

        long finalQuantidadeDia = quantidadeDia;

        assertThrows(IllegalArgumentException.class, ()-> taxaservice.realizarTaxacao(transferencia, Taxacao.procurarEnun(finalQuantidadeDia)));


    }
}
