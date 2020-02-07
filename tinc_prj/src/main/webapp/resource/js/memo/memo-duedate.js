var selectedYear;
var selectedMonth;
var selectedDay;
var selectedHour;
var selectedMin;

var years = [];
var months = [
	"January",
	"Feburary",
	"March",
	"April",
	"May",
	"June",
	"July",
	"August",
	"September",
	"October",
	"November",
	"December"
];
var daysOfLeapYear = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
var daysOfNormalYear = [31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
var days = [];
var hours = [];
var mins = [];

var date = new Date();

// duedate �Ѱ� �ڽ��� �߾� ��ġ ã��
var duedateContainerOffset;
// duedate �Ѱ� �ڽ��� �ϴ�/��� ��ġ ã��
var duedateContainerMaxOffset;
var duedateContainerMinOffset;

// duedate �ڽ��� �߾� ��ġ ã��
var duedateContainerMidPos;

var duedateDaysDiv;
var duedateMonthDiv;
var duedateYearDiv;
var duedateHourDiv;
var duedateMinDiv;


var completeFlag = false;
var isOverDue = false;
var newDueDate = new Array(5);

// deadline ����� ���� ������
var tmpYear;
var tmpMonth;
var tmpDay;
var tmpHour;
var tmpMin;
var dateIn;
var timeIn;

var cardId = 0;
var initFlag = false;

var tid;

window.addEventListener("load", function (e) {
	// get cardId cookie
	cardId = getCookie("cardId");

	// ���� ��ư Ŭ���� â fadeout
	$(".memo-duedate-popup .memo-duedate-btn-area .cancel-btn")
		.off("click")
		.click(function () {
			let request = new XMLHttpRequest();
			request.open("POST", "../../../../memo/del-duedate");
			request.setRequestHeader('Content-Type', 'application/json');

			let sendCardIdData = JSON.stringify({
				cardId: cardId
			});

			request.onload = function () {
				console.log(request.responseText);
				if (request.responseText === "del-duedate success") {
					let duedatePlace = document.querySelector(".memo-detail-duedate > span");
					$(duedatePlace).fadeOut();

				}
			};
			request.send(sendCardIdData);

			$(".memo-duedate-popup").fadeOut();
			$(".popup").fadeOut();
			$(".mask").fadeOut();
		});

	// duedate �Ϸ� ����� üũ
	duedateCheckBoxHandler();

	// deadline���� �Լ���
	tid = this.setInterval(function (e) {
		if (cardId > 0) {
			// dealine ������ ���̸� ���������� ǥ��
			isDeadline();
			changeDeadLineColor();
		}
	}, 100);


	// duedate ������ ������ ǥ��
	let duedate = $(".memo-detail-duedate > span").text();
	if (duedate.trim()) {
		$(".memo-detail-duedate > span").fadeIn();
	}

	let trimmedDueDate = duedate.replace(/\s/g, ",").split(",");

	let date = trimmedDueDate[3];
	let time = trimmedDueDate[5];


	dateIn = date;
	timeIn = time;


	// DueDate��ư Ŭ���� duedate ���� â ����
	$(".memo-detail-duedate > input.memo-detail-duedate-button")
		.off("click")
		.click(function (e) {
			e.stopPropagation();
			e.preventDefault();

			$(".popup").fadeIn();
			$(".mask").fadeIn();
			$(".memo-duedate-popup").fadeIn();


			// x ��ư Ŭ���� ����
			$(".duedate-close").off("click").click(function (e) {
				e.preventDefault();
				var duedateContainer = document.querySelector(
					".memo-duedate-container"
				)
				for (let i = 0; i < duedateContainer.children.length; i++) {
					$(duedateContainer.children).animate({ scrollTop: 0 });
				}

				$(".memo-duedate-popup").fadeOut();
				$(".popup").fadeOut();
				$(".mask").fadeOut();
			});

			// duedate �ڽ� ���� ����
			createDueDateContainer(dateIn, timeIn);

			// duedate ���� ��ư Ŭ�� ��
			$(".memo-duedate-popup .memo-duedate-btn-area .ok-btn")
				.off("click")
				.click(function (e) {
					e.stopPropagation();
					e.preventDefault();
					initFlag = true;
					// console.log(newDueDate);
					for (let i = 0; i < months.length; i++) {
						if (months[i] === newDueDate[1]) {
							newDueDate[1] = i + 1;
							break;
						}
					}

					let date = newDueDate[0] + "-" + newDueDate[1] + "-" + newDueDate[2];
					let time = newDueDate[3] + ":" + newDueDate[4];
					let isComplete = $(".memo-detail-duedate > input[name=\"duedate-complete\"]").val();

					let sendDueDate = JSON.stringify({
						id: 0,
						date: date,
						time: time,
						isCompplete: isComplete,
						cardId: cardId
					});

					let request = new XMLHttpRequest();
					request.open("POST", "../../../../memo/update-duedate", true);
					request.setRequestHeader('Content-Type', 'application/json');
					request.onload = function () {

						let returnedDueDate = JSON.parse(request.responseText);

						console.log(returnedDueDate);
						if (returnedDueDate !== "insert-duedate error" && returnedDueDate !== "update-duedate error") {

							let duedatePlace = document.querySelector(".memo-detail-duedate > span");
							$(duedatePlace).fadeIn();
							dateIn = returnedDueDate.date
							timeIn = returnedDueDate.time;
							if (duedatePlace.querySelector("i").classList.contains("fa-check-square")) {
								duedatePlace.innerHTML = "&nbsp;" + `<i class="far fa-check-square"></i>` + "&nbsp;&nbsp;" + returnedDueDate.date + "&nbsp;&nbsp;" + returnedDueDate.time + "&nbsp;";

							}
							else {
								duedatePlace.innerHTML = "&nbsp;" + `<i class="far fa-square"></i>` + "&nbsp;&nbsp;" + returnedDueDate.date + "&nbsp;&nbsp;" + returnedDueDate.time + "&nbsp;";
							}

							initSelectDiv(duedateDaysDiv, duedateMonthDiv,
								duedateYearDiv, duedateHourDiv, duedateMinDiv
							);
						}
					}
					request.send(sendDueDate);


					$(".memo-duedate-popup").fadeOut();
					$(".popup").fadeOut();
					$(".mask").fadeOut();
				});
		});

});


/* deadline Ȯ�� */
function isDeadline() {
	tmpYear = dateIn.split("-")[0];
	tmpMonth = months[parseInt(dateIn.split("-")[1]) - 1];
	tmpDay = dateIn.split("-")[2];
	tmpHour = timeIn.split(":")[0];
	tmpMin = timeIn.split(":")[1];


	for (let i = 0; i < months.length; i++) {
		if (months[i] === tmpMonth) {
			tmpMonth = i;
			break;
		}
	}

	let today = date.getTime();
	let deadline = new Date(tmpYear, tmpMonth, tmpDay, tmpHour, tmpMin);

	let betweenDay = (deadline.getTime() - today) / 1000 / 60 / 60 / 24;
	console.log(betweenDay);

	if (betweenDay <= 7) {
		isOverDue = true;
	} else {
		isOverDue = false;
	}
}

function changeDeadLineColor() {
	var memoDueDateChecked = document.querySelector(
		".memo-detail-duedate span"
	);

	if (!completeFlag) {
		if (isOverDue) {
			memoDueDateChecked.style.color = "#ffffff";
			memoDueDateChecked.style.border = "1px solid #DF0101";
			memoDueDateChecked.style.backgroundColor = "#DF0101";
		}
		else {
			memoDueDateChecked.style.color = "#000000";
			memoDueDateChecked.style.border = "1px solid #ffffff";
			memoDueDateChecked.style.backgroundColor = "#ffffff";
		}
	}

}

/* due-date üũ ��ư �̺�Ʈ */
function duedateCheckBoxHandler() {

	let isComplete = $(".memo-detail-duedate > input[name=\"duedate-complete\"]").val();
	if (isComplete === "true") {
		let Checked = document.querySelector(
			".memo-detail-duedate span i"
		);

		if (Checked.classList[1] === "fa-square") {
			Checked.className = "far fa-check-square";
			completeFlag = true;
			Checked.parentNode.style.color = "#ffffff";
			Checked.parentNode.style.border = "1px solid #04B404";
			Checked.parentNode.style.backgroundColor = "#04B404";

		}
	}

	$(".memo-detail-duedate span")
		.off("click")
		.click(function (e) {
			e.stopPropagation();
			e.preventDefault();

			let memoDueDateChecked = document.querySelector(
				".memo-detail-duedate span i"
			);

			if (memoDueDateChecked.classList[1] === "fa-check-square") {
				memoDueDateChecked.className = "far fa-square";
				memoDueDateChecked.parentNode.style.color = "#000000";
				memoDueDateChecked.parentNode.style.border = "1px solid #ffffff";
				memoDueDateChecked.parentNode.style.backgroundColor = "#ffffff";

				//completeFlag = false;
				//$(".memo-detail-duedate > input[name=\"duedate-complete\"]").val("false");
				updateDueDateComplete(false);
				return;
			}

			if (memoDueDateChecked.classList[1] === "fa-square") {
				memoDueDateChecked.className = "far fa-check-square";
				memoDueDateChecked.parentNode.style.color = "#ffffff";
				memoDueDateChecked.parentNode.style.border = "1px solid #04B404";
				memoDueDateChecked.parentNode.style.backgroundColor = "#04B404";

				//completeFlag = true;
				//$(".memo-detail-duedate > input[name=\"duedate-complete\"]").val("true");
				updateDueDateComplete(true);
				return;
			}
		});
}

function updateDueDateComplete(isCompleteIn) {
	completeFlag = isCompleteIn;

	let sendCompleteData = JSON.stringify({
		cardId: cardId,
		isComplete: isCompleteIn
	});

	let request = new XMLHttpRequest();
	request.open("POST", "../../../../memo/update-duedate-complete");
	request.setRequestHeader('Content-Type', 'application/json');
	request.onload = function () {
		let receiveMsg = request.responseText;
		//console.log(receiveMsg);
		if (receiveMsg === "update-complete-status success") {
			$(".memo-detail-duedate > input[name=\"duedate-complete\"]").val(isCompleteIn);
		}
	}
	request.send(sendCompleteData);
}

// ���� �Ǵ�
function isLeapYear(year) {
	if ((year % 4 === 0 && year % 100 !== 0) || year % 400 === 0) {
		return true;
	} else {
		return false;
	}
}

// ���� ����
function createDueDateDays() {

	let leapYearFlag = isLeapYear(selectedYear);
	let matchedMonthIdx = -1;

	for (let i = 0; i < months.length; i++) {
		if (selectedMonth === months[i]) {
			matchedMonthIdx = i;
		}
	}

	if (matchedMonthIdx !== -1) {
		days = [];

		if (leapYearFlag) {
			for (let i = 0; i < daysOfLeapYear[matchedMonthIdx]; i++) {
				days[i] = ("0" + (i + 1)).slice(-2);
			}
		} else {
			for (let i = 0; i < daysOfNormalYear[matchedMonthIdx]; i++) {
				days[i] = ("0" + (i + 1)).slice(-2);
			}
		}
	}
}

// duedate span �±׸� �����Ͽ� �⵵,��,��,�ð�,�� �������� �ִ´�
function createDueDateSpanTag(elem, div) {
	div.innerHTML = "";
	for (let i = 0; i < elem.length; i++) {
		var spanTag = document.createElement("span");
		spanTag.innerText = elem[i];
		div.append(spanTag);
	}
}

/* due-date ���� �Լ� */
function createDueDateContainer(dateIn, timeIn) {
	//console.log(dateIn + ", " + timeIn);
	if (!dateIn && !timeIn) {
		selectedYear = date.getFullYear();
		selectedMonth = months[date.getMonth()];
	}
	else {

		selectedYear = dateIn.split("-")[0];
		selectedMonth = months[parseInt(dateIn.split("-")[1]) - 1];
		selectedDay = dateIn.split("-")[2];
		selectedHour = timeIn.split(":")[0];
		selectedMin = timeIn.split(":")[1];

		// newDueDate�� �߰�
		newDueDate[0] = selectedYear;
		newDueDate[1] = selectedMonth;
		newDueDate[2] = selectedDay;
		newDueDate[3] = selectedHour;
		newDueDate[4] = selectedMin;

	}


	var duedatePopup = document.querySelector(".memo-duedate-popup");
	var duedateContainer = duedatePopup.querySelector(
		".memo-duedate-container"
	);

	var duedateDives = duedateContainer.children;
	duedateDaysDiv = duedateDives[0];
	duedateMonthDiv = duedateDives[1];
	duedateYearDiv = duedateDives[2];
	duedateHourDiv = duedateDives[3];
	duedateMinDiv = duedateDives[4];

	// duedate �ڽ��� �߾� ��ġ ã��
	duedateContainerMidPos =
		duedateContainer.getBoundingClientRect().top +
		duedateContainer.getBoundingClientRect().height / 2;
	// duedate �Ѱ� �ڽ��� �߾� ��ġ ã��
	duedateContainerOffset =
		(duedateContainer.getBoundingClientRect().height * 0.2) / 2;
	// duedate �Ѱ� �ڽ��� �ϴ�/��� ��ġ ã��
	duedateContainerMaxOffset = duedateContainerMidPos + duedateContainerOffset;
	duedateContainerMinOffset = duedateContainerMidPos - duedateContainerOffset;

	// duedate�� �ʿ��� �ʱⰪ�� ����(��,��,��,��)
	createDueDate(duedateDaysDiv, duedateMonthDiv, duedateYearDiv,
		duedateHourDiv, duedateMinDiv).then(resp => {
			if (resp) {
				// �ʱ⿡ �ڽ� ���� ��ġ ��� �����ֱ�
				initSelectDiv(duedateDaysDiv, duedateMonthDiv, duedateYearDiv,
					duedateHourDiv, duedateMinDiv);
			}
		});

	// duedate �ڽ� Ŭ�� �ڵ鷯
	duedateClickHandler(duedateDaysDiv, duedateMonthDiv, duedateYearDiv,
		duedateHourDiv, duedateMinDiv, (resp) => {
			if (resp) {
				recreateDays();
			}
		});


}

/* due date���� ǥ�õ� ��,��,�⵵,��,�� span tag�� ���� */
function createDueDate(duedateDaysDiv, duedateMonthDiv, duedateYearDiv,
	duedateHourDiv, duedateMinDiv) {
	return new Promise(function (resolve, reject) {
		for (let i = 0; i < 10; i++) {
			years[i] = date.getFullYear() + i;
		}

		for (let i = 0; i < 24; i++) {
			hours[i] = ("0" + i).slice(-2);
		}

		for (let i = 0; i < 60; i++) {
			mins[i] = ("0" + i).slice(-2);
		}

		createDueDateDays();

		createDueDateSpanTag(days, duedateDaysDiv);
		createDueDateSpanTag(years, duedateYearDiv);
		createDueDateSpanTag(months, duedateMonthDiv);
		createDueDateSpanTag(hours, duedateHourDiv);
		createDueDateSpanTag(mins, duedateMinDiv);

		resolve(true);
	});
}

/* �ʱ� due date�� ��� �κп� �ִ� ��,��,�⵵,�ð�,�� ���� */
function initSelectDiv(duedateDaysDivIn, duedateMonthDivIn, duedateYearDivIn,
	duedateHourDivIn, duedateMinDivIn) {

	selectCalenderDiv(duedateYearDivIn);
	selectCalenderDiv(duedateMonthDivIn);
	selectCalenderDiv(duedateDaysDivIn);
	selectCalenderDiv(duedateHourDivIn);
	selectCalenderDiv(duedateMinDivIn);
}

function selectCalenderDiv(selectDiv) {
	if (/*!selectedDay &&*/ !selectedHour && !selectedMin) {
		for (var i = 0; i < selectDiv.children.length; i++) {
			var divTop = selectDiv.children[i].getBoundingClientRect().top;
			var divHeight = selectDiv.children[i].getBoundingClientRect()
				.height;
			var divMidPos = divTop + divHeight / 2;

			if (divMidPos >= duedateContainerMinOffset &&
				divMidPos <= duedateContainerMaxOffset) {
				selectDiv.children[i].style.color = "#000000";
				selectDiv.children[i].scrollIntoView({ block: "center" });

				if (selectDiv === duedateYearDiv) {
					newDueDate[0] = selectDiv.children[i].innerText;
				}

				if (selectDiv === duedateMonthDiv) {
					for (let j = 0; j < months.length; j++) {
						if (selectDiv.children[i].innerText === months[j]) {
							newDueDate[1] = ("0" + (j + 1)).slice(-2);
							break;
						}
					}
				}

				if (selectDiv === duedateDaysDiv) {
					newDueDate[2] = selectDiv.children[i].innerText;
					selectedDay = selectDiv.children[i].innerText;
				}

				if (selectDiv === duedateHourDiv) {
					newDueDate[3] = selectDiv.children[i].innerText;
				}

				if (selectDiv === duedateMinDiv) {
					newDueDate[4] = selectDiv.children[i].innerText;
				}

			} else {
				selectDiv.children[i].style.border = "";
				selectDiv.children[i].style.color = "#5e5e5e";
			}
		}
	} else {
		for (let i = 0; i < selectDiv.children.length; i++) {

			if (selectDiv === duedateDaysDiv) {
				if (selectDiv.children[i].innerText === selectedDay) {
					selectDiv.children[i].style.color = "#000000";
					selectDiv.children[i].scrollIntoView({ block: "center" });
				} else {
					selectDiv.children[i].style.border = "";
					selectDiv.children[i].style.color = "#5e5e5e";
				}
			}

			if (selectDiv === duedateMonthDiv) {
				if (selectDiv.children[i].innerText === selectedMonth) {
					selectDiv.children[i].style.color = "#000000";
					selectDiv.children[i].scrollIntoView({ block: "center" });
				} else {
					selectDiv.children[i].style.border = "";
					selectDiv.children[i].style.color = "#5e5e5e";
				}
			}

			if (selectDiv === duedateYearDiv) {
				if (selectDiv.children[i].innerText === selectedYear) {
					selectDiv.children[i].style.color = "#000000";
					selectDiv.children[i].scrollIntoView({ block: "center" });
				} else {
					selectDiv.children[i].style.border = "";
					selectDiv.children[i].style.color = "#5e5e5e";
				}
			}

			if (selectDiv === duedateHourDiv) {
				if (selectDiv.children[i].innerText === selectedHour) {
					selectDiv.children[i].style.color = "#000000";
					selectDiv.children[i].scrollIntoView({ block: "center" });
				} else {
					selectDiv.children[i].style.border = "";
					selectDiv.children[i].style.color = "#5e5e5e";
				}
			}

			if (selectDiv === duedateMinDiv) {
				if (selectDiv.children[i].innerText === selectedMin) {
					selectDiv.children[i].style.color = "#000000";
					selectDiv.children[i].scrollIntoView({ block: "center" });
				} else {
					selectDiv.children[i].style.border = "";
					selectDiv.children[i].style.color = "#5e5e5e";
				}
			}
		}
	}
}


/* ���콺�� ��,��,�⵵,�ð�,�� ���� ��� */
function duedateClickHandler(duedateDaysDiv, duedateMonthDiv,
	duedateYearDiv, duedateHourDiv, duedateMinDiv, callback) {

	duedateClickFunc(duedateDaysDiv).then((resp) => {
		callback(resp);
	});

	duedateClickFunc(duedateMonthDiv).then((resp) => {
		callback(resp);
	});

	duedateClickFunc(duedateYearDiv).then((resp) => {
		callback(resp);
	});

	duedateClickFunc(duedateHourDiv).then((resp) => {
		callback(resp);
	});

	duedateClickFunc(duedateMinDiv).then((resp) => {
		callback(resp);
	});

}

function duedateClickFunc(selectDiv) {
	return new Promise((resolve, reject) => {
		$(selectDiv).off("click").click(function (e) {
			for (let i = 0; i < selectDiv.children.length; i++) {
				if (e.target === selectDiv.children[i]) {
					selectDiv.children[i].style.color = "#000000";
					selectDiv.children[i].scrollIntoView({ behavior: "smooth", block: "center" });

					let tmpInnerText = selectDiv.children[i].innerText;
					if (selectDiv === duedateYearDiv) {
						newDueDate[0] = tmpInnerText;
						selectedYear = tmpInnerText;
						resolve(true);
					}

					if (selectDiv === duedateMonthDiv) {
						for (let j = 0; j < months.length; j++) {
							if (months[j] === tmpInnerText) {
								newDueDate[1] = ("0" + (j + 1)).slice(-2);
								break;
							}
							selectedMonth = tmpInnerText;
						}
						resolve(true);
					}

					if (selectDiv === duedateDaysDiv) {
						newDueDate[2] = tmpInnerText;
						selectedDay = tmpInnerText;
						resolve(false);
					}

					if (selectDiv === duedateHourDiv) {
						newDueDate[3] = tmpInnerText;
						selectedHour = tmpInnerText;
						resolve(false);
					}

					if (selectDiv === duedateMinDiv) {
						newDueDate[4] = tmpInnerText;
						selectedMin = tmpInnerText;
						resolve(false);
					}
				}
				else {
					selectDiv.children[i].style.border = "";
					selectDiv.children[i].style.color = "#5e5e5e";
				}
			}
		});
	});
}

// duedate �ڽ� Ŭ�� �� ���� �ٽ� ����
function recreateDays() {
	createDueDateDays();
	createDueDateSpanTag(days, duedateDaysDiv);

	for (let i = 0; i < duedateDaysDiv.children.length; i++) {
		if (duedateDaysDiv.children[i].innerText === selectedDay) {
			duedateDaysDiv.children[i].style.color = "#000000";
		} else {
			duedateDaysDiv.children[i].style.border = "";
			duedateDaysDiv.children[i].style.color = "#5e5e5e";
		}
	}
}

