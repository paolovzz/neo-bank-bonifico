package neo.bank.bonifico.application.exceptions;

public class BonificoNonTrovatoException extends RuntimeException {
    
    public BonificoNonTrovatoException(String idBonifico) {
        super(String.format("Bonifico con id [%s] non trovato...", idBonifico));
    }
}
