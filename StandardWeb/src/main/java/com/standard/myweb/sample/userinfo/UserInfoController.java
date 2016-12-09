package com.standard.myweb.sample.userinfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.standard.myweb.sample.table.userinfo.UserInfoVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class UserInfoController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);
	
	@Autowired
	UserInfoService userInfoService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/userinfo/userInfoLt.view", method = RequestMethod.GET)
	public String userInfoLt(UserInfoVO vo, Model model) {
		vo.setItemPerPage(10);
		vo.setTotalItemCount(userInfoService.selectUserListCnt());
		
		model.addAttribute("pagination", vo);
		return "/userinfo/userInfoLt";
	}
	
	@RequestMapping(value = "/userinfo/userInfoLt.json", method = RequestMethod.POST)
	@ResponseBody
	public JsonArray userInfoLtData(UserInfoVO vo) {
		return userInfoService.selectUserListJson(vo);
	}
}
