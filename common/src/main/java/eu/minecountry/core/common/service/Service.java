package eu.minecountry.core.common.service;

import jakarta.inject.Qualifier;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.*;

/**
 * This annotation enables the use of registered services from the service registry in an injection context.
 * <p>
 * Instead of doing something like:
 * <pre>
 * {@code
 *   @Inject
 *   public TestClass(ServiceRegistry registry) {
 *     var playerManager = registry.firstProvider(PlayerManager.class);
 *     playerManager.onlineCount();
 *   }
 * }
 * </pre>
 * Using this annotation you can do something like:
 * <pre>
 * {@code
 *   @Inject
 *   public TestClass(@Service PlayerManager playerManager) {
 *     playerManager.onlineCount();
 *   }
 * }
 * </pre>
 * <p>
 * Note: If a service or a service name is requested that the service registry does not know {@code null} is passed as
 * parameter value.
 *
 * @since 1.0
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD})
public @interface Service {

    /**
     * The name of the registered provider in the service registry. Leave empty if the name of the provider is not
     * important and any provider with a matching class is accepted.
     *
     * @return name of the registered provider in the service registry.
     */
    @NotNull String name() default "";

}
