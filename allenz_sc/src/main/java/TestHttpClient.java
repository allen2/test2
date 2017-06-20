import bean.Idfa;
import dao.IdfaDAO;
import dao.IpDAO;
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
    Log log = LogFactory.getLog( TestHttpClient.class );
    static String user_agent[] =
            {"Mozilla/5.0 (iPod; U; CPU iPhone OS 4_3_2 like Mac OS X; zh-cn) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8H7 Safari/6533.18.5",
                    "Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_3_2 like Mac OS X; zh-cn) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8H7 Safari/6533.18.5",
                    "Mozilla/5.0 (iPhone; CPU iPhone OS 5_0 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9A5313e Safari/7534.48.3", //IE
                    "Mozilla/5.0 (iPhone; CPU iPhone OS 5_0 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9A5313e Safari/7534.48.3",
                    "Mozilla/5.0 (iPhone; CPU iPhone OS 5_0 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9A5313e Safari/7534.48.3",

            };

    static String ip[] ={"100.37.205.27",
                          "100.80.12.22",
                          "100.80.16.35"

    } ;

public static void main( String[] args ) throws IOException {

        RoseAppContext rose = new RoseAppContext();

        IdfaDAO idfaDao  =  rose.getBean(IdfaDAO.class);
        IpDAO ipDao  =  rose.getBean(IpDAO.class);
        List<Idfa>  idfals = idfaDao.getIdfa(1,100);
        //System.out.println(idfals);
        //List<Ip> ipls  = ipDao.getIp(1,100);


        HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod( "https://lnk0.com/ZNhk0s" );

        // 这里设置字符编码，避免乱码
        method.setRequestHeader( "Content-Type", "text/html;charset=utf-8" );
        String randomAgent =user_agent[new Random().nextInt(user_agent.length-1)];
        method.setRequestHeader("User-Agent", randomAgent);
        String randomip =ip[new Random().nextInt(ip.length-1)];
        //System.out.println(randomip) ;
        method.setRequestHeader("X-Forwarded-For",randomip);
        client.executeMethod( method );
        // 打印服务器返回的状态
        System.out.println( method.getStatusLine() );

       // 获取返回的html页面
        byte[] body = method.getResponseBody();
        // 打印返回的信息
        System.out.println( new String( body, "utf-8" ));
        // 释放连接
        method.releaseConnection();
        }

}