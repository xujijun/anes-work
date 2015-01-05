package com.xjj.anes.dao.anes;

import java.util.List;

import com.xjj.anes.bean.anes.WorkAnalysisVo;
import com.xjj.anes.dao.BaseDao;
import com.xjj.anes.entity.PagerEntity;
import com.xjj.anes.entity.anes.Work;

public interface WorkDao extends BaseDao<Work> {
	//根据anesthetistId查找work数据数
	long countByAnesthetistId(String anesthetistId);

	//Work selectById(String id);

	long count(PagerEntity pagerEntity);

	List<Work> search(PagerEntity pagerEntity);

	//int insert(Work work);

	//int update(Work work);

	//int delete(String id);

	List<WorkAnalysisVo> workAnalyze(Work work);
}
