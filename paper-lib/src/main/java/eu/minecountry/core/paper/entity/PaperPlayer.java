package eu.minecountry.core.paper.entity;

import de.chojo.sadu.mapper.rowmapper.RowMapping;
import eu.minecountry.core.api.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

/**
 * Represents a concrete implementation of an {@link User} for the paper plattform.
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PaperPlayer implements User {

    private final UUID unique;

    /**
     * Returns an {@link Optional} containing the bukkit representation of a player if currently online,
     * otherwise an empty Optional.
     *
     * @return The optional
     */
    public @NonNull Optional<Player> asBukkitPlayer() {
        return Optional.ofNullable(Bukkit.getPlayer(unique));
    }

    /* ===================================================================
       |                     Begin private API                           |
       =================================================================== */

    @Internal
    @Contract(pure = true)
    public static @NotNull RowMapping<User> map() {
        return row -> new PaperPlayer(row.getUuidFromString("uuid"));
    }

    @Internal
    public static @NotNull PaperPlayer of(@NotNull UUID unique) {
        return new PaperPlayer(unique);
    }
}
