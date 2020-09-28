package com.abenas.transform;

import com.abenas.vo.Transaction;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.apache.camel.Exchange;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static com.abenas.util.FileUtils.getFile;

public class FileToTransactionBean {

    public static List<Transaction> transform(Exchange exchange) throws FileNotFoundException {

        File file = getFile(exchange);
        FileReader fileReader = new FileReader(file);

        HeaderColumnNameMappingStrategy<Transaction> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(Transaction.class);

        CsvToBean<Transaction> csvToBean = new CsvToBeanBuilder<Transaction>(fileReader)
                .withMappingStrategy(strategy)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        return csvToBean.parse();

    }

}
