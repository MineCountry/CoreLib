package eu.minecountry.core.common.service;

import com.google.common.collect.Iterables;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collection;

public interface ServiceRegistry {

    @NotNull
    <T, E extends T> ServiceRegistry registerProvider(@NotNull Class<T> service, @NotNull String name, @NotNull E provider);

    @NotNull
    <T, E extends T> ServiceRegistry unregisterProvider(@NotNull Class<T> service, @NotNull Class<E> provider);

    @NotNull
    <T, E extends T> ServiceRegistry unregisterProvider(@NotNull Class<T> service, @NotNull E provider);

    @NotNull
    <T> ServiceRegistry unregisterProvider(@NotNull Class<T> service, @NotNull String name);

    @NotNull
    <T> ServiceRegistry unregisterProvider(@NotNull Class<T> service);

    @NotNull
    ServiceRegistry unregisterAll();

    @NotNull
    ServiceRegistry unregisterAll(@NotNull ClassLoader classLoader);

    <T> boolean hasProvider(@NotNull Class<T> service, @NotNull String name);

    @NotNull
    Collection<Class<?>> providedServices();

    @UnknownNullability
    <T> T provider(@NotNull Class<T> service, @NotNull String name);

    @UnmodifiableView
    @NotNull
    <T> Collection<T> providers(@NotNull Class<T> service);

    default @UnknownNullability <T> T firstProvider(@NotNull Class<T> service) {
        return Iterables.getFirst(this.providers(service), null);
    }

}
