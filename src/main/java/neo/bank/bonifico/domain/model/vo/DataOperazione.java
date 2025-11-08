package neo.bank.bonifico.domain.model.vo;

import java.time.LocalDateTime;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import neo.bank.bonifico.domain.exceptions.ValidazioneException;
import neo.bank.bonifico.domain.model.enums.CodiceErrore;

@Getter
@EqualsAndHashCode
public class DataOperazione {

    private LocalDateTime dataOra;

    public DataOperazione(LocalDateTime dataOra) {
        if (dataOra == null) {
            throw new ValidazioneException(DataOperazione.class.getSimpleName(),
                    CodiceErrore.CAUSALE_NON_PUO_ESSERE_NULL.getCodice());
        }
        this.dataOra = dataOra;
    }
}
