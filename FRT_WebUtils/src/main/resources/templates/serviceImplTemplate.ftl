package ${basePackage};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
* ${className?cap_first}ServiceImpl
* @author zyinn
* @date ${currentDate}
*/
@Service
public class ${className?cap_first}ServiceImpl implements ${className?cap_first}Service{
	
	@Autowired
	private ${className?cap_first}Mapper ${className}Dao;

	public PageInfo<?> queryPageList(${className?cap_first}Entity ${className}Entity,PageBaseDto pageBaseDto){
		StringBuffer orderStr = new StringBuffer(pageBaseDto.getOrderColumn() + " " + pageBaseDto.getOrderBy());
		PageHelper.startPage(pageBaseDto.getPageNum(), pageBaseDto.getPageSize(), orderStr.toString());
		return new PageInfo<>(${className}Dao.queryPageList(${className}Entity));
	}

	public int ${className}Save(${className?cap_first}Entity ${className}Entity){
		return ${className}Dao.${className}Save(${className}Entity);
	}

	public int ${className}Modify(${className?cap_first}Entity ${className}Entity){
		return ${className}Dao.${className}Modify(${className}Entity);
	}

	public int ${className}Del(${className?cap_first}Entity ${className}Entity){
		return ${className}Dao.${className}Del(${className}Entity);
	}

}
