package ${basePackage};

import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
* ${className?cap_first}Entity
* @author zyinn
* @date ${currentDate}
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "${tableName}")
public class ${className?cap_first}Entity {

	<#list columnMap?keys as key>
    	@Column(name = "${columnMap[key]}")
    	@JSONField(name ="${key}")
		private String ${key};//${columnMap[key]}
	</#list>

}
