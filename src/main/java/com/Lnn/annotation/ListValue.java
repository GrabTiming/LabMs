package com.Lnn.annotation;


import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashSet;
import java.util.Set;

/**
 * 字段值限制为指定的几个属性
 */
@Constraint(validatedBy = {ListValue.ListValueConstraintValidator.class,ListValue.ListStringValueConstraintValidator.class})
@Target({ElementType.FIELD}) //应用于字段属性
@Retention(RetentionPolicy.RUNTIME)
public @interface ListValue {

    // 默认的提示内容
    String message() default "必须提交指定的值";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    int[] values() default { }; //数值数组，提交的值只能是数组里面

    String[] strValues() default { }; //字符串数组，提交的值只能是数组里面

    //限制值的类型为Integer
    class ListValueConstraintValidator implements ConstraintValidator<ListValue, Integer> {

        private Set<Integer> set = new HashSet<>();

        /**
         * 初始化方法
         */
        @Override
        public void initialize(ListValue constraintAnnotation) {
            int[] values = constraintAnnotation.values();
            for (int val : values) {
                set.add(val);
            }
        }

        /**
         * 判断是否校验成功
         */
        @Override
        public boolean isValid(Integer value, ConstraintValidatorContext context) {
            return set.contains(value);
        }
    }


    //限制值类型为String
    class ListStringValueConstraintValidator implements ConstraintValidator<ListValue, String> {

        private Set<String> set = new HashSet<>();

        /**
         * 初始化方法
         */
        @Override
        public void initialize(ListValue constraintAnnotation) {
            String[] values = constraintAnnotation.strValues();
            for (String val : values) {
                set.add(val);
            }
        }

        /**
         * 判断是否校验成功
         */
        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            return set.contains(value);
        }
    }

}
