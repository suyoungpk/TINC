package com.tinc.web.controller;


import java.lang.reflect.Type;
import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tinc.web.dao.MemberDao;
import com.tinc.web.entity.CheckList;
import com.tinc.web.entity.CheckListItem;
import com.tinc.web.entity.DueDate;
import com.tinc.web.entity.FriendsShareView;
import com.tinc.web.entity.GroupMemoList;
import com.tinc.web.entity.GroupShareFullView;
import com.tinc.web.entity.GroupShareMemberView;
import com.tinc.web.entity.GroupShareView;
import com.tinc.web.entity.MemoCard;
import com.tinc.web.entity.PrivateMemoList;
import com.tinc.web.service.CheckListItemService;
import com.tinc.web.service.CheckListService;
import com.tinc.web.service.DueDateService;
import com.tinc.web.service.GroupMemoListService;
import com.tinc.web.service.MemberService;
import com.tinc.web.service.MemoCardService;
import com.tinc.web.service.MemoShareService;
import com.tinc.web.service.PrivateMemoListService;

@Controller
@RequestMapping("/memo/")
public class MemoController
{
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
	@Autowired
	private MemoShareService memoShareService;
	@Autowired
	private MemberService memberService;
	

	@GetMapping("list")
	public String list(Model model, Principal principal)
	{

		String mId = "";
		//mId = principal.getName();
		mId = "user2";

		List<PrivateMemoList> privateMemoList = PrivateMemoListService.getPrivateMemoList(mId);
		List<GroupMemoList> groupMemoList = groupMemoListService.getGroupMemoList(mId);
		List<MemoCard> memoCardList = memoCardService.getList();
		
		
		model.addAttribute("privateMemoList", privateMemoList);
		model.addAttribute("groupMemoList", groupMemoList);
		model.addAttribute("memoCardList", memoCardList);

		return "memo/list";
	}

	@PostMapping("list")
	public String list(@RequestBody String params, MemoCard memoCard)
	{
		Gson gson = new Gson();		
		Type paramMapType = new TypeToken<Map<String, String>>() {}.getType();
		Map<String, String> paramMap = gson.fromJson(params, paramMapType);
		
		
		if (paramMap.get("privateListId").equals("0") && !paramMap.get("groupListId").equals("0"))
		{
			memoCard.setGroupListId(Integer.parseInt(paramMap.get("groupListId")));
		}
		if (!paramMap.get("privateListId").equals("0") && paramMap.get("groupListId").equals("0"))
		{
			memoCard.setPrivateListId(Integer.parseInt(paramMap.get("privateListId")));
		}

		memoCard.setTitle(paramMap.get("title"));
		memoCard.setContent(paramMap.get("content"));
		System.out.println(memoCard.toString());

		int ret = -1;
		ret = memoCardService.insert(memoCard);
		if (ret <= 0)
		{
			System.out.println(MemoCard.class + " insert error");
		}

		return "redirect:list";
	}

	@GetMapping("detail")
	public String detail(Model model, @RequestParam(name="cardId", defaultValue="0") int cardIdParam, HttpServletResponse resp)
	{
		int cardId = 0;	
		cardId = cardIdParam;
		
		MemoCard memoCard = memoCardService.getById(cardId);		
		List<CheckList> checkList = checkListService.getListByCardId(cardId);
		List<CheckListItem> checkListItemList  = checkListItemService.getList();
		DueDate duedate = dueDateService.getByCardId(cardId);
		
		model.addAttribute("memoCard", memoCard);
		model.addAttribute("checkList", checkList);
		model.addAttribute("checkListItemList", checkListItemList);
		model.addAttribute("duedate", duedate);
		
		Cookie cardIdCookie = new Cookie("cardId", String.valueOf(cardId));
		cardIdCookie.setPath("/");
		resp.addCookie(cardIdCookie);
		
		return "memo/detail";
	}
	
