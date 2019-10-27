package org.kakarrot.util;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public final class SessionFactory {

	private static SqlSessionFactory factory;

	static {
		String resource = "mybatis-config.xml";
		try (InputStream inputStream = Resources.getResourceAsStream(resource)) {
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SqlSession getSession() {
		SqlSession session = null;
		
		try {
			session = factory.openSession(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return session;
	}

}
