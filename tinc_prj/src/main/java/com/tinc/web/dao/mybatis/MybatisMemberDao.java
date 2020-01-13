package com.tinc.web.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tinc.web.dao.MemberDao;
import com.tinc.web.entity.Member;

@Repository
public class MybatisMemberDao implements MemberDao{
	
	private SqlSession sqlSession;
	private MemberDao mapperDao;
	
	@Autowired
	public MybatisMemberDao(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
		this.mapperDao = sqlSession.getMapper(MemberDao.class);
	}

	@Override
	public int joinMember(Member member) {
		// TODO Auto-generated method stub
		return mapperDao.joinMember(member);
	}
	
	@Override
	public int editMember(Member member) {
		// TODO Auto-generated method stub
		return mapperDao.editMember(member);
	}
	
	@Override
	public int withdrawalMember(String id) {
		// TODO Auto-generated method stub
		return mapperDao.withdrawalMember(id);
	}
	
	@Override
	public int addFriend(String memberId, String friendsId) {
		// TODO Auto-generated method stub
		return mapperDao.addFriend(memberId, friendsId);
	}
	
	@Override
	public int deleteFriend(String memberId, String friendsId) {
		// TODO Auto-generated method stub
		return mapperDao.deleteFriend(memberId, friendsId);
	}
	
	@Override
	public int blockUser(String memberId, String blackId) {
		// TODO Auto-generated method stub
		return mapperDao.blockUser(memberId, blackId);
	}
	
	@Override
	public int unblockUser(String memberId, String blackId) {
		// TODO Auto-generated method stub
		return mapperDao.unblockUser(memberId, blackId);
	}
	
	@Override
	public int getFriendsListCount(String memberId) {
		// TODO Auto-generated method stub
		return mapperDao.getFriendsListCount(memberId);
	}
	
	@Override
	public Member getMyProfile(String id) {
		// TODO Auto-generated method stub
		return mapperDao.getMyProfile(id);
	}
	
	@Override
	public List<Member> getFriendsProfile(String memberId) {
		// TODO Auto-generated method stub
		return mapperDao.getFriendsProfile(memberId);
	}
	
	@Override
	public List<Member> searchFriendforAdding(String id) {
		// TODO Auto-generated method stub
		return mapperDao.searchFriendforAdding(id);
	}
	
	@Override
	public List<Member> getListOfUserWhoHaveAddedMe(String friendsId) {
		// TODO Auto-generated method stub
		return mapperDao.getListOfUserWhoHaveAddedMe(friendsId);
	}
	
	@Override
	public List<Member> getListOfUserWhoBlockedMe(String blackId) {
		// TODO Auto-generated method stub
		return mapperDao.getListOfUserWhoBlockedMe(blackId);
	}
	
	@Override
	public List<Member> getListOfUserIhaveblocked(String memberId) {
		// TODO Auto-generated method stub
		return mapperDao.getListOfUserIhaveblocked(memberId);
	}
	
	@Override
	public Member findId(String email) {
		// TODO Auto-generated method stub
		return mapperDao.findId(email);
	}
	
	@Override
	public String sendTemporaryPassword(String id, String email) {
		// TODO Auto-generated method stub
		return mapperDao.sendTemporaryPassword(id, email);
	}

	@Override
	public Member getFriend(String memberId) {
		// TODO Auto-generated method stub
		return mapperDao.getFriend(memberId);
	}

	@Override
	public Member get(String id) {
		System.out.println(id);
		
		System.out.println(mapperDao.get("user1"));
		return mapperDao.get(id);
		
	}

}
