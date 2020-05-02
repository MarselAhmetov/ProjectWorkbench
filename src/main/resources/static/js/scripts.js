function changeFriend() {
    var friend = document.getElementById('inputFriendSelect');
    var selected = friend.options[friend.selectedIndex].value;
    if (selected != -1) {
        document.getElementById('DebtorName').disabled = true;
        console.log(selected + "");
    } else {
        document.getElementById('DebtorName').disabled = false;
        console.log(selected + "");
    }
}