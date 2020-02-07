package com.tinc.web.service.tinc;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinc.web.dao.MemberDao;
import com.tinc.web.entity.BlackList;
import com.tinc.web.entity.FriendsList;
import com.tinc.web.entity.Member;
import com.tinc.web.entity.MemberRole;
import com.tinc.web.service.MemberService;

@Service
public class TincMemberService implements MemberService{

	@Autowired
	private MemberDao memberDao;


	@Override
	public int joinMember(Member member) {
		// TODO Auto-generated method stub
		return memberDao.joinMember(member);
	}

	@Override
	public int editMember(Member member) {
		// TODO Auto-generated method stub
		return memberDao.editMember(member);
	}

	@Override
	public int withdrawalMember(String id) {
		// TODO Auto-generated method stub
		return memberDao.withdrawalMember(id);
	}

	@Override
	public int addFriend(FriendsList friendsList) {
		// TODO Auto-generated method stub
		return memberDao.addFriend(friendsList);
	}

	@Override
	public int deleteFriend(FriendsList friendsList) {
		// TODO Auto-generated method stub
		return memberDao.deleteFriend(friendsList);
	}

	@Override
	public int blockUser(BlackList blackList) {
		// TODO Auto-generated method stub
		return memberDao.blockUser(blackList);
	}

	@Override
	public int unblockUser(BlackList blackList) {
		// TODO Auto-generated method stub
		return memberDao.unblockUser(blackList);
	}

	@Override
	public int getFriendsListCount(String memberId) {
		// TODO Auto-generated method stub
		return memberDao.getFriendsListCount(memberId);
	}

	@Override
	public Member getFriend(String memberId) {
		// TODO Auto-generated method stub
		return memberDao.getFriend(memberId);
	}

	@Override
	public Member getMyProfile(String id) {
		// TODO Auto-generated method stub
		return memberDao.getMyProfile(id);
	}

	@Override
	public List<Member> getFriendsProfile(String memberId) {
		// TODO Auto-generated method stub
		return memberDao.getFriendsProfile(memberId);
	}

	@Override
	public List<Member> searchFriendsforAdding(Map<String, String> item) {
		// TODO Auto-generated method stub
		return memberDao.searchFriendsforAdding(item);
	}

	@Override
	public List<Member> getListOfUserWhoHaveAddedMe(String friendsId) {
		// TODO Auto-generated method stub
		return memberDao.getListOfUserWhoHaveAddedMe(friendsId);
	}

	@Override
	public List<Member> getListOfUserWhoBlockedMe(String blackId) {
		// TODO Auto-generated method stub
		return memberDao.getListOfUserWhoBlockedMe(blackId);
	}

	@Override
	public List<Member> getListOfUserIhaveblocked(String memberId) {
		// TODO Auto-generated method stub
		return memberDao.getListOfUserIhaveblocked(memberId);
	}

	@Override
	public Member findId(String email) {
		// TODO Auto-generated method stub
		return memberDao.findId(email);
	}


	@Override
	public Member get(String id) {
		// TODO Auto-generated method stub
		return memberDao.get(id);
	}


	@Override
	public boolean isValidMember(String id, String password) {
		Member member = memberDao.get(id);
		if(member.getPassword().equals(password)) {
			return true;
		}
		return false;
	}

	@Override
	public String isDuplicatedId(String id) {
		Member member = memberDao.get(id);
		if(member != null) {
			return "true";
		}
		return "false";
	}

	
	// 응또니 //
	@Override
	public boolean emailOpen(String emailOpen) {
		// TODO Auto-generated method stub
		return memberDao.emailOpen(emailOpen);
	}

	@Override
	public boolean phoneNumOpen(String phoneNumOpen) {
		// TODO Auto-generated method stub
		return memberDao.phoneNumOpen(phoneNumOpen);
	}

	@Override
	public boolean idOpen(String idOpen) {
		// TODO Auto-generated method stub
		return memberDao.idOpen(idOpen);
	}

	@Override
	public boolean chattingAlarm(String chattingAlarm) {
		// TODO Auto-generated method stub
		return memberDao.chattingAlarm(chattingAlarm);
	}

	@Override
	public boolean memoAlarm(String memoAlarm) {
		// TODO Auto-generated method stub
		return memberDao.memoAlarm(memoAlarm);
	}
	@Override
	public List<Member> getListToExcludeFromSearch(String id) {
		// TODO Auto-generated method stub
		return memberDao.getListToExcludeFromSearch(id);
	}
	@Override
	public List<Member> searchFriendsToAddToTheChat(Map<String, String> item) {
		// TODO Auto-generated method stub
		return memberDao.searchFriendsToAddToTheChat(item);
	}
	@Override
	public int addRole(MemberRole memberRole) {
		// TODO Auto-generated method stub
		return memberDao.addRole(memberRole);
	}

	@Override
	public Member findPwd(Member member) {
		// TODO Auto-generated method stub
		return memberDao.findPwd(member);
	}

}
