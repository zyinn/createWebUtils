package ${basePackage};

import com.github.pagehelper.PageInfo;

/**
* ${className?cap_first}Service
* @author zyinn
* @date ${currentDate}
*/
public interface ${className?cap_first}Service {

	/**
	* ${className?cap_first}Entity 分页查询
	* @param ${className}Dmo ${className?cap_first}Entity
	* @param dto PageBaseDto
	* @return PageInfo<?>
	**/
	PageInfo<?> queryPageList(${className?cap_first}Entity ${className}Dmo,PageBaseDto dto);

	/**
	* ${className}Save 新增
	* @param ${className}Dmo ${className?cap_first}Entity
	* @return 1/0 int
	**/
	int ${className}Save(${className?cap_first}Entity ${className}Dmo);

	/**
	* ${className}Modify 修改
	* @param ${className}Dmo ${className?cap_first}Entity
	* @return 1/0 int
	**/
	int ${className}Modify(${className?cap_first}Entity ${className}Dmo);

	/**
	* ${className}Del 删除
	* @param ${className}Dmo ${className?cap_first}Entity
	* @return 1/0 int
	**/
	int ${className}Del(${className?cap_first}Entity ${className}Dmo);

}
