package com.itaoniu.testserver.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.itaoniu.testserver.persist.dao.base.Page;

public class CopyUtils {

	public static <T1,T2> Page<T2> copy(Page<T1> source, Class<T2> clazz){
		Page<T2> page = new Page<T2>();
		if(source==null){
			return  page;
		}
		List<T2> list = copy(source.getRecords(),clazz);
		page.setCurrentpage(source.getCurrentpage());
		page.setMaxResult(source.getMaxResult());
		page.setTotalpage(source.getTotalpage());
		page.setTotalrecord(source.getTotalrecord());
		page.setRecords(list);
		return  page;
	}
	
	public static <T1,T2> List<T2> copy(List<T1> source, Class<T2> clazz){
		List<T2> list = new ArrayList<T2>();
		if(source==null||source.isEmpty()){
			return  list;
		}	
		for(T1 item:source){

			list.add(copy(item,clazz));
		}
		return list;
	}
	
	
	public static <T1,T2> T2 copy(T1 source, Class<T2> clazz){
		if(source==null) return null;
		try {
			T2 o = clazz.newInstance();
			BeanUtils.copyProperties(source, o);
			return o;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
