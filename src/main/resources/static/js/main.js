var idList;

function changeDisplayForTwoElements(show, hide){
    document.getElementById(show).style.display = "block";
    document.getElementById(hide).style.display = "none";
}

function showOrHideElement(id) {
    if(document.getElementById(id).style.display == "block")
        document.getElementById(id).style.display = "none";
    else
        document.getElementById(id).style.display = "block";
}

function removeBoard(id) {
    document.getElementById("modal").style.display = "block";
    document.getElementById("remove-board-by-id").href = "/remove-board?id=" + id;
}

function taskListHead(show, hide, form, name){
    document.getElementById(show).style.display = "block";
    document.getElementById(hide).style.display = "none";
    document.getElementById(form).style.display = "none";
    document.getElementById(name).style.display = "block";
}

function removeTaskList(id) {
    document.getElementById("modal").style.display = "block";
    document.getElementById("remove-list-by-id").href = "/remove-task-list?id=" + id;
    idList = id;
}

function hideModal() {
    document.getElementById("modal").style.display = "none";
    document.getElementById("menu-list-" + idList).style.display = "none";
    document.getElementById("open-menu-list-" + idList).style.display = "block";
    document.getElementById("form-edit-" + idList).style.display = "none";
    document.getElementById("name-" + idList).style.display = "block";
}