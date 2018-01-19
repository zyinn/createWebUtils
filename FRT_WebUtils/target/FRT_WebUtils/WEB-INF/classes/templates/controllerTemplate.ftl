package ${basePackage};

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

/**
* ${className?cap_first}Controller
* @author zyinn
* @date ${currentDate}
*/
@RestController
@CrossOrigin
public class ${className?cap_first}Controller {

    @Autowired
    private ${className?cap_first}Service  ${className}Service;

	@RequestMapping(value = "query${className?cap_first}")
	public ResponseEntity<?> query${className?cap_first}(@RequestBody ${className?cap_first}Entity ${className}Entity) {
        PageBaseDto dto = new PageBaseDto();
        PageInfo<?> pageInfo=${className}Service.queryPageList(${className}Entity, dto);
		return ResponseEntity.ok().body(templateOut(pageInfo));
	}

    @RequestMapping(value = "save${className?cap_first}")
    public ResponseEntity<?> save${className?cap_first}(@RequestBody ${className?cap_first}Entity ${className}Entity) {
        return ResponseEntity.ok().body(${className}Service.${className}Save(${className}Entity));
    }

    @RequestMapping(value = "modify${className?cap_first}")
    public ResponseEntity<?> modify${className?cap_first}(@RequestBody ${className?cap_first}Entity ${className}Entity) {
        return ResponseEntity.ok().body(${className}Service.${className}Modify(${className}Entity));
    }

    @RequestMapping(value = "del${className?cap_first}")
    public ResponseEntity<?> del${className?cap_first}(@RequestBody ${className?cap_first}Entity ${className}Entity) {
        return ResponseEntity.ok().body(${className}Service.${className}Del(${className}Entity));
    }

    public Map<String,Object> templateOut(PageInfo<?> pageInfo){
        Map<String,Object> map = new HashMap<>();
        List<?> list = pageInfo.getList();
        List<Object> listA = new ArrayList<>();
        list.forEach(l -> {
        Object o = JSON.toJSON(l);
        listA.add(o);
        });
        map.put("recordsFiltered",pageInfo.getTotal());
        map.put("recordsTotal",10);
        map.put("data", listA);
        return map;
    }
}
