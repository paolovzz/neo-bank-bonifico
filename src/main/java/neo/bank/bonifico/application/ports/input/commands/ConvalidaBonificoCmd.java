package neo.bank.bonifico.application.ports.input.commands;

import lombok.Value;
import neo.bank.bonifico.domain.model.vo.IdOperazione;

@Value
public class ConvalidaBonificoCmd {
    
    private IdOperazione idOperazione;
}
