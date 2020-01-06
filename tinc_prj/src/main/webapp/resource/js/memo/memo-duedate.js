window.addEventListener("load", function () {
    var duedatePopup = document.querySelector(".memo-duedate-popup");
    var duedateContainer = duedatePopup.querySelector(".memo-duedate-container");

    var duedateDives = duedateContainer.children;
    var duedateDaysDiv = duedateDives[0];
    var duedateMonthDiv = duedateDives[1];
    var duedateYearDiv = duedateDives[2];
    var duedateHourDiv = duedateDives[3];
    var duedateMinDiv = duedateDives[4];

    var duedateContainerMidPos = duedateContainer.getBoundingClientRect().top + (duedateContainer.getBoundingClientRect().height / 2);
    var duedateContainerOffset = duedateContainer.getBoundingClientRect().height * 0.2 / 2;
    var duedateContainerMaxOffset = duedateContainerMidPos + duedateContainerOffset;
    var duedateContainerMinOffset = duedateContainerMidPos - duedateContainerOffset;


    let years = [];
    let months = ["January", "Feburary", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    ];
    let daysOfLeapYear = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    let daysOfNormalYear = [31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    let days = [];
    let hours = [];
    let mins = [];

    let selectedYear;
    let selectedMonth;

    function isLeapYear(year) {
        if ((year % 4 === 0 && year % 100 !== 0) || year % 400 === 0) {
            return true;
        } else {
            return false;
        }
    }

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
                    days[i] = i + 1;
                }
            } else {
                for (let i = 0; i < daysOfNormalYear[matchedMonthIdx]; i++) {
                    days[i] = i + 1;
                }
            }
        }

        // console.log(days);
        duedateDaysDiv.innerHTML = "";
        createDueDateSpanTag(days, duedateDaysDiv);
        selectCalenderDiv(duedateDaysDiv);
    };

    function createDueDateSpanTag(elem, div) {
        for (let i = 0; i < elem.length; i++) {
            var spanTag = document.createElement("span");
            spanTag.innerText = elem[i];
            div.append(spanTag);
        }
    };

    /* due date에서 표시될 일,월,년도,시,분 span tag들 생성 */
    var createDueDate = function () {
        const date = new Date();

        for (let i = 0; i < 10; i++) {
            years[i] = date.getFullYear() + i;
        }

        for (let i = 0; i < 25; i++) {
            hours[i] = i;
        }

        for (let i = 0; i < 60; i++) {
            mins[i] = i;
        }


        createDueDateSpanTag(years, duedateYearDiv);
        createDueDateSpanTag(months, duedateMonthDiv);
        createDueDateSpanTag(hours, duedateHourDiv);
        createDueDateSpanTag(mins, duedateMinDiv);

    };


    var selectCalenderDiv = function (selectDiv) {
        for (var i = 0; i < selectDiv.children.length; i++) {

            var divTop = selectDiv.children[i].getBoundingClientRect().top;
            var divHeight = selectDiv.children[i].getBoundingClientRect().height;
            var divMidPos = divTop + (divHeight / 2);

            if (divMidPos >= duedateContainerMinOffset && divMidPos <= duedateContainerMaxOffset) {
                selectDiv.children[i].style.borderTop = "2px ridge #D8D8D8";
                selectDiv.children[i].style.borderBottom = "2px ridge #D8D8D8";
                selectDiv.children[i].style.color = "#000000";

                if (selectDiv === duedateYearDiv) {
                    selectedYear = selectDiv.children[i].innerText;

                }
                if (selectDiv === duedateMonthDiv) {
                    selectedMonth = selectDiv.children[i].innerText;
                }

            } else {
                selectDiv.children[i].style.border = "";
                selectDiv.children[i].style.color = "#5e5e5e";
            }
        }
    };

    /* 초기 due date의 가운데 부분에 있는 일,월,년도,시간,분 선택 */
    var initSelectDiv = function () {
        selectCalenderDiv(duedateMonthDiv);
        selectCalenderDiv(duedateYearDiv);
        selectCalenderDiv(duedateHourDiv);
        selectCalenderDiv(duedateMinDiv);
    };


    createDueDate();
    initSelectDiv();
    createDueDateDays();

    /* 마우스로 일,월,년도,시간,분 선택 기능 */
    duedateDaysDiv.addEventListener("click", function (e) {

        for (var i = 0; i < duedateDaysDiv.children.length; i++) {

            if (e.target == duedateDaysDiv.children[i]) {
                duedateDaysDiv.children[i].style.borderTop = "2px ridge #D8D8D8";
                duedateDaysDiv.children[i].style.borderBottom = "2px ridge #D8D8D8";
                duedateDaysDiv.children[i].style.color = "#000000";
            } else {
                duedateDaysDiv.children[i].style.border = "";
                duedateDaysDiv.children[i].style.color = "#5e5e5e";
            }
        }
    });

    duedateMonthDiv.addEventListener("click", function (e) {

        for (var i = 0; i < duedateMonthDiv.children.length; i++) {

            if (e.target == duedateMonthDiv.children[i]) {
                duedateMonthDiv.children[i].style.borderTop = "2px ridge #D8D8D8";
                duedateMonthDiv.children[i].style.borderBottom = "2px ridge #D8D8D8";
                duedateMonthDiv.children[i].style.color = "#000000";

                selectedMonth = duedateMonthDiv.children[i].innerText;
                createDueDateDays();
            } else {
                duedateMonthDiv.children[i].style.border = "";
                duedateMonthDiv.children[i].style.color = "#5e5e5e";
            }
        }
    });

    duedateYearDiv.addEventListener("click", function (e) {

        for (var i = 0; i < duedateYearDiv.children.length; i++) {

            if (e.target == duedateYearDiv.children[i]) {
                duedateYearDiv.children[i].style.borderTop = "2px ridge #D8D8D8";
                duedateYearDiv.children[i].style.borderBottom = "2px ridge #D8D8D8";
                duedateYearDiv.children[i].style.color = "#000000";

                selectedYear = duedateYearDiv.children[i].innerText;
                createDueDateDays();
            } else {
                duedateYearDiv.children[i].style.border = "";
                duedateYearDiv.children[i].style.color = "#5e5e5e";
            }
        }
    });

    duedateHourDiv.addEventListener("click", function (e) {

        for (var i = 0; i < duedateHourDiv.children.length; i++) {

            if (e.target == duedateHourDiv.children[i]) {
                duedateHourDiv.children[i].style.borderTop = "2px ridge #D8D8D8";
                duedateHourDiv.children[i].style.borderBottom = "2px ridge #D8D8D8";
                duedateHourDiv.children[i].style.color = "#000000";
            } else {
                duedateHourDiv.children[i].style.border = "";
                duedateHourDiv.children[i].style.color = "#5e5e5e";
            }
        }
    });

    duedateMinDiv.addEventListener("click", function (e) {

        for (var i = 0; i < duedateMinDiv.children.length; i++) {

            if (e.target == duedateMinDiv.children[i]) {
                duedateMinDiv.children[i].style.borderTop = "2px ridge #D8D8D8";
                duedateMinDiv.children[i].style.borderBottom = "2px ridge #D8D8D8";
                duedateMinDiv.children[i].style.color = "#000000";
            } else {
                duedateMinDiv.children[i].style.border = "";
                duedateMinDiv.children[i].style.color = "#5e5e5e";
            }
        }
    });

    /* cancel, ok, close button events */
    $(".memo-duedate-popup .memo-duedate-btn-area .cancel-btn")
        .click(function () {
            $(".memo-duedate-popup").fadeOut();
            $(".popup").fadeOut();
            $(".mask").fadeOut();
        });

    $(".memo-duedate-popup .memo-duedate-btn-area .ok-btn")
        .click(function () {
            $(".memo-duedate-popup").fadeOut();
            $(".popup").fadeOut();
            $(".mask").fadeOut();
        });

    $(".popup a.btn-close").click(function () {
        $(".popup").fadeOut();
        $(".mask").fadeOut();
    });

    $(".memo-detail-duedate > input.memo-detail-duedate-button")
        .click(function () {
            $(".memo-duedate-popup").fadeIn();
            $(".popup").fadeIn();
            $(".mask").fadeIn();
        });
});