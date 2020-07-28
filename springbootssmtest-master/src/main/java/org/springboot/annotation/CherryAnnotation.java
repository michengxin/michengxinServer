package org.springboot.annotation;

/**
 * @ClassName CherryAnnotation
 * @Description TODO
 * @Author mcx
 * @Date 2020/7/28 8:58
 * @Version 1.0
 */

import java.lang.annotation.*;


@Target({ElementType.TYPE,ElementType.METHOD}) //定义这个注解用在哪些地方. @CherryAnnotation被限定只能使用在类、接口或方法上面
// 类，接口（包括注解类型）或枚举的声明(TYPE) 属性的声明 (FIELD) 方法的声明 (METHOD)
// 方法形式参数声明(PARAMETER)  构造方法的声明(CONSTRUCTOR) 局部变量声明(LOCAL_VARIABLE)
//注解类型声明(ANNOTATION_TYPE) 包的声明(PACKAGE)
@Retention(RetentionPolicy.RUNTIME)//定义注解在什么时候使用
//如果一个注解被定义为RetentionPolicy.SOURCE，则它将被限定在Java源文件中，那么这个注解即不会参与编译也不会在运行期起任何作用，这个注解就和一个注释是一样的效果，只能被阅读Java文件的人看到；
//如果一个注解被定义为RetentionPolicy.CLASS，则它将被编译到Class文件中，那么编译器可以在编译时根据注解做一些处理动作，但是运行时JVM（Java虚拟机）会忽略它，我们在运行期也不能读取到；
//如果一个注解被定义为RetentionPolicy.RUNTIME，那么这个注解可以在运行期的加载阶段被加载到Class对象中。那么在程序运行阶段，我们可以通过反射得到这个注解，并通过判断是否有这个注解或这个注解中属性的值，从而执行不同的程序代码段。我们实际开发中的自定义注解几乎都是使用的RetentionPolicy.RUNTIME；
//在默认的情况下，自定义注解是使用的RetentionPolicy.CLASS。
@Documented//注解，是被用来指定自定义注解是否能随着被定义的java文件生成到JavaDoc文档当中。
@Inherited//指定某个自定义注解如果写在了父类的声明部分，那么子类的声明部分也能自动拥有该注解
public @interface CherryAnnotation {
    public String name();//注解类型元素
}
