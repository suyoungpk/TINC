window.addEventListener("load", function () {
	assignCheckListItemId();
	/* 포커싱된 아이템 삭제 버튼 보이기 */
	showCheckListItemDelBtn();
	/* 체크리스트 아이템 추가 */
	addCheckListItem();
	/* 체크리스트 슬라이드 효과 추가 */
	checkListSlideHandler();
	/* 체크리스트 추가 */
	addCheckList();
	/* 아이템 체크 박스 해들러 */
	checkCompleteCheckListItem();
	/* 체크리스트 설정 창 띄우기 */
	showCheckListSettingPopup();
	/* 체크리스트 아이템 삭제 */
	deleteCheckListItem();
	/* 체크리스트 아이템 내용 변경시 저장 */
	updateCheckListItemTitle();
	/* 초기에 아이템 숨기기 기능 활성화된 체크리스트의 아이템들 숨기기 */
	hideCompletedItemOnLoad();
});

function updateCheckListItemTitle() {
	$('input[name="checklist-item-content"]')
		.off("change")
		.change(function (e) {
			e.preventDefault();
			e.stopPropagation();

			let updatedItemId = $(e.target)
				.next()
				.next()
				.val();
			let updatedItemTitle = $(e.target).val();
			let updatedItemData = JSON.stringify({
				id: updatedItemId,
				title: updatedItemTitle
			});

			let request = new XMLHttpRequest();
			request.open("POST", "../../../../memo/update-item-title");
			request.setRequestHeader("Content-Type", "application/json");
			request.onload = function () {
				let returnedItemData = JSON.parse(request.responseText);
				$(e.target).val(returnedItemData.title);
				console.log("update item title done");
			};
			request.send(updatedItemData);
		});
}

// 체크리스트 아이템 삭제
function deleteCheckListItem() {
	$("button.memo-checklist-item-del")
		.off("click")
		.click(function (e) {
			console.log("del");
			e.preventDefault();
			e.stopPropagation();

			let deletedItem = e.target;
			let delCheckListItemId = $(deletedItem.nextElementSibling).val();
			console.log(deletedItem.nextElementSibling);
			let delItemData = JSON.stringify({
				id: delCheckListItemId
			});

			let request = new XMLHttpRequest();
			request.open("POST", "../../../../memo/item-del");
			request.setRequestHeader("Content-Type", "application/json");
			request.onload = function () {
				deletedItem.parentNode.remove();
				console.log("item delete done");
			};
			request.send(delItemData);
		});
}

/* 체크리스트 아이템의 체크박스에 새로운 id 부여하고 label을 해당 체크박스에 연결하는 작업*/
function assignCheckListItemId() {
	var checkListItemList = document.querySelectorAll(
		".memo-checklist-item-list > li:first-child"
	);

	for (var i = 0; i < checkListItemList.length; i++) {
		if (checkListItemList[i].children[0].id === "checklist-item-checkbox") {
			checkListItemList[i].children[0].id = "checklist-item-checkbox" + i;
			checkListItemList[i].children[1].setAttribute(
				"for",
				checkListItemList[i].children[0].id
			);
		}
	}
}

/* 메모 체크리스트 아이템 클릭시 삭제 아이콘 보이기 */
function showCheckListItemDelBtn() {
	var checkListItem = document.querySelectorAll(
		'.memo-checklist-item-list > li input[type="text"]'
	);

	for (let i = 0; i < checkListItem.length; i++) {
		$(checkListItem[i]).hover(function (e) {
			if (e.type === "mouseenter") {
				//console.log("mouseenter");
				$(e.target.nextElementSibling).fadeIn();
				return;
			}

			if (e.type === "mouseleave") {
				//console.log("mouseleave");
				$(e.target.nextElementSibling).fadeOut();
				return;
			}
		});
	}
}

