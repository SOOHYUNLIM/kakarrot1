package org.kakarrot.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.kakarrot.domain.MemberDTO;
import org.kakarrot.domain.Paging;
import org.kakarrot.util.SessionFactory;

public class MemberDAOImpl implements MemberDAO {

	@Override
	public boolean insert(MemberDTO dto) {
		SqlSession session = SessionFactory.getSession();
		int a = session.insert("org.kakarrot.dao.memberMapper.insert",dto);
		session.commit();
		return a != 1 ? false : true;
	}

	@Override
	public MemberDTO selectOne(String id) {
		SqlSession session = SessionFactory.getSession();
		MemberDTO dto = session.selectOne("org.kakarrot.dao.memberMapper.selectOne",id);
		return dto;
	}

	@Override
	public List<MemberDTO> selectList(Paging p) {
		// TODO Auto-generated method stub
		return null;
	}


}
