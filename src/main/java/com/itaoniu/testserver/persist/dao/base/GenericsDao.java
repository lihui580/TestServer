package com.itaoniu.testserver.persist.dao.base;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.SessionFactoryUtils;

import com.itaoniu.testserver.exception.DatabaseException;

public class GenericsDao implements IGenericsDao {

	private static final Log log = LogFactory.getLog(GenericsDao.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 获得当前事务的session
	 * 
	 * @return org.hibernate.Session
	 */
	protected Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	/**
	 * 获取当前session的jdbc Connection
	 * 
	 * @return Connection
	 * @throws SQLException
	 */
	protected Connection getConnection() {
		Connection conn = null;
		try {
			conn = SessionFactoryUtils.getDataSource(this.sessionFactory).getConnection();
		} catch (SQLException e) {
			log.error("创建数据库jdbc连接异常", e);
		}
		return conn;
	}

	@Override
	public Serializable save(Object obj){
		return getCurrentSession().save(obj);	
	}

	@Override
	public void delete(Object obj) {
		getCurrentSession().delete(obj);
	}

	@Override
	public void update(Object obj) {
		try{
			Class clazz = obj.getClass();
			PropertyDescriptor pd2 = new PropertyDescriptor("updateTime", clazz);
			Method setUpdateTime = pd2.getWriteMethod();
			setUpdateTime.invoke(obj,new Date());
			getCurrentSession().update(obj);
			
		}catch(Exception e){
			throw new DatabaseException(e);
			
		}
	}

//	@Override
//	public void saveOrUpdate(Object obj) {
//		getCurrentSession().saveOrUpdate(obj);
//	}

	@Override
	public <T> void delete(Class<T> clazz, Map<String, Object> params) {
		String hql = "delete from " + clazz.getName() + " as c";
		if (params != null && params.size() > 0) {
			hql += " where ";
			int index = 0;
			for (String param : params.keySet()) {
				index++;
				if (params.get(param) instanceof Collection<?>) {
					hql += " c." + param + " in:" + param;
				} else {
					hql += " c." + param + "=:" + param;
				}
				if (index != params.size()) {
					hql += " and ";
				}
			}
		}
		Query query = getCurrentSession().createQuery(hql);
		if (params != null && params.size() > 0) {
			for (String key : params.keySet()) {
				Object obj = params.get(key);
				if (obj instanceof Collection<?>) {
					query.setParameterList(key, (Collection<?>) obj);
				} else {
					query.setParameter(key, obj);
				}
			}
		}
		query.executeUpdate();
	}

	@Override
	public <T> void update(Class<T> clazz, Map<String, Object> values, Map<String, Object> params) {
		String hql = "update " + clazz.getName() + " as c";
		if (values != null && values.size() > 0) {
			hql += " set ";
			int valueIndex = 0;
			for (String value : values.keySet()) {
				valueIndex++;
				hql += "c." + value + "=:" + value;
				if (valueIndex != values.size()) {
					hql += " , ";
				}
			}
			if (params != null && params.size() > 0) {
				hql += " where ";
				int paramIndex = 0;
				for (String param : params.keySet()) {
					paramIndex++;
					// 支持in查询
					if (params.get(param) instanceof Collection<?>) {
						hql += " c." + param + " in:" + param;
					} else {
						hql += " c." + param + "=:" + param;
					}
					if (paramIndex != params.size()) {
						hql += " and ";
					}
				}
			}
		}
		Query query = getCurrentSession().createQuery(hql);
		if (values != null && values.size() > 0) {
			for (String value : values.keySet()) {
				query.setParameter(value, values.get(value));
			}
		}
		if (params != null && params.size() > 0) {
			for (String key : params.keySet()) {
				Object obj = params.get(key);
				// 支持in查询
				if (obj instanceof Collection<?>) {
					query.setParameterList(key, (Collection<?>) obj);
				} else {
					query.setParameter(key, obj);
				}
			}
		}
		query.executeUpdate();
	}

	@Override
	public Long count(String hql) {
		if (!hql.contains("count(*)")) {
			hql = "select count(*) " + hql;
		}
		Query q = this.getCurrentSession().createQuery(hql);
		return (Long) q.uniqueResult();
	}

	@Override
	public Long count(String hql, Map<String, Object> params) {
		if (!hql.contains("count(*)")) {
			hql = "select count(*) " + hql;
		}
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (Long) q.uniqueResult();
	}

	@Override
	public Long count(Class<?> clazz) {
		String hql = "select count(*) from " + clazz.getName();
		return count(hql);
	}

	@Override
	public Long count(Class<?> clazz, Map<String, Object> params) {
		String hql = "select count(*) from " + clazz.getName() + " as c ";
		if (params != null && params.size() > 0) {
			hql += " where ";
			int index = 0;
			for (String param : params.keySet()) {
				index++;
				hql += " c." + param + "=:" + param;
				if (index != params.size()) {
					hql += " and ";
				}
			}
		}
		return count(hql, params);
	}

	@Override
	public <T> T get(Class<T> clazz, Serializable id) {
		return (T) getCurrentSession().get(clazz, id);
	}

	@Override
	public <T> T get(Class<T> clazz, Map<String, Object> params) {
		List<?> list = find(clazz, params);
		if (list != null && list.size() > 0) {
			return (T) list.get(0);
		}
		return null;
	}
	@Override
	public <T> T get(Class<T> clazz, Map<String, Object> params, String orderBy, String orderByType) {
		Page<T> list = find(clazz, params,null,orderBy,orderByType,1,1);
		if(list!=null&&list.getRecords()!=null&&list.getRecords().size()>0){
			return (T)list.getRecords().get(0);
		}
		return null;
	}

	@Override
	public <T> List<T> find(Class<T> clazz) {
		String hql = "from " + clazz.getName();
		return find(hql);
	}

	@Override
	public <T> List<T> find(Class<T> clazz, Map<String, Object> params) {
		String hql = "from " + clazz.getName() + " as c ";
		if (params != null && params.size() > 0) {
			hql += " where ";
			int index = 0;
			for (String param : params.keySet()) {
				index++;
				// 支持in查询
				if (params.get(param) instanceof Collection<?>) {
					hql += " c." + param + " in:" + param;
				} else {
					hql += " c." + param + "=:" + param;
				}
				if (index != params.size()) {
					hql += " and ";
				}
			}
		}
		return find(hql, params);
	}

	@Override
	public <T> List<T> find(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.list();
	}

	@Override
	public <T> List<T> find(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				Object obj = params.get(key);
				// 支持in查询
				if (obj instanceof Collection<?>) {
					q.setParameterList(key, (Collection<?>) obj);
				} else {
					q.setParameter(key, obj);
				}
			}
		}
		return q.list();
	}

	@Override
	public <T> Page<T> find(Class<T> clazz, int currentPage, int maxResult) {
		return find(clazz, null, null, currentPage, maxResult);
	}

	@Override
	public <T> Page<T> find(Class<T> clazz, Map<String, Object> params, String[] condition, int currentPage, int maxResult) {
		return find(clazz, params, condition, null, null, currentPage, maxResult);
	}

	@Override
	public <T> Page<T> find(Class<T> clazz, Map<String, Object> params, String[] condition, String orderBy, String orderByType, int currentPage, int maxResult) {
		Page<T> page = new Page<T>(maxResult);
		String hql = "from " + clazz.getName() + " as c ";
		if (params != null && params.size() > 0) {
			hql += " where ";
			int conditionIndex = 0;
			for (String param : params.keySet()) {
				if(condition==null||condition.length==0){
					hql += "c." + param + " " + "=" + " '" + params.get(param) + "' ";
				}else{
					hql += "c." + param + " " + condition[conditionIndex] + " '" + params.get(param) + "' ";
				}
				conditionIndex++;
				if (conditionIndex != params.size())
					hql += " and ";
			}
		}

		String noOrderbyHQL = hql;
		if (orderBy != null && !orderBy.equals("")) {
			hql += " order by c." + orderBy + " " + orderByType;
		}

		Query query = getCurrentSession().createQuery(hql);
		query.setFirstResult((currentPage - 1) * maxResult);
		query.setMaxResults(maxResult);

		page.setRecords((List<T>) query.list());
		page.setCurrentpage(currentPage);
		page.setTotalrecord(count(noOrderbyHQL));
		page.setTotalpage((page.getTotalrecord() % page.getMaxResult()) == 0 ? (page.getTotalrecord() / page.getMaxResult()) : (page.getTotalrecord() / page.getMaxResult()) + 1);

		return page;
	}

	@Override
	public List<?> findProcedureBySql(String sql, Map<String, Object> params) {
		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		if (params != null && params.size() > 0) {
			for (String key : params.keySet()) {
				query.setString(key, params.get(key).toString());
			}
		}
		return query.list();
	}

	

}
