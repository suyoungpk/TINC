package com.tinc.web.controller.JSON;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.tinc.web.entity.CheckList;
import com.tinc.web.entity.CheckListItem;
import com.tinc.web.entity.DueDate;
import com.tinc.web.entity.GroupMemoList;
import com.tinc.web.entity.GroupShareFullView;
import com.tinc.web.entity.GroupShareMemberView;
import com.tinc.web.entity.MemoCard;
import com.tinc.web.entity.PrivateMemoList;
import com.tinc.web.service.ChattingService;
import com.tinc.web.service.CheckListItemService;
import com.tinc.web.service.CheckListService;
import com.tinc.web.service.DueDateService;
import com.tinc.web.service.GroupMemoListService;
import com.tinc.web.service.MemberService;
import com.tinc.web.service.MemoCardService;
import com.tinc.web.service.MemoShareService;
import com.tinc.web.service.PrivateMemoListService;

@Controller
@RequestMapping("/jsonTinc/memo/")
public class MemoJSON {

	@Autowired
	private PrivateMemoListService PrivateMemoListService;
	@Autowired
	private GroupMemoListService groupMemoListService;
	@Autowired
	private MemoCardService memoCardService;
	@Autowired
	private CheckListService checkListService;
	@Autowired
	private CheckListItemService checkListItemService;
	@Autowired
	private DueDateService dueDateService;
	
	@ResponseBody
	@GetMapping("list")
	public String list(Principal principal, @RequestParam(name="crId", defaultValue="0") String crId)
	{
		String mId = "";
		//mId = principal.getName();
		mId = "user2";
		
		List<String> list = new ArrayList<String>();
		Gson gson = new Gson();
		
		String mList = "";
		
		if(Integer.parseInt(crId) == 0)
		{
			List<PrivateMemoList> privateMemoList = PrivateMemoListService.getPrivateMemoList(mId);
			List<GroupMemoList> groupMemoList = groupMemoListService.getGroupMemoList(mId);
			List<MemoCard> memoCardList = memoCardService.getList();
			
			list.add(gson.toJson(privateMemoList));
			list.add(gson.toJson(groupMemoList));
			list.add(gson.toJson(memoCardList));
			list.add(gson.toJson(mId));
			
			mList = list.toString();
			
		}
		else
		{
			List<GroupMemoList> groupMemoList = new ArrayList<>();
			groupMemoList.add(groupMemoListService.get(Integer.parseInt(crId), mId));
			List<MemoCard> memoCardList = memoCardService.getList();
			
			list.add(gson.toJson(groupMemoList));
			list.add(gson.toJson(memoCardList));
			list.add(gson.toJson(mId));
			
			mList = list.toString();
		}

		return mList;
	}
	
	@ResponseBody
	@GetMapping("detail")
	public String detail(@RequestParam(name="cardId", defaultValue="0") int cardIdParam, 
			HttpServletResponse resp, HttpServletRequest req)
	{
		int cardId = 0;	
		cardId = cardIdParam;
		
		List<String> list = new ArrayList<String>();
		Gson gson = new Gson();
		
		String mList = "";
		
		MemoCard memoCard = memoCardService.getById(cardId);		
		List<CheckList> checkList = checkListService.getListByCardId(cardId);
		List<CheckListItem> checkListItemList  = checkListItemService.getList();
		DueDate duedate = dueDateService.getByCardId(cardId);
		
		list.add(gson.toJson(memoCard));
		list.add(gson.toJson(checkList));
		list.add(gson.toJson(checkListItemList));
		list.add(gson.toJson(duedate));
		
		
		Cookie cardIdCookie = new Cookie("cardId", String.valueOf(cardId));
		cardIdCookie.setPath("/");
		
		list.add(gson.toJson(cardIdCookie));
		
		mList = list.toString();
		
		return mList;
	}
	
}
