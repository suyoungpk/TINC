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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="../../../resource/js/memo/memo-list.js"></script>
<link rel="stylesheet" href="../../../resource/css/common.css" />
<link rel="stylesheet" href="../../../resource/css/memo/memo-list.css" />

</head>

<body>
	<section class="wrapper">
		<main class="memo-list-container">
			<c:forEach var="pml" items="${privateMemoList }">
			<div class="memo-list-wrapper">
				<div class="memo-list-title">
					<div></div>
					<input readonly type="text" name="memo-list-title" value="${pml.title }">
					<input type="hidden" id="private-memo-list-id" name="private-memo-list-id" value="${pml.id }">
				</div>
				<div class="memo-card-list-wrapper">
					<c:forEach var="mc" items="${memoCardList}">
					<c:if test="${mc.privateListId == pml.id && not empty mc.privateListId }">
					<div class="memo-card">
						<div class="memo-card-title">
							<input readonly type="text" name="memo-card-title" value="${mc.title }">
							<input type="hidden" name="memo-card-id" value="${mc.id }">
						</div>
						<div class="memo-card-content">
							<textarea readonly class="memo-card-content-textarea" name="memo-card-content-textarea">${mc.content}</textarea>
						</div>
					</div>
					</c:if>
					</c:forEach>					
				</div>
				<div class="memo-list-add-wrapper">
					<div>
						<i class="fas fa-plus"></i><input type="button" name="memo-list-add-button" value="add">
					</div>
				</div>
			</div>
			</c:forEach>
			<c:forEach var="gml" items="${groupMemoList }">
			<div class="memo-list-wrapper">				
				<div class="memo-list-title">
					<div></div>
					<input readonly type="text" name="memo-list-title" value="${gml.title }">
					<input type="hidden" id="group-memo-list-id" name="group-memo-list-id" value="${gml.id }">
				</div>
				<div class="memo-card-list-wrapper">
					<c:forEach var="mc" items="${memoCardList}">
					<c:if test="${mc.groupListId == gml.id && not empty mc.groupListId }">
					<div class="memo-card">
						<div class="memo-card-title">
							<input readonly type="text" name="memo-card-title" value="${mc.title }">
							<input type="hidden" name="memo-card-id" value="${mc.id }">
						</div>
						<div class="memo-card-content">
							<textarea readonly class="memo-card-content-textarea" name="memo-card-content-textarea">${mc.content}</textarea>
						</div>
					</div>
					</c:if>
					</c:forEach>					
				</div>
				<div class="memo-list-add-wrapper">
					<div>
						<i class="fas fa-plus"></i><input type="button" name="memo-list-add-button" value="add">
					</div>
				</div>				
			</div>
			</c:forEach>
			 <template id="memo-card-template">
                <div class="memo-card">
                    <div class="memo-card-title">
                        <input type="text" name="memo-card-title" value="">
						<input type="hidden" name="memo-card-id" value="">
                    </div>
                    <div class="memo-card-content">
                        <textarea readonly class="memo-card-content-textarea" name="memo-card-content-textarea"></textarea>
                    </div>
                </div>
            </template>

		</main>
		<!-- container end -->
		<div class="memo-list-bottom-wrapper">
			<input type="button" name="memo-list" value="닫기">
		</div>
	</section>
	<!-- wrapper end -->
</body>

</html>