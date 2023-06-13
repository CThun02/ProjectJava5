var checkBtnDataAdmin = document.getElementsByClassName("check-data-select");
var checkBtnAddAdmin = document.getElementsByClassName("check-add-select");
var href = location.href;

window.onload = function (){
    if((href.includes("data")||href.includes("search")||href.includes("filter"))&&(href.includes("admin"))){
        checkBtnDataAdmin[0].classList.add("hover");
        checkBtnAddAdmin[0].classList.remove(("hover"));
    }else if(href.includes("create")){
        checkBtnDataAdmin[0].classList.remove("hover");
        checkBtnAddAdmin[0].classList.add(("hover"));
    }
    if(href.includes("admin")){
        let heightAdminFunction = document.getElementsByClassName("btn-functions-admin")[0];
        let heightAminFooter = document.getElementsByTagName("footer")[0];
        let heightAminHeader = document.getElementsByTagName("header")[0];
        let navMenu = document.getElementsByClassName("nav-menu")[0];
        let totalHeight = heightAdminFunction.offsetHeight + heightAminFooter.offsetHeight + heightAminHeader.offsetHeight
        navMenu.style.height = totalHeight;
    }
    if(href!=="http://localhost:8080/" && !(href.includes("admin"))){
        let headerUserHome = document.getElementsByClassName("home-nav")[0];
        headerUserHome.classList.add("bg-cl-red");
    }
    if(href.includes("cart") || href.includes("order")){
        let subtotal = document.getElementsByClassName("sub-total");
        let total = 0;
        for (let i = 0; i < subtotal.length; i++) {
            total+= Number(subtotal[i].innerHTML);
        }
        let totalDom = document.getElementsByClassName("total")[0];
        totalDom.innerHTML = "Total: " + parseFloat(total) + "$";
    }
}

chooseSearch = function(){
    let selectSearch = document.getElementsByName("chooseSearch")[0];
    let viewSelectSearch = document.getElementById("chooseSearch");
    let selectSearchName = document.getElementsByClassName("search-by-name")[0];
    let selectSearchPrice = document.getElementsByClassName("search-by-price");
    let viewSearch = document.getElementsByClassName("search")[0];
    let hrefSearch = document.getElementById("action-search");
    var value = selectSearch.value;
    viewSelectSearch.classList.add("d-none")
    viewSearch.classList.add("d-block")
    viewSearch.classList.remove("d-none")
    if(value=="name"){
        selectSearchName.classList.add("d-block")
        selectSearchName.classList.remove("d-none")
        selectSearchName.removeAttribute("disabled")
        for (let i=0; i<selectSearchPrice.length;i++){
            selectSearchPrice[i].classList.add("d-none")
            selectSearchPrice[i].classList.remove("d-block")
            selectSearchPrice[i].setAttribute("disabled", true)
        }
        hrefSearch.setAttribute("action", "/admin/product/searchbyname");
    }else{
        selectSearchName.classList.add("d-none")
        selectSearchName.classList.remove("d-block")
        selectSearchName.setAttribute("disabled", true)
        for (let i=0; i<selectSearchPrice.length;i++){
            selectSearchPrice[i].classList.add("d-block")
            selectSearchPrice[i].classList.remove("d-none")
            selectSearchPrice[i].removeAttribute("disabled")
        }
        hrefSearch.setAttribute("action", "/admin/product/searchbyprice");

    }
}

closeViewSearch = function () {
    let viewSelectSearch = document.getElementById("chooseSearch");
    let viewSearch = document.getElementsByClassName("search")[0];

    viewSelectSearch.classList.remove("d-none");
    viewSearch.classList.add("d-none")
}

window.addEventListener('scroll', function() {
    let isHome = href=="http://localhost:8080/";
    let headerUserHome = document.getElementsByClassName("home-nav")[0];
    if(isHome){
        if(document.documentElement.scrollTop == 0 && document.body.scrollTop == 0){
            headerUserHome.classList.remove("bg-cl-red");
        }else{
            headerUserHome.classList.add("bg-cl-red");
        }
    }
});

