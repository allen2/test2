package controllers;

import net.paoding.rose.scanning.context.RoseAppContext;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//HttpClient使用get的方式
public class T {
    Log log = LogFactory.getLog(T.class);



    public static void main(String[] args) throws IOException {

        RoseAppContext rose = new RoseAppContext();

        /*ThreadPoolExecutor tPool = new ThreadPoolExecutor(20, 10, 1000, TimeUnit.MINUTES,
                new ArrayBlockingQueue<Runnable>(50));


        for (int i = 0; i < 10; i++) {
            for (int j = 0; j <10; j++) {


                Runnable r = new Task("", "" ,"");
                tPool.execute(r);
            }
        }
        */
    }



}