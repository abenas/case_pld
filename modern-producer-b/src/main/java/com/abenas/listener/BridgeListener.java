package com.abenas.listener;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import io.smallrye.reactive.messaging.kafka.KafkaRecord;
import io.smallrye.reactive.messaging.kafka.OutgoingKafkaRecord;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class BridgeListener {

    @Incoming("bridge")
    @Outgoing("transactions")
    @Broadcast
    public OutgoingKafkaRecord<String, String> process(KafkaRecord<String, String> record) {
        return KafkaRecord.of(UUID.randomUUID().toString(), record.getPayload());
    }

}