	@ResponseBody
	@PostMapping("detail")
	public String detail(@RequestBody String detailDataParam)
	{
		Gson gson = new Gson();		
		Type paramMapType = new TypeToken<Map<String, String>>() {}.getType();
		Map<String, String> paramMap = gson.fromJson(detailDataParam, paramMapType);
		
		MemoCard memoCard = new MemoCard();
		if(!paramMap.get("privateListId").equals("") && paramMap.get("groupListId").equals(""))
		{
			memoCard.setPrivateListId(Integer.parseInt(paramMap.get("privateListId")));
		}
		if(paramMap.get("privateListId").equals("") && !paramMap.get("groupListId").equals(""))
		{
			memoCard.setPrivateListId(Integer.parseInt(paramMap.get("groupListId")));
		}
		memoCard.setId(Integer.parseInt(paramMap.get("id")));
		memoCard.setTitle(paramMap.get("title"));
		memoCard.setContent(paramMap.get("content"));

		int ret = memoCardService.update(memoCard);
		if(ret <= 0)
		{
			return "detail-update-error";
		}
		
		return "detail-update-success";
	}
	
	@ResponseBody
	@PostMapping("item-del")
	public String itemDel(@RequestBody String delItemParam)
	{
		System.out.println("del" + delItemParam);
		
		Gson gson = new Gson();
		Type paramMapType = new TypeToken<Map<String, String>>() {}.getType();
		Map<String, String> paramMap = gson.fromJson(delItemParam, paramMapType);
		int delItemId = Integer.parseInt(paramMap.get("id"));
		int ret = checkListItemService.delete(delItemId);
		if(ret <= 0)
		{
			System.out.println("item del error");
			return "item-del-error";
		}
		
		return "item-del-success";
	}
	
	@ResponseBody
	@PostMapping("new-item")
	public String newItem(@RequestBody String newItemParam)
	{
		System.out.println("add" + newItemParam);
		
		Gson gson = new Gson();
		Type paramMapType = new TypeToken<Map<String, String>>() {}.getType();
		Map<String, String> paramMap = gson.fromJson(newItemParam, paramMapType);
		
		CheckListItem checkListItem = new CheckListItem(paramMap.get("title"),
				false, Integer.parseInt(paramMap.get("checkListId")));
		
		int ret = checkListItemService.insert(checkListItem);
		if(ret <= 0)
		{
			System.out.println("item insert error");
			return "item-insert-error";
		}
		
		int newItemId = checkListItemService.getNewItemId(checkListItem.getCheckListId());
		checkListItem = checkListItemService.getById(newItemId);
		String newCheckListItemJson = gson.toJson(checkListItem);
		System.out.println(newCheckListItemJson.toString());
		
		return newCheckListItemJson;
	}
	
	@ResponseBody
	@PostMapping("update-checkstatus")
	public String updateItemCheckStatus(@RequestBody String checkedStatus)
	{
		Gson gson = new Gson();
		Type paramMapType = new TypeToken<Map<String, String>>() {}.getType();
		Map<String, String> paramMap = gson.fromJson(checkedStatus, paramMapType);
		
		// 체크상태와 아이템아이디로 분리
		int cliId = Integer.parseInt(paramMap.get("id"));
		boolean checkStatus = Boolean.parseBoolean(paramMap.get("checkStatus"));
		// 아이템아이디에 해당하는 데이터를 db에서 불러온 후
		CheckListItem checkListItem = checkListItemService.getById(cliId);
		// db에더 불러온 아이템 정보에서 체크 상태만 업데이트
		checkListItem.setCheckStatus(checkStatus);
		// 업데이트된 정보를 다시 cb에 저장
		System.out.println("checkStatus"+checkListItem.toString());
		int ret = checkListItemService.update(checkListItem);
		if(ret <= 0)
		{
			return "update-checkstatus error";
		}
		
		String updatedCheckListItem = gson.toJson(checkListItem);
		
		return updatedCheckListItem;
	}
	
