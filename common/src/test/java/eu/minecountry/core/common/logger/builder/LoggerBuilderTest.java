package eu.minecountry.core.common.logger.builder;

import eu.minecountry.core.common.logger.DefaultLogger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoggerBuilderTest {

    @Test
    @DisplayName("Test LoggerBuilder")
    void testLoggerBuilder() {
        DefaultLogger logger = LoggerBuilder.of(DefaultLogger::new)
                .with(DefaultLogger::useConsole, true)
                .with(DefaultLogger::useFile, false)
                .build();

        assertTrue(logger.isUsingConsole());
        assertFalse(logger.isUsingFile());
    }
}