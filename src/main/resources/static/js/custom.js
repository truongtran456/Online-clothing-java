(function ($) {
    "use strict";
    // Mean Menu
    $('.mean-menu').meanmenu({
        meanScreenWidth: "991"
    });

    // Header Sticky
    $(window).on('scroll', function () {
        if ($(this).scrollTop() > 120) {
            $('.navbar-area').addClass("is-sticky");
        }
        else {
            $('.navbar-area').removeClass("is-sticky");
        }
    });

    // Search Menu JSde
    $(".others-option .search-btn-box i").on("click", function () {
        $(".search-overlay").toggleClass("search-overlay-active");
    });
    $(".search-overlay-close").on("click", function () {
        $(".search-overlay").removeClass("search-overlay-active");
    });

    // To Days-Slider
    $('.todays_slider').owlCarousel({
        loop: true,
        dots: false,
        autoplayHoverPause: true,
        autoplay: true,
        smartSpeed: 1000,
        margin: 30,
        navText: [
            "<i class='fas fa-chevron-left'></i>",
            "<i class='fas fa-chevron-right'></i>"
        ],
        nav: true,
        responsive: {
            0: {
                items: 1,
            },
            480: {
                items: 2,
            },
            576: {
                items: 2,
            },
            768: {
                items: 3,
            },
            1200: {
                items: 4,
            }
        }
    });

    // Instgram-Slider
    $('.instagram_post_slider').owlCarousel({
        loop: true,
        dots: false,
        autoplayHoverPause: true,
        autoplay: true,
        smartSpeed: 1000,
        margin: 0,
        nav: false,
        responsive: {
            0: {
                items: 2,
            },
            768: {
                items: 3,
            },
            992: {
                items: 4,
            },
            1200: {
                items: 5,
            }
        }
    });
    // Product Modal-Slider
    $('.products_modal_sliders').owlCarousel({
        loop: true,
        dots: true,
        autoplayHoverPause: true,
        autoplay: true,
        smartSpeed: 1000,
        margin: 0,
        nav: false,
        responsive: {
            0: {
                items: 1,
            },
            768: {
                items: 1,
            },
            992: {
                items: 1,
            },
            1200: {
                items: 1,
            }
        }
    });
    // Team Slider Slider
    $('.team_slider').owlCarousel({
        loop: true,
        dots: false,
        autoplayHoverPause: true,
        autoplay: true,
        smartSpeed: 1000,
        margin: 30,
        nav: false,
        navText: [
            "<i class='fas fa-chevron-left'></i>",
            "<i class='fas fa-chevron-right'></i>"
        ],
        responsive: {
            0: {
                items: 1,
            },
            768: {
                items: 2,
            },
            992: {
                items: 3,
            },
            1200: {
                items: 4,
            }
        }
    });

    /*===========================================
                Furniture Banner Slider
    =============================================*/
    $(".furniture_slider_box").owlCarousel({
        animateOut: "fadeOut",
        animateIn: "fadeIn",
        loop: true,
        margin: 0,
        nav: false,
        singleItem: true,
        smartSpeed: 500,
        autoplay: true,
        autoplayTimeout: 6000,
        navText: [
            '<span class="fas fa-angle-left"></span>',
            '<span class="fas fa-angle-right"></span>'
        ],
        responsive: {
            0: {
                items: 1
            },
            600: {
                items: 1
            },
            1024: {
                items: 1
            }
        }
    });

    /*===========================================
            Furniture Popular Product
    =============================================*/
    $(".furniture_popular_slider").owlCarousel({
        animateOut: "fadeOut",
        animateIn: "fadeIn",
        loop: true,
        margin: 30,
        nav: true,
        singleItem: true,
        smartSpeed: 500,
        autoplay: true,
        autoplayTimeout: 6000,
        navText: [
            '<span class="fas fa-angle-left"></span>',
            '<span class="fas fa-angle-right"></span>'
        ],
        responsive: {
            0: {
                items: 1
            },
            600: {
                items: 2
            },
            1024: {
                items: 3
            }
        }
    });


    /*===========================================
                Electronics Banner Slider
    =============================================*/
    $(".electronics_slider_box").owlCarousel({
        animateOut: "fadeOut",
        animateIn: "fadeIn",
        loop: true,
        margin: 0,
        dots: false,
        nav: true,
        singleItem: true,
        smartSpeed: 500,
        autoplay: true,
        autoplayTimeout: 6000,
        navText: [
            '<span class="fas fa-angle-left"></span>',
            '<span class="fas fa-angle-right"></span>'
        ],
        responsive: {
            0: {
                items: 1
            },
            600: {
                items: 1
            },
            1024: {
                items: 1
            }
        }
    });


    /*===========================================
           Weekly Deal Product
    =============================================*/
    $(".elce_weekly_slider").owlCarousel({
        animateOut: "fadeOut",
        animateIn: "fadeIn",
        loop: true,
        margin: 30,
        nav: true,
        dots: false,
        singleItem: true,
        smartSpeed: 500,
        autoplay: true,
        autoplayTimeout: 6000,
        navText: [
            '<span class="fas fa-angle-left"></span>',
            '<span class="fas fa-angle-right"></span>'
        ],
        responsive: {
            0: {
                items: 1
            },
            600: {
                items: 2
            },
            1024: {
                items: 4
            }
        }
    });

    // Grocery Page
    /*===========================================
          Top Categories 
    =============================================*/

    $('.grocery_categories_slider').owlCarousel({
        loop: true,
        dots: false,
        autoplayHoverPause: true,
        autoplay: true,
        smartSpeed: 1000,
        margin: 30,

        navText: [
            "<i class='fas fa-chevron-left'></i>",
            "<i class='fas fa-chevron-right'></i>"
        ],
        nav: true,
        dots: false,
        responsive: {
            0: {
                items: 1,
            },
            480: {
                items: 2,
            },
            576: {
                items: 2,
            },
            768: {
                items: 4,
            },
            1200: {
                items: 7,
            }
        }
    });
    /*===========================================
         Trending Product
    =============================================*/

    $('.grocery_trending_product').owlCarousel({
        loop: true,
        dots: false,
        autoplayHoverPause: true,
        autoplay: true,
        smartSpeed: 1000,
        margin: 30,

        navText: [
            "<i class='fas fa-chevron-left'></i>",
            "<i class='fas fa-chevron-right'></i>"
        ],
        nav: true,
        dots: false,
        responsive: {
            0: {
                items: 1,
            },
            480: {
                items: 2,
            },
            576: {
                items: 2,
            },
            768: {
                items: 4,
            },
            1200: {
                items: 7,
            }
        }
    });
    /*===========================================
          Featured Sldier
    =============================================*/

    $('.featured_grocery_slider').owlCarousel({
        loop: true,
        dots: false,
        autoplayHoverPause: true,
        autoplay: true,
        smartSpeed: 1000,
        margin: 30,

        navText: [
            "<i class='fas fa-chevron-left'></i>",
            "<i class='fas fa-chevron-right'></i>"
        ],
        nav: true,
        dots: false,
        responsive: {
            0: {
                items: 1,
            },
            480: {
                items: 2,
            },
            576: {
                items: 2,
            },
            768: {
                items: 4,
            },
            1200: {
                items: 5,
            }
        }
    });


    /*===========================================
          Pharmacy Page
    =============================================*/

    /*===========================================
         Top Categories Sldier
    =============================================*/

    $('.pharmacy_top_cate').owlCarousel({
        loop: true,
        dots: false,
        autoplayHoverPause: true,
        autoplay: true,
        smartSpeed: 1000,
        margin: 30,

        navText: [
            "<i class='fas fa-chevron-left'></i>",
            "<i class='fas fa-chevron-right'></i>"
        ],
        nav: true,
        dots: false,
        responsive: {
            0: {
                items: 1,
            },
            480: {
                items: 2,
            },
            576: {
                items: 2,
            },
            768: {
                items: 4,
            },
            1200: {
                items: 6,
            }
        }
    });
    /*===========================================
          Hot item Sldier
    =============================================*/

    $('.pharmacy_hot_item_slider').owlCarousel({
        loop: true,
        dots: false,
        autoplayHoverPause: true,
        autoplay: true,
        smartSpeed: 1000,
        margin: 30,

        navText: [
            "<i class='fas fa-chevron-left'></i>",
            "<i class='fas fa-chevron-right'></i>"
        ],
        nav: true,
        dots: false,
        responsive: {
            0: {
                items: 1,
            },
            480: {
                items: 1,
            },
            576: {
                items: 2,
            },
            768: {
                items: 3,
            },
            1200: {
                items: 4,
            }
        }
    });
    /*===========================================
          Top Brand Sldier
    =============================================*/

    $('.pharmacy_top_brand_slider').owlCarousel({
        loop: true,
        dots: false,
        autoplayHoverPause: true,
        autoplay: true,
        smartSpeed: 1000,
        margin: 30,

        navText: [
            "<i class='fas fa-chevron-left'></i>",
            "<i class='fas fa-chevron-right'></i>"
        ],
        nav: true,
        dots: false,
        responsive: {
            0: {
                items: 1,
            },
            480: {
                items: 2,
            },
            576: {
                items: 2,
            },
            768: {
                items: 3,
            },
            1200: {
                items: 5,
            }
        }
    });

    // ---Jewellary Page
    /*===========================================
          Popular Slider
    =============================================*/

    $('.jewellary_popular_slider').owlCarousel({
        loop: true,
        dots: true,
        autoplayHoverPause: true,
        autoplay: true,
        smartSpeed: 1000,
        margin: 30,
        nav: false,
        responsive: {
            0: {
                items: 1,
            },
            480: {
                items: 2,
            },
            576: {
                items: 2,
            },
            768: {
                items: 3,
            },
            1200: {
                items: 4,
            }
        }
    });
    // ---Baby Shop Page
    /*===========================================
         Top Product  Slider
    =============================================*/

    $('.baby_top_slider').owlCarousel({
        loop: true,
        dots: true,
        autoplayHoverPause: true,
        autoplay: true,
        smartSpeed: 1000,
        margin: 30,
        nav: false,
        responsive: {
            0: {
                items: 1,
            },
            480: {
                items: 2,
            },
            576: {
                items: 2,
            },
            768: {
                items: 3,
            },
            1200: {
                items: 4,
            }
        }
    });
    /*=============================================
        =    		 Cart Active  	         =
    =============================================*/
    $(".cart_plus_minus").append('<div class="dec qtybutton">-</div><div class="inc qtybutton">+</div>');
    $(".qtybutton").on("click", function () {
        var $button = $(this);
        var oldValue = $button.parent().find("input").val();
        if ($button.text() == "+") {
            var newVal = parseFloat(oldValue) + 1;
        } else {
            // Don't allow decrementing below zero
            if (oldValue > 0) {
                var newVal = parseFloat(oldValue) - 1;
            } else {
                newVal = 0;
            }
        }
        $button.parent().find("input").val(newVal);
    });

    /*=====================
     24. Cookiebar
     ==========================*/

    window.setTimeout(function () {
        $(".cookie-bar").addClass('show')
    }, 5000);

    $('.cookie-bar .btn, .cookie-bar .btn-close').on('click', function () {
        $(".cookie-bar").removeClass('show')
    });
    // Offer Modal
    $(window).on('load', function () {
        setTimeout(function () {
            $('#exampleModal').modal('show');
        }, 500);
    });
    // Preeloader 
    $(window).on("load", function () {
        $("#status").fadeOut();
        $("#preloader")
            .delay(350)
            .fadeOut("slow");
    });


}(jQuery));

