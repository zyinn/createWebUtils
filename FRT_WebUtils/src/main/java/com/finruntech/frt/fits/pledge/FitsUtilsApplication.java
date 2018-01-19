package com.finruntech.frt.fits.pledge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by yinan.zhang on 2018/1/12.
 */
@SpringBootApplication
public class FitsUtilsApplication  implements CommandLineRunner {

        private Logger logger = LoggerFactory.getLogger(FitsUtilsApplication.class);

        public static void main(String[] args) {
            SpringApplication.run(FitsUtilsApplication.class, args);
        }

        @Override
        public void run(String... args) throws Exception {
            logger.info("Utils服务启动完成!");
        }

}
