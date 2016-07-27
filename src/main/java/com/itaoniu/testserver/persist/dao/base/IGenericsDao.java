package com.itaoniu.testserver.persist.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IGenericsDao {
	/**
	 * 保存一个对象
	 * @param obj
	 * @return 对象id
	 */
	public Serializable save(Object obj);
	
	/**
	 * 删除一个对象
	 * @param obj
	 */
	public void delete(Object obj);
	
	
	/**
	 * 更新一个对象
	 * @param obj
	 */
	public void update(Object obj);
	
//	/**
//	 * 添加或更新一个对象（建议业务需要时才使用该方法）
//	 * @param obj
//	 */
//	public void saveOrUpdate(Object obj);
	
	/**
	 * 根据条件删除对象
	 * @param clazz 对象类.class
	 * @param params 参数<条件,条件值>
	 */
	public <T> void delete(Class<T> clazz,Map<String, Object> params);
	
	/**
	 * 根据条件更新一个对象对应的值
	 * @param clazz 对象类.class
	 * @param params 更新的参数和值
	 * @param condition 更新的条件和值
	 */
	public <T> void update(Class<T> clazz,Map<String, Object> values,Map<String, Object> params);
	
	
	/**
	 * 统计数目
	 * 
	 * @param hql
	 *            HQL语句(select count(*) from T)
	 * @return long
	 */
	public Long count(String hql);

	/**
	 * 统计数目
	 * 
	 * @param hql
	 *            HQL语句(select count(*) from T where xx = :xx)
	 * @param params
	 *            参数
	 * @return long
	 */
	public Long count(String hql, Map<String, Object> params);
	

	/**
	 * 统计数目	
	 * @param clazz  对象类.class
	 * @return 
	 */
	public Long count(Class<?> clazz);
	/**
	 * 统计数目
	 * @param clazz 对象类.class
	 * @param params 参数
	 * @return
	 */
	public Long count(Class<?> clazz,Map<String, Object> params);
	
	/**
	 * 获取一个对象
	 * @param Clazz 对象类.class
	 * @param id 对象id
	 * @return 对象
	 */
	public <T> T get(Class<T> clazz,Serializable id);
	
	/**
	 * 获取一个对象
	 * @param Clazz 对象类.class
	 * @param params 参数<条件,条件值>
	 * @return
	 */
	public <T> T get(Class<T> clazz,Map<String, Object> params);
	
	public <T> T get(Class<T> clazz, Map<String, Object> params,String orderBy,String orderByType);
	
	/**
	 * 获取对象列表
	 * @param clazz 对象类.class
	 * @return 对象列表
	 */
	public <T> List<T> find(Class<T> clazz);
	
	/**
	 * 根据参数获取对象类别
	 * @param clazz 对象类.class
	 * @param params 参数<条件,条件值>
	 * @return
	 */
	public <T> List<T> find(Class<T> clazz,Map<String, Object> params);
	
	/**
	 * 获得对象列表
	 * 
	 * @param hql
	 *            HQL语句
	 * @return List
	 */
	public <T> List<T> find(String hql);

	/**
	 * 获得对象列表
	 * 
	 * @param hql
	 *            HQL语句
	 * @param params
	 *            参数
	 * @return List
	 */
	public <T> List<T> find(String hql, Map<String, Object> params);
	
	/**
	 * 分页
	 * @param clazz 对象类.class
	 * @param currentPage 当前页
	 * @param maxResult 每页显示的数量
	 * @return
	 */
	public <T> Page<T> find(Class<T> clazz,int currentPage,int maxResult);
	
	/**
	 * 分页
	 * @param clazz 对象类.class
	 * @param params 参数
	 * @param condition 条件{"=","like"}
	 * @param currentPage 当前页
	 * @param maxResult 每页显示的数据量
	 * @return
	 */
	public <T> Page<T> find(Class<T> clazz,Map<String, Object> params,String[] condition,int currentPage,int maxResult);
	
	/**
	 * 分页
	 * @param clazz 对象类.class
	 * @param params 参数
	 * @param condition 条件{"=","like"}
	 * @param orderBy 排序{"desc",asc}
	 * @param currentPage 当前页
	 * @param maxResult 每页显示的数据量
	 * @return 分页对象
	 */
	public <T> Page<T> find(Class<T> clazz,Map<String, Object> params,String[] condition,String orderBy,String orderByType,int currentPage,int maxResult);
	
	
	/**
	 * sql调用存储过程 - 查询
	 * @param sql 查询语句
	 * @param params 参数
    * @param currentPage 当前页
    * @param maxResult 每页显示的数据量
    * @return 分页对象
	 */
	public List<?> findProcedureBySql(String sql,Map<String, Object> params);
}
