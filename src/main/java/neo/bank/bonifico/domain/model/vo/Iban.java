package neo.bank.bonifico.domain.model.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import neo.bank.bonifico.domain.exceptions.ValidazioneException;
import neo.bank.bonifico.domain.model.enums.CodiceErrore;

@Getter
@EqualsAndHashCode
public class Iban {

    private String codice;

    public Iban(String codice) {
        if (codice == null) {
            throw new ValidazioneException(Iban.class.getSimpleName(),
                    CodiceErrore.IBAN_NON_PUO_ESSERE_NULL.getCodice());
        }
        this.codice = codice;
    }
}
