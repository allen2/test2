package controllers;

import bean.Idfa;
import bean.Ip;
import bean.Ua;
import dao.IdfaDAO;
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

//HttpClient使用get的方式
public class TestHttpClient {
    Log log = LogFactory.getLog(TestHttpClient.class);



    public static void main(String[] args) throws IOException {

        RoseAppContext rose = new RoseAppContext();

        String url = args[0];
        String sleep = args[1];
        int flag = Integer.valueOf(args[2]);

        IpDAO ipDao = rose.getBean(IpDAO.class);
        UaDAO uaDao = rose.getBean(UaDAO.class);
        List <String> uals = uaDao.getUa();
        int count = ipDao.getCount();
        int t = count / flag ;
        List<String> ipls = ipDao.getIp(new Random().nextInt(t)*flag, flag);
        HttpClient client = new HttpClient();
        int s = Integer.parseInt(sleep);
        for (int i = 0; i < flag; i++) {
            for (int j = 0 ; j <uals.size() ;j ++) {

                String ua = uals.get(j);
                try {

                    HttpMethod method = new GetMethod(url);
                    // 这里设置字符编码，避免乱码
                    method.setRequestHeader("Content-Type", "text/html;charset=utf-8");
                    String randomAgent = ua;
                    method.setRequestHeader("User-Agent", randomAgent);
                    String randomip = ipls.get(i);
                    method.setRequestHeader("X-Forwarded-For", randomip);
                    client.executeMethod(method);
                    // 打印服务器返回的状态
                    System.out.println(method.getStatusLine());
                    method.releaseConnection();
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        }
    }

}