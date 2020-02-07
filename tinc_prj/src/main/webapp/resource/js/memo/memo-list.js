var isTitleChanged = false;

window.addEventListener("load", function () {
	addMemoCard();
	showMemoCard();
})


function showMemoCard() {
	let pid = setInterval(function () {
		$(".memo-card .memo-card-content .memo-card-content-textarea")
			.off("click").click(function (e) {
				e.preventDefault();
				e.stopPropagation();


				if (!isTitleChanged) {
					let cardId = e.target.parentNode.previousElementSibling.children[1].value;
					let url = "/memo/detail?cardId=" + cardId;
					let oldUrl = window.location.pathname + window.location.search;

					$.get(url, function (data) {
						//console.log(data);
						let newDoc = document.open(oldUrl, "replace");
						newDoc.write(data);
						newDoc.close();
						history.pushState(null, null, url);
					});
				}
			});
	}, 10);
}



function addMemoCard() {
	$(".memo-list-add-wrapper div input").off("click").click(function (e) {
		e.stopPropagation();
		let memoCardTemplate = document.querySelector("#memo-card-template");
		let memoCardListWrapperElem = e.target.parentNode.parentNode.previousElementSibling;
		let clonedMemoCard = document.importNode(memoCardTemplate.content, true);

		memoCardListWrapperElem.append(clonedMemoCard);
		inputMemoCardTitle(memoCardListWrapperElem);
	});
}


function inputMemoCardTitle(Elem) {
	let privateMemoCardId;
	let groupMemoCardId;
	let MemoCards = Elem.querySelectorAll(".memo-card");
	let newMemoCard = MemoCards[MemoCards.length - 1];
	let newMemoCardTitle = newMemoCard.querySelector(".memo-card-title input[name=\"memo-card-title\"]");
	let newMomoCardContent = newMemoCard.querySelector(".memo-card-content .memo-card-content-textarea");

	$(Elem).animate({
		scrollTop: $(newMemoCard).offset().top
	}, 500);

	$(newMemoCardTitle).focus();
	let isTitleAvailable = new Promise(function (resolve, reject) {

		$(newMemoCardTitle).blur(function () {
			console.log(newMemoCardTitle);
			if (newMemoCardTitle.value != "") {
				addReadOnlyAttrToTitle();
				isTitleChanged = true;
				resolve(true);
			} else {
				resolve(false);
			}
		});
	});

	isTitleAvailable.then((result) => {
		if (result === true) {
			createNewMemoCard(Elem);
		} else {
			newMemoCard.remove();
		}
	})
}

function createNewMemoCard(Elem) {
	let privateMemoCardId;
	let groupMemoCardId;
	let MemoCards = Elem.querySelectorAll(".memo-card");
	let newMemoCard = MemoCards[MemoCards.length - 1];
	let newMemoCardTitle = newMemoCard.querySelector(".memo-card-title input[name=\"memo-card-title\"]");
	let newMomoCardContent = newMemoCard.querySelector(".memo-card-content .memo-card-content-textarea");

	let newMemoCardData;

	if (Elem.previousElementSibling.querySelector("input#private-memo-list-id") != null) {
		privateMemoCardId = Elem.previousElementSibling.querySelector("input#private-memo-list-id");
		newMemoCardData = JSON.stringify({
			id: 0,
			privateListId: privateMemoCardId.value,
			groupListId: 0,
			title: newMemoCardTitle.value,
			content: newMomoCardContent.value
		});
	}

	if (Elem.previousElementSibling.querySelector("input#group-memo-list-id") != null) {
		groupMemoCardId = Elem.previousElementSibling.querySelector("input#group-memo-list-id");
		newMemoCardData = JSON.stringify({
			id: 0,
			privateListId: 0,
			groupListId: groupMemoCardId.value,
			title: newMemoCardTitle.value,
			content: newMomoCardContent.value
		});
	}

	let request = new XMLHttpRequest();

	request.open("POST", "../../../../memo/list", true);
	request.setRequestHeader('Content-Type', 'application/json');

	request.onload = function () { // 요청완료 후 실행됨

		let receivedData = JSON.parse(request.responseText);
		//console.log(receivedData);
		$("input[name=\"memo-card-id\"]").val(receivedData.id);
		isTitleChanged = false;
	};

	request.send(newMemoCardData); //  요청한 후 기다림
}

function addReadOnlyAttrToTitle() {
	let memoCardTitleText = document.querySelectorAll(".memo-card-title > input[name=\"memo-card-title\"]");

	for (let i = 0; i < memoCardTitleText.length; i++) {
		if (memoCardTitleText[i].readOnly === false) {
			memoCardTitleText[i].readOnly = true;
		}
	}
}