$(document).ready(() => fetchAllUsers())

function fetchAllUsers() {

    $.getJSON("admin/get-all-users",
        data => {
            let user = '';

            $.each(data, (key, value) => {
                let admin = value['roles'].length !== 1;
                user +=
                    '<tr>' +
                    '    <td>' + value['id'] + '</td> ' +
                    '    <td>' + value['username'] + '</td> ' +
                    '    <td>' + value['email'] + '</td> ' +
                    '    <td id="role' + value['id'] + '">' + titleCase(value['roles'][0]['name']);
                if (admin) user += ', ' + titleCase(value['roles'][1]['name']);
                user += '    </td> ';
                user += admin === true
                    ? '        <td><a  id="user' + value['id'] + '" class="tomato-text" onclick="' + 'modifyRole( \'' + value['id'] + '\')">Remove Admin</a></td>'
                    : '        <td><a  id="user' + value['id'] + '" class="green-text" onclick="' + 'modifyRole(\'' + value['id'] + '\')">Add Admin</a></td>'
            })

            $('#users-table').append(user);
        }
    )
}

function modifyRole(id) {

    let actionElement = document.getElementById('user' + id);
    let roleElement = document.getElementById('role' + id);

    if (actionElement.innerText === 'Remove Admin') {
        $.get('admin/remove-admin/' + id);
        actionElement.innerText = 'Add Admin';
        actionElement.classList.remove('tomato-text');
        actionElement.classList.add('green-text');
        roleElement.innerHTML = 'Customer';
    } else {
        $.get('admin/make-admin/' + id);
        actionElement.innerText = 'Remove Admin';
        actionElement.classList.remove('green-text');
        actionElement.classList.add('tomato-text');
        roleElement.innerHTML = 'Customer, Admin';
    }
}

function titleCase(str) {
    return str.toLowerCase().replace(/(^|\s)\S/g, L => L.toUpperCase());
}