$(document).ready(function () {
    // Add new ingredient input fields
    $('#addIngredient').click(function () {
        const ingredientField = `
                <div class="input-group mb-2">
                   <input type="number" class="form-control" name="MealIDs[]" placeholder="Enter Meal ID" autocomplete="off" required>
                    <input type="number" class="form-control" name="ingredientIDs[]" placeholder="Enter ingredient ID" autocomplete="off" required>
                    <input type="number" class="form-control" name="ingredientQuantities[]" placeholder="Enter ingredient quantity" autocomplete="off" required>
                    <div class="input-group-append">
                        <button type="button" class="btn btn-danger remove-ingredient"><i class="fas fa-minus"></i></button>
                    </div>
                </div>`;
        $('#ingredientsList').append(ingredientField);
    });

    // Remove ingredient input fields
    $(document).on('click', '.remove-ingredient', function () {
        $(this).closest('.input-group').remove();
    });

    // Save ingredients
    $('#saveIngredients').click(function () {
        // Logic to save ingredients
        // You can make an AJAX call here to save ingredients or handle form submission
        $('#ingredientModal').modal('hide');
    });
    $('#ingredientModal').on('hidden.bs.modal', function () {
        $('#ingredientForm')[0].reset();
        $('#ingredientsList').html(`
                    <div class="input-group mb-2">
                         <input type="number" class="form-control" name="MealIDs[]" placeholder="Enter Meal ID" autocomplete="off" required>
                        <input type="number" class="form-control" name="ingredientIDs[]" placeholder="Enter ingredient ID" autocomplete="off" required>
                        <input type="number" class="form-control" name="ingredientQuantities[]" placeholder="Enter ingredient quantity" autocomplete="off" required>
                        <div class="input-group-append">
                            <button type="button" class="btn btn-danger remove-ingredient"><i class="fas fa-minus"></i></button>
                        </div>
                    </div>
                `);
    });
});

function previewImage(event) {
                   var reader = new FileReader();
                      reader.onload = function () {
                                                    var output = document.getElementById('preview');
                                                    output.src = reader.result;
                                                    output.style.display = 'block';
                                                };
                                                reader.readAsDataURL(event.target.files[0]);
                                            }