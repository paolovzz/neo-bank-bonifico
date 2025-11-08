package neo.bank.bonifico.domain.model.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import neo.bank.bonifico.domain.exceptions.ValidazioneException;
import neo.bank.bonifico.domain.model.enums.CodiceErrore;

@Getter
@EqualsAndHashCode
public class Causale {

    private String causale;

    public Causale(String causale) {
        if (causale == null) {
            throw new ValidazioneException(Causale.class.getSimpleName(),
                    CodiceErrore.CAUSALE_NON_PUO_ESSERE_NULL.getCodice());
        }
        this.causale = causale;
    }
}
