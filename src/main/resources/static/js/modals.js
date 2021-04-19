$('.no-review').on('click', function () {
    const transactionId = $(this).attr('transactionId');
    $('#reviewModal').on('show.bs.modal', function () {
        $.get("/modals/review-modal?transactionId=" + transactionId, function (data) {
            $('#reviewModal').find('.modal-content').html(data);
        })
    })
});

$('.no-rating').on('click', function () {
    const transactionId = $(this).attr('transactionId');
    $('#rateAndReviewModal').on('show.bs.modal', function () {
        $.get("/modals/rate-and-review-modal?transactionId=" + transactionId, function (data) {
            $('#rateAndReviewModal').find('.modal-content').html(data);
        })
    })
});

$('.edit-profile').on('click', function () {
    const userId = $(this).attr('userId');
    $('#editProfileModal').on('show.bs.modal', function () {
        $.get("/modals/edit-profile-modal?userId=" + userId, function (data) {
            $('#editProfileModal').find('.modal-content').html(data);
        })
    })
});

$('.change-password').on('click', function () {
    const userId = $(this).attr('userId');
    $('#changePasswordModal').on('show.bs.modal', function () {
        $.get("/modals/change-password-modal?userId=" + userId, function (data) {
            $('#changePasswordModal').find('.modal-content').html(data);
        })
    })
});

$(window).on('load', function() {
    let userParams = new URLSearchParams(window.location.search);
    let wrongPass = userParams.get('wrongPass');
    let userExists = userParams.get('userExists');
    if (wrongPass === 'true') {
        $('#wrongPasswordModal').modal('show');
    }
    if (userExists === 'true') {
        $('#userExistsModal').modal('show');
    }
});