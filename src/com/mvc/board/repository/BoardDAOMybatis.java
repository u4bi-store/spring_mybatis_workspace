package com.mvc.board.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mvc.board.domain.ReBoard;

@Repository
public class BoardDAOMybatis implements BoardDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List selectAll() {
		return sqlSessionTemplate.selectList("ReBoard.selectAll");
	}

	@Override
	public ReBoard select(int board_id) {
		return sqlSessionTemplate.selectOne("ReBoard.selectOne", board_id);
	}

	@Override
	public void insert(ReBoard board) {
		sqlSessionTemplate.insert("ReBoard.insert", board);
	}

	@Override
	public void update(ReBoard board) {
		sqlSessionTemplate.update("ReBoard.update", board);
	}

	@Override
	public void delete(int board_id) {
		sqlSessionTemplate.delete("ReBoard.delete", board_id);		
	}

}
