package eu.minecountry.core.common.exception;

import org.jetbrains.annotations.NotNull;

/**
 * Represents an abstract implementation of custom exceptions that supports formatted messages.
 *
 * @since 1.0.0
 */
public abstract class CustomException extends Exception {

    /**
     * Constructs a new exception with the specified detail message. Optionally the message can be formatted with placeholders.
     *
     * @param template The message including placeholders
     * @param args     The replacements
     */
    public CustomException(@NotNull String template, Object... args) {
        super(String.format(template, args));
    }

}
