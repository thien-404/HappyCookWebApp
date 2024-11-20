
$(document).ready(function () {
    const currentUrl = window.location.href;
    if (currentUrl.includes('ManageUsers') || currentUrl.includes('BlockUsersList')) {
        $('#manageUsers').show().prev('.nav-link-parent').addClass('open');
    } else if (currentUrl.includes('AddMealPage') || currentUrl.includes('ManageMeal') || currentUrl.includes('DeletedMealList')) {
        $('#manageMeal').show().prev('.nav-link-parent').addClass('open');
    } else if (currentUrl.includes('AddIngredientPage') || currentUrl.includes('AddIngredientPage') || currentUrl.includes('DeletedIngredientList')) {
        $('#manageIngredient').show().prev('.nav-link-parent').addClass('open');
    }
});
