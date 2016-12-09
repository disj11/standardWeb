package com.standard.myweb.sample.table.userinfo;

import org.apache.ibatis.type.Alias;

import com.standard.myweb.sample.common.pagination.Pagination;

@Alias("UserInfoVO")
public class UserInfoVO extends Pagination {
	private String userId;
	private String userName;
	private String userAge;
	private int gender;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserAge() {
		return userAge;
	}
	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	
	public String getGenderToString() {
		switch(this.gender) {
		case 1:
			return "남자";
		case 2:
			return "여자";
		default:
			throw new AssertionError("Unknow gender : " + this.gender);
		}
	}
}
