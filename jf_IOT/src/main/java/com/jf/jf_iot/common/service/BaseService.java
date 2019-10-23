package com.jf.jf_iot.common.service;

import com.jf.jf_iot.common.entity.PageResult;

import java.util.List;

/**
 *定义一些通用的mapper方法
 */
public interface BaseService <T>{

    /**
     * 查找所有的列表
     * @return
     */
    public List<T> findAll();

    /**
     * 带条件的分页查询
     * @param pageNum
     * @param pageSize
     * @param t 名字
     * @return
     */
    public PageResult findPage(int pageNum, int pageSize, T t);

    /**
     * 根据id查询单个站点
     * @param id
     * @return 返回站点对象
     */
    public T findOne(Integer id);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    public int deleteById(Integer id);

    /**
     * 修改,
     * @param t
     * @return
     */
    public int updateByid(T t);

    /**
     * 新增
     * @param t
     * @return
     */
    public int insert(T t);

}