/* 체크리스트 아이템 추가 */
function addCheckListItem() {
	let checkListItemTemplate = document.querySelector(
		"#checklist-item-template"
	);
	let checkListAddItemBtn = document.querySelectorAll(
		".checklist-item-add-wrapper"
	);

	for (let i = 0; i < checkListAddItemBtn.length; i++) {
		checkListAddItemBtn[i].onclick = function (e) {
			e.preventDefault();
			e.stopPropagation();

			let clonedCheckListItem = document.importNode(
				checkListItemTemplate.content,
				true
			);
			checkListAddItemBtn[i].parentNode.insertBefore(
				clonedCheckListItem,
				checkListAddItemBtn[i]
			);

			assignCheckListItemId();
			showCheckListItemDelBtn();
			deleteCheckListItem();

			let checkListItem = checkListAddItemBtn[i].parentNode.querySelectorAll("li");
			let newCheckListItem = checkListItem[checkListItem.length - 2];

			// 새로 만들어진 아이템의 타이틀 부분에 포커스 주기
			let newCheckListItemTitle = newCheckListItem.children[2];
			console.log(newCheckListItemTitle);
			newCheckListItemTitle.focus(e => {
				e.preventDefault();
				e.stopPropagation();
			});
			// 포커스를 잃으면 새로 생긴 아이템 데이터를 서버로 전송
			newCheckListItemTitle.onblur = function (e) {
				e.preventDefault();
				e.stopPropagation();

				//let cliId = newCheckListItem.querySelector("input[name=\"checklist-item-id\"]").value;
				let clId =
					newCheckListItem.parentNode.parentNode.parentNode
						.previousElementSibling.value;
				//let cliCheckStatus = newCheckListItem.querySelector("input[name=\"checklist-item-checkbox\"]").value;
				let cliTitle = newCheckListItem.querySelector(
					'input[name="checklist-item-content"]'
				).value;

				let newItemData = JSON.stringify({
					id: 0,
					title: cliTitle,
					checkStatus: false,
					checkListId: clId
				});

				let request = new XMLHttpRequest();
				request.open("POST", "../../../../memo/new-item");
				request.setRequestHeader("Content-Type", "application/json");
				request.onload = function () {
					checkCompleteCheckListItem();
					let newItem = JSON.parse(request.responseText);
					newCheckListItem.querySelector(
						'input[name="checklist-item-id"]'
					).value = newItem.id;
					console.log("item add done");
				};
				request.send(newItemData);
			};
		};
	}
}

// 체크리스트 추가
function addCheckList() {
	let checkListButton = document.querySelector(
		".memo-detail-checklist-button"
	);

	checkListButton.onclick = function (e) {
		e.preventDefault();
		e.stopPropagation();

		let checkListWrapperTemplate = document.querySelector(
			"#checklist-wrapper-template"
		);
		let clonedCheckListWrapper = document.importNode(
			checkListWrapperTemplate.content,
			true
		);
		let checkListWrapper = document.querySelector(
			".memo-checklist-wrapper"
		);
		checkListWrapper.append(clonedCheckListWrapper);

		// 새로 만들어진 체크리스트의 데이터를 서버로 전송 후 cb에 등록
		// db에 등록된 새로운 체크리스트의 정보를 클라이언트로 불러와서
		// 그 정보들을  새로운 체크리스트에 삽입해준다
		// 정송 시 필요한 정보 cardId
		let checkListElems = checkListWrapper.querySelectorAll(
			"ul.memo-checklist"
		);
		let newCheckListElem = checkListElems[checkListElems.length - 1];
		let cardId = document.querySelector('input[name="memo-detail-id"]')
			.value;
		let newCheckListTitle = newCheckListElem.querySelector(
			'li.memo-checklist-title > div:nth-child(1) > span > input[type="text"]'
		);
		//console.log(newCheckListTitle);
		$(newCheckListTitle).focus();

		$(newCheckListTitle).blur(() => {
			let newCheckListData = JSON.stringify({
				id: 0,
				title: newCheckListTitle.value,
				hideStatus: false,
				cardId: cardId
			});

			let request = new XMLHttpRequest();
			request.open("POSt", "../../../../memo/add-checklist");
			request.setRequestHeader("Content-Type", "application/json");
			request.onload = function () {
				let responseData = JSON.parse(request.responseText);
				//console.log(responseData);
				newCheckListTitle.value = responseData.title;
				newCheckListElem.previousElementSibling.value = responseData.id;
				newCheckListElem.firstElementChild.lastElementChild.lastElementChild.value =
					responseData.hideStatus;

				assignCheckListItemId();
				showCheckListItemDelBtn();
				/* 체크리스트 아이템 추가 */
				addCheckListItem();
				/* 체크리스트 슬라이드 효과 추가 */
				checkListSlideHandler();
				/* 아이템 완료 체크 박스 해들러 */
				checkCompleteCheckListItem();
				/* 체크리스트 설정 창 띄우기 */
				showCheckListSettingPopup();
				/* 체크리스트 아이템 삭제 */
				deleteCheckListItem();
				/* 체크리스트 아이템 내용 변경시 저장 */
				updateCheckListItemTitle();
			};
			request.send(newCheckListData);
		});
	};
}

