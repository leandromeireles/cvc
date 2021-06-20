package br.com.cvc.agendamento.transferencia.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Entity
public class Transferencia {

    @Id
    @GeneratedValue
    public long id;
    public String contaOrigem;
    public String contaDestino;
    public BigDecimal valor;
    public BigDecimal taxa;
    public LocalDate dataTransferencia;
    public LocalDate dataAgendamento;

    public Transferencia(String contaOrigem, String contaDestino, BigDecimal valor, BigDecimal taxa, LocalDate dataTransferencia, LocalDate dataAgendamento) {
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
        this.taxa = taxa;
        this.dataTransferencia = dataTransferencia;
        this.dataAgendamento = dataAgendamento;
    }

    public Transferencia() {
    }

    public String getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(String contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public String getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(String contaDestino) {
        this.contaDestino = contaDestino;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getTaxa() {
        return taxa;
    }

    public void setTaxa(BigDecimal taxa) {
        this.taxa = taxa;
    }

    public LocalDate getDataTransferencia() {
        return dataTransferencia;
    }

    public void setDataTransferencia(LocalDate dataTransferencia) {
        this.dataTransferencia = dataTransferencia;
    }

    public LocalDate getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(LocalDate dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public void inserirValorTaxado(BigDecimal taxa) {
        this.taxa = taxa;
        this.valor = this.valor.add(taxa).setScale(2, RoundingMode.HALF_UP);
    }
}
