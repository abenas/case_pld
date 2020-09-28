package com.abenas.routes;

import com.abenas.service.TransactionService;
import com.abenas.transform.FileToTransactionBean;
import com.abenas.vo.Transaction;
import org.apache.camel.builder.RouteBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class FileWatchRouter extends RouteBuilder {

    @Inject
    TransactionService transactionService;

    @Override
    public void configure() throws Exception {

        from("file:target/download?moveFailed=../error&delete=true")
                .routeId("FileWatch Route")
                .log("Detected file ${file:name}")
                .process(
                        exchange -> {
                            List<Transaction> transactions = FileToTransactionBean.transform(exchange);
                            transactionService.publishTransactions(transactions);
                        }
                )
                .log("File ${file:name} processed")
                .to("file:target/processed?moveFailed=../error");
    }

}
