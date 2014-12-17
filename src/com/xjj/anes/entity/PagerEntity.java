package com.xjj.anes.entity;

public class PagerEntity {
	private int offset;
	private int pageSize;
	private Boolean needPage = true;
	private Object entity;

	public PagerEntity() {
	}

	public PagerEntity(Object entity, int offset, int pageSize) {
		this.entity = entity;
		this.offset = offset;
		this.pageSize = pageSize;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Object getEntity() {
		return entity;
	}

	public void setEntity(Object entity) {
		this.entity = entity;
	}

	public Boolean getNeedPage() {
		return needPage;
	}

	public void setNeedPage(Boolean needPage) {
		this.needPage = needPage;
	}

	@Override
	public String toString() {
		return "PagerEntity [startIndex=" + offset + ", endIndex=" + pageSize
				+ ", entity=" + entity + "]";
	}
}
