package br.com.cvc.agendamento.transferencia.controller;

import br.com.cvc.agendamento.transferencia.modelo.Transferencia;
import br.com.cvc.agendamento.transferencia.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AngendarTransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @PostMapping(path = "/agendartransferencia", consumes = "application/json", produces = "application/json")
    public void agendarTransferencia(@RequestBody Transferencia form) {
        transferenciaService.realizarTransferencia(form);
            }
}
