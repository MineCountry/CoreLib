package eu.minecountry.core.api.entity;

import eu.minecountry.core.common.database.Entity;

import java.util.UUID;

/**
 * Represents an abstract implementation of a player entity stored in a database.
 *
 * @since 1.0.0
 */
public interface User extends Entity<UUID> {

    /**
     * Returns the current username of the player.
     *
     * @return The username
     */
    String name();

}
