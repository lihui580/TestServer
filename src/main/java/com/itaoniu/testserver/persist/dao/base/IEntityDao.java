package com.itaoniu.testserver.persist.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IEntityDao<T> {
	
	/**
	 * 保存一个对象，保存的对象必须是通过给方法获取的对象
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
	public void delete(Map<String, Object> params);
	
	/**
	 * 根据条件更新一个对象对应的值
	 * @param clazz 对象类.class
	 * @param params 更新的参数和值
	 * @param condition 更新的条件和值
	 */
	public void update(Map<String, Object> values,Map<String, Object> params);
	
	
//	/**
//	 * 统计数目
//	 * 
//	 * @param hql
//	 *            HQL语句(select count(*) from T)
//	 * @return long
//	 */
//	public Long count(String hql);

//	/**
//	 * 统计数目
//	 * 
//	 * @param hql
//	 *            HQL语句(select count(*) from T where xx = :xx)
//	 * @param params
//	 *            参数
//	 * @return long
//	 */
//	public Long count(String hql, Map<String, Object> params);
	

	/**
	 * 统计数目	
	 * @param clazz  对象类.class
	 * @return 
	 */
	public Long count();
	/**
	 * 统计数目
	 * @param clazz 对象类.class
	 * @param params 参数
	 * @return
	 */
	public Long count(Map<String, Object> params);
	
	/**
	 * 获取一个对象
	 * @param Clazz 对象类.class
	 * @param id 对象id
	 * @return 对象
	 */
	public T get(Serializable id);
	
	/**
	 * 获取一个对象
	 * @param Clazz 对象类.class
	 * @param params 参数<条件,条件值>
	 * @return
	 */
	public T get(Map<String, Object> params);
	
	/**
	 * 
	 * 获取一个对象
	 * @param Clazz 对象类.class
	 * @param params 参数<条件,条件值>
	 * @param orderBy 排序字段
	 * @param orderByType asc desc
	 * @return
	 */
	public T get(Map<String,Object> params,String orderBy,String orderByType);
	
//	/**
//	 * 获取对象列表
//	 * @param clazz 对象类.class
//	 * @return 对象列表
//	 */
//	public  List<T> find();
	
	/**
	 * 根据参数获取对象类别
	 * @param clazz 对象类.class
	 * @param params 参数<条件,条件值>
	 * @return
	 */
	public  List<T> find(Map<String, Object> params);

	/**
	 * 分页
	 * @param clazz 对象类.class
	 * @param currentPage 当前页
	 * @param maxResult 每页显示的数量
	 * @return
	 */
	public Page<T> find(int currentPage,int maxResult);
	
	/**
	 * 分页
	 * @param clazz 对象类.class
	 * @param params 参数
	 * @param condition 条件{"=","like"}
	 * @param currentPage 当前页
	 * @param maxResult 每页显示的数据量
	 * @return
	 */
	public Page<T> find(Map<String, Object> params,String[] condition,int currentPage,int maxResult);
	
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
	public Page<T> find(Map<String, Object> params,String[] condition,String orderBy,String orderByType,int currentPage,int maxResult);
	
	/**
	 * 判断是否存在
	 */
	public boolean exist(T t);
	
}
