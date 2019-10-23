package com.company.web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.company.web.dto.Ch10Board;

@Component
public class Ch10BoardDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	// MyBatis를 사용하기 위하여 전달 받음
	
	public List<Ch10Board> selectList(int startRowNum, int endRowNum) {
		// TODO Auto-generated method stub
		Map<String, Integer> rowNum = new HashMap<String, Integer>();
		rowNum.put("startRowNum", startRowNum);
		rowNum.put("endRowNum", endRowNum);
		List<Ch10Board> boardList = sqlSessionTemplate.selectList("board.selectList", rowNum);
		// <mapper namespace="board">의 namespace를 가져온다.
		return boardList;
	}
	
	public int selectTotalRowNum() {
		int selectTotalRowNum = sqlSessionTemplate.selectOne("board.selectTotalRowNum");
		return selectTotalRowNum;
	}
	
	
	public int insertList(Ch10Board ch10board) {
		int insertedRows = sqlSessionTemplate.insert("board.insertList", ch10board);
		// 설정파일을 지정하고 Controller에서 부터 전달 받은 ch10board를 
		// 설정파일의 sql형성문 id를 통해 실행되도록 지정한다.
		// insertedRows에 반영된 행의 수를 저장한다.
		// insert는 변경된 행의 갯수를 반환한다.
		return insertedRows;
	}

	public Ch10Board selectBoard(int bnumber) {
		// TODO Auto-generated method stub
		Ch10Board board = sqlSessionTemplate.selectOne("board.selectByBnumber",bnumber);
		return board;
	}

	public int updateHitcount(int bnumber) {
		// TODO Auto-generated method stub
		int rows = sqlSessionTemplate.update("board.updateHitCount",bnumber);
		return rows;
	}

	public int updateBoard(Ch10Board board) {
		// TODO Auto-generated method stub
		int rows = sqlSessionTemplate.update("board.updateBoard",board);
		return rows;
	}

	public int deleteBoard(int bnumber) {
		// TODO Auto-generated method stub
		int rows = sqlSessionTemplate.delete("board.deleteBoardByBnumber",bnumber);
		return rows;
	}
	
}
