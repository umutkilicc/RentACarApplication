$(document).ready(() => fetchAllFoods())

function fetchAllFoods() {

    $.getJSON("get-all-foods",
        data => {
            let foods = '';

            $.each(data, (key, value) => {
                foods +=
                    '<tr id="food' + value['id'] + '" >' +
                    '    <td>' + value['id'] + '</td> ' +
                    '    <td>' + value['category'] + '</td> ' +
                    '    <td>' + value['title'] + '</td> ' +
                    '    <td>' + value['price'] + '</td> ' +
                    '    <td> ' +
                    '        <a class="tomato-text" onclick="' + 'deleteFood(\'' + value['id'] + '\')">Delete</a> ' +
                    '    </td>' +
                    '</tr>';
            })

            $('#foods-table').append(foods);
        }
    )
}

function deleteFood(id) {

    $.ajax({
        url: 'admin/delete-food/' + id,
        method: "GET",
        success: $('#food' + id).remove()
    });
}