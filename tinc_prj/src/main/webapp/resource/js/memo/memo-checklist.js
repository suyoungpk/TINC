window.addEventListener("load", function () {
    var checkListItemList = document.querySelectorAll(".memo-checklist-item-list");


    for (var i = 0; i < checkListItemList.length; i++) {

        for (var j = 0; j < checkListItemList[i].children.length; j++) {

            checkListItemList[i].children[j].children[0].id = "checklist-item-checkbox" + i + "-" + j;
            checkListItemList[i].children[j].children[1].setAttribute("for", checkListItemList[i].children[j].children[0].id);
        }
    }
});