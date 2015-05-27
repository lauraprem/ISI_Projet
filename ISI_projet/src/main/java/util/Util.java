package util;

import com.sun.istack.internal.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author Alexandre
 *         27/05/2015
 */
public class Util {
    private static Object invokeMethod(@NotNull Class methodClass, @NotNull Object instance, @NotNull String methodName, Object... params) {
        Class[] paramTypes = null;
        if (params != null) {
            paramTypes = new Class[params.length];
            for (int i = 0; i < params.length; i++) paramTypes[i] = params[i].getClass();
        }
        Method method = null;
        try {
            method = (params != null ? methodClass.getDeclaredMethod(methodName, paramTypes) : methodClass.getDeclaredMethod(methodName));
            method.setAccessible(true);
            return (params != null ? method.invoke(instance, params) : method.invoke(instance));
        } catch (NoSuchMethodException e) {
            if(!Object.class.equals(methodClass)) return invokeMethod(methodClass.getSuperclass(), instance, methodName, params);
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object invokeMethod(@NotNull Object instance, @NotNull String methodName, Object... params) {
        return invokeMethod(instance.getClass(), instance, methodName, params);
    }
}
