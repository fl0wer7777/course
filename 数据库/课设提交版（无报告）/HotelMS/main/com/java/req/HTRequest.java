package com.java.req;

public class HTRequest {
	
	private int PageNow;
	private int PageSize;
	private int start;
	//≤È—Ø¥ 
	private String searchKey = "lx";
	
	public int getStart() {
		return (PageNow - 1) * PageSize;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	
	public static void main(String[] args) {


	}

}
