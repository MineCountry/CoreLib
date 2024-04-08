package eu.minecountry.core.paper.entity;

import de.chojo.sadu.mapper.rowmapper.RowMapping;
import eu.minecountry.core.api.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Represents a concrete implementation of an {@link User} for the paper plattform.
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PaperPlayer implements User {

    private final UUID unique;
    private final String name;

    /**
     * Returns the current username of the player.
     *
     * @return The username
     */
    @Override
    public String name() {
        return name;
    }

    /* ===================================================================
       |                     Begin private API                           |
       =================================================================== */

    @Internal
    @Contract(pure = true)
    public static @NotNull RowMapping<User> map() {
        return row -> new PaperPlayer(row.getUuidFromString("uuid"), row.getString("name"));
    }

    @Internal
    public static @NotNull PaperPlayer of(@NotNull UUID unique, @NotNull String name) {
        return new PaperPlayer(unique, name);
    }
}