	@ResponseBody
	@PostMapping("update-item-title")
	public String updateItemTitle(@RequestBody String itemTitleParam)
	{
		Gson gson = new Gson();
		Type paramMapType = new TypeToken<Map<String, String>>() {}.getType();
		Map<String, String> paramMap = gson.fromJson(itemTitleParam, paramMapType);
		System.out.println(itemTitleParam);
		
		CheckListItem checkListItem = checkListItemService.getById(Integer.parseInt(paramMap.get("id")));
		checkListItem.setTitle(paramMap.get("title"));
		int ret = checkListItemService.update(checkListItem);
		if(ret <= 0)
		{
			return "update-item-title error";
		}
		
		String updatedCheckListItem = gson.toJson(checkListItem);
				
		return updatedCheckListItem;
	}
	
	@ResponseBody
	@PostMapping("add-checklist")
	public String addNewCheckList(@RequestBody String checkListPram)
	{
		Gson gson = new Gson();
		Type paramMapType = new TypeToken<Map<String, String>>() {}.getType();
		Map<String, String> paramMap = gson.fromJson(checkListPram, paramMapType);
		System.out.println("add-checklist" + checkListPram);
		
		CheckList checkList = new CheckList(
				paramMap.get("title"), 
				Boolean.parseBoolean(paramMap.get("hideStatus")),
				Integer.parseInt(paramMap.get("cardId"))
				);
		int ret = checkListService.insert(checkList);
		if(ret <= 0)
		{
			return "add-checklist error";
		}
		
		checkList = checkListService.getNewCheckListByCardId(Integer.parseInt(paramMap.get("cardId")));
		String newCheckListData = gson.toJson(checkList);
		System.out.println(newCheckListData);
		return newCheckListData;
	}
	
	@ResponseBody
	@PostMapping("del-checklist")
	public String delCheckList(@RequestBody String clId)
	{
		System.out.println(clId);
		
		int ret = checkListService.delete(Integer.parseInt(clId));
		if(ret <= 0)
		{
			return "del-checklist error";
		}
		
		return "delete checklist success";
	}
	
	@ResponseBody
	@PostMapping("update-hide")
	public String updateHide(@RequestBody String hideStatusParam)
	{
		Gson gson = new Gson();
		Type paramMapType = new TypeToken<Map<String, String>>() {}.getType();
		Map<String, String> paramMap = gson.fromJson(hideStatusParam, paramMapType);
		
		int clId = Integer.parseInt(paramMap.get("id"));
		boolean hideStatus = Boolean.parseBoolean(paramMap.get("hideStatus"));
		
		CheckList checkList = checkListService.get(clId);
		checkList.setHideStatus(hideStatus);
		int ret = checkListService.update(checkList);
		if(ret <= 0)
		{
			return "update-hide eroor";
		}
		
		return "update-hide success";
	}
	
	@ResponseBody
	@PostMapping("update-duedate")
	public String updateDueDate(@RequestBody String duedateParam)
	{
		Gson gson = new Gson();
		Type paramMapType = new TypeToken<Map<String, String>>() {}.getType();
		Map<String, String> paramMap = gson.fromJson(duedateParam, paramMapType);
		System.out.println("update-duedate" + paramMap);
		
				
		DueDate duedate = null;
		duedate = dueDateService.getByCardId(Integer.parseInt(paramMap.get("cardId")));

		// duedate 데이터를 새로 만들경우 insert
		if(duedate == null)
		{
			duedate = new DueDate(
					paramMap.get("date"), 
					paramMap.get("time"),
					Boolean.parseBoolean(paramMap.get("isComplete")),
					Integer.parseInt(paramMap.get("cardId"))
					);
			
			int ret = dueDateService.insert(duedate);
			if(ret <= 0)
			{
				return "insert-duedate error";
			}
		}
		else // 이미 존재할 경우 update
		{			
			duedate.setDate(paramMap.get("date"));
			duedate.setTime(paramMap.get("time"));
			
			int ret = dueDateService.update(duedate);
			if(ret <= 0)
			{
				return "update-duedate error";
			}
		}
		
		duedate = dueDateService.getByCardId(Integer.parseInt(paramMap.get("cardId")));
		String updatedDueDateJson = gson.toJson(duedate);

		return updatedDueDateJson;
	}
	
