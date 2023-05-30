var checkBtnDataAdmin = document.getElementsByClassName("check-data-select");
var checkBtnAddAdmin = document.getElementsByClassName("check-add-select");
var href = location.href;

window.onload = function (){
    if(href.includes("data")){
        checkBtnDataAdmin[0].classList.add("hover");
        checkBtnAddAdmin[0].classList.remove(("hover"));
    }else if(href.includes("create")){
        checkBtnDataAdmin[0].classList.remove("hover");
        checkBtnAddAdmin[0].classList.add(("hover"));
    }
}

