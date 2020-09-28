package com.abenas.service;

import com.abenas.vo.Transaction;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;


@ApplicationScoped
public class TransactionService {

    @Inject
    KafkaService kafkaService;

    public void publishTransactions(List<Transaction> transactions) {
        kafkaService.produce(transactions);
    }

}
