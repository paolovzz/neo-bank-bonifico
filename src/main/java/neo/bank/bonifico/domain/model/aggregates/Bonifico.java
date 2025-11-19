package neo.bank.bonifico.domain.model.aggregates;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import neo.bank.bonifico.domain.model.enums.StatoBonifico;
import neo.bank.bonifico.domain.model.events.BonificoAnnullato;
import neo.bank.bonifico.domain.model.events.BonificoConvalidato;
import neo.bank.bonifico.domain.model.events.BonificoCreato;
import neo.bank.bonifico.domain.model.events.EventPayload;
import neo.bank.bonifico.domain.model.vo.Causale;
import neo.bank.bonifico.domain.model.vo.DataOperazione;
import neo.bank.bonifico.domain.model.vo.Iban;
import neo.bank.bonifico.domain.model.vo.IdOperazione;



@Slf4j
@Getter
@NoArgsConstructor
public class Bonifico extends AggregateRoot<Bonifico> implements Applier  {

    public static final String AGGREGATE_NAME = "BONIFICO";

    private IdOperazione idOperazione;
    private DataOperazione dataOperazione;
    private Iban ibanMittente;
    private Iban ibanDestinatario;
    private Causale causale;
    private double importo;
    private StatoBonifico stato;
    

    public static Bonifico crea(Iban ibanMittente, Iban ibanDestinatario, Causale causale, double importo) {

        IdOperazione idOperazione = new IdOperazione(UUID.randomUUID().toString());
        DataOperazione dataOp = new DataOperazione(LocalDateTime.now(ZoneOffset.UTC));
        Bonifico bonifico = new Bonifico();
        bonifico.idOperazione = idOperazione;
        bonifico.dataOperazione = dataOp;
        bonifico.ibanDestinatario = ibanDestinatario;
        bonifico.ibanMittente = ibanMittente;
        bonifico.causale = causale;
        bonifico.importo = importo;

        bonifico.events(new BonificoCreato(idOperazione, dataOp, ibanMittente, ibanDestinatario, causale, importo));
        return bonifico;
    }

    public void convalida() {
        events(new BonificoConvalidato());
    }

    public void annulla() {
        events(new BonificoAnnullato());
    }

    private void apply(BonificoCreato event) {
        this.idOperazione = event.getIdOperazione();
        this.ibanDestinatario = event.getIbanDestinatario();
        this.ibanMittente = event.getIbanMittente();
        this.causale = event.getCausale();
        this.dataOperazione = event.getDataOperazione();
        this.importo = event.getImporto();
        this.stato = StatoBonifico.CREATO;
    }

    private void apply(BonificoConvalidato event) {
        this.stato = StatoBonifico.CONVALIDATO;
    }

    private void apply(BonificoAnnullato event) {
        this.stato = StatoBonifico.ANNULLATO;
    }


    @Override
    public void apply(EventPayload event) {
         switch (event) {
            case BonificoCreato ev -> apply((BonificoCreato) ev);
            case BonificoConvalidato ev -> apply((BonificoConvalidato) ev);
            case BonificoAnnullato ev -> apply((BonificoAnnullato) ev);
            default -> throw new IllegalArgumentException("Evento non supportato");
        }
    }
}