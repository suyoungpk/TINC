window.addEventListener("load", function () {
    var checkListItemList = document.querySelectorAll(".memo-checklist-item-list");

    // this.console.log(checkListItemList.length);
    // this.console.log(checkListItemList[0].children.length);
    for (var i = 0; i < checkListItemList.length; i++) {


        for (var j = 0; j < checkListItemList[i].children.length; j++) {
            this.console.log(checkListItemList[i].children[j].children[0]);
            this.console.log(checkListItemList[i].children[j].children[1]);
            this.console.log(checkListItemList[i].children[j].children[2]);
            checkListItemList[i].children[j].children[0].id = "checklist-item-checkbox" + i + "-" + j;
            checkListItemList[i].children[j].children[1].setAttribute("for", checkListItemList[i].children[j].children[0].id);
        }
    }
});