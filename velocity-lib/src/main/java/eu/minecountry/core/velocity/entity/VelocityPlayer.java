package eu.minecountry.core.velocity.entity;

import com.velocitypowered.api.proxy.Player;
import de.chojo.sadu.mapper.rowmapper.RowMapping;
import eu.minecountry.core.api.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class VelocityPlayer implements User {

    private final UUID unique;

    public Optional<Player> asProxyPlayer() {
        return Optional.empty();
    }

    /* ===================================================================
       |                     Begin private API                           |
       =================================================================== */

    @Internal
    @Contract(pure = true)
    public static @NotNull RowMapping<User> map() {
        return row -> new VelocityPlayer(row.getUuidFromString("uuid"));
    }

    @Internal
    public static @NotNull VelocityPlayer of(@NotNull UUID unique) {
        return new VelocityPlayer(unique);
    }

}
