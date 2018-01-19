package ${basePackage};

import java.util.List;
import org.springframework.stereotype.Repository;

/**
* ${className?cap_first}Mapper
* @author zyinn
* @date ${currentDate}
*/
@Repository("${className?cap_first}Mapper")
public interface ${className?cap_first}Mapper {
    //分页查询
	List<${className?cap_first}Entity> queryPageList(${className?cap_first}Entity ${className}Entity);

	//新增
	int ${className}Save(${className?cap_first}Entity ${className}Entity);
	
	//修改
	int ${className}Modify(${className?cap_first}Entity ${className}Entity);
	
	//删除
	int ${className}Del(${className?cap_first}Entity ${className}Entity);

}
