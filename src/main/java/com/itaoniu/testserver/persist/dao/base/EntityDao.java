package com.itaoniu.testserver.persist.dao.base;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itaoniu.testserver.exception.DatabaseException;

public class EntityDao<T> extends GenericsDao implements IEntityDao<T> {
	private static final Log log = LogFactory.getLog(EntityDao.class);

	private Class<T> clazz;

	public EntityDao() {
		clazz = GenericsUtils.getSuperClassGenricType(getClass());
	}

	public EntityDao(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public Serializable save(Object obj) {
//		try {
//			PropertyDescriptor pd0 = new PropertyDescriptor("exist", obj.getClass());
//			Method setExist = pd0.getWriteMethod();
//			setExist.invoke(obj, 1);
//			PropertyDescriptor pd1 = new PropertyDescriptor("createTime", obj.getClass());
//			Method setCreateTime = pd1.getWriteMethod();
//			setCreateTime.invoke(obj, new Date());
//			PropertyDescriptor pd2 = new PropertyDescriptor("updateTime", obj.getClass());
//			Method setUpdateTime = pd2.getWriteMethod();
//			setUpdateTime.invoke(obj, new Date());
//			return super.save(obj);
//
//		} catch (Exception e) {
//			throw new DatabaseException(e);
//		}
		try {
			PropertyDescriptor pid = new PropertyDescriptor("id", obj.getClass());
			Method getId = pid.getReadMethod();
			Long id = (Long) getId.invoke(obj);
			
			PropertyDescriptor pExist = new PropertyDescriptor("exist", obj.getClass());
			Method getExist = pExist.getReadMethod();
			int exist = (int) getExist.invoke(obj);
			
			if (id == null&&exist==0) {//新建的对象
				PropertyDescriptor pd0 = new PropertyDescriptor("exist", obj.getClass());
				Method setExist = pd0.getWriteMethod();
				setExist.invoke(obj, 1);
				PropertyDescriptor pd1 = new PropertyDescriptor("createTime", obj.getClass());
				Method setCreateTime = pd1.getWriteMethod();
				setCreateTime.invoke(obj, new Date());
				PropertyDescriptor pd2 = new PropertyDescriptor("updateTime", obj.getClass());
				Method setUpdateTime = pd2.getWriteMethod();
				setUpdateTime.invoke(obj, new Date());
				return super.save(obj);
				
			} else if(id!=null&&exist==2){//已有的对象，一般情况下为get获取的对象
				PropertyDescriptor pd0 = new PropertyDescriptor("exist", obj.getClass());
				Method setExist = pd0.getWriteMethod();
				setExist.invoke(obj, 1);
				PropertyDescriptor pd2 = new PropertyDescriptor("updateTime", obj.getClass());
				Method setUpdateTime = pd2.getWriteMethod();
				setUpdateTime.invoke(obj, new Date());
				super.update(obj);
				return id;	
			}else {
				throw new DatabaseException("保存格式不正确");
			}

		} catch (Exception e) {
			throw new DatabaseException(e);
		}
	}

	@Override
	public void delete(Object obj) {
		try {
			PropertyDescriptor pd0 = new PropertyDescriptor("exist", obj.getClass());
			Method setExist = pd0.getWriteMethod();
			setExist.invoke(obj, 2);

		} catch (Exception e) {
			throw new DatabaseException(e);
		}

	}

	@Override
	public void update(Object obj) {
		try {
			PropertyDescriptor pd2 = new PropertyDescriptor("updateTime", obj.getClass());
			Method setUpdateTime = pd2.getWriteMethod();
			setUpdateTime.invoke(obj, new Date());
			super.update(obj);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
	}

	@Override
	public void delete(Map<String, Object> params) {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("exist", 2);
		super.update(clazz, values, params);
	}

	@Override
	public void update(Map<String, Object> values, Map<String, Object> params) {
		values.put("updateTime", new Date());
		this.update(clazz, values, params);
	}

	@Override
	public Long count() {
		return this.count(clazz);
	}

	@Override
	public Long count(Map<String, Object> params) {
		return this.count(clazz, params);
	}

	@Override
	public T get(Serializable id) {
		return this.get(clazz, id);
	}

	@Override
	public T get(Map<String, Object> params) {
		return this.get(clazz, params);
	}

	@Override
	public T get(Map<String, Object> params, String orderBy, String orderByType) {
		return this.get(clazz, params, orderBy, orderByType);
	}

	// @Override
	// public List<T> find() {
	// return this.find(clazz);
	// }

	@Override
	public List<T> find(Map<String, Object> params) {
		return this.find(clazz, params);
	}

	@Override
	public Page<T> find(int currentPage, int maxResult) {
		return this.find(clazz, currentPage, maxResult);
	}

	@Override
	public Page<T> find(Map<String, Object> params, String[] condition, int currentPage, int maxResult) {
		return this.find(clazz, params, condition, currentPage, maxResult);
	}

	@Override
	public Page<T> find(Map<String, Object> params, String[] condition, String orderBy, String orderByType, int currentPage, int maxResult) {
		return this.find(clazz, params, condition, orderBy, orderByType, currentPage, maxResult);
	}

	@Override
	public boolean exist(T obj) {
		if (obj == null) {
			return false;
		}
		try {
			PropertyDescriptor exist = new PropertyDescriptor("exist", obj.getClass());
			Method getExist = exist.getReadMethod();
			int existInt = (int) getExist.invoke(obj);
			if (existInt == 1) {
				return true;
			}
			return true;

		} catch (Exception e) {
			throw new DatabaseException(e);

		}

	}

}
