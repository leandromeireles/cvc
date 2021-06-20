package br.com.cvc.agendamento.transferencia.repository;

import br.com.cvc.agendamento.transferencia.modelo.Transferencia;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {



}
