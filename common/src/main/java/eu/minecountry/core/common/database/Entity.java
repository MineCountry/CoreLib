package eu.minecountry.core.common.database;

/**
 * Represents an abstract implementation of a dataset in a database.
 *
 * @param <T> The type of data in the unique identifier column. Often {@code UUID}, {@code Long} or {@code Integer}
 * @since 1.0.0
 */
public interface Entity<T> {

    /**
     * Returns the unique identifier of the entity.
     *
     * @return The identifier
     */
    T unique();

}
