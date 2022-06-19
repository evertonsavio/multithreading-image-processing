package dev.evertonsavio.app.reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Printer<T> implements Print<T>
{
    private final T obj;

    public Printer(T obj){
        this.obj = obj;
        Class<?> aClass = obj.getClass();
        System.out.println(aClass.getCanonicalName());
        Method[] methods = aClass.getMethods();
        for (Method method: methods) System.out.print(method.getName() + " ");
        System.out.println();
    }

    @Override
    public void print(String[] methodName)
    {
        try
        {
            for (String method : methodName){
                Method declaredMethod = this.obj.getClass().getDeclaredMethod(method);
                Object invoke = declaredMethod.invoke(this.obj);
                System.out.print(invoke.toString() + " ");
            }
            System.out.println();
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e)
        {
            e.printStackTrace();
        }
    }
}
