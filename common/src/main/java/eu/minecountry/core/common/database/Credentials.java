package eu.minecountry.core.common.database;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a utility class that's only purpose is to store sensitive login data to a data-model like a database. Only works for non file based models.
 *
 * @param host     The host
 * @param port     The port. Normally 3306
 * @param database The name of the database
 * @param username The username to login. {@code root} is not recommended
 * @param password The user password. Blank passwords are not recommended
 */
public record Credentials(@NotNull String host, @NotNull String port, @NotNull String database, @NotNull String username, @NotNull String password) {
}
