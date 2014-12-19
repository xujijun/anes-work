package com.xjj.anes.cache.session;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Date;

public class CacheHttpSession {

	public static class CacheSessionHeader implements Externalizable {
		private static final long serialVersionUID = -1001L;
		private boolean isNew;
		private long createTime;
		private long lastAccessTime;
		private long expireDt;

		public CacheSessionHeader() {
		}

		public boolean getIsNew() {
			return isNew;
		}

		public void setIsNew(boolean value) {
			isNew = value;
		}

		public long getCreateTime() {
			return createTime;
		}

		public void setCreateTime(long value) {
			createTime = value;
		}

		public long getLastAccessTime() {
			return lastAccessTime;
		}

		public void setLastAccessTime(long value) {
			lastAccessTime = value;
		}

		public long getExpireDt() {
			return expireDt;
		}

		public void setExpireDt(long value) {
			expireDt = value;
		}

		public boolean isAvalable() {
			if (expireDt == 0) {
				return true;
			}
			return new Date().getTime() < expireDt;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			final CacheSessionHeader other = (CacheSessionHeader) obj;
			if (isNew != other.isNew) {
				return false;
			}
			if (createTime != other.createTime) {
				return false;
			}
			if (lastAccessTime != other.lastAccessTime) {
				return false;
			}
			if (expireDt != other.expireDt) {
				return false;
			}
			return true;
		}

		@Override
		public int hashCode() {
			int hash = 7;
			hash = 29 * hash + (isNew ? 1 : 0);
			hash = 29 * hash + (int) (createTime ^ (createTime >>> 32));
			hash = 29 * hash + (int) (lastAccessTime ^ (lastAccessTime >>> 32));
			hash = 29 * hash + (int) (expireDt ^ (expireDt >>> 32));
			return hash;
		}

		@Override
		public void writeExternal(ObjectOutput out) throws IOException {
			out.writeBoolean(isNew);
			out.writeLong(createTime);
			out.writeLong(lastAccessTime);
			out.writeLong(expireDt);
		}

		@Override
		public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
			isNew = in.readBoolean();
			createTime = in.readLong();
			lastAccessTime = in.readLong();
			expireDt = in.readLong();
		}

		@Override
		public String toString() {
			return "CacheSessionHeader [isNew=" + isNew + ", createTime="
					+ createTime + ", lastAccessTime=" + lastAccessTime
					+ ", expireDt=" + expireDt + "]";
		}
	}
}
