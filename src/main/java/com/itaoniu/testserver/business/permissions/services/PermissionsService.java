package com.itaoniu.testserver.business.permissions.services;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.jaxrs.model.ClassResourceInfo;

import com.itaoniu.testserver.annotation.Permission;
import com.itaoniu.testserver.business.base.BaseBusiness;
import com.itaoniu.testserver.business.permissions.PermissionErrorCode;
import com.itaoniu.testserver.business.permissions.model.PermissionModel;
import com.itaoniu.testserver.utils.CxfUtils;
import com.itaoniu.testserver.utils.StringUtils;

public class PermissionsService extends BaseBusiness<PermissionErrorCode>{

	/**
	 *  获取方法中定义的所有权限
	 * @return
	 */
	public static List<PermissionModel> getMethodPermissionList(){
		List<PermissionModel> list = new ArrayList<PermissionModel>();
		List<ClassResourceInfo> resourceList = CxfUtils.getClassResourceInfoList();
		for(ClassResourceInfo item:resourceList){
			Class<?> clazz= item.getServiceClass();
			if(!clazz.isAnnotationPresent(Permission.class)){
				continue;
			}
			
			String base = clazz.getAnnotation(Permission.class).value();
			Method[] methodList = clazz.getDeclaredMethods();
			
			for(Method method:methodList){
				if (!method.isAnnotationPresent(Permission.class)) {
					continue;
				}
				
				String name =  method.getAnnotation(Permission.class).value();
				
				if(!StringUtils.isEmpty(base+name)){
					name = base +"-"+name;
				}else{
					name="未命名";
				}
				
				PermissionModel model = new PermissionModel();
				model.setClazz(clazz.getName());
				model.setMethod(method.getName());
				model.setName(name);
				
				list.add(model);
			}
		}
		return list;
	}
}
