package bean;

/**
 * Created with IntelliJ IDEA.
 * User: yunming.zhu
 * Date: 17-6-12
 * Time: 下午7:08
 * To change this template use File | Settings | File Templates.
 */
public class Ua {
    private long   id  ;
    private String  idfa;
    private String  mac  ;

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    private String  ua;
    private String  source  ;


    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

}
