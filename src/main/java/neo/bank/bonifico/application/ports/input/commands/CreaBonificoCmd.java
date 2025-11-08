package neo.bank.bonifico.application.ports.input.commands;

import lombok.Value;
import neo.bank.bonifico.domain.model.vo.Causale;
import neo.bank.bonifico.domain.model.vo.Iban;

@Value
public class CreaBonificoCmd {

    private Iban ibanMittente;
    private Iban ibanDestinatario;
    private Causale causale;
    private double importo;
}
