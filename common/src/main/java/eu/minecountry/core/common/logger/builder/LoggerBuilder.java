package eu.minecountry.core.common.logger.builder;

import eu.minecountry.core.common.Builder;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Represents a builder used for constructing loggers.
 * * A typical use-case for this builder can be visualized with this snippet: <br>
 * <pre>
 * {@code DefaultLogger logger = LoggerBuilder.of(DefaultLogger::new)
 *          .with(DefaultLogger::useConsole, true)
 *          .with(DefaultLogger::useFile, false)
 *          .build();}
 * </pre>
 *
 * @param <T> The type that is constructed within this builder
 * @implNote Theoretically can be used to construct any concrete implementation of a type. Nor recommend nor supported.
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class LoggerBuilder<T> implements Builder<T> {

    private final Supplier<T> typeSupplier;
    private final List<Consumer<T>> modifiers = new ArrayList<>();

    /**
     * Instantiates a new builder with the given type. <br>
     *
     * @param typeSupplier The type
     * @param <T>          Specified though concrete implementation
     * @return A new instance of the builder
     */
    @Contract("_ -> new")
    public static <T> @NotNull LoggerBuilder<T> of(Supplier<T> typeSupplier) {
        return new LoggerBuilder<>(typeSupplier);
    }

    /**
     * Applies modifications to the object.
     *
     * @param consumer The objects method that modifies the object
     * @param value    The parameter required by the method invocations
     * @param <U>      The type of parameter. Defined through method invocation
     * @return The instance of the builder
     */
    public <U> @NotNull LoggerBuilder<T> with(@NotNull BiConsumer<T, U> consumer, @NotNull U value) {
        Consumer<T> modifier = instance -> consumer.accept(instance, value);
        modifiers.add(modifier);
        return this;
    }

    /**
     * Constructs the object with the given modifications though this builder.
     *
     * @return The object
     */
    @Override
    public T build() {
        T logger = typeSupplier.get();
        modifiers.forEach(modifier -> modifier.accept(logger));
        modifiers.clear();
        return logger;
    }
}
