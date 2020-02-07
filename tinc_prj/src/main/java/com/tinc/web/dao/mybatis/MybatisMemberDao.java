package com.tinc.web.dao.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tinc.web.dao.MemberDao;
import com.tinc.web.entity.BlackList;
import com.tinc.web.entity.FriendsList;
import com.tinc.web.entity.Member;
import com.tinc.web.entity.MemberRole;

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
	public int addFriend(FriendsList friendsList) {
		// TODO Auto-generated method stub
		return mapperDao.addFriend(friendsList);
	}
	
	@Override
	public int deleteFriend(FriendsList friendsList) {
		// TODO Auto-generated method stub
		return mapperDao.deleteFriend(friendsList);
	}
	
	@Override
	public int blockUser(BlackList blackList) {
		// TODO Auto-generated method stub
		return mapperDao.blockUser(blackList);
	}
	
	@Override
	public int unblockUser(BlackList blackList) {
		// TODO Auto-generated method stub
		return mapperDao.unblockUser(blackList);
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
	public List<Member> searchFriendsforAdding(Map<String, String> item) {
		// TODO Auto-generated method stub
		return mapperDao.searchFriendsforAdding(item);
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
	public Member getFriend(String memberId) {
		// TODO Auto-generated method stub
		return mapperDao.getFriend(memberId);
	}

	@Override
	public Member get(String id) {
		return mapperDao.get(id);
		
	}
	
	
	// 응또니 //
	@Override
	public boolean emailOpen(String emailOpen) {
		// TODO Auto-generated method stub
		return mapperDao.emailOpen(emailOpen);
	}

	@Override
	public boolean phoneNumOpen(String phoneNumOpen) {
		// TODO Auto-generated method stub
		return mapperDao.phoneNumOpen(phoneNumOpen);
	}

	@Override
	public boolean idOpen(String idOpen) {
		// TODO Auto-generated method stub
		return mapperDao.idOpen(idOpen);
	}

	@Override
	public boolean chattingAlarm(String chattingAlarm) {
		// TODO Auto-generated method stub
		return mapperDao.chattingAlarm(chattingAlarm);
	}

	@Override
	public boolean memoAlarm(String memoAlarm) {
		// TODO Auto-generated method stub
		return mapperDao.memoAlarm(memoAlarm);
	}

	@Override
	public List<Member> getListToExcludeFromSearch(String id) {
		// TODO Auto-generated method stub
		return mapperDao.getListToExcludeFromSearch(id);
	}

	@Override
	public List<Member> searchFriendsToAddToTheChat(Map<String, String> item) {
		// TODO Auto-generated method stub
		return mapperDao.searchFriendsToAddToTheChat(item);
	}

	@Override
	public int addRole(MemberRole memberRole) {
		// TODO Auto-generated method stub
		return mapperDao.addRole(memberRole);
	}

	@Override
	public Member findPwd(Member member) {
		// TODO Auto-generated method stub
		return mapperDao.findPwd(member);
	}

	
	   
	   

}
