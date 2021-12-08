package com.f_lin.future.java.basic.rawtype;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author F_lin fengjunlin@sobey.com
 * @version 1.0
 * @since 2021/11/29
 */
public abstract class TopRawClass<T, O> {
    protected Class<T> rawTypeT = null;
    protected Class<O> rawTypeO = null;


    @SuppressWarnings("unchecked")
    public static <X> Class<X> getRawType(Class<?> tClass, Integer order) {

        Type superclass = tClass.getGenericSuperclass();
        if (superclass instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) superclass).getActualTypeArguments();
            if (order > actualTypeArguments.length - 1) {
                //这里超长了
                throw new IllegalArgumentException("order must lte raw type's length!");
            }
            Type typeArgument = actualTypeArguments[order];
            if (typeArgument instanceof Class) {
                return (Class<X>) typeArgument;
            }
            return (Class<X>) ((ParameterizedType) typeArgument).getRawType();
        }
        return getRawType((Class<?>) superclass, order);
    }
}
