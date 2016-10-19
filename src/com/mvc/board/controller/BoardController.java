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
	
	//�� �޼��尡 ���۵Ƿ���, Ŭ���̾�Ʈ�� get������� ��û�� �ؾ��Ѵ�.
	@RequestMapping(value="/board", method=RequestMethod.GET)
	public String selectAll(Model model){
		//System.out.println("selectAll �޼��� ȣ���߾�!!");
		List list=boardService.selectAll();
		model.addAttribute("list", list);
		return "board/list";
	}
	
	//Ŭ���̾�Ʈ�� json���� ����Ʈ��û�� �Ҷ�...
	//@ResponseBody�� �޼��� ó������� Ŭ���̾�Ʈ�� ���Ե� ������ �������� �ƴ�
	//������ ��ü�� ��� ���ȴ�. �ַ� RESTful ���� ���ȴ�.
	@RequestMapping(value="/board", method=RequestMethod.GET, produces={"application/json"})
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<ReBoard> selectList(){
		List list=boardService.selectAll();
		return list;
	}
	
	
	//��Ͽ�û ó�� 
	/*	
	@RequestMapping(value="/board", method=RequestMethod.POST)
	public String insert(ReBoard board){
		boardService.insert(board);
		return "redirect:/board";
	}
	 */
	
	//json ��û�� �۾��� 
	@RequestMapping(value="/board", method=RequestMethod.POST)
	//@ResponseStatus(HttpStatus.NO_CONTENT) //200 ����� ó����	
	public String insert(ReBoard board){
		System.out.println("�ۼ��ڴ� "+board.getWriter());
		System.out.println("������ "+board.getTitle());
		System.out.println("������ "+board.getContent());
		
		boardService.insert(board);	
		return "redirect:/board";
	}
	
	
	//�� �Ѱ� ��û�ޱ� 
	//�����͸��� ���ϴ� Ŭ���̾�Ʈ�ϰ��...android, û�ұ�, �����..
	/*	
	@RequestMapping(value="/board/{id}", method=RequestMethod.GET)
	public @ResponseBody ReBoard select(@PathVariable("id") int reboard_id){
		System.out.println("reboard_id �� "+reboard_id);
		ReBoard board=boardService.select(reboard_id);
		return board;
	}
	 */	
	
	//Ŭ���̾�Ʈ�� ������ �ϰ�쿣, �������� ������ ���� �ϹǷ�, @ResponeBody �ݹ�!
	@RequestMapping(value="/board/{id}", method=RequestMethod.GET)
	public ModelAndView select(@PathVariable("id") int reboard_id){
		System.out.println("reboard_id �� "+reboard_id);
		ReBoard board=boardService.select(reboard_id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("board", board);
		mav.setViewName("board/detail");
		return mav;
	}
	
	@RequestMapping(value="/reply", method=RequestMethod.POST)
	//@ResponseStatus(HttpStatus.NO_CONTENT) //200 ����� ó����	
	public String reply(ReBoard board){
		System.out.println("�ۼ��ڴ� "+board.getWriter());
		System.out.println("������ "+board.getTitle());
		System.out.println("������ "+board.getContent());
		
		boardService.insert(board);	
		return "redirect:/board";
	}
	
}










