package eu.minecountry.core.paper.database.repository;

import de.chojo.sadu.queries.api.call.Call;
import de.chojo.sadu.queries.api.query.Query;
import de.chojo.sadu.queries.call.adapter.UUIDAdapter;
import eu.minecountry.core.api.database.repository.UserRepository;
import eu.minecountry.core.api.entity.User;
import eu.minecountry.core.common.exception.InvalidParameterException;
import eu.minecountry.core.paper.entity.PaperPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Represents a concrete implementation of an {@link UserRepository} on the paper plattform.
 */
public class PaperPlayerRepository implements UserRepository {

    /**
     * Returns an {@link Optional} wrapping the {@link User} or an {@link Optional#empty()} if the query failed or finished executing with en empty result.
     *
     * @param uuid The uuid of the user
     * @return The optional
     */
    @Override
    public Optional<User> findByUUID(@NotNull UUID uuid) {
        return Query.query("SELECT * FROM users WHERE uuid=?;")
                .single(Call.of().bind(uuid, UUIDAdapter.AS_STRING))
                .map(PaperPlayer.map())
                .first();
    }

    /**
     * Returns an {@link Optional} wrapping the {@link User} or an {@link Optional#empty()} if the query failed or finished executing with en empty result.
     *
     * @param username The name of the user
     * @return The optional
     */
    @Override
    public Optional<User> findByUsername(@NotNull String username) {
        return Query.query("SELECT * FROM users WHERE name=?;")
                .single(Call.of().bind(username))
                .map(PaperPlayer.map())
                .first();
    }

    /**
     * Returns an immutable collection of all entities that are present in the data-model.
     *
     * @return The collection
     */
    @Override
    public Collection<User> findAll() {
        Collection<User> collection = Query.query("SELECT * FROM users;")
                .single()
                .map(PaperPlayer.map())
                .all();

        return Collections.unmodifiableCollection(collection);
    }

    /**
     * Creates a new entity that can be saved into the data-model. The order of parameters has to be identical with the parameter order of the objects constructor.
     *
     * @param parameters The parameters necessary to create the entity
     * @return The entity
     */
    @Override
    public User create(@NotNull List<Object> parameters) throws InvalidParameterException {
        if (parameters.size() != 2) throw new InvalidParameterException("List of parameters must have exactly two values. Given: %s", parameters.size());
        if (!(parameters.getFirst() instanceof UUID uuid)) throw new InvalidParameterException("UUID must be a UUID. Given: %s", parameters.getFirst().getClass().getName());
        if (!(parameters.getLast() instanceof String name)) throw new InvalidParameterException("Name must be a String. Given: %s", parameters.getLast().getClass().getName());

        return PaperPlayer.of(uuid, name);
    }

}
