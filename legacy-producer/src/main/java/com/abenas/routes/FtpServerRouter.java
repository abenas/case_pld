package com.abenas.routes;

import org.apache.camel.builder.RouteBuilder;

public class FtpServerRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        getContext().getPropertiesComponent().setLocation("classpath:ftp.properties");
        getContext().getShutdownStrategy().setTimeout(10);

        from("{{ftp.server}}").routeId("FtpServer Route")
                .to("file:target/download?fileName=${date:now:yyyMMdd}-${file:name}")
                .log("Downloaded file ${file:name} complete.");

    }

}
