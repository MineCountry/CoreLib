package eu.minecountry.core.paper;

import eu.minecountry.core.api.CoreLib;
import eu.minecountry.core.paper.database.repository.PaperPlayerRepository;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class PaperCoreLib extends JavaPlugin implements CoreLib {

    @Getter private static PaperCoreLib instance;

    private PaperPlayerRepository playerRepository;

    @Override
    public void onLoad() {
        instance = this;
        playerRepository = new PaperPlayerRepository();
    }

    @Override
    public @NotNull PaperPlayerRepository userRepository() {
        return playerRepository;
    }
}
