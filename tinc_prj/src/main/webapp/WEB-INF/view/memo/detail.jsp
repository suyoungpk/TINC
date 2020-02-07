<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="ko">

<head>
<title>메모와 채팅을 동시에, TINC</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="../../../resource/css/common.css" />
<link rel="stylesheet" href="../../../resource/css/memo/memo-detail.css" />
<link rel="stylesheet" href="../../../resource/css/memo/memo-checklist.css" />
<link rel="stylesheet" href="../../../resource/css/memo/memo-duedate.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="../../../resource/js/memo/memo-duedate.js"></script>
<script src="../../../resource/js/memo/memo-detail.js"></script>
<script src="../../../resource/js/memo/memo-checklist.js"></script>
<script src="../../../resource/js/memo/CookieUtil.js"></script>
</head>

<body>
	<section class="wrapper">
		<div class="memo-detail-menu">
			<div class="close-button-wrapper">
				<i id="memo-detail-close-icon" class="fas fa-times"></i>
			</div>
		</div>
		<div class="memo-detail-title">
			<input class="memo-detail-title-text" name="memo-detail-title-text" type="text" value="${memoCard.title }">
			<input type="hidden" name="memo-detail-id" value="${memoCard.id }">
			<input type="hidden" name="memo-detail-plId" value="${memoCard.privateListId }">
			<input type="hidden" name="memo-detail-glId" value="${memoCard.groupListId }">
		</div>
		<div class="memo-detail-content">
			<textarea class="memo-detail-content-textarea" name="memo-detail-content-textarea">${memoCard.content }</textarea>
			<div class="memo-checklist-wrapper">
				<template id="checklist-wrapper-template">
					<input type="hidden" name="memo-checklist-id" value="">	
					<ul class="memo-checklist">
						<li class="memo-checklist-title">
							<div>
								<i class="far fa-check-square"></i><span><input type="text" name="memo-checklist-title" value="checklist" style="width:80% !important; color:#ffffff;" maxlength="10"/></span>
							</div>
							<div>
								<i class="fas fa-ellipsis-h"></i><i class="fas fa-chevron-up"></i>
								<input type="hidden" name="checklist-hidestatus" value="">
							</div>
						</li>
						<li class="memo-checklist-item-list-wrapper">
							<ul class="memo-checklist-item-list">														
								<li class="checklist-item-add-wrapper">
									<label> <i class="fas fa-plus"></i></label> 
									<label class="checklist-item-add"><span>add</span></label>
								</li>
							</ul>
						</li>
					</ul>
				</template>
				<template id="checklist-item-template">
					<li>
						<input type="checkbox" id="checklist-item-checkbox" name="checklist-item-checkbox" value=""> 
						<label for="checklist-item-checkbox"> 
							<i class="far fa-square"></i>
						</label>
						<input type="text" name="checklist-item-content" value="내용입력" maxlength="16">
						<button class="memo-checklist-item-del fas fa-trash"></button>
						<input type="hidden" name="checklist-item-id" value="">
					</li>					
				</template>
				<c:forEach var="cl" items="${checkList }">
				<c:if test="${cl.cardId == memoCard.id }">
				<input type="hidden" name="memo-checklist-id" value="${cl.id}">				
				<ul class="memo-checklist">
					<li class="memo-checklist-title">
						<div>
							<i class="far fa-check-square"></i><span><input type="text" name="memo-checklist-title" value="${cl.title }" style="width:80%; color:#ffffff;" maxlength="10"/></span>							
						</div>
						<div>
							<i class="fas fa-ellipsis-h"></i><i class="fas fa-chevron-up"></i>
							<input type="hidden" name="checklist-hidestatus" value="${cl.hideStatus }">
						</div>
					</li>
					<li class="memo-checklist-item-list-wrapper">												
						<ul class="memo-checklist-item-list">
							<c:forEach var="cli" items="${checkListItemList }">
							<c:if test="${cli.checkListId == cl.id }">
							<li>
								<input type="checkbox" id="checklist-item-checkbox" name="checklist-item-checkbox" value="${cli.checkStatus }"> 
								<label for="checklist-item-checkbox">
									<c:if test="${cli.checkStatus == true }"> 
									<i class="far fa-check-square"></i>
									</c:if>
									<c:if test="${cli.checkStatus == false }">
									<i class="far fa-square"></i>
									</c:if>
								</label> 
								<input type="text" name="checklist-item-content" value="${cli.title }" maxlength="16">
								<button class="memo-checklist-item-del fas fa-trash"></button>
								<input type="hidden" name="checklist-item-id" value="${cli.id}">
							</li>																					
							</c:if>						
							</c:forEach>
							<li class="checklist-item-add-wrapper">
								<label> <i class="fas fa-plus"></i></label> 
								<label class="checklist-item-add"><span>add</span></label>
							</li>
						</ul>						
					</li>
				</ul>	
				</c:if>			
				</c:forEach>
			</div>
		</div>
		<div class="memo-detail-share">
			<input type="button" class="memo-detail-share-button" name="memo-share-button" value="공유하기">
		</div>
		<div class="memo-detail-checklist">
			<input type="button" class="memo-detail-checklist-button" name="memo-checklist-button"
				value="체크리스트"
			>
		</div>
		<div class="memo-detail-duedate">
			<input type="hidden" name="duedate-complete" value="${duedate.isComplete}" />
			<input type="button" class="memo-detail-duedate-button" name="memo-duedate-button"
				value="DueDate"/>							
			<span style="display:none">&nbsp;<i class="far fa-square"></i>&nbsp;&nbsp;${duedate.date }&nbsp;&nbsp;${duedate.time }&nbsp;</span>
		</div>
		<div class="memo-detail-delete">
			<input type="button" class="memo-detail-delete-button" name="memo-del-button" value="삭제하기">
		</div>

	</section>
	<!-- wrapper end -->

	<div class="popup" style="display: none">
		<div class="memo-delete-popup popup-wrap"
			style="width: 290px; left: 50%; transform: translate(-50%, 0); display: none;">
			<div class="popup-container">
				<div class="context">
					<p>삭제하시겠습니까?</p>
				</div>
				<!-- context -->
				<div class="btn-area">
					<a href="#" class="cancel-btn btn">취소</a> <a href="#" class="ok-btn btn">확인</a>
				</div>
				<a href="#" class="btn-close fas fa-times">닫기</a>
			</div>
			<!-- popup-container -->
		</div>
		<!-- popup-wrap -->

		<div class="memo-duedate-popup popup-wrap" style="display: none;">
			<div class="popup-container">
				<div class="context">
					<p>Due Date</p>
				</div>
				<!-- context -->
				<div class="memo-duedate-container">
					<!-- days -->
					<div></div>
					<!-- Months -->
					<div></div>
					<!-- Years -->
					<div></div>
					<!-- Hours -->
					<div></div>
					<!-- Mins -->
					<div></div>
				</div>
				<div class="memo-duedate-btn-area btn-area">
					<a href="#" class="cancel-btn btn">삭제</a> <a href="#" class="ok-btn btn">확인</a>
				</div>
				<a href="#" class="duedate-close btn-close fas fa-times">닫기</a>
			</div>
			<!-- popup-container -->
		</div>
		<!-- popup-wrap -->

		<div class="memo-checklist-setting-popup popup-wrap" style="display: none;">
			<div class="popup-container">
				<div class="context">
					<p>체크리스트 설정</p>
				</div>
				<!-- context -->
				<div class="memo-checklist-setting-btn-area btn-area">
					<a href="#" class="hide-btn btn">
						<i class="far fa-square"></i>&nbsp;완료시 숨김
					</a>
					<a href="#" class="del-btn btn">삭제</a>
				</div>
				<a href="#" class="btn-close fas fa-times">닫기</a>
			</div>
			<!-- popup-container -->
		</div>
		<!-- popup-wrap -->

		<div class="share-popup popup-wrap" style="display: none; min-height:5rem">
			<div class="popup-container" style="top:15px;">
				<div class="context" style="margin:auto 0;">
					<p>메모 공유가 완료되었습니다</p>
				</div>				
			</div>
			<!-- popup-container -->
		</div>
	</div>
	<!-- popup -->

	<div class="mask" style="display: none; position: fixed;"></div>
	</body>
</html>