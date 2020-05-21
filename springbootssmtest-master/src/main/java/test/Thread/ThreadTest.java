package test.Thread;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @ClassName ThreadTest
 * @Description TODO
 * @Author mcx
 * @Date 2020/5/20 11:14
 * @Version 1.0
 */
@Data
public class ThreadTest implements Runnable{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadTest.class);
    String name;
    int t =10;
    @Override
    public synchronized void run() {

        for(int i =0;i<500;i++){
            if(t>0) {
                LOGGER.info(name+ "----" + (--t));
            }
        }
    }

}