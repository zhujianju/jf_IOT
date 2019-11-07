package com.jf.jf_iot.common.utill;

import com.jf.jf_iot.common.enums.ExceptionEnum;
import com.jf.jf_iot.common.exception.IOTException;
import com.jf.jf_iot.user.entity.User;

import javax.servlet.http.HttpSession;

/**
 * 通用的校验权限的的方法
 */
public class SecurityUtil {
    /**
     * 获得权限
     * @return
     */
    public static Integer getAutho(HttpSession session){
        User user = (User) session.getAttribute("user");
        Integer autho = user.getAutho();
        if(autho == null){
            throw new IOTException(ExceptionEnum.USER_NOT_AUTHO);
        }
        return autho;
    }

    /**
     * 执行部分方法需要管理员权限。这里检验权限是否为3，管理员
     */
    public static void isRoot(HttpSession session){
        if(SecurityUtil.getAutho(session)!=3 ){//判断权限是否够
            throw new IOTException(ExceptionEnum.USER_NOT_AUTHO);
        }
    }

}
