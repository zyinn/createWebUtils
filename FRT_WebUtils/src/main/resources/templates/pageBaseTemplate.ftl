package ${basePackage};

import lombok.*;

/**
* PageBaseDto 分页参数
* @author zyinn
* @date ${currentDate}
*/

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PageBaseDto{
	private String orderBy; //DESC ASC
	private String orderColumn; //排序字段名
	private int pageNum;
	private int pageSize;
}
