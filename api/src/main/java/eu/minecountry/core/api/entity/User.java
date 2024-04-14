package eu.minecountry.core.api.entity;

import eu.cloudnetservice.driver.inject.InjectionLayer;
import eu.cloudnetservice.driver.registry.ServiceRegistry;
import eu.cloudnetservice.modules.bridge.player.CloudOfflinePlayer;
import eu.cloudnetservice.modules.bridge.player.CloudPlayer;
import eu.cloudnetservice.modules.bridge.player.PlayerManager;
import eu.minecountry.core.common.database.Entity;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

/**
 * Represents an abstract implementation of a player entity stored in a database.
 *
 * @since 1.0.0
 */
public interface User extends Entity<UUID> {

    /**
     * Returns the name of the {@link CloudPlayer}.
     *
     * @return The name
     */
    default @NotNull String name() {
        var cloudPlayer = cloudPlayer().orElseThrow();
        return cloudPlayer.name();
    }

    /**
     * Returns an {@link Optional} containing the {@link CloudPlayer} if currently online, otherwise an empty Optional.
     *
     * @return The Optional
     */
    default @NotNull Optional<CloudPlayer> cloudPlayer() {
        var playerManager = playerManager();
        return Optional.ofNullable(playerManager.onlinePlayer(unique()));
    }

    /**
     * Returns an {@link Optional} containing the {@link CloudPlayer} associated with the given
     * uuid or en empty Optional if the player was never previously online.
     *
     * @return The Optional
     */
    default @NotNull Optional<CloudOfflinePlayer> offlinePlayer() {
        var playerManager = playerManager();
        return Optional.ofNullable(playerManager.offlinePlayer(unique()));
    }

    private @NotNull PlayerManager playerManager() {
        try (var layer = InjectionLayer.boot()) {
            var registry = layer.instance(ServiceRegistry.class);
            return registry.firstProvider(PlayerManager.class);
        }
    }

}
