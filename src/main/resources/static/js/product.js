function inputProductData(){
    var product = $('#form').serialize();
    $.ajax({
        url: "/product/get",
        data: product,
        type:"POST",
        cache: false
    }).done(function (fragment) {
        $("#list").replaceWith(fragment);
    });
}