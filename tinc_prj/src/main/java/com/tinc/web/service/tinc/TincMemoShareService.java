package com.tinc.web.service.tinc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinc.web.dao.CheckListDao;
import com.tinc.web.dao.CheckListItemDao;
import com.tinc.web.dao.DueDateDao;
import com.tinc.web.dao.FriendsShareFullViewDao;
import com.tinc.web.dao.FriendsShareViewDao;
import com.tinc.web.dao.GroupMemoListDao;
import com.tinc.web.dao.GroupShareFullViewDao;
import com.tinc.web.dao.GroupShareMemberViewDao;
import com.tinc.web.dao.GroupShareViewDao;
import com.tinc.web.dao.MemoCardDao;
import com.tinc.web.dao.MemoCardViewDao;
import com.tinc.web.dao.PrivateMemoListDao;
import com.tinc.web.entity.CheckList;
import com.tinc.web.entity.CheckListItem;
import com.tinc.web.entity.DueDate;
import com.tinc.web.entity.FriendsShareFullView;
import com.tinc.web.entity.FriendsShareView;
import com.tinc.web.entity.GroupMemoList;
import com.tinc.web.entity.GroupShareFullView;
import com.tinc.web.entity.GroupShareMemberView;
import com.tinc.web.entity.GroupShareView;
import com.tinc.web.entity.MemoCard;
import com.tinc.web.entity.MemoCardView;
import com.tinc.web.entity.PrivateMemoList;
import com.tinc.web.service.MemoShareService;
import com.tinc.web.service.PrivateMemoListService;

@Service
public class TincMemoShareService implements MemoShareService
{
	@Autowired
	private FriendsShareViewDao friendsShareViewDao;
	@Autowired
	private GroupShareViewDao groupShareViewDao;
	@Autowired
	private GroupShareFullViewDao groupShareFullViewDao;
	@Autowired
	private PrivateMemoListDao privateMemoListDao;
	@Autowired
	private GroupMemoListDao groupMemoListDao;
	@Autowired
	private MemoCardDao memoCardDao;
	@Autowired
	private CheckListDao checkListDao;
	@Autowired
	private CheckListItemDao checkListItemDao;
	@Autowired
	private DueDateDao dueDateDao;
	@Autowired
	private GroupShareMemberViewDao gsMemberViewDao;
	@Autowired
	private FriendsShareFullViewDao friendsShareFullViewDao;
	
	@Override
	public List<FriendsShareView> getFriendsShareViewList(String id)
	{
		// TODO Auto-generated method stub
		return friendsShareViewDao.getList(id);
	}

	@Override
	public List<GroupShareView> getGroupShareViewList(String id)
	{
		// TODO Auto-generated method stub
		return groupShareViewDao.getList(id);
	}

	@Transactional
	@Override
	public int shareMemoToFriends(String mId, int mcId)
	{
		// TODO Auto-generated method stub		
		PrivateMemoList privateMemoList = privateMemoListDao.getByMemberId(mId);
		
		MemoCard memoCard = memoCardDao.getById(mcId);
		memoCard.setPrivateListId(privateMemoList.getId());
		memoCard.setGroupListId(null);
		memoCardDao.insert(memoCard);
		int newMcId = memoCardDao.getNewMcId();
		
		List<CheckList> clList = checkListDao.getListByCardId(mcId);
		for(CheckList cl : clList)
		{
			cl.setCardId(newMcId);
			checkListDao.insert(cl);
			int newClId = checkListDao.getNewClIdByCardId(newMcId);
			
			List<CheckListItem> cliList = checkListItemDao.getListByCheckListId(cl.getId());
			for(CheckListItem cli : cliList)
			{
				cli.setCheckListId(newClId);
				checkListItemDao.insert(cli);
			}			
		}
		
		DueDate duedate = dueDateDao.getByCardId(mcId);
		if(duedate != null)
		{
			duedate.setCardId(newMcId);
			dueDateDao.insert(duedate);
		}
		
		return 0;
	}

	@Transactional
	@Override
	public int shareMemoToChattingRoom(String mId, int gsId, int mcId)
	{
		// TODO Auto-generated method stub
		GroupMemoList groupMemoList = groupMemoListDao.get(gsId, mId);
		if(groupMemoList == null)
		{
			return -1;
		}
		
		MemoCard memoCard = memoCardDao.getById(mcId);
		memoCard.setGroupListId(groupMemoList.getId());
		memoCard.setPrivateListId(null);
		memoCardDao.insert(memoCard);
		int newMcId = memoCardDao.getNewMcId();
		System.out.println("newMcId: " + newMcId);
		
		List<CheckList> clList = checkListDao.getListByCardId(mcId);
		for(CheckList cl : clList)
		{
			cl.setCardId(newMcId);
			checkListDao.insert(cl);
			int newClId = checkListDao.getNewClIdByCardId(newMcId);
			
			List<CheckListItem> cliList = checkListItemDao.getListByCheckListId(cl.getId());
			for(CheckListItem cli : cliList)
			{
				cli.setCheckListId(newClId);
				checkListItemDao.insert(cli);
			}
			
		}
		
		DueDate duedate = dueDateDao.getByCardId(mcId);
		if(duedate != null)
		{
			duedate.setCardId(newMcId);
			dueDateDao.insert(duedate);
		}
		
		return 0;
	}

	@Override
	public List<GroupShareFullView> getGroupShareFullViewList(String id)
	{
		// TODO Auto-generated method stub
		return groupShareFullViewDao.getListById(id);
	}

	@Override
	public List<GroupShareMemberView> getGroupShareMemberViewList()
	{
		// TODO Auto-generated method stub
		return gsMemberViewDao.getList();
	}

	@Override
	public List<FriendsShareFullView> getFriendsShareFullViewList(String id)
	{
		// TODO Auto-generated method stub
		return friendsShareFullViewDao.getList(id);
	}
	
	// id1과 id2가 동시에 존재하는 채팅룸 갯수 반환
	@Override
	public int hasPrivateChatRoom(String id1, String id2)
	{
		// TODO Auto-generated method stub
		int count = 0;
		List<FriendsShareFullView> list = friendsShareFullViewDao.getList(id1);
		for(FriendsShareFullView fsf : list)
		{
			if(fsf.getFriendsId().equals(id2))
			{
				count++;
			}
		}		
		
		return count;
	}

}
