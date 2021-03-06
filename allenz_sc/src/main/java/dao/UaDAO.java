package dao;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

import java.util.List;


@DAO(catalog = "ABC")
public interface UaDAO {
    static final String TABLE_NAME= "ua";
    static final String FIELDS = "id, idfa,mac,ua,source" ;
    static final String INSERT_FIELDS = " idfa,mac,ua,source" ;

	@SQL("select ua from " + TABLE_NAME )
	public List<String> getUa();




    @SQL("select count(id) from " + TABLE_NAME )
    public int  getCount();
}
