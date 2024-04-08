package eu.minecountry.core.common.database;

import de.chojo.sadu.queries.api.results.writing.manipulation.ManipulationResult;
import eu.minecountry.core.common.exception.InvalidParameterException;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Represents an abstract implementation of a repository.
 *
 * @param <T> The object type that is retrieved with this repository representing a dataset in an e.g. database
 * @since 1.0.0
 */
public interface Repository<T> {

    /**
     * Returns an immutable collection of all entities that are present in the data-model.
     *
     * @return The collection
     */
    Collection<T> findAll();

    /**
     * Creates a new entity that can be saved into the data-model. The order of parameters has to be identical with the parameter order of the objects constructor.
     *
     * @param parameters The parameters necessary to create the entity
     * @return The entity
     */
    T create(@NotNull List<Object> parameters) throws InvalidParameterException;

    /**
     * Saves an entity into the data-model.
     *
     * @param entity The entity
     * @return A {@link CompletableFuture} completing asynchronously with the result of the database call
     */
    CompletableFuture<ManipulationResult> save(@NotNull T entity);

    /**
     * Deletes an existing entity from the data-model.
     *
     * @param entity The entity
     * @return A {@link CompletableFuture} completing asynchronously with the result of the database call
     */
    CompletableFuture<ManipulationResult> delete(@NotNull T entity);
}
