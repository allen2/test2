package controllers;

import dao.IdfaDAO;
import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

/**
 * 地推人员工具
 *
 * @author zhaoxiufei
 */

@Path("/")
public class ToolsController {

    @Autowired
    private IdfaDAO idfaDAO;
    /**
     * 首次上传扫码文件
     *
     * @param inv
     * @return
     */
    @Get("load")
    @Post("load")
    public String uploadFile(Invocation inv) {

       List idfas = idfaDAO.getIdfa(0,3)   ;

       return "@" + idfas.toString();
    }

    @Get("cb")
    @Post("cb")
    public String cb(Invocation inv) {

        String  ip = inv.getParameter("ip") ;
        String  ua = inv.getParameter("ua") ;
        System.out.println( ip + " " +  ua );
        return "@" + ip + " " +  ua  ;
    }



}
