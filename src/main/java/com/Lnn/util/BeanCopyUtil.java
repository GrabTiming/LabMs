package com.Lnn.util;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtil {

    //声明为静态类
    private BeanCopyUtil(){

    }

    //单个元素转换
    public static <O,V> V copyBean(O source,Class<V> clazz)  {

        V result = null;
        try {
            result = clazz.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        BeanUtils.copyProperties(source,result);
        return result;
    }


    //List层面的元素类型转换
    public static <O,V> List<V> copyBeanList(List<O> list, Class<V> clazz)
    {
        return list.stream().map(
                o -> copyBean(o,clazz)
        ).collect(Collectors.toList());
    }


}
