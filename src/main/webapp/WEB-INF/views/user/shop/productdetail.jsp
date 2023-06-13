<section class="col-8 offset-md-2 pb-5 productdetail">
    <div class="p-5 bg-cl-white">
        <div class="p-5 product row">
            <div class="col-6">
                <img src="data:image/jpeg;base64,${imgUlities.getImgByteBase64(product.img)}" width="100%" alt="">
            </div>
            <div class="col-6">
                <span>Home/Product detail</span><br>
                <a href="" class="mt-5 mb-5 link-red">${product.category.ten}</a>
                <h1 class="">${product.ten}</h1>
                <h5>$${product.gia}</h5>
                <h6>Quantity: ${product.soLuongTon}</h6>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                    Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                <form action="/shop/addtocart" method="get">
                    <input type="text" name="id" class="d-none" readonly value="${product.ID}" id="">
                    <input type="number" value="1" name="quantity" class="quantity form-control d-inline-block" id="">
                    <button class="btn-bg-red ps-4 pe-4 pt-2 pb-2">ADD TO CART</button>
                </form>
                <p class="text-danger">${ErrorQuantity}</p>
            </div>
            <div>
                <hr>
                <span>Description</span>
                <p class="mt-2">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                    Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                    Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
                    Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
            </div>
        </div>

    </div>
</section>