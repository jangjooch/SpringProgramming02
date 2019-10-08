package com.company.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.web.dto.Ch05Board;

@Controller
@RequestMapping("/ch05")
public class Ch05Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Ch05Controller.class);
	
	@RequestMapping("/content")
	public String content() {
		return "ch05/content";
	}
	
	@RequestMapping("getBoardList")
	public String getBoard(int pageNo, Model model) {
		// 데이터를 받기 위한 같은 parameter이름 pageNo
		// 데이터를 전달 하기위한 Request와 동일한 방식의 Model 생성
		int startNo = (pageNo - 1) * 10 + 1;
		int endNo = pageNo * 10;
		
		List<Ch05Board> boardList = new ArrayList<>();
		for(int i = startNo ; i <= endNo ; i++) {
			Ch05Board board = new Ch05Board();
			board.setBno(i);
			board.setTitle("Honest Man" + i);
			board.setContent("Continuously Working" + i);
			board.setWriter("Jang");
			board.setDate(new Date());
			board.setHitcount(1);
			boardList.add(board);
		}
		
		model.addAttribute("boardList", boardList);
		// Model은 정보의 집합체이다. 고로 이 Model를 생성 및 저장하여 이를 전달하는 것이다.
		model.addAttribute("totalNo", 100);
		// model은 Request와 같은 쓰임세이지만 mvc를
		// 사용하여 편의성을 증가시키기 위하여 사용되는 것이다.
		
		//return "ch05/getBoardList";
		// html과 java가 뒤섞이여 잘 사용되지 않는다.
		return "ch05/getBoardList2";
		// jstl을 사용하여 html에서 간편하게 사용이 가능하다.

	}
	
	
}
