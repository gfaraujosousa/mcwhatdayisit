package com.mcwhatdayisit.neoforge.client;

import com.mcwhatdayisit.common.ModConstants;
import com.mcwhatdayisit.neoforge.client.render.NeoForgeHudRenderer;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

public final class NeoForgeHudLayerRegistrar {
    private static final List<String> GUI_LAYER_INTERFACES = List.of(
            "net.neoforged.neoforge.client.gui.GuiLayer",
            "net.minecraft.client.gui.LayeredDraw$Layer"
    );

    private final NeoForgeHudRenderer renderer;
    private final NeoForgeResourceIdentifierFactory resourceIdentifierFactory;
    private final Class<?> guiLayerInterface;
    private final Object hudLayerId;

    public NeoForgeHudLayerRegistrar(NeoForgeHudRenderer renderer) {
        this(renderer, new NeoForgeResourceIdentifierFactory());
    }

    NeoForgeHudLayerRegistrar(NeoForgeHudRenderer renderer, NeoForgeResourceIdentifierFactory resourceIdentifierFactory) {
        this.renderer = renderer;
        this.resourceIdentifierFactory = resourceIdentifierFactory;
        this.guiLayerInterface = locateGuiLayerInterface();
        this.hudLayerId = resourceIdentifierFactory.create(ModConstants.MOD_ID, "hud");
    }

    public void registerHudLayer(RegisterGuiLayersEvent event) {
        try {
            Method registerBelow = RegisterGuiLayersEvent.class.getMethod(
                    "registerBelow",
                    resourceIdentifierFactory.resourceIdentifierClass(),
                    resourceIdentifierFactory.resourceIdentifierClass(),
                    guiLayerInterface
            );
            registerBelow.invoke(event, VanillaGuiLayers.CHAT, hudLayerId, createGuiLayer());
        } catch (ReflectiveOperationException exception) {
            throw new IllegalStateException("Unable to register What days is it? HUD layer", exception);
        }
    }

    private Object createGuiLayer() {
        InvocationHandler invocationHandler = (proxy, method, arguments) -> {
            if (method.getDeclaringClass() == Object.class) {
                return method.invoke(this, arguments);
            }

            renderer.render((GuiGraphics) arguments[0], (DeltaTracker) arguments[1]);
            return null;
        };

        return Proxy.newProxyInstance(guiLayerInterface.getClassLoader(), new Class<?>[]{guiLayerInterface}, invocationHandler);
    }

    private Class<?> locateGuiLayerInterface() {
        for (String className : GUI_LAYER_INTERFACES) {
            try {
                return Class.forName(className);
            } catch (ClassNotFoundException ignored) {
            }
        }

        throw new IllegalStateException("No NeoForge GUI layer interface is available");
    }
}
