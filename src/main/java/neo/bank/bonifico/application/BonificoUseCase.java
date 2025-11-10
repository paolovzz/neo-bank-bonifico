package neo.bank.bonifico.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import neo.bank.bonifico.application.ports.input.commands.AnnullaBonificoCmd;
import neo.bank.bonifico.application.ports.input.commands.ConvalidaBonificoCmd;
import neo.bank.bonifico.application.ports.input.commands.CreaBonificoCmd;
import neo.bank.bonifico.application.ports.input.commands.RecuperaBonificoDaIdCmd;
import neo.bank.bonifico.application.ports.output.BonificoOutputPort;
import neo.bank.bonifico.application.ports.output.NotificaService;
import neo.bank.bonifico.domain.model.aggregates.Bonifico;
import neo.bank.bonifico.domain.model.enums.TipologiaNotifica;

@ApplicationScoped
@Slf4j
public class BonificoUseCase {
    
    @Inject
    private BonificoOutputPort ccOutputPort;
    
    @Inject
    private NotificaService notificaService;

    public void crea(CreaBonificoCmd cmd) {
        log.info("Comando [crea] in esecuzione...");
        Bonifico bonifico = Bonifico.crea(cmd.getIbanMittente(), cmd.getIbanDestinatario(), cmd.getCausale(), cmd.getImporto());
        ccOutputPort.salva(bonifico);
        notificaService.inviaEmail(cmd.getIbanMittente(), cmd.getImporto(), bonifico.getIdOperazione() , TipologiaNotifica.PREDISPOSIZIONE_BONIFICO);
        log.info("Comando [crea] terminato...");
    }

    public void convalida(ConvalidaBonificoCmd cmd) {
        log.info("Comando [convalida] in esecuzione...");
        Bonifico bonifico = ccOutputPort.recuperaDaId(cmd.getIdOperazione());
        bonifico.convalida();
        ccOutputPort.salva(bonifico);
        notificaService.inviaEmail(bonifico.getIbanMittente(), bonifico.getImporto(), bonifico.getIdOperazione() , TipologiaNotifica.APPLICAZIONE_BONIFICO);
        notificaService.inviaEmail(bonifico.getIbanDestinatario(), bonifico.getImporto(), bonifico.getIdOperazione() , TipologiaNotifica.RICEZIONE_SALDO_PER_BONIFICO);
        log.info("Comando [convalida] terminato...");
    }

    public void annulla(AnnullaBonificoCmd cmd) {
        log.info("Comando [annulla] in esecuzione...");
        Bonifico bonifico = ccOutputPort.recuperaDaId(cmd.getIdOperazione());
        bonifico.annulla();
        ccOutputPort.salva(bonifico);
        notificaService.inviaEmail(bonifico.getIbanMittente(), bonifico.getImporto(), bonifico.getIdOperazione() , TipologiaNotifica.ANNULLAMENTO_BONIFICO);
        log.info("Comando [annulla] terminato...");
    }


    public Bonifico recuperaBonificoDaId(RecuperaBonificoDaIdCmd cmd) {
        log.info("Recupero info recuperaBonificoDaId per id [{}]", cmd);
        Bonifico bonifico = ccOutputPort.recuperaDaId(cmd.getIdOperazione());
        log.info("Recupero terminato");
        return bonifico;
    }
}