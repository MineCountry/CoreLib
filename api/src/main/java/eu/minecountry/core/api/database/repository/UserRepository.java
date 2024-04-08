package eu.minecountry.core.api.database.repository;

import de.chojo.sadu.queries.api.call.Call;
import de.chojo.sadu.queries.api.query.Query;
import de.chojo.sadu.queries.api.results.writing.manipulation.ManipulationResult;
import de.chojo.sadu.queries.call.adapter.UUIDAdapter;
import eu.minecountry.core.api.entity.User;
import eu.minecountry.core.common.database.Repository;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Represents an abstract plattform independent implementation of a repository that handles user related queries.
 *
 * @since 1.0.0
 */
public interface UserRepository extends Repository<User> {

    /**
     * Returns an {@link Optional} wrapping the {@link User} or an {@link Optional#empty()} if the query failed or finished executing with en empty result.
     *
     * @param uuid The uuid of the user
     * @return The optional
     */
    Optional<User> findByUUID(@NotNull UUID uuid);

    /**
     * Returns an {@link Optional} wrapping the {@link User} or an {@link Optional#empty()} if the query failed or finished executing with en empty result.
     *
     * @param username The name of the user
     * @return The optional
     */
    Optional<User> findByUsername(@NotNull String username);


    /**
     * Saves an entity into the data-model.
     *
     * @param entity The entity
     * @return A {@link CompletableFuture} completing asynchronously with the result of the database call
     */
    @Override
    default CompletableFuture<ManipulationResult> save(@NotNull User entity) {
        ManipulationResult result = Query.query("INSERT INTO users (uuid, name) VALUES (?, ?) ON DUPLICATE KEY UPDATE name=?;")
                .single(Call.of().bind(entity.unique(), UUIDAdapter.AS_STRING).bind(entity.name()).bind(entity.name()))
                .update();

        CompletableFuture<ManipulationResult> future = new CompletableFuture<>();
        future.completeAsync(() -> result);
        return future;
    }

    /**
     * Deletes an existing entity from the data-model.
     *
     * @param entity The entity
     * @return A {@link CompletableFuture} completing asynchronously with the result of the database call
     */
    @Override
    default CompletableFuture<ManipulationResult> delete(@NotNull User entity) {
        ManipulationResult result = Query.query("DELETE FROM users WHERE uuid=?;")
                .single(Call.of().bind(entity.unique(), UUIDAdapter.AS_STRING))
                .delete();

        CompletableFuture<ManipulationResult> future = new CompletableFuture<>();
        future.completeAsync(() -> result);
        return future;
    }

}
