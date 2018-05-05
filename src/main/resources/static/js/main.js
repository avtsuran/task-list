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
