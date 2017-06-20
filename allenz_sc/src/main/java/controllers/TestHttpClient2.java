package controllers;

import dao.IpDAO;
import dao.UaDAO;
import net.paoding.rose.scanning.context.RoseAppContext;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//HttpClient使用get的方式
public class TestHttpClient2 {
    Log log = LogFactory.getLog(TestHttpClient2.class);



    public static void main(String[] args) throws IOException {

        RoseAppContext rose = new RoseAppContext();

        String url = args[0];
        String sleep = args[1];
        int flag = Integer.valueOf(args[2]);

        IpDAO ipDao = rose.getBean(IpDAO.class);
        UaDAO uaDao = rose.getBean(UaDAO.class);
        List<String> uals = uaDao.getUa();
        int count = ipDao.getCount();
        int t = count / flag;
        List<String> ipls = ipDao.getIp(new Random().nextInt(t) * flag, flag);
        ThreadPoolExecutor tPool = new ThreadPoolExecutor(20, 10, 1000L, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(50),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println("reject....");

                    }
                });



        int s = Integer.parseInt(sleep);
        for (int i = 0; i < flag; i++) {
            for (int j = 0; j < uals.size(); j++) {

                String randomip = ipls.get(i);
                String randomAgent = uals.get(j);
                Runnable r = new Task(url, randomAgent ,randomip);
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