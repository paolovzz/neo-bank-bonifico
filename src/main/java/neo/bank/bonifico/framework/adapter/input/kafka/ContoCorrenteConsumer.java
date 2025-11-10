package neo.bank.bonifico.framework.adapter.input.kafka;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletionStage;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.smallrye.common.annotation.Blocking;
import io.smallrye.reactive.messaging.kafka.api.IncomingKafkaRecordMetadata;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import neo.bank.bonifico.application.BonificoUseCase;
import neo.bank.bonifico.application.ports.input.commands.CreaBonificoCmd;
import neo.bank.bonifico.domain.model.vo.Causale;
import neo.bank.bonifico.domain.model.vo.Iban;

@ApplicationScoped
@Slf4j
public class ContoCorrenteConsumer {

    @Inject
    private ObjectMapper mapper;
    @Inject
    private BonificoUseCase app;

    private static final String EVENT_OWNER = "CONTO_CORRENTE";
    private static final String BONIFICO_PREDISPOSTO_EVENT_NAME = "BonificoPredisposto";

    @Incoming("conto-corrente-notifications")
    @Blocking
    public CompletionStage<Void> consume(Message<String> msg) {
        var metadata = msg.getMetadata(IncomingKafkaRecordMetadata.class).orElseThrow();
        String eventType = new String(metadata.getHeaders().lastHeader("eventType").value(), StandardCharsets.UTF_8);
        String aggregateName = new String(metadata.getHeaders().lastHeader("aggregateName").value(),
                StandardCharsets.UTF_8);
        String payload = msg.getPayload();
        log.info("INCOMING:\n- EventType => {}\n- AggregateName => {}", eventType, aggregateName);
        if (aggregateName.equals(EVENT_OWNER)) {
            JsonNode json = convertToJsonNode(payload);
            switch (eventType) {
                case BONIFICO_PREDISPOSTO_EVENT_NAME:{
                    Iban ibanMittente = new Iban(json.get("ibanMittente").asText());
                    Iban ibanDestinatario = new Iban(json.get("ibanDestinatario").asText());
                    Causale causale = new Causale(json.get("causale").asText());
                    double importo =  Math.abs(json.get("importo").asDouble());
                    app.crea(new CreaBonificoCmd(ibanMittente, ibanDestinatario, causale, importo));
                    break;
                }
                default:
                    log.warn("Evento [{}] non gestito...", eventType);
                    break;
            }
        }
        return msg.ack();
    }

    private JsonNode convertToJsonNode(String payload) {
        try {
            return mapper.readTree(payload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Errore durante la conversione json del messaggio kafka", e);
        }
    }
}
