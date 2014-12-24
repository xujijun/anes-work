package com.xjj.anes.service.anes;

import com.xjj.anes.bean.common.Pager;
import com.xjj.anes.bean.common.ResultBean;
import com.xjj.anes.entity.anes.Work;

public interface WorkService {

	ResultBean selectById(String id);

	ResultBean search(Pager<Work> pager, Work work);

	ResultBean insert(Work work);

	ResultBean update(Work work);

	ResultBean delete(String id);

	ResultBean workAnlys(Work work);
}
