$(".addToCart").click(function () {
    var dataString = "action=addToCart&productId=" + $(this).val();
    $.ajax({
        type: "POST",
        url: "cart",
        data: dataString,
        dataType: "json",

        success: function (data, textStatus, jqXHR) {
            changeHeaderCartCount(data.count);
        },

    });
});

$(".remove_from_cart").click(function (event) {
    var currentElement = $(this);
    var dataString = "action=removeFromCart&productId=" + $(this).val();
    $.ajax({
        type: "POST",
        url: "cart",
        data: dataString,
        dataType: "json",

        success: function (data, textStatus, jqXHR) {
            remove(data, textStatus, jqXHR, currentElement)
        }
    });
});

function remove(data, textStatus, jqXHR, currentElement) {
    changeHeaderCartCount(data.count);
    if (data.success) {
        if (data.count == 0) {
            hideCart();
        }
        currentElement.parents(".top-box").hide('slow', function () {
            $(this).remove;
        });
        $(".total").html("");
        $(".total").append("$" + data.total + ".00");
    }
}

$(".cart-amount-changer").click(function () {
    var currentElement = $(this);
    var dataString;
    if (currentElement.attr('name') == "plus") {
        dataString = "action=incrementQuantity";
    } else if (currentElement.attr('name') == "minus") {
        dataString = "action=decrementQuantity";
    }
    dataString = dataString + "&productId=" + $(this).val();
    $.ajax({
        type: "POST",
        url: "cart",
        data: dataString,
        dataType: "json",

        success: function (data, textStatus, jqXHR) {
            changeQuantity(data, textStatus, jqXHR, currentElement.val());
        }
    });
});

$(".quantity").change(function () {
    var currentElement = $(this);
    id = currentElement.attr('id').substring(currentElement.attr('id').indexOf("quantityId") + "quantityId".length, currentElement.attr('id').length)
    var dataString = "action=changeQuantity&productId=" + id + "&newQuantity=" + currentElement.val();
    $.ajax({
        type: "POST",
        url: "cart",
        data: dataString,
        dataType: "json",

        success: function (data, textStatus, jqXHR) {
            changeQuantity(data, textStatus, jqXHR, id);
        }
    });
});


function changeQuantity(data, textStatus, jqXHR, productId) {
    changeHeaderCartCount(data.count);
    if (data.success) {
        $("#quantityId" + productId).val(data.productQuantity);
        $("#productTotal" + productId + " .actual").html("");
        $("#productTotal" + productId + " .actual").append("$" + data.productTotalPrice + ".00");
        if (data.count == 0) {
            hideCart();
        }
        if (data.productQuantity == 1) {
            $("button[name='minus']").css("disabled", true);
        } else {
            $("button[name='minus']").css("disabled", true);
        }
        $(".total").html("");
        $(".total").append("$" + data.total + ".00");
    }
}

$(".clean_cart").click(function () {
    var dataString = "action=cleanCart";
    $.ajax({
        type: "POST",
        url: "cart",
        data: dataString,
        dataType: "json",

        success: cleanCart
    });
});


function cleanCart(data, textStatus, jqXHR) {
    changeHeaderCartCount(data.count);
    if (data.count == 0) {
        hideCart();
    }
}

function hideCart() {
    $("#cart_table").hide('slow', function () {
        $(".empty_cart_wrapper").removeClass("display-false");
        $("#cart_table").addClass("display-false");
    });
}


function changeHeaderCartCount(count) {
    $("#cart_inner").html("");
    $("#cart_inner").append(count);
}