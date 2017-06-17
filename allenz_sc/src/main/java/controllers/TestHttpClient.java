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
    static String [] user_agent =   {
                     "Mozilla/5.0 (iPhone; CPU iPhone OS 5_0 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9A5313e Safari/7534.48.3", //IE
                     "Mozilla/5.0 (iPhone; CPU iPhone OS 5_0 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9A5313e Safari/7534.48.3",
                     "Mozilla/5.0 (iPhone; CPU iPhone OS 5_0 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9A5313e Safari/7534.48.3",
                     "Mozilla/5.0 (iPhone; CPU iPhone OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A403 Safari/8536.25"
                     ,"Mozilla/5.0 (iPhone; U; CPU iPhone OS 5_0 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Mobile/9A334 Safari/7534.48.3"
                     ,"Mozilla/5.0 (iPhone; CPU iPhone OS 5_0_1 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Mobile/9A405 Safari/7534.48.3"
                     ,"Mozilla/5.0 (iPhone; CPU iPhone OS 5_1_1 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9B206 Safari/7534.48."
                     ,"Mozilla/5.0 (iPhone; CPU iPhone OS 8_0 like Mac OS X) AppleWebKit/600.1.3 (KHTML, like Gecko) Version/8.0 Mobile/12A4345d Safari/600.1.4"
                     ,"Mozilla/5.0 (iPhone; CPU iPhone OS 6_0 like Mac OS X) AppleWebKit/600.1.3 (KHTML, like Gecko) Version/8.0 Mobile/12A4345d Safari/600.1.4"
                     ,"Mozilla/5.0 (iPhone; CPU iPhone OS 6_1 like Mac OS X) AppleWebKit/600.1.3 (KHTML, like Gecko) Version/8.0 Mobile/12A4345d Safari/600.1.4"
                     ,"Mozilla/5.0 (iPhone; CPU iPhone OS 7_0 like Mac OS X; en-us) AppleWebKit/537.51.1 (KHTML, like Gecko) Version/7.0 Mobile/11A465 Safari/9537.53"
                     ,"Mozilla/5.0 (iPad; CPU OS 7_0 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) CriOS/30.0.1599.12 Mobile/11A465 Safari/8536.25"
                     ,"Mozilla/5.0 (iPad; CPU OS 7_0 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) Version/7.0 Mobile/11A465 Safari/9537.53"
                     ,"Mozilla/5.0 (iPhone; CPU iPhone OS 6_1_4 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10B350 Safari/8536.25"
                     ,"Mozilla/5.0 (iPad; CPU OS 5_1_1 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9B206 Safari/7534.48.3"
                     ,"Mozilla/5.0 (iPhone; CPU iPhone OS 6_1_3 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10B329 Safari/8536.25"

                  };

    public static void main(String[] args) throws IOException {

        RoseAppContext rose = new RoseAppContext();

        String url = args[0];
        String sleep = args[1];
        int flag = Integer.valueOf(args[2]);

        IpDAO ipDao = rose.getBean(IpDAO.class);
        UaDAO uaDao = rose.getBean(UaDAO.class);
        List <Ua> uals = uaDao.getUa();
        int count = ipDao.getCount();
        int t = count / flag ;
        List<Ip> ipls = ipDao.getIp(new Random().nextInt(t)*flag, flag);

        int s = Integer.parseInt(sleep);
        for (int i = 0; i < flag; i++) {
            for (int j = 0 ; j <uals.size() ;j ++) {

                String ua = uals.get(j).getUa();
                try {
                    HttpClient client = new HttpClient();
                    HttpMethod method = new GetMethod(url);

                    // 这里设置字符编码，避免乱码
                    method.setRequestHeader("Content-Type", "text/html;charset=utf-8");
                    String randomAgent = ua;
                    method.setRequestHeader("User-Agent", randomAgent);
                    String randomip = ipls.get(i).getIp();
                    method.setRequestHeader("X-Forwarded-For", randomip);
                    client.executeMethod(method);

                    // 打印服务器返回的状态
                    System.out.println(method.getStatusLine());
                    method.releaseConnection();
                    Thread.sleep(new Random().nextInt(s));
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        }
    }

}