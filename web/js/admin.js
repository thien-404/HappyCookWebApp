$(document).ready(function() {
    $('.nav-link-parent').click(function(e) {
        e.preventDefault();

        var $submenu = $(this).next('.submenu');

        $('.submenu').not($submenu).slideUp(200).prev('.nav-link-parent').removeClass('open');
        $submenu.stop(true, true).slideToggle(200);

        $(this).toggleClass('open');
    });

    $('.submenu').click(function(e) {
        e.stopPropagation();
    });
});
