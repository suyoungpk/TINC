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

    // console.log(duedateContainer.getBoundingClientRect().top + "," + duedateContainer.getBoundingClientRect().height);
    // console.log("max offset:" + duedateContainerMaxOffset);
    // console.log("min offset:" + duedateContainerMinOffset);

    var initSelectDiv = function () {

        for (var i = 0; i < duedateDaysDiv.children.length; i++) {

            var divTop = duedateDaysDiv.children[i].getBoundingClientRect().top;
            var divHeight = duedateDaysDiv.children[i].getBoundingClientRect().height;
            var divMidPos = divTop + (divHeight / 2);

            if (divMidPos >= duedateContainerMinOffset && divMidPos <= duedateContainerMaxOffset) {
                duedateDaysDiv.children[i].style.borderTop = "2px ridge #D8D8D8";
                duedateDaysDiv.children[i].style.borderBottom = "2px ridge #D8D8D8";
            } else {
                duedateDaysDiv.children[i].style.border = "";
            }
        }

        for (var i = 0; i < duedateMonthDiv.children.length; i++) {

            var divTop = duedateMonthDiv.children[i].getBoundingClientRect().top;
            var divHeight = duedateMonthDiv.children[i].getBoundingClientRect().height;
            var divMidPos = divTop + (divHeight / 2);

            if (divMidPos >= duedateContainerMinOffset && divMidPos <= duedateContainerMaxOffset) {
                duedateMonthDiv.children[i].style.borderTop = "2px ridge #D8D8D8";
                duedateMonthDiv.children[i].style.borderBottom = "2px ridge #D8D8D8";
            } else {
                duedateMonthDiv.children[i].style.border = "";
            }
        }

        for (var i = 0; i < duedateYearDiv.children.length; i++) {

            var divTop = duedateYearDiv.children[i].getBoundingClientRect().top;
            var divHeight = duedateYearDiv.children[i].getBoundingClientRect().height;
            var divMidPos = divTop + (divHeight / 2);

            if (divMidPos >= duedateContainerMinOffset && divMidPos <= duedateContainerMaxOffset) {
                duedateYearDiv.children[i].style.borderTop = "2px ridge #D8D8D8";
                duedateYearDiv.children[i].style.borderBottom = "2px ridge #D8D8D8";
            } else {
                duedateYearDiv.children[i].style.border = "";
            }
        }

        for (var i = 0; i < duedateHourDiv.children.length; i++) {

            var divTop = duedateHourDiv.children[i].getBoundingClientRect().top;
            var divHeight = duedateHourDiv.children[i].getBoundingClientRect().height;
            var divMidPos = divTop + (divHeight / 2);

            if (divMidPos >= duedateContainerMinOffset && divMidPos <= duedateContainerMaxOffset) {
                duedateHourDiv.children[i].style.borderTop = "2px ridge #D8D8D8";
                duedateHourDiv.children[i].style.borderBottom = "2px ridge #D8D8D8";
            } else {
                duedateHourDiv.children[i].style.border = "";
            }
        }

        for (var i = 0; i < duedateMinDiv.children.length; i++) {

            var divTop = duedateMinDiv.children[i].getBoundingClientRect().top;
            var divHeight = duedateMinDiv.children[i].getBoundingClientRect().height;
            var divMidPos = divTop + (divHeight / 2);

            if (divMidPos >= duedateContainerMinOffset && divMidPos <= duedateContainerMaxOffset) {
                duedateMinDiv.children[i].style.borderTop = "2px ridge #D8D8D8";
                duedateMinDiv.children[i].style.borderBottom = "2px ridge #D8D8D8";
            } else {
                duedateMinDiv.children[i].style.border = "";
            }
        }
    };


    initSelectDiv();

    duedateDaysDiv.addEventListener("click", function (e) {

        for (var i = 0; i < duedateDaysDiv.children.length; i++) {

            if (e.target == duedateDaysDiv.children[i]) {
                console.log(duedateDaysDiv.children[i]);
                duedateDaysDiv.children[i].style.borderTop = "2px ridge #D8D8D8";
                duedateDaysDiv.children[i].style.borderBottom = "2px ridge #D8D8D8";
            } else {
                duedateDaysDiv.children[i].style.border = "";
            }
        }
    });

    duedateMonthDiv.addEventListener("click", function (e) {

        for (var i = 0; i < duedateMonthDiv.children.length; i++) {

            if (e.target == duedateMonthDiv.children[i]) {
                console.log(duedateMonthDiv.children[i]);
                duedateMonthDiv.children[i].style.borderTop = "2px ridge #D8D8D8";
                duedateMonthDiv.children[i].style.borderBottom = "2px ridge #D8D8D8";
            } else {
                duedateMonthDiv.children[i].style.border = "";
            }
        }
    });

    duedateYearDiv.addEventListener("click", function (e) {

        for (var i = 0; i < duedateYearDiv.children.length; i++) {

            if (e.target == duedateYearDiv.children[i]) {
                console.log(duedateYearDiv.children[i]);
                duedateYearDiv.children[i].style.borderTop = "2px ridge #D8D8D8";
                duedateYearDiv.children[i].style.borderBottom = "2px ridge #D8D8D8";
            } else {
                duedateYearDiv.children[i].style.border = "";
            }
        }
    });

    duedateHourDiv.addEventListener("click", function (e) {

        for (var i = 0; i < duedateHourDiv.children.length; i++) {

            if (e.target == duedateHourDiv.children[i]) {
                console.log(duedateHourDiv.children[i]);
                duedateHourDiv.children[i].style.borderTop = "2px ridge #D8D8D8";
                duedateHourDiv.children[i].style.borderBottom = "2px ridge #D8D8D8";
            } else {
                duedateHourDiv.children[i].style.border = "";
            }
        }
    });

    duedateMinDiv.addEventListener("click", function (e) {

        for (var i = 0; i < duedateMinDiv.children.length; i++) {

            if (e.target == duedateMinDiv.children[i]) {
                console.log(duedateMinDiv.children[i]);
                duedateMinDiv.children[i].style.borderTop = "2px ridge #D8D8D8";
                duedateMinDiv.children[i].style.borderBottom = "2px ridge #D8D8D8";
            } else {
                duedateMinDiv.children[i].style.border = "";
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


});