$(function () {
    $('#upload').change(function () {
        var input = this;
        var url = $(this).val();
        var ext = url.substring(url.lastIndexOf('.') + 1).toLowerCase();
        if (input.files && input.files[0] && (ext == "gif" || ext == "png" || ext == "jpeg" || ext == "jpg")) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#avatar').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
        else {
            $('#avatar').attr('src', '/img/team/team1.png');
        }
    });

});
function open_upload() {
    $('#upload').click();
}

function view_cart() {
    $.ajax({
        url: '/user/cart/view-cart',
        type: 'GET',
        dataType: 'json',
        success: function(cart) {
            const formatter = new Intl.NumberFormat('en-US', {
                style: 'currency',
                currency: 'USD'
            });
            var total = 0;
            var html = `<h3>My Cart (${cart.length})</h3>`;
            html += `<div class="products-cart-content">`;
            for (var i = 0; i < cart.length; i++) {
                html += `<div class="products-cart d-flex align-items-center" id="cart-item-${cart[i].productInventoryId}">`;
                html += `<div class="products-image">`;
                html += `<a href="/products/single-product/${cart[i].id}"><img src="data:image/png;base64, ${cart[i].image}" alt="image"></a>`;
                html += `</div>`;
                html += `<div class="products-content">`;
                html += `<h3><a href="/products/single-product/${cart[i].id}">${cart[i].name}</a></h3>`;
                html += `<div class="products-price">`;
                html += `<span>${cart[i].quantity}</span>`;
                html += `<span>&nbsp x &nbsp</span>`;
                html += `<span class="price">${formatter.format(cart[i].price)}</span>`;
                html += `</div>`;
                html += `</div>`;
                html += `<a onclick="remove_cart_item(${cart[i].productInventoryId})" class="remove-btn"><i class="fas fa-trash-alt"></i></a>`;
                html += `</div>`;
                total += cart[i].price * cart[i].quantity;
            }
            html += `</div>`;
            html += `<div class="products-cart-subtotal">`;
            html += `<span>Subtotal</span>`;
            html += `<span class="subtotal">${formatter.format(total)}</span>`;
            html += `</div>`;
            if(cart.length > 0){
                html += `<div class="products-cart-btn">`;
                html += `<a href="/user/cart/checkout" class="theme-btn-one btn-black-overlay btn_md">Checkout</a>`;
                html += `</div>`;
            }
            $('#cart').html(html);
            $('#shoppingCartModal').modal('show');
        }
    });
}

