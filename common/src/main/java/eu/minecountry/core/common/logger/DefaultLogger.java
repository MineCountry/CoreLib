package eu.minecountry.core.common.logger;

import eu.minecountry.core.common.ILogger;
import eu.minecountry.core.common.logger.settings.DefaultLogFilter;
import eu.minecountry.core.common.logger.settings.DefaultLogFormatter;
import lombok.Setter;
import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents an adjustable logger that contains the most common used logging methods.
 */
public class DefaultLogger implements ILogger {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private Path filePath;
    @Setter private boolean useConsole = false;
    @Setter private boolean useFile = false;

    /**
     * Logs the given message prefixed with the level. Supports formatted messages.
     *
     * @param level   The level
     * @param message The message
     * @param args    Replacements for formatted messages
     */
    @Override
    public void log(@NotNull Level level, @NotNull String message, Object... args) {
        logger.log(level, String.format(message, args));
    }

    /**
     * Logs the given message with {@link Level#INFO} as the log level. Supports formatted messages.
     *
     * @param message The message
     * @param args    Replacements for formatted messages
     */
    @Override
    public void info(@NotNull String message, Object... args) {
        log(Level.INFO, message, args);
    }

    /**
     * Logs the given message with {@link Level#WARNING} as the log level. Supports formatted messages.
     *
     * @param message The message
     * @param args    Replacements for formatted messages
     */
    @Override
    public void warning(@NotNull String message, Object... args) {
        log(Level.WARNING, message, args);
    }

    /**
     * Logs the given message with {@link Level#SEVERE} as the log level. Supports formatted messages.
     *
     * @param message The message
     * @param args    Replacements for formatted messages
     */
    @Override
    public void error(@NotNull String message, Object... args) {
        log(Level.SEVERE, message, args);
    }

    /**
     * Specifies the path where the log should be written to. The path must be accessible for writing. <br>
     * Only needed when using {@link #useConsole(boolean)} is set to {@code true}.
     *
     * @param path The path
     */
    public void applyPath(@NotNull Path path) {
        filePath = path;
        try {
            addHandler();
        } catch (IOException exception) {
            error("An error occurred while trying to add logger handlers: %s", exception.getMessage());
        }
    }

    /* ===================================================================
       |                     Begin private API                           |
       =================================================================== */

    /**
     * Gets the console usage mode.
     *
     * @return The mode
     */
    @Internal
    public boolean isUsingConsole() {
        return useConsole;
    }

    /**
     * Gets if the file output mode is in use.
     *
     * @return The mode in use
     */
    @Internal
    public boolean isUsingFile() {
        return useFile;
    }

    /**
     * Adds the {@link ConsoleHandler} and/or the {@link  FileHandler} to the logger.
     *
     * @throws IOException If the path to the output file is incorrect or cannot be used
     */
    @Internal
    private void addHandler() throws IOException {
        var formatter = new DefaultLogFormatter();
        var filter = new DefaultLogFilter();

        if (useConsole) {
            var consoleHandler = new ConsoleHandler();
            consoleHandler.setEncoding("UTF-8");
            consoleHandler.setFormatter(formatter);
            consoleHandler.setFilter(filter);
            logger.addHandler(consoleHandler);
        }

        if (useFile) {
            var directory = new File(filePath.toFile().getAbsolutePath());
            if (!directory.exists()) {
                //noinspection ResultOfMethodCallIgnored
                directory.mkdirs();
            }

            var fileHandler = new FileHandler(new File(directory, "log_%g.log").getAbsolutePath(), 1000, 20, true);
            fileHandler.setEncoding("UTF-8");
            fileHandler.setFormatter(formatter);
            fileHandler.setFilter(filter);
            logger.addHandler(fileHandler);
        }
    }
}
