package eu.minecountry.core.api;

import eu.minecountry.core.api.database.repository.UserRepository;
import org.jetbrains.annotations.NotNull;

public interface CoreLib {

    @NotNull
    UserRepository userRepository();

}
