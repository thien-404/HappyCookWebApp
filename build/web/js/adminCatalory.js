$(document).ready(function () {
    // Collapse all submenus by default
     
    $('.submenu').hide();

    // Restore the state of submenus from localStorage
    var openSubmenu = localStorage.getItem('openSubmenu');
    if (openSubmenu) {
        $(openSubmenu).show().prev('.nav-link-parent').addClass('open');
    }

    // Toggle submenu on click and save state to localStorage
    $('.nav-link-parent').click(function (e) {
        e.preventDefault();

        var $submenu = $(this).next('.submenu');

        $('.submenu').not($submenu).slideUp(200).prev('.nav-link-parent').removeClass('open');
        $submenu.stop(true, true).slideToggle(200);

        $(this).toggleClass('open');

        // Save the state of the currently open submenu to localStorage
        if ($submenu.is(':visible')) {
            localStorage.setItem('openSubmenu', '#' + $submenu.attr('id'));
        } else {
            localStorage.removeItem('openSubmenu');
        }
    });

    $('.submenu').click(function (e) {
        e.stopPropagation();
    });

});
            