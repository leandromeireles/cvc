package br.com.cvc.agendamento.transferencia.util;

public class ParseQuantidadeDiaUtil {
    public static long converteQuantidadeDia(long quantidadeDia) {

        if (quantidadeDia == 0) {

            return 0;
        } else if (quantidadeDia < 11)  {

            return 1;
        } else {

            return 2;
        }


    }
}
