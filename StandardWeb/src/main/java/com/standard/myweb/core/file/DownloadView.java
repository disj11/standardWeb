package com.standard.myweb.core.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class DownloadView extends AbstractView {

	public DownloadView() {
		this.setContentType("applicaiton/download;charset=utf-8");
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, String> fileInfo = (Map<String, String>)model.get("fileInfo"); // 넘겨받은 모델(파일 정보)

		String filePath = fileInfo.get("filePath");	// 실제 파일이 저장된 경로
		String saveName = fileInfo.get("saveName");	// 저장할 파일명
		String fileName = fileInfo.get("fileName");	// 실제 저장된 파일명

		File downloadFile = new File(filePath + File.separator + fileName);
		
		response.setContentType(getContentType());
		response.setContentLength((int)downloadFile.length());
		String userAgent = request.getHeader("User-Agent");
		boolean ie = userAgent.indexOf("MSIE") > -1;

		if (ie) {
			saveName = URLEncoder.encode(saveName, "utf-8");
		} else {
			saveName = new String(saveName.getBytes("utf-8"), "iso-8859-1");
		}

		response.setHeader("Content-Disposition", "attachment; filename=\"" + saveName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		OutputStream out = response.getOutputStream();

		FileInputStream fis = null;

		try {
			fis = new FileInputStream(downloadFile);
			FileCopyUtils.copy(fis, out);
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException ex) {
				}
			}
		}
		out.flush();
	}
}
