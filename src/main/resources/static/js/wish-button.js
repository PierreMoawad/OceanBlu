// Initialize all wishlist buttons with proper colors and tooltips
$(window).on('load', function () {

    let wishButtons = document.getElementsByClassName("wish");

    for (let i = 0; i < wishButtons.length; i++) {

        let productExists = wishButtons[i].getAttribute("product-exists");

        if (productExists === 'true') {

            wishButtons[i].classList.add("text-danger");
            wishButtons[i].setAttribute("title", "Remove from Wishlist");

        } else {

            wishButtons[i].classList.add("text-gray-700");
            wishButtons[i].setAttribute("title", "Add to Wishlist");
        }
    }

    // Activate Bootstrap 4 tooltip
    $('.wish').attr('data-delay', '{"show":"1000", "hide":"300"}')
        .tooltip({
            trigger: 'hover'
        });
});

function isProductInWishlist(productId) {

    return new Promise(function (resolve, reject) {
        $.ajax({
            url: `/products/${productId}/product-in-wishlist`,
            type: 'GET',
            success: function (data) {
                resolve(data);
            },
            error: function (err) {
                reject("Request Failed! " + err);
            }
        });
    });
}

function addProductToWishlist(productId, wishButton) {

    $.ajax({
        url: `/products/${productId}/add-to-wishlist`,
        type: 'PUT',
        success: function () {
            wishButton.addClass('text-danger')
                .removeClass('text-gray-700')
                .attr('title', 'Remove from Wishlist')
                .tooltip('_fixTitle')
                .tooltip('show');
        }
    });
}

function removeProductFromWishlist(productId, wishButton) {

    $.ajax({
        url: `/products/${productId}/remove-from-wishlist`,
        type: 'DELETE',
        success: function () {
            wishButton.addClass('text-gray-700')
                .removeClass('text-danger')
                .attr('title', 'Add to Wishlist')
                .tooltip('_fixTitle')
                .tooltip('show');
        }
    });
}

$('.wish').on('click', function (event) {

    // Prevents going to top of page upon clicking
    event.preventDefault();

    let wishButton = $(this);
    let productId = wishButton.attr('product-id');

    // Wait for get request to be successfully executed
    // to see whether product is in wishlist or not
    isProductInWishlist(productId).then(
        result => result ?
            removeProductFromWishlist(productId, wishButton) :
            addProductToWishlist(productId, wishButton)
    )
});