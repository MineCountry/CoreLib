package eu.minecountry.core.velocity.entity;

import com.google.inject.Inject;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import eu.minecountry.core.api.CoreLib;
import eu.minecountry.core.common.logger.DefaultLogger;
import eu.minecountry.core.common.logger.builder.LoggerBuilder;
import eu.minecountry.core.velocity.repository.VelocityPlayerRepository;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

@Plugin(id = "velocity-lib", version = "1.0.0-SNAPSHOT", authors = {"Merry", "TheMiCraft"})
public class VelocityCoreLib implements CoreLib {

    @Getter private static VelocityCoreLib instance;

    private final VelocityPlayerRepository playerRepository;

    @Inject
    public VelocityCoreLib(@NotNull ProxyServer proxyServer, @DataDirectory @NotNull Path dataDirectory) {
        DefaultLogger logger = LoggerBuilder.of(DefaultLogger::new)
                .with(DefaultLogger::useFile, true)
                .with(DefaultLogger::applyPath, Path.of(dataDirectory.toUri().getPath() + "/logs"))
                .build();

        instance = this;
        playerRepository = new VelocityPlayerRepository();

        logger.info("CoreLib was initialized successfully");

    }

    @Override
    public @NotNull VelocityPlayerRepository userRepository() {
        return playerRepository;
    }
}
