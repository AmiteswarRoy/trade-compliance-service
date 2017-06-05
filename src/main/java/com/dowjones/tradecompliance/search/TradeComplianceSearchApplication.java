package com.dowjones.tradecompliance.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.dowjones.tradecompliance.search"
})
public class TradeComplianceSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradeComplianceSearchApplication.class, args);
	}
}
