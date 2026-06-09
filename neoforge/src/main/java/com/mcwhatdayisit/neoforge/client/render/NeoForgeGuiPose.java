package com.mcwhatdayisit.neoforge.client.render;

import java.lang.reflect.Method;

final class NeoForgeGuiPose {
    private final Object pose;

    NeoForgeGuiPose(Object pose) {
        this.pose = pose;
    }

    void push() {
        invokeFirst("pushMatrix", "pushPose");
    }

    void pop() {
        invokeFirst("popMatrix", "popPose");
    }

    void translate(int x, int y) {
        if (!invoke("translate", new Class<?>[]{float.class, float.class}, (float) x, (float) y)) {
            invokeRequired("translate", new Class<?>[]{double.class, double.class, double.class}, (double) x, (double) y, 0.0D);
        }
    }

    void scale(double scale) {
        float scaleFactor = (float) scale;

        if (!invoke("scale", new Class<?>[]{float.class, float.class}, scaleFactor, scaleFactor)) {
            invokeRequired("scale", new Class<?>[]{float.class, float.class, float.class}, scaleFactor, scaleFactor, 1.0F);
        }
    }

    private void invokeFirst(String firstMethodName, String secondMethodName) {
        if (!invoke(firstMethodName, new Class<?>[0])) {
            invokeRequired(secondMethodName, new Class<?>[0]);
        }
    }

    private void invokeRequired(String methodName, Class<?>[] parameterTypes, Object... parameters) {
        if (!invoke(methodName, parameterTypes, parameters)) {
            throw new IllegalStateException("Unable to transform Minecraft GUI pose");
        }
    }

    private boolean invoke(String methodName, Class<?>[] parameterTypes, Object... parameters) {
        try {
            Method method = pose.getClass().getMethod(methodName, parameterTypes);
            method.invoke(pose, parameters);
            return true;
        } catch (ReflectiveOperationException exception) {
            return false;
        }
    }
}