	@ResponseBody
	@PostMapping("del-duedate")
	public String deleteDueDate(@RequestBody String delDeuDateParam)
	{
		Gson gson = new Gson();
		Type paramMapType = new TypeToken<Map<String, String>>() {}.getType();
		Map<String, String> paramMap = gson.fromJson(delDeuDateParam, paramMapType);
		System.out.println("del-duedate" + paramMap);
		
		int ret = dueDateService.deleteByCardId(Integer.parseInt(paramMap.get("cardId")));
		if(ret <= 0)
		{
			return "del-duedate error";
		}
		
		return "del-duedate success";
	}
	
	@ResponseBody
	@PostMapping("update-duedate-complete")
	public String updateComplete(@RequestBody String completeParam)
	{
		Gson gson = new Gson();
		Type paramMapType = new TypeToken<Map<String, String>>() {}.getType();
		Map<String, String> paramMap = gson.fromJson(completeParam, paramMapType);
		
		
		DueDate duedate = dueDateService.getByCardId(Integer.parseInt(paramMap.get("cardId")));
		duedate.setIsComplete(Boolean.parseBoolean(paramMap.get("isComplete")));
		int ret = dueDateService.update(duedate);
		if(ret <= 0)
		{
			return "update-complete-status error";
		}
		
		return "update-complete-status success";
	}
	
	@GetMapping("share")
	public String share(@RequestParam String mcId, Model model, Principal principal)
	{
		String mId = "";
		mId = "user2";
		//mId = principal.getName();
		
		List<GroupShareFullView> gsfViewList = memoShareService.getGroupShareFullViewList(mId);
		List<GroupShareMemberView> gsmViewList = memoShareService.getGroupShareMemberViewList();
		model.addAttribute("mcId", mcId);
		model.addAttribute("gsfViewList", gsfViewList);
		model.addAttribute("gsmViewList", gsmViewList);
		
		return "memo/share";
	}
	
	@ResponseBody
	@PostMapping("share")
	public String share(@RequestBody String shareParam, Principal principal)
	{
		String mId = "";
		mId = "user2";
		//mId = principal.getName();
		
		Gson gson = new Gson();
		Type paramMapType = new TypeToken<Map<String, Object>>() {}.getType();
		Map<String, Object> paramMap = gson.fromJson(shareParam, paramMapType);
		System.out.println(paramMap);
	
		Integer mcId = Integer.parseInt((String) paramMap.get("mcId"));
		
		
		List<String> gsIdList = (List<String>) paramMap.get("gsIdList"); 
		if(!gsIdList.isEmpty())
		{
			for(String gsId : gsIdList)
			{
				System.out.println(mId + "," + gsId);
				memoShareService.shareMemoToChattingRoom(mId, Integer.parseInt(gsId), mcId);
			}
		}
		
		return "memo-share success";
	}
	
	@ResponseBody
	@PostMapping("show-private-share")
	public String showPrivateShare(@RequestParam String mdId, Principal principal)
	{
		String mId = "";
		mId = "user2";
		//mId = principal.getName();
		
		Gson gson = new Gson();
		Type paramMapType = new TypeToken<Map<String, String>>() {}.getType();
		Map<String, String> paramMap = gson.fromJson(mdId, paramMapType);
		
		List<FriendsShareView> fsViewList = memoShareService.getFriendsShareViewList(mId);
		String fsList = gson.toJson(fsViewList);
		
		return fsList;
	}
}
