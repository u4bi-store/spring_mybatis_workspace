package com.mvc.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.board.domain.ReBoard;
import com.mvc.board.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	//이 메서드가 동작되려면, 클라이언트는 get방식으로 요청을 해야한다.
	@RequestMapping(value="/board", method=RequestMethod.GET)
	public String selectAll(Model model){
		//System.out.println("selectAll 메서드 호출했어!!");
		List list=boardService.selectAll();
		model.addAttribute("list", list);
		return "board/list";
	}
	
	//클라이언트가 json으로 리스트요청을 할때...
	//@ResponseBody란 메서드 처리결과가 클라이언트가 보게될 디자인 페이지가 아닌
	//데이터 자체일 경우 사용된다. 주로 RESTful 에서 사용된다.
	@RequestMapping(value="/board", method=RequestMethod.GET, produces={"application/json"})
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<ReBoard> selectList(){
		List list=boardService.selectAll();
		return list;
	}
	
	
	//등록요청 처리 
	/*	
	@RequestMapping(value="/board", method=RequestMethod.POST)
	public String insert(ReBoard board){
		boardService.insert(board);
		return "redirect:/board";
	}
	 */
	
	//json 요청시 글쓰기 
	@RequestMapping(value="/board", method=RequestMethod.POST)
	//@ResponseStatus(HttpStatus.NO_CONTENT) //200 제대로 처리함	
	public String insert(ReBoard board){
		System.out.println("작성자는 "+board.getWriter());
		System.out.println("제목은 "+board.getTitle());
		System.out.println("내용은 "+board.getContent());
		
		boardService.insert(board);	
		return "redirect:/board";
	}
	
	
	//글 한건 요청받기 
	//데이터만을 원하는 클라이언트일경우...android, 청소기, 냉장고..
	/*	
	@RequestMapping(value="/board/{id}", method=RequestMethod.GET)
	public @ResponseBody ReBoard select(@PathVariable("id") int reboard_id){
		System.out.println("reboard_id 는 "+reboard_id);
		ReBoard board=boardService.select(reboard_id);
		return board;
	}
	 */	
	
	//클라이언트가 브라우저 일경우엔, 디자인을 유저가 봐야 하므로, @ResponeBody 금물!
	@RequestMapping(value="/board/{id}", method=RequestMethod.GET)
	public ModelAndView select(@PathVariable("id") int reboard_id){
		System.out.println("reboard_id 는 "+reboard_id);
		ReBoard board=boardService.select(reboard_id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("board", board);
		mav.setViewName("board/detail");
		return mav;
	}
	
	@RequestMapping(value="/reply", method=RequestMethod.POST)
	//@ResponseStatus(HttpStatus.NO_CONTENT) //200 제대로 처리함	
	public String reply(ReBoard board){
		System.out.println("작성자는 "+board.getWriter());
		System.out.println("제목은 "+board.getTitle());
		System.out.println("내용은 "+board.getContent());
		
		boardService.insert(board);	
		return "redirect:/board";
	}
	
}










