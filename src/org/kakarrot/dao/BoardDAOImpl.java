package org.kakarrot.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.kakarrot.domain.BoardVO;
import org.kakarrot.domain.Paging;
import org.kakarrot.domain.SearchPaging;
import org.kakarrot.util.SessionFactory;

public class BoardDAOImpl implements BoardDAO {
	

	@Override
	public boolean insert(BoardVO vo) {
		SqlSession session = null;
		int result = 0;
		try {
			session = SessionFactory.getSession();
			result = session.insert("org.kakarrot.dao.boardMapper.insert",vo);
			
			List<String> fnames = vo.getFnames();
			
			for(String fname : fnames) {
				session.insert("org.kakarrot.dao.boardMapper.insertFile",fname);
			}
			
			session.commit();
			
		} catch(Exception e) {
			System.out.println("ÀúÀå¾ÈµÊ");
			session.rollback();
		} finally {
			session.close();
		}
		
		return result == -1 ? false : true;
	}

	@Override
	public BoardVO selectOne(Long key) {
		SqlSession session = SessionFactory.getSession();
		BoardVO vo = session.selectOne("org.kakarrot.dao.boardMapper.selectOne",key);
		
		List<String> fnames = session.selectList("org.kakarrot.dao.boardMapper.selectFile",key);
		
		for(String fname : fnames)
			vo.addFileName(fname);
		
		session.close();
		return vo;
	}

	@Override
	public List<BoardVO> selectList(Paging p) {
		SqlSession session = SessionFactory.getSession();
		List<BoardVO> list = session.selectList("org.kakarrot.dao.boardMapper.selectList",p);
		session.close();
		return list;
	}
	
//	@Override
//	public int getCount() {
//		SqlSession session = SessionFactory.getSession();
//		int result = session.selectOne("org.kakarrot.dao.boardMapper.getCount");
//		return result;
//	}

}