function remove_cart_item(id){
    $.ajax({
        url: '/user/cart/remove-cart-item',
        type: 'POST',
        data: {
            id: id
        },
        dataType: 'json',
        success: function(response) {
            if(response.status == 200){
                view_cart();
            }
        }
    });
}

function view_wishlist() {
    $.ajax({
        url: '/user/cart/view-wishlist',
        type: 'GET',
        dataType: 'json',
        success: function(wishlist) {
            const formatter = new Intl.NumberFormat('en-US', {
                style: 'currency',
                currency: 'USD'
            });
            var html = `<h3>My Wishlist (${wishlist.length})</h3>`;
            html += `<div class="products-cart-content">`;
            for (var i = 0; i < wishlist.length; i++) {
                html += `<div class="products-cart d-flex align-items-center" id="cart-item-${wishlist[i].productId}">`;
                html += `<div class="products-image">`;
                html += `<a href="/products/single-product/${wishlist[i].productId}"><img src="data:image/png;base64, ${wishlist[i].image}" alt="image"></a>`;
                html += `</div>`;
                html += `<div class="products-content">`;
                html += `<h3><a href="/products/single-product/${wishlist[i].productId}">${wishlist[i].name}</a></h3>`;
                html += `<div class="products-price">`;
                html += `<span class="price">${formatter.format(wishlist[i].price)}</span>`;
                html += `</div>`;
                html += `</div>`;
                html += `<a onclick="remove_wishlist_item(${wishlist[i].productId})" class="remove-btn"><i class="fas fa-trash-alt"></i></a>`;
                html += `</div>`;
            }
            html += `</div>`;
            $('#wishlist').html(html);
            $('#shoppingWishlistModal').modal('show');
        }
    });
}

function remove_wishlist_item(id){
    $.ajax({
        url: '/user/cart/remove-wishlist-item',
        type: 'POST',
        data: {
            id: id
        },
        dataType: 'json',
        success: function(response) {
            if(response.status == 200){
                view_wishlist();
            }
        }
    });
}

function add_to_wishlist(id){
    $.ajax({
        url: '/user/cart/add-to-wishlist',
        type: 'POST',
        data: {
            id: id
        },
        dataType: 'json',
        success: function(response) {
            if(response.status == 200){
                view_wishlist();
            }
        }
    });
}

function apply_coupon(){
    $.ajax({
        url: '/user/cart/apply-coupon',
        type: 'POST',
        data: {
            name: $('#coupon').val()
        },
        dataType: 'json',
        success: function(response) {
            if(response.status == 200){
                view_cart();
            }
        }
    });
}