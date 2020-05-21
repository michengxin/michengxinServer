package test.service;

import org.testng.annotations.Test;
import test.Thread.ThreadTest;

import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName Testtest
 * @Description TODO
 * @Author mcx
 * @Date 2020/5/20 9:43
 * @Version 1.0
 */
public class Testtest {
    //生成流水号测试
    @Test
    public void test1(){
        DecimalFormat df = new DecimalFormat("00000");          //设置格式
        AtomicInteger z = new AtomicInteger(); //number线程安全方式增加或减少的对象
        for(int i=0; i<100001; i++){
         if(i==100000){
             i =0;
             z.set(i);
             continue;
         }
         z.set(i);
         System.out.println(df.format(z.get()));//df.format()转换number的格式
        }
    }
    @Test
    public  void test2() {
        ThreadTest mt = new ThreadTest();
        Thread t1 = new Thread(mt,"一号窗口");
        Thread t2 = new Thread(mt,"二号窗口");
        Thread t3 = new Thread(mt,"三号窗口");
        t1.start();
        t2.start();
        t3.start();
    }


}