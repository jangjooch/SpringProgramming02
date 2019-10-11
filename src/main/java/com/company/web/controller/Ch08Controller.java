package com.company.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/ch08")
public class Ch08Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Ch08Controller.class);
	
	@RequestMapping("/content")
	public String content() {
		logger.info("Ch08 content Activate");
		return "ch08/content";
	}
	
	@PostMapping("/fileUpload")
	public String upload(HttpServletRequest request, String title, String description
			, MultipartFile attach01, MultipartFile attach02, Model model) throws Exception{
		
		ServletContext application = request.getServletContext();
		String savePath = application.getRealPath("/resources/upload/");
		// request를 통해 context를 얻어내고 이를 이용해 context-root위치를 얻어낸다.
		
		logger.info("Ch08 fileUpload Activate");
		logger.info(title);
		logger.info(description);
		if(!attach01.isEmpty()) {
			logger.info("---------------");
			logger.info("attach01's Name		 : " + attach01.getOriginalFilename());
			logger.info("attach01's Content-Type	 : " + attach01.getContentType());
			logger.info("attach01's Size		 : " + attach01.getSize());
			String saveFileName =  new Date().getTime() + "-" + attach01.getOriginalFilename();
			logger.info("attach01's saving name 	 : " + saveFileName);
			attach01.transferTo(new File(savePath + saveFileName));
			// 아래가 실제 저장되는 위치이다. war에서 실행되는 것이기에 war가 실행되는 위치인 .metadata에서
			// 저장되는 위치가 결정되는 것이다. savePath가 upload까지의 path를 받아온다.
			// D:\IoTCourse\workspace_web\.metadata\.plugins\org.eclipse.wst.server.core\tmp0
			// \wtpwebapps\SpringProgramming02\resources\\upload 
		}
		if(!attach02.isEmpty()) {
			logger.info("---------------");
			logger.info("attach02's Name		 : " + attach02.getOriginalFilename());
			logger.info("attach02's Content-Type	 : " + attach02.getContentType());
			logger.info("attach02's Size		 : " + attach02.getSize());
			String saveFileName =  new Date().getTime() + "-" + attach02.getOriginalFilename();
			logger.info("attach02's saving name 	 : " + saveFileName);
			attach02.transferTo(new File(savePath + saveFileName));
		}
		// 만약 파일을 upload하지 않았다면 size가 0이 출력된다.
		
		// .jsp로 다시 이동
		
		model.addAttribute("title",title);
		model.addAttribute("description", description);
		if(!attach01.isEmpty()) {
			model.addAttribute("attach01",attach01.getOriginalFilename());
		}
		if(!attach02.isEmpty()) {
			model.addAttribute("attach02",attach02.getOriginalFilename());
		}
		
		return "ch08/fileUpload";
		
	}
	
}
