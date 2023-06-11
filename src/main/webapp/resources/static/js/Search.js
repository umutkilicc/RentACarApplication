let search = $('#search-input');

$(document).ready(() => searchFood());

search.keyup(() => searchFood());

function searchFood() {
    $.ajax({
        url: 'search-foods-by-name?name=' + search.val(),
        type: "GET",
        success: data => {

            if (data.length === 0) {

                document.getElementById('foods-table').innerHTML = "<p class='not-found'>" + search.val() + " not available!</p>";
                return;
            }

            writeFoodHtml(data);
        }
    });
}