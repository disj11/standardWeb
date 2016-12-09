package com.standard.myweb.core.file;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@Value("#{properties[filePath]}") String filePath; 

	/***
	 * 
	 * @param fileName : 저장된 파일명
	 * @return 파일 다운로드
	 */
	@RequestMapping(value = "/fileDown.do", method = RequestMethod.GET)
	public ModelAndView fileDown(@RequestParam("fileName") String fileName) {
		try {
			Map<String, String> fileInfo = new HashMap<String, String>();
			
			String saveName = fileName;
			
			fileInfo.put("filePath", filePath); // TODO : DB에서 파일경로 가져오기
			fileInfo.put("saveName", saveName);	// TODO : DB에서 원본 파일명 가져오기
			fileInfo.put("fileName", fileName);
			
			return new ModelAndView("download", "fileInfo", fileInfo);
		}catch(Exception e) {
			logger.info ("Exception : ", e);
		}
		return new ModelAndView("/ERROR"); // TODO : 에러 페이지로 이동
	}
}
