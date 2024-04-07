package eu.minecountry.core.common;

/**
 * Represents an abstract implementation of a builder.
 *
 * @param <T> The type that will be created with this builder
 * @since 1.0.0
 */
public interface IBuilder<T> {

    /**
     * Constructs the object with the given modifications though this builder.
     *
     * @return The object
     */
    T build();

}
