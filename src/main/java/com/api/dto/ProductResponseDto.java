package com.api.dto;

import java.util.List;

public class ProductResponseDto {
	
	private List<ProductDto> content;
	private boolean firstPage;
	private boolean lastPage;
	private int totalPage;
	private int pageSise;
	private int totalElement;
	private int pageNo;
	
	
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public List<ProductDto> getContent() {
		return content;
	}
	public void setContent(List<ProductDto> content) {
		this.content = content;
	}
	public boolean isFirstPage() {
		return firstPage;
	}
	public void setFirstPage(boolean firstPage) {
		this.firstPage = firstPage;
	}
	public boolean isLastPage() {
		return lastPage;
	}
	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPageSise() {
		return pageSise;
	}
	public void setPageSise(int pageSise) {
		this.pageSise = pageSise;
	}
	public int getTotalElement() {
		return totalElement;
	}
	public void setTotalElement(int totalElement) {
		this.totalElement = totalElement;
	}
	

}
