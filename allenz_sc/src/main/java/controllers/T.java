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

        ThreadPoolExecutor tPool = new ThreadPoolExecutor(10, 30, 1000L, TimeUnit.MINUTES,
                new ArrayBlockingQueue<Runnable>(50));


        for (int i = 0; i < 10; i++) {
            for (int j = 0; j <10; j++) {


                Runnable r = new Task("", "" ,"");
                tPool.execute(r);
            }
        }

    }
    private static class  Task implements Runnable{
        String url = "";
        String randomAgent = "";
        String randomip = "";

        public Task(String url,String randomAgent,String randomip){
            this.url = url;
            this.randomAgent = randomAgent;
            this.randomip = randomip;
        }

        public void run() {
            HttpClient client = new HttpClient();
            HttpMethod method = new GetMethod(url);

            try {
                // 这里设置字符编码，避免乱码
                method.setRequestHeader("Content-Type", "text/html;charset=utf-8");
                method.setRequestHeader("User-Agent", randomAgent);

                method.setRequestHeader("X-Forwarded-For", randomip);
                client.executeMethod(method);
                // 打印服务器返回的状态
                System.out.println(method.getStatusLine());

            } catch (Exception e) {
                e.printStackTrace();

            }finally {
                method.releaseConnection();

            }

        }
    }


}