package eu.minecountry.core.examples.logger;

import eu.minecountry.core.common.logger.DefaultLogger;
import eu.minecountry.core.common.logger.builder.LoggerBuilder;

import java.nio.file.Path;
import java.util.logging.Level;

public class ExampleLogger {

    public static void main(String[] args) {
        DefaultLogger logger = LoggerBuilder.of(DefaultLogger::new)
                .with(DefaultLogger::useFile, true)
                .with(DefaultLogger::applyPath, Path.of(System.getProperty("user.dir") + "/logs/"))
                .build();

        logger.info("This is a message without a template");
        logger.warning("This is a message with a template: %s", "Hello World");
        logger.error("This is a message with a template: %s", logger.isUsingFile());
        logger.log(Level.INFO, "This is a message with a template: %s", "Hello World");
    }

}
