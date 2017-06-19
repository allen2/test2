package dao;

import bean.Ip;
import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

import java.util.List;


@DAO(catalog = "ABC")
public interface IpDAO {
    static final String TABLE_NAME= "ip";
    static final String FIELDS = "id, idfa,mac,ip,source" ;
    static final String INSERT_FIELDS = " idfa,mac,ip,source" ;

	@SQL("select ip from " + TABLE_NAME + "   limit :1,:2")
	public List<String> getIp(int start, int offset);


    @SQL("insert into  " + TABLE_NAME + "(" + INSERT_FIELDS + ") values(:1.idfa," +
            ":1.mac,:1.ip,:1.source)  ")
    public int  insertUpdate(Ip o);

    @SQL("select count(id) from " + TABLE_NAME )
    public int  getCount();
}
