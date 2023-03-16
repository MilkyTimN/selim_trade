package kg.megalab.selim_trade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPid;

@SpringBootApplication
public class SelimTradeApplication {
    private final static Logger log = LoggerFactory.getLogger(SelimTradeApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SelimTradeApplication.class, args);
        log.info(new ApplicationPid().toString());
    }


}