/* memo checklist slide effect */
function checkListSlideHandler() {
	$(".memo-checklist-title > div > i.fas:nth-child(2)").off("click");

	$(".memo-checklist-title > div > i.fas:nth-child(2)").click(e => {
		//console.log("clicked");
		$(e.target)
			.parent()
			.parent()
			.parent()
			.children()
			.eq(1)
			.slideToggle();

		if (e.target.classList[1] === "fa-chevron-up") {
			$(e.target)
				.parent()
				.parent()
				.parent()
				.children()
				.eq(1)
				.slideUp();
			e.target.className = "fas fa-chevron-down";
			$(e.target)
				.parent()
				.parent()
				.parent()
				.css("border-bottom", "none");

			return;
		}

		if (e.target.classList[1] === "fa-chevron-down") {
			$(e.target)
				.parent()
				.parent()
				.parent()
				.children()
				.eq(1)
				.slideDown();
			e.target.className = "fas fa-chevron-up";
			$(e.target)
				.parent()
				.parent()
				.parent()
				.css("border-bottom", "1px solid rgb(139, 139, 139)");

			return;
		}
	});
}

// 체크리스트 아이템 완료체크
function checkCompleteCheckListItem() {
	let checkListItemCompleteLabel = document.querySelectorAll(
		".memo-checklist-item-list > li > label"
	);

	for (let i = 0; i < checkListItemCompleteLabel.length; i++) {
		checkListItemCompleteLabel[i].onclick = function (e) {
			//console.log(e.target);

			let checkListItemId = $(e.target)
				.parent()
				.next()
				.next()
				.next()
				.val();

			if (e.target.classList[1] === "fa-check-square") {
				e.target.className = "far fa-square";
				updateCompleteCheckStatus(false, checkListItemId);

				return;
			}

			if (e.target.classList[1] === "fa-square") {
				e.target.className = "far fa-check-square";
				updateCompleteCheckStatus(true, checkListItemId);

				return;
			}
		};
	}
}

// 체크리스트아이템 완료여부를 서버로 전송
function updateCompleteCheckStatus(isChecked, cliId) {
	let request = new XMLHttpRequest();
	request.open("POST", "../../../../memo/update-checkstatus");
	request.setRequestHeader("Content-Type", "application/json");

	let checkStatus = JSON.stringify({
		id: cliId,
		checkStatus: isChecked
	});

	request.onload = function () {
		console.log(request.responseText);
	};

	request.send(checkStatus);
}

function hideCompletedItemOnLoad() {
	let hideStatusElems = document.querySelectorAll(
		'input[name="checklist-hidestatus"]'
	);

	for (let i = 0; i < hideStatusElems.length; i++) {
		let checkListItems =
			hideStatusElems[i].parentNode.parentNode.nextElementSibling
				.firstElementChild.children;

		for (let j = 0; j < checkListItems.length - 1; j++) {
			if (
				checkListItems[j].children[1].firstElementChild.classList[1] ===
				"fa-check-square"
			) {
				if (hideStatusElems[i].value === "true") {
					$(checkListItems[j]).fadeOut();
				} else if (hideStatusElems[i].value === "false") {
					$(checkListItems[j]).fadeIn();
				}
			}
		}
	}
}

