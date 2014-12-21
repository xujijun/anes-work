package com.xjj.anes.dao.anes;

import java.util.List;

import com.xjj.anes.entity.PagerEntity;
import com.xjj.anes.entity.anes.Anesthetist;

public interface AnesthetistDao {

	Anesthetist selectById(String id);

	long count(PagerEntity pagerEntity);

	List<Anesthetist> search(PagerEntity pagerEntity);

	int insert(Anesthetist anesthetist);

	int update(Anesthetist anesthetist);

	int delete(String id);

	List<Anesthetist> selectAllValid();

}
