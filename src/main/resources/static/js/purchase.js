$(window).on('load', function () {

    $('#file-selector-label')
        .attr('data-delay', '{"show":"1000", "hide":"300"}')
        .tooltip('_fixTitle')
        .tooltip({
            trigger: 'hover'
        });
})

document.getElementById("existRadio").checked = true;

function switchInput() {

    const newRadio = document.getElementById("newRadio");
    const existElements = document.getElementsByClassName("exist-product");
    const newElements = document.getElementsByClassName("new-product");

    for (let i = 0; i < existElements.length; i++) {

        existElements[i].disabled = newRadio.checked;
    }

    for (let i = 0; i < newElements.length; i++) {

        newElements[i].disabled = !newRadio.checked;
    }
}

$('#my-file-selector').change( function () {

    let fileSize = this.files[0].size;
    let maxSize = $(this).attr('maxSize');
    let message = `Invalid Size ${Math.round(fileSize/1024)}KB`;

    if (fileSize > maxSize) {

        $('#upload-file-info').addClass('text-danger')
            .text(message);
        this.setCustomValidity(message);
    }
    else {

        $('#upload-file-info').removeClass('text-danger');
        this.setCustomValidity("");
    }
});