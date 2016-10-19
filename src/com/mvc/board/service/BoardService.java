package com.mvc.board.service;

import java.util.List;

import com.mvc.board.domain.ReBoard;

public interface BoardService {
	public void insert(ReBoard board);
	public List selectAll();
	public ReBoard select(int board_id);
	public void update(ReBoard board);
	public void delete(int board_id);
	
}
