package com.abenas.service;

import com.abenas.vo.Transaction;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.smallrye.reactive.messaging.kafka.KafkaRecord;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.reactivestreams.Publisher;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class KafkaService {

    private FlowableEmitter<KafkaRecord<String, String>> emitter;

    private Flowable<KafkaRecord<String, String>> outgoingStream;

    @PostConstruct
    void init() {
        outgoingStream = Flowable.create(emitter -> {
            this.emitter = emitter;
        }, BackpressureStrategy.BUFFER);
    }

    public void produce(List<Transaction> transactions) {
        transactions.parallelStream().forEach(it -> {
            String id = UUID.randomUUID().toString();
            String message = "{\"fiscalYear\": " + it.fiscalYear +
                    ", \"fiscalPeriod\": " + it.fiscalPeriod +
                    ", \"department\": \"" + it.department + "\"" +
                    ", \"division\": \"" + it.division + "\"" +
                    ", \"merchant\": \"" + it.merchant + "\"" +
                    ", \"category\": \"" + it.category + "\"" +
                    ", \"creationDate\": \"" + it.creationDate + "\"" +
                    ", \"amount\": " + it.amount + "}";

            emitter.onNext(KafkaRecord.of(id,message));

        });

    }

    @PreDestroy
    void dispose() {
        emitter.onComplete();
    }

    @Outgoing("internal")
    Publisher<KafkaRecord<String, String>> produceKafkaMessage() {
        return outgoingStream;
    }

    @Incoming("internal")
    @Outgoing("transactions")
    KafkaRecord<String, String> transform(KafkaRecord<String, String> arg) {
        return arg;
    }

}
