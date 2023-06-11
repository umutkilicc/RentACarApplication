$(document).ready(() => fetchAllUsers())

function fetchAllUsers() {

    $.getJSON("admin/get-all-users",
        data => {
            let user = '';

            $.each(data, (key, value) => {
                user +=
                    '<tr id="row-user-' + value['id'] + '">' +
                    '    <td>' + value['id'] + '</td> ' +
                    '    <td>' + value['username'] + '</td> ' +
                    '    <td>' + value['email'] + '</td> ' +
                    '    <td>' + value['spent'] + ' TL</td> ' +
                    '    <td id="status-user-' + value['id'] + '"> ';
                user += value['enabled'] === true ? 'Enabled</td><td>' : 'Disabled</td><td>';
                user += value['enabled'] === true
                    ? '        <a  id="user' + value['id'] + '" class="tomato-text" onclick="' + 'changeStatus(\''  + value['id'] + '\' )">Disable</a></td>'
                    : '        <a  id="user' + value['id'] + '" class="green-text" onclick="' + 'changeStatus(\'' + value['id'] + '\')">Enable</a></td>';
                user += '<td><a class="red-text delete-col" onclick="' + 'deleteUser(\'' + value['id'] + '\')">Delete</a></td></tr>';
            })

            $('#users-table').append(user);
        }
    )
}

function deleteUser(id) {

    $.ajax({
        url: 'admin/delete-user-by-id/' + id,
        method: "GET",
        success: $('#row-user-' + id).remove()
    });
}

function changeStatus(id) {
    let actionElement = document.getElementById('user' + id);
    let statusElement = document.getElementById('status-user-' + id);

    if (actionElement.innerText === 'Enable') {
        $.get('admin/enable-user/' + id);
        actionElement.innerText = 'Disable';
        actionElement.classList.remove('green-text');
        actionElement.classList.add('tomato-text');
        statusElement.innerText = 'Enabled';
    } else {
        $.get('admin/disable-user/' + id);
        actionElement.innerText = 'Enable';
        actionElement.classList.remove('tomato-text');
        actionElement.classList.add('green-text');
        statusElement.innerText = 'Disabled';
    }
}