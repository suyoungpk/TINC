package com.tinc.web.service.tinc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinc.web.dao.MemberDao;
import com.tinc.web.entity.Member;
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
		return 0;
	}

	@Override
	public int withdrawalMember(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addFriend(String memberId, String friendsId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteFriend(String memberId, String friendsId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int blockUser(String memberId, String blackId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int unblockUser(String memberId, String blackId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFriendsListCount(String memberId) {
		// TODO Auto-generated method stub
		return memberDao.getFriendsListCount(memberId);
	}

	@Override
	public Member getFriend(String memberId) {
		// TODO Auto-generated method stub
		return null;
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
	public List<Member> searchFriendforAdding(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> getListOfUserWhoHaveAddedMe(String friendsId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> getListOfUserWhoBlockedMe(String blackId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> getListOfUserIhaveblocked(String memberId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member findId(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sendTemporaryPassword(String id, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member get(String id) {
		// TODO Auto-generated method stub
		return memberDao.get(id);
	}


	@Override
	public boolean isValidMember(String id, String password) {
		System.out.println("ㅎㅇㅎㅇㅎ");
		String iid = "user1";
		Member member = memberDao.get(iid);
		if(member.getPassword().equals(password)) {
			System.out.println("if"+member);
			return true;
		}
		return false;
	}

	@Override
	public boolean isDuplicatedId(String id) {
		// TODO Auto-generated method stub
		return false;
	}


	


}
