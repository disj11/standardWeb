package com.standard.myweb.sample.userinfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.standard.myweb.sample.common.code.CommonCode;
import com.standard.myweb.sample.table.userinfo.UserInfoDAO;
import com.standard.myweb.sample.table.userinfo.UserInfoVO;

@Service
public class UserInfoService {
	@Autowired
	private UserInfoDAO userInfoDao;
	
	public JsonArray selectUserListJson(UserInfoVO vo) {
		try {
			Gson gson = new Gson();
			
			List<UserInfoVO> list = this.userInfoDao.selectUserInfo(vo);
			JsonArray jsonArr = gson.toJsonTree(list).getAsJsonArray();
			
			return jsonArr;
		} catch(Exception e) {
			return null;
		}
	}
	
	public int selectUserListCnt() {
		try {
			return this.userInfoDao.selectUserInfoCnt();
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public JsonObject insertUser(UserInfoVO vo) {
		JsonObject jsonObj = new JsonObject();
		
		if(vo.getGender()!=0 && vo.getGender()!=1) {
			jsonObj.addProperty(CommonCode.AJAX_RESULT, CommonCode.AJAX_FAIL);
			jsonObj.addProperty(CommonCode.AJAX_MESSAGE, "올바른 성별이 아닙니다.");
		}
		
		try {
			int insCnt = userInfoDao.insertUserInfo(vo);
			
			if(insCnt == 0) {
				jsonObj.addProperty(CommonCode.AJAX_RESULT, CommonCode.AJAX_FAIL);
				jsonObj.addProperty(CommonCode.AJAX_MESSAGE, "회원 등록에 실패하였습니다. 잠시후 다시 시도해주세요.");
			}
			else {
				jsonObj.addProperty(CommonCode.AJAX_RESULT, CommonCode.AJAX_SUCCESS);
			}
		} catch(Exception e) {
			jsonObj.addProperty(CommonCode.AJAX_RESULT, CommonCode.AJAX_FAIL);
			jsonObj.addProperty(CommonCode.AJAX_MESSAGE, "회원 등록에 실패하였습니다. 잠시후 다시 시도해주세요.");
		}
		
		return jsonObj;
	}
	
	public JsonObject updateUser(UserInfoVO vo) {
		JsonObject jsonObj = new JsonObject();
		
		try {
			int updCnt = userInfoDao.updateUserInfo(vo);
			
			if(updCnt == 0) {
				jsonObj.addProperty(CommonCode.AJAX_RESULT, CommonCode.AJAX_FAIL);
				jsonObj.addProperty(CommonCode.AJAX_MESSAGE, "회원 정보 수정에 실패하였습니다. 잠시후 다시 시도해주세요.");
			}
			else {
				jsonObj.addProperty(CommonCode.AJAX_RESULT, CommonCode.AJAX_SUCCESS);
			}
		} catch (Exception e) {
			jsonObj.addProperty(CommonCode.AJAX_RESULT, CommonCode.AJAX_FAIL);
			jsonObj.addProperty(CommonCode.AJAX_MESSAGE, "회원 정보 수정에 실패하였습니다. 잠시후 다시 시도해주세요.");
		}
		
		return jsonObj;
	}
	
	public JsonObject deleteUser(String userId) {
		JsonObject jsonObj = new JsonObject();
		
		try {
			int updCnt = userInfoDao.deleteUserInfo(userId);
			
			if(updCnt == 0) {
				jsonObj.addProperty(CommonCode.AJAX_RESULT, CommonCode.AJAX_FAIL);
				jsonObj.addProperty(CommonCode.AJAX_MESSAGE, "삭제할 회원이 없습니다.");
			}
			else {
				jsonObj.addProperty(CommonCode.AJAX_RESULT, CommonCode.AJAX_SUCCESS);
			}
		} catch (Exception e) {
			jsonObj.addProperty(CommonCode.AJAX_RESULT, CommonCode.AJAX_FAIL);
			jsonObj.addProperty(CommonCode.AJAX_MESSAGE, "회원 삭제에 실패하였습니다. 잠시후 다시 시도해주세요.");
		}
		
		return jsonObj;
	}
}
