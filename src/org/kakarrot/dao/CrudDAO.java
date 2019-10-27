package org.kakarrot.dao;

import java.util.List;

import org.kakarrot.domain.Paging;

public interface CrudDAO<V,K> {
	
	boolean insert(V vo);
	V selectOne(K key);
	List<V> selectList(Paging p);	

}
