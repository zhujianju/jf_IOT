package com.jf.jf_iot.common.utill;

import com.jf.jf_iot.common.entity.SelectEntityIdInteger;
import com.jf.jf_iot.common.entity.SelectEntityIdString;
import org.springframework.stereotype.Component;

/**
 * 类型转换工具类
 */
@Component
public class SelectUtil {
    /**
     * id为数值形的转换
     * @param id
     * @param text
     * @return
     */
    public SelectEntityIdInteger exchangToSelect(Long id, String text){
        SelectEntityIdInteger selectEntity=new SelectEntityIdInteger();
        selectEntity.setId(id);
        selectEntity.setText(text);
        return selectEntity;
    }

    /**
     * id为字符串类的转换
     * @param id
     * @param text
     * @return
     */
    public SelectEntityIdString exchangToSelect(String id, String text){
        SelectEntityIdString selectEntity=new SelectEntityIdString();
        selectEntity.setId(id);
        selectEntity.setText(text);
        return selectEntity;
    }

}
