package com.abenas.resources;

import com.abenas.service.TransactionService;
import com.abenas.vo.Transaction;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.util.Collections;

@Path("/transactions")
@Consumes(MediaType.APPLICATION_JSON)
public class TransactionResource {

    @Inject
    private TransactionService transactionService;

    @POST
    public String add(Transaction transaction) {
        transactionService.publishTransactions(transaction);
        return "Feito!";
    }

}
