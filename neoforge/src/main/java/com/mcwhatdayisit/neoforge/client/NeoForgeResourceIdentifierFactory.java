package com.mcwhatdayisit.neoforge.client;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

public final class NeoForgeResourceIdentifierFactory {
    private static final List<String> RESOURCE_IDENTIFIER_CLASSES = List.of(
            "net.minecraft.resources.Identifier",
            "net.minecraft.resources.ResourceLocation"
    );

    private final Class<?> resourceIdentifierClass;

    public NeoForgeResourceIdentifierFactory() {
        this.resourceIdentifierClass = locateResourceIdentifierClass();
    }

    public Object create(String namespace, String path) {
        Object identifier = invokeFactoryMethod("fromNamespaceAndPath", namespace, path);

        if (identifier != null) {
            return identifier;
        }

        identifier = invokeFactoryMethod("tryBuild", namespace, path);

        if (identifier != null) {
            return identifier;
        }

        return invokeConstructor(namespace, path);
    }

    public Class<?> resourceIdentifierClass() {
        return resourceIdentifierClass;
    }

    private Class<?> locateResourceIdentifierClass() {
        for (String className : RESOURCE_IDENTIFIER_CLASSES) {
            try {
                return Class.forName(className);
            } catch (ClassNotFoundException ignored) {
            }
        }

        throw new IllegalStateException("No Minecraft resource identifier class is available");
    }

    private Object invokeFactoryMethod(String methodName, String namespace, String path) {
        try {
            Method method = resourceIdentifierClass.getMethod(methodName, String.class, String.class);

            if (!Modifier.isStatic(method.getModifiers())) {
                return null;
            }

            return method.invoke(null, namespace, path);
        } catch (ReflectiveOperationException ignored) {
            return null;
        }
    }

    private Object invokeConstructor(String namespace, String path) {
        try {
            Constructor<?> constructor = resourceIdentifierClass.getDeclaredConstructor(String.class, String.class);
            constructor.setAccessible(true);
            return constructor.newInstance(namespace, path);
        } catch (ReflectiveOperationException exception) {
            throw new IllegalStateException("Unable to create Minecraft resource identifier", exception);
        }
    }
}
