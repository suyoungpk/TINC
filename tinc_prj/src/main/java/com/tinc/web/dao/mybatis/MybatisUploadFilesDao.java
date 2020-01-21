package com.tinc.web.dao.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tinc.web.dao.UploadFilesDao;
import com.tinc.web.entity.UploadFiles;

@Repository
public class MybatisUploadFilesDao implements UploadFilesDao {

	private SqlSession sqlSession;
	private UploadFilesDao mapperDao;

	@Autowired
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
		mapperDao = sqlSession.getMapper(UploadFilesDao.class);
	}

	@Override
	public int uploadFiles(UploadFiles uploadFiles) {
		// TODO Auto-generated method stub
		return mapperDao.uploadFiles(uploadFiles);
	}

}
