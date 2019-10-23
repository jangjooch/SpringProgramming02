package com.company.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.web.dao.Ch10BoardDao;
import com.company.web.dao.Ch10MemberDao;
import com.company.web.dto.Ch10Board;
import com.company.web.dto.Ch10Member;

// 관리 객체화
@Service
public class Ch10Service {
	
	@Autowired
	private Ch10BoardDao ch10boarddao;
	
	@Autowired
	private Ch10MemberDao ch10memberdao;
	
	public List<Ch10Board> getBoardList(int startRowNum, int endRowNum){
		// 여기서 이제 DB에 접근하여 List를 반환한다.
		
		List<Ch10Board> boardList = ch10boarddao.selectList(startRowNum, endRowNum);
		
		return boardList;
	}
	
	public int getTotalRowNum() {
		int totalRowNum = ch10boarddao.selectTotalRowNum();
		return totalRowNum;
	}
	
	public int writeBoardList(Ch10Board ch10board) {
		int insertedRows = ch10boarddao.insertList(ch10board);
		// Controller에서 전달받은 ch10board를 dao에 전달
		return insertedRows;
	}
	
	public Ch10LoginResult login(String mid, String mpassword) {
		Ch10Member member = ch10memberdao.selectMember(mid);
		// DAO에서 DB에 접근하도록 한다.
		if(member == null) {
			return Ch10LoginResult.FailId;
		}
		else {
			if(mpassword.equals(member.getMpassword())) {
				return Ch10LoginResult.Succes;
			}
			else {
				return Ch10LoginResult.FailPw;
			}
		}
	}
	
	public boolean exists(String mid) {
		Ch10Member member = ch10memberdao.selectMember(mid);
		// 아이디로 가져올 것이 있는가
		if(member==null)
			return true;
		else
			return false;
	}

	public void join(Ch10Member member) {
		// TODO Auto-generated method stub
		ch10memberdao.insert(member);
	}

	public Ch10Board getBoard(int bnumber) {
		// TODO Auto-generated method stub
		Ch10Board board = ch10boarddao.selectBoard(bnumber);
		return board;
	}

	public void upHitcount(int bnumber) {
		// TODO Auto-generated method stub
		ch10boarddao.updateHitcount(bnumber);
	}

	public void updateBoard(Ch10Board board) {
		// TODO Auto-generated method stub
		ch10boarddao.updateBoard(board);
	}

	public void deleteBoard(int bnumber) {
		// TODO Auto-generated method stub
		ch10boarddao.deleteBoard(bnumber);
	}
	
}
