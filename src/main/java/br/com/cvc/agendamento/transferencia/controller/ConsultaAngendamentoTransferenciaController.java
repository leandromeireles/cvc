package br.com.cvc.agendamento.transferencia.controller;

import br.com.cvc.agendamento.transferencia.modelo.Transferencia;
import br.com.cvc.agendamento.transferencia.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsultaAngendamentoTransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @GetMapping(path = "/consultaagendamentotransferencia", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Page<Transferencia>> agendarTransferencia() {
                return ResponseEntity.ok(transferenciaService.consultarTransferencia());
            }
}
