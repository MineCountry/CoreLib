package eu.minecountry.core.common.database;

import de.chojo.sadu.datasource.DataSourceCreator;
import de.chojo.sadu.mapper.RowMapperRegistry;
import de.chojo.sadu.mariadb.databases.MariaDb;
import de.chojo.sadu.mariadb.mapper.MariaDbMapper;
import de.chojo.sadu.queries.api.query.Query;
import de.chojo.sadu.queries.configuration.QueryConfiguration;
import eu.minecountry.core.common.logger.DefaultLogger;
import eu.minecountry.core.common.logger.builder.LoggerBuilder;
import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.NotNull;
import org.mariadb.jdbc.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.nio.file.Path;

/**
 * Represents a utility class used to initialize database interactions. Has to be used when interacting with a database.
 */
public final class DatabaseInitializer {

    private static final Logger log = LoggerFactory.getLogger(DatabaseInitializer.class);
    @Internal private QueryConfiguration configuration;
    @Internal private Credentials credentials;

    private final DefaultLogger logger = LoggerBuilder.of(DefaultLogger::new)
            .with(DefaultLogger::useFile, true)
            .with(DefaultLogger::applyPath, Path.of(System.getProperty("user.dir") + "/logs"))
            .build();

    /**
     * Initializes the {@link QueryConfiguration} and sets this config to a static reference of a {@link Query}. Tries to log into the database.
     * The created datasource has a connection-pool-size of 3 and keeps at minimum one connection open.
     *
     * @param credentials The credentials to login into the database
     */
    public void initialize(@NotNull Credentials credentials) {
        QueryConfiguration configuration = QueryConfiguration.builder(createDataSource(credentials))
                .setExceptionHandler(err -> logger.error("An error occurred during a database request: %s", err.getMessage()))
                .setRowMapperRegistry(new RowMapperRegistry().register(MariaDbMapper.getDefaultMapper()))
                .build();

        if (configuration.dataSource() == null) {
            logger.error("Could not initialize database due to missing data source");
            return;
        }

        QueryConfiguration.setDefault(configuration);
        this.configuration = configuration;

        logger.info("Connected successfully to database");
    }

    private DataSource createDataSource(@NotNull Credentials credentials) {
        this.credentials = credentials;

        return DataSourceCreator.create(MariaDb.get())
                .configure(config -> config
                        .driverClass(Driver.class)
                        .host(credentials.host())
                        .port(credentials.port())
                        .database(credentials.database())
                        .user(credentials.username())
                        .password(credentials.password()))
                .create()
                .withMaximumPoolSize(3)
                .withMinimumIdle(1)
                .build();
    }

    /* ===================================================================
       |                     Begin private API                           |
       =================================================================== */

    @Internal
    public QueryConfiguration configuration() {
        return configuration;
    }

    @Internal
    public Credentials credentials() {
        return credentials;
    }

}
