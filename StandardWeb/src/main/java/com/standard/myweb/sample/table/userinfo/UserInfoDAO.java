package com.standard.myweb.sample.table.userinfo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfoDAO {

	@Autowired
	private SqlSession sqlSession;

	private static final String NS = UserInfoDAO.class.getPackage().getName() + ".";

	public List<UserInfoVO> selectUserInfo(UserInfoVO vo) throws Exception {
		return this.sqlSession.selectList(NS + "selectUserInfo", vo);
	}
	
	public int selectUserInfoCnt() throws Exception {
		return this.sqlSession.selectOne(NS + "selectUserInfoCnt");
	}

	public int insertUserInfo(UserInfoVO vo) throws Exception {
		return this.sqlSession.insert(NS + "insertUserInfo", vo);
	}

	public int updateUserInfo(UserInfoVO vo) throws Exception {
		return this.sqlSession.update(NS + "updateUserInfo", vo);
	}

	public int deleteUserInfo(String userId) throws Exception {
		return this.sqlSession.delete(NS + "deleteUserInfo", userId);
	}
}
