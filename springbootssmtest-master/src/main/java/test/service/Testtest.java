package test.service;


import com.alibaba.excel.metadata.Sheet;
import org.springboot.utils.DateUtil;
import org.springboot.utils.ExcelUtil;
import org.testng.annotations.Test;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.*;
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
    //日期工具类测试
    @Test
    public void test2() {
        Date date = new Date();
        long a = date.getTime();
        a+=2*60*1000;
        Date dateToken = new Date(a);
        System.out.println(DateUtil.getDateBeforeOrAfterMinute(date,dateToken));
        Date date1 = new Date();
        long b = date1.getTime();
        b+=3*60*1001;
        Date dateToken1 = new Date(b);
        System.out.println(DateUtil.getDateBeforeOrAfterMinute(date1,dateToken1));
    }
    //finally try return
    @Test
    public void test3() {
        System.out.println(DateUtil.a());
    }

    @Test
    public void test4() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
//list.add("a"); // 这样直接添加肯定是不允许的
//下面通过java的反射，绕过泛型 来给添加字符串
        Method add = null;
        try {
            add = list.getClass().getMethod("add", Object.class);
            add.invoke(list,"a");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(list); //[1, a] 输出没有没问题
    }
    @Test
    public void test5(){
        Map<String, String> map = new HashMap<>();
        String key = "key";
        Integer val = Integer.valueOf(1);
        Method m = null;
        try {
            m = HashMap.class.getDeclaredMethod("put", new Class[]{Object.class, Object.class});
            m.invoke(map, key, val);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(map); //{key=1}
        //但是下面的输出会报错
        System.out.println(map.get(key)); // java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String
    }
    @Test
    public void test6(){
        int [] nums1 = {1,2,3,0,0,0};
        int m = 3;
        int [] nums2 ={2,5,6};
        int n = 3;
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
        for(int i =0;i<nums1.length;i++){
            System.out.println(nums1[i]);
        }
    }
    @Test
    public void test7(){
        String a = "a";
        String b = "b";
        String c = new String("a");
        String d = new String("a");
        String e = new String("b");
        System.out.println(c.equals(d));
        System.out.println(c == d);
//        System.out.println(a.equals(c));
//        System.out.println(a==c);
//        System.out.println(b.equals(d));
//        System.out.println(b==d);
//        System.out.println(d.equals(e));
//        System.out.println(e==d);
    }
    @Test
    public void test8(){

    }
}