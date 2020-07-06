package org.springboot.utils;

import java.util.Date;

/**
 * @ClassName DateUtil
 * @Description TODO
 * @Author mcx
 * @Date 2020/5/22 11:27
 * @Version 1.0
 */
public  class DateUtil {
    public static boolean getDateBeforeOrAfterMinute(Date date,Date dateToken){
        Long millisecond = date.getTime();
        Long millisecondToken = dateToken.getTime();
        if(millisecondToken -millisecond>0 && millisecondToken -millisecond<3 * 60 * 1000){
            return true;
        }else{
            return false;
        }
    }

    public static String a(){
        try{
            System.out.println("try");
            return "tryreturn";
        }catch(Exception e){

        }finally {
            System.out.println("finally");
            return "finallyreturn";
        }
    }


}