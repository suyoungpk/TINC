package com.tinc.web.dao;

import java.util.List;

import com.tinc.web.entity.Member;

public interface MemberDao{
	int joinMember(Member member); // 회원가입
	int editMember(Member member); // 회원정보수정 
	int withdrawalMember(String id); // 회원탈퇴 
	
	int addFriend(String memberId, String friendsId); // 친구추가 
	int deleteFriend(String memberId, String friendsId); // 친구삭제(차단 하기 전) 
	int blockUser(String memberId, String blackId); // 친구차단 
	int unblockUser(String memberId, String blackId); // 차단해제 
	
	int getFriendsListCount(String memberId); // 친구 수
	Member get(String id); 
	Member getMyProfile(String id); // 내 프로필 출력 
	List<Member> getFriendsProfile(String memberId); // 친구 프로필 출력 
	Member getFriend(String memberId);
	List<Member> searchFriendforAdding(String id); // 추가할 친구 찾기
	
	List<Member> getListOfUserWhoHaveAddedMe(String friendsId); // 나를 추가한 사람 목록출력
	List<Member> getListOfUserWhoBlockedMe(String blackId); // 나를 차단한 사람 목록출력
	List<Member> getListOfUserIhaveblocked(String memberId); // 내가 차단한 사람 목록출력

	Member findId(String email); // 아이디 찾기 
	String sendTemporaryPassword(String id, String email); // 임시비밀번호 발급
}