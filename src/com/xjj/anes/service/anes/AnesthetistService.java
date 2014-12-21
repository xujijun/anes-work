package com.xjj.anes.service.anes;

import com.xjj.anes.bean.common.Pager;
import com.xjj.anes.bean.common.ResultBean;
import com.xjj.anes.entity.anes.Anesthetist;

public interface AnesthetistService {

	ResultBean selectById(String id);

	ResultBean search(Pager<Anesthetist> pager, Anesthetist anesthetist);

	ResultBean insert(Anesthetist anesthetist);

	ResultBean update(Anesthetist anesthetist);

	ResultBean delete(String id);

	ResultBean selectAllValid();

}