// 완료된 체크리스트 아이템 숨기기
function hideCompletedItem(target) {
	let checkListHideStatus = $(target)
		.next()
		.next()
		.val();
	let checkListId = $(target)
		.parent()
		.parent()
		.parent()
		.prev()
		.val();

	return new Promise(function (resolve, reject) {
		console.log(checkListHideStatus + "," + checkListId);
		if (checkListHideStatus === "true") {
			$(".memo-checklist-setting-btn-area > a:first-child i").removeClass(
				"fa-square"
			);
			$(".memo-checklist-setting-btn-area > a:first-child i").addClass(
				"fa-check-square"
			);
		}
		if (checkListHideStatus === "false") {
			$(".memo-checklist-setting-btn-area > a:first-child i").removeClass(
				"fa-check-square"
			);
			$(".memo-checklist-setting-btn-area > a:first-child i").addClass(
				"fa-square"
			);
		}

		$(".popup").fadeIn();
		$(".mask").fadeIn();
		$(".memo-checklist-setting-popup").fadeIn();

		$(".memo-checklist-setting-btn-area > a:first-child")
			.off("click")
			.on("click", function (e) {
				e.preventDefault();

				if (checkListHideStatus === "false") {
					$(
						".memo-checklist-setting-btn-area > a:first-child i"
					).removeClass("fa-square");
					$(
						".memo-checklist-setting-btn-area > a:first-child i"
					).addClass("fa-check-square");
				} else if (checkListHideStatus === "true") {
					$(
						".memo-checklist-setting-btn-area > a:first-child i"
					).removeClass("fa-check-square");
					$(
						".memo-checklist-setting-btn-area > a:first-child i"
					).addClass("fa-square");
				}

				let checkListItems =
					target.parentNode.parentNode.nextElementSibling
						.firstElementChild.children;

				for (let i = 0; i < checkListItems.length - 1; i++) {
					if (
						checkListItems[i].children[1].firstElementChild
							.classList[1] === "fa-check-square"
					) {
						if (checkListHideStatus === "false") {
							$(checkListItems[i]).fadeOut();
						} else if (checkListHideStatus === "true") {
							$(checkListItems[i]).fadeIn();
						}
					}
				}

				let sendHideStatus;
				if (checkListHideStatus === "false") {
					sendHideStatus = "true";
				}
				if (checkListHideStatus === "true") {
					sendHideStatus = "false";
				}

				let checkListHideData = JSON.stringify({
					id: checkListId,
					hideStatus: sendHideStatus
				});

				let request = new XMLHttpRequest();
				request.open("POST", "../../../../memo/update-hide");
				request.setRequestHeader("Content-Type", "application/json");
				request.onload = function (e) {
					console.log(request.responseText);
				};
				request.send(checkListHideData);

				resolve(checkListHideStatus);
			});
	});
}

/* 체크리스트 세팅 팝업창 보이기 */
function showCheckListSettingPopup() {
	let checkListSettingElem = document.querySelectorAll(
		".memo-checklist-title > div:nth-child(2) > i:first-child"
	);

	for (let i = 0; i < checkListSettingElem.length; i++) {
		checkListSettingElem[i].onclick = function (e) {
			// 체크리스트 삭제
			deleteCheckList(e.target).then(function (resp) {
				//console.log("resp:" + resp);
				if (resp === true) {
					$(".popup").fadeOut();
					$(".mask").fadeOut();
					$(".memo-checklist-setting-popup").fadeOut();
					//window.location.reload();
				}
			});

			// 완료된 아이템 숨기기
			hideCompletedItem(e.target).then(function (resp) {
				let hideStatusVal;
				if (resp === "true") {
					hideStatusVal = false;
				}
				if (resp === "false") {
					hideStatusVal = true;
				}

				e.target.nextElementSibling.nextElementSibling.value = hideStatusVal;

				$(".popup").fadeOut();
				$(".mask").fadeOut();
				$(".memo-checklist-setting-popup").fadeOut();
			});

			$(".memo-checklist-setting-btn-area btn-area > a:last-child")
				.off("click")
				.on("click", function (e) {
					$(".popup").fadeOut();
					$(".mask").fadeOut();
					$(".memo-checklist-setting-popup").fadeOut();
				});
		};
	}
}

function deleteCheckList(target) {
	$(".popup").fadeIn();
	$(".mask").fadeIn();
	$(".memo-checklist-setting-popup").fadeIn();

	return new Promise(function (resolve, reject) {
		$(".memo-checklist-setting-btn-area > a:last-child")
			.off("click")
			.click(e => {
				e.preventDefault();

				let clId = $(target).parent().parent().parent()
					.prev().val();
				console.log(clId);

				let request = new XMLHttpRequest();
				request.open("POST", "../../../../memo/del-checklist");
				request.setRequestHeader("Content-type", "text/plain");
				request.onload = function () {
					console.log(request.responseText);
					if (request.responseText === "delete checklist success") {
						$(target).parent().parent().parent().prev()
							.remove();
						$(target).parent().parent().parent()
							.remove();
						resolve(true);
					}
					else {
						resolve(false);
					}
				};
				request.send(clId);
			});
	});
}
