package com.mvc.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.board.domain.ReBoard;
import com.mvc.board.repository.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService{
	String TAG=this.getClass().getName();
	
	@Autowired
	private BoardDAO boardDAO;
	
	
	public void insert(ReBoard board) {
		System.out.println(TAG+" insert »£√‚");	
		boardDAO.insert(board);
	}

	@Override
	public List selectAll() {
		return boardDAO.selectAll();
	}

	@Override
	public ReBoard select(int board_id) {
		return boardDAO.select(board_id);
	}

	@Override
	public void update(ReBoard board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int board_id) {
		// TODO Auto-generated method stub
		
	}

}
