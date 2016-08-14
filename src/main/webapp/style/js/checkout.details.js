$("#payment_type").change(function () {
    disableOdd(this, "#card_number", "card");
});

$("#shipping_type").change(function () {
    disableOdd(this, "#address", "to_door");
});

function disableOdd(select, input, condition) {
    if ($(select).val() != condition) {
        $(input).prop('disabled', true);
    } else {
        $(input).prop('disabled', false);
    }
};

$(".order_details_form").submit(function (event) {
    if (!$("#address").prop('disabled')) {
        if (!$("#address").val().match("[\s\dA-Za-z.,-]{5,20}")) {
            $("#address").addClass('invalid');
            event.preventDefault();
        }
    }
    if (!$("#card_number").prop('disabled')) {
        if (!$("#card_number").val().match("[0-9]{16}")){
            event.preventDefault();
            $("#card_number").addClass('invalid');
        }
    }
});

function validateCardNumber(event) {
    event = event || window.event;
    if (event.charCode && (event.charCode < 48 || event.charCode > 57))
        return false;
};
