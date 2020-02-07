package com.tinc.web.service;

import java.util.List;
import java.util.Map;

import com.tinc.web.entity.BlackList;
import com.tinc.web.entity.FriendsList;
import com.tinc.web.entity.Member;
import com.tinc.web.entity.MemberRole;

public interface MemberService{
	int joinMember(Member member); // 회원가입
	int editMember(Member member); // 회원정보수정 
	int withdrawalMember(String id); // 회원탈퇴 
	
	int addRole(MemberRole memberRole); // role추가 
	int addFriend(FriendsList friendsList); // 친구추가 
	int deleteFriend(FriendsList friendsList); // 친구삭제(차단 하기 전) 
	int blockUser(BlackList blackList); // 친구차단 
	int unblockUser(BlackList blackList); // 차단해제 
	
	int getFriendsListCount(String memberId); // 친구 수
	Member get(String id); 
	Member getFriend(String memberId);
	Member getMyProfile(String id); // 내 프로필 출력 
	List<Member> getFriendsProfile(String memberId); // 친구 프로필 출력 
	
	List<Member> getListOfUserWhoHaveAddedMe(String friendsId); // 나를 추가한 사람 목록출력
	List<Member> getListOfUserWhoBlockedMe(String blackId); // 나를 차단한 사람 목록출력
	List<Member> getListOfUserIhaveblocked(String memberId); // 내가 차단한 사람 목록출력
	List<Member> getListToExcludeFromSearch(String id); // 검색에서 제외할 유저목록
	List<Member> searchFriendsforAdding(Map<String, String> item); // 추가할 친구 찾기
	List<Member> searchFriendsToAddToTheChat(Map<String, String> item); // 채팅에 추가할 친구찾기

	Member findId(String email); // 아이디 찾기 
	Member findPwd(Member member); // 비밀번호 찾기
	
	boolean isValidMember(String id, String password); // 로그인 
	String isDuplicatedId(String id); // 중복id 
	
	
	// 응또니 //
	boolean emailOpen(String emailOpen); // 메일공개
	boolean phoneNumOpen(String phoneNumOpen); // 번호공개
	boolean idOpen(String idOpen); // 아이디 공개
	boolean chattingAlarm(String chattingAlarm); // 채팅알람
	boolean memoAlarm(String memoAlarm); // 메모알람
	
}
