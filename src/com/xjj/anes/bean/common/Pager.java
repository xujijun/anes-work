package com.xjj.anes.bean.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Pager<T> implements Serializable {
	private static final long serialVersionUID = 1000L;
	private Integer pageSize = 20;
	private Integer currentPage = 0;
	private Integer dataSize = 0;
	private boolean needPage = true;
	private List<T> beanList = new ArrayList<T>();

	private Map<String, Object> dataMap = new LinkedHashMap<String, Object>();

	public Pager() {
	}

	public Pager(Integer psize) {
		pageSize = psize;
	}

	public Pager(Integer psize, Integer cpage, Integer dataSize) {
		setPageSize(psize);
		setCurrentPage(cpage);
		setDataSize(dataSize);
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer value) {
		pageSize = value;
	}

	public Integer getCurrentPage() {
		if (currentPage == 0 && beanList != null) {
			currentPage = 1;
		}
		return currentPage;
	}

	public void setCurrentPage(Integer value) {
		currentPage = value;
	}

	public int getOffset() {
		if (currentPage == 0)
			return 0;
		return (currentPage - 1) * pageSize;
	}

	public Integer getDataSize() {
		return dataSize;
	}

	public void setDataSize(Integer value) {
		dataSize = value;
	}

	public void setDataSize(Long value) {
		dataSize = value.intValue();
	}

	public boolean getNeedPage() {
		return needPage;
	}

	public void setNeedPage(boolean value) {
		needPage = value;
	}

	public List<T> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<T> value) {
		beanList = value;
	}

	public void put(String key, Object value) {
		dataMap.put(key, value);
	}

	public Object get(String key) {
		return dataMap.get(key);
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	// //////////////////////////////////////////////////////////////////////////////////
	public long getTotalPages() {
		if (getDataSize() == getPageSize() && getDataSize() == 0) {
			return 0;
		} else if (getDataSize() <= getPageSize()) {
			return 1;
		} else {
			long totalPages = getDataSize() / getPageSize();
			if (getDataSize() % getPageSize() != 0) {
				totalPages += 1;
			}
			return totalPages;
		}
	}

	public boolean isEnableFirst() {
		if (getCurrentPage() > 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isEnablePrev() {
		if (getCurrentPage() > 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isEnableNext() {
		if (getCurrentPage() < getTotalPages()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isEnableLast() {
		if (getCurrentPage() < getTotalPages()) {
			return true;
		} else {
			return false;
		}
	}

	public long getFromRecordNo() {
		if (dataSize == 0) {
			return 0;
		}
		if (getCurrentPage() == 0 && getDataSize() == 0) {
			return 0;
		} else {
			return (getCurrentPage() - 1) * getPageSize() + 1;
		}
	}

	public long getToRecordNo() {
		if (getCurrentPage() == 0 && getDataSize() == 0) {
			return 0;
		} else {
			return getCurrentPage() < getTotalPages() ? getCurrentPage()
					* getPageSize() : getDataSize();
		}
	}

	public long getStartIndex() {
		long sindex = (currentPage - 1) * pageSize + 1;
		if (sindex < 0) {
			sindex = 0;
		}
		return sindex;
	}

	public long getEndIndex() {

		long end = currentPage * pageSize;
		if (end <= 0)
			return pageSize;
		else
			return end;
	}

	@Override
	public String toString() {
		return "Pager [pageSize=" + pageSize + ", currentPage=" + currentPage
				+ ", dataSize=" + dataSize + ", needPage=" + needPage
				+ ", beanList=" + beanList + ", dataMap=" + dataMap + "]";
	}
}