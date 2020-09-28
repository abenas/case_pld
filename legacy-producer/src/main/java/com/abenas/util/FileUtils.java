package com.abenas.util;

import org.apache.camel.Exchange;
import org.apache.camel.component.file.GenericFile;

import java.io.File;

public class FileUtils {

    public static File getFile(GenericFile file) {
        return (File) file.getBody();
    }

    public static File getFile(Exchange exchange) {
        GenericFile genericFile = exchange.getIn().getBody(GenericFile.class);
        File file;

        if (genericFile == null) {
            file = exchange.getIn().getBody(File.class);
        } else {
            file = getFile(genericFile);
        }
        return file;
    }

}
