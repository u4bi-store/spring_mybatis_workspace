package com.mvc.board.repository;

import java.util.List;

import com.mvc.board.domain.ReBoard;

public interface BoardDAO {
	public List selectAll();
	public ReBoard select(int board_id);
	public void insert(ReBoard board);
	public void update(ReBoard board);
	public void delete(int board_id);
}
