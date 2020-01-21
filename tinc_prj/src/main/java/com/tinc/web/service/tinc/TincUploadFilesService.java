package com.tinc.web.service.tinc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinc.web.dao.ChattingRoomDao;
import com.tinc.web.dao.ChattingRoomOptionDao;
import com.tinc.web.dao.UploadFilesDao;
import com.tinc.web.entity.ChattingRoom;
import com.tinc.web.entity.Member;
import com.tinc.web.entity.UploadFiles;
import com.tinc.web.service.ChattingService;
import com.tinc.web.service.UploadFilesService;

@Service
public class TincUploadFilesService implements UploadFilesService {

	@Autowired
	private UploadFilesDao uploadFilesDao;

	@Override
	public int uploadFiles(UploadFiles uploadFiles) {
		// TODO Auto-generated method stub
		return uploadFilesDao.uploadFiles(uploadFiles);
	}

}
