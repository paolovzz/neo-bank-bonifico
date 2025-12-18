package neo.bank.bonifico.domain.model.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import neo.bank.bonifico.domain.exceptions.ValidazioneException;
import neo.bank.bonifico.domain.model.enums.CodiceErrore;


@Getter
@EqualsAndHashCode
public class IdOperazione {

    private String id;

    public IdOperazione(String id) {
        if (id == null || id.isBlank()) {
            throw new ValidazioneException(
                IdOperazione.class.getSimpleName(),
                CodiceErrore.ID_NON_PUO_ESSERE_NULL.getCodice()
            );
        }
        this.id = id;
    }
}
