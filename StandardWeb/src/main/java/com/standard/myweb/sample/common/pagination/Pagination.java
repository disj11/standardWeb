package com.standard.myweb.sample.common.pagination;

public class Pagination {
	private int itemPerPage = 10; // 한 페이지에 출력할 게시글 수
	private int nextPage = 10; // 다음, 이전 버튼 클릭시 뛰어넘을 페이지 수
	private int page = 1; // 페이지 변수
	private int totalItemCount; // 총 게시글 수

	// 현재 위치한 페이지를 구함
	public int getCurrentPage() {
		int page = this.page;
		if (page < 1) {
			page = 1;
		}
		int pageCount = this.getPageCount();
		if (page > pageCount) {
			page = pageCount;
		}
		return page;
	}

	// 전체 페이지 수를 구함
	public int getPageCount() {
		return (this.totalItemCount - 1) / this.itemPerPage + 1;
	}

	// 페이지의 시작 위치를 구함
	public int getPageBegin() {
		return (this.getCurrentPage() - 1) / this.nextPage * this.nextPage + 1;
	}

	// 페이지의 끝 위치를 구함
	public int getPageEnd() {
		int pageCount = this.getPageCount();
		int num = this.getPageBegin() + this.nextPage - 1;
		return Math.min(pageCount, num);
	}

	public int getTotalItemCount() {
		return this.totalItemCount;
	}

	public void setTotalItemCount(final int totalItemCount) {
		this.totalItemCount = totalItemCount;
	}

	public int getItemPerPage() {
		return this.itemPerPage;
	}

	public void setItemPerPage(final int itemPerPage) {
		this.itemPerPage = itemPerPage;
	}

	public int getNextPage() {
		return this.nextPage;
	}

	public void setNextPage(final int nextPage) {
		this.nextPage = nextPage;
	}

	public int getPage() {
		return this.page;
	}

	public void setPage(final int page) {
		this.page = page;
	}

	// 현재 게시글 위치 (mysql limit에 사용)
	public int getCurrentItem() {
		return (this.page - 1) * this.itemPerPage;
	}

	// 현재 게시글 위치 (mysql limit에 사용)
	public int getCurrentItemEnd() {
		return (this.page - 1) * this.itemPerPage + this.itemPerPage;
	}

	// 다음으로 점프하는 페이지
	public int getJumpNextPage() {
		return Math.min(this.getPageCount(), this.page + this.nextPage);
	}

	// 이전으로 점프하는 페이지
	public int getJumpPrevPage() {
		return Math.max(1, this.page - this.nextPage);
	}
}
