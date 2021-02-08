package com.kousik.HibernateCaching;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StudentCache {
	private int stCacheId;
	private String stCacheName;
	
	@Id
	public int getStCacheId() {
		return stCacheId;
	}
	public void setStCacheId(int stCacheId) {
		this.stCacheId = stCacheId;
	}
	public String getStCacheName() {
		return stCacheName;
	}
	public void setStCacheName(String stCacheName) {
		this.stCacheName = stCacheName;
	}
	@Override
	public String toString() {
		return "StudentCache [stCacheId=" + stCacheId + ", stCacheName=" + stCacheName + "]";
	}
	
}
