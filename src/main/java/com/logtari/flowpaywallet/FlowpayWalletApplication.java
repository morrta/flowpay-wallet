package com.logtari.flowpaywallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class FlowpayWalletApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowpayWalletApplication.class, args);
    }

}
