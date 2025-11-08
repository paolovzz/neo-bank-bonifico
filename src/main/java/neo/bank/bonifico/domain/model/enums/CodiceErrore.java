package neo.bank.bonifico.domain.model.enums;

public enum CodiceErrore {
    
    IBAN_NON_PUO_ESSERE_NULL("IBAN_NON_PUO_ESSERE_NULL"),
    ID_NON_PUO_ESSERE_NULL("ID_NON_PUO_ESSERE_NULL"),
    DATA_OPERAZIONE_NON_PUO_ESSERE_NULL("DATA_OPERAZIONE_NON_PUO_ESSERE_NULL"),
    CAUSALE_NON_PUO_ESSERE_NULL("CAUSALE_NON_PUO_ESSERE_NULL");

    private String codice;

    private CodiceErrore(String codice) {
        this.codice = codice;
    }

    public String getCodice() {
        return codice;
    }
    
    
}
