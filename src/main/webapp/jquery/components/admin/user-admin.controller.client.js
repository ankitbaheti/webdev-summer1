(function () {
    jQuery(main);

    var tbody;
    var template;
    var userService = new UserServiceClient();

    function main() {
        tbody = $('tbody');
        template = $('.template');
        $('.wbdv-create').click(createUser);
        $('.wbdv-update').click(updateUser);

        userService.findAllUsers()
            .then(renderUsers);
    }

    function createUser() {
        console.log('createUser');

        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();
        var role = $('#roleFld').val();
        var user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            role: role
        };

        userService
            .createUser(user)
            .then(findAllUsers);
    }

    function findAllUsers() {
        userService
            .findAllUsers()
            .then(renderUsers);
    }

    function renderUsers(users) {
        tbody.empty();
        for(var i=0; i<users.length; i++) {
            var user = users[i];
            var clone = template.clone();

            clone.attr('id', user.id);
            clone.attr('username', user.username);
            clone.attr('password', user.password);
            clone.attr('firstName', user.firstName);
            clone.attr('lastName', user.lastName);
            clone.attr('role', user.role);
            //$('.wbdv-update').attr('id', user.id);


            clone.find('.wbdv-remove').click(deleteUser);
            clone.find('.wbdv-edit').click(renderUser);

            clone.find('.wbdv-username')
                .html(user.username);
            clone.find('.wbdv-first-name')
                .html(user.firstName);
            clone.find('.wbdv-last-name')
                .html(user.lastName);
            clone.find('.wbdv-role')
                .html(user.role);
            tbody.append(clone);
        }
    }

    function renderUser(event) {
        var editBtn = $(event.currentTarget);
        var userId = editBtn.parent().parent().parent().attr('id');
        $('.wbdv-update').attr('id',userId);
        var firstName = editBtn.parent().parent().parent().attr('firstName');
        var lastName = editBtn.parent().parent().parent().attr('lastName');
        var role = editBtn.parent().parent().parent().attr('role');
        var username = editBtn.parent().parent().parent().attr('username');
        var password = editBtn.parent().parent().parent().attr('password');
        $('#firstNameFld').val(firstName);
        $('#lastNameFld').val(lastName);
        $('#roleFld').val(role);
        $('#usernameFld').val(username);
        $('#passwordFld').val(password);
    }

    function updateUser(event) {
        var updateBtn = $(event.currentTarget);
        var userId = updateBtn.attr('id');
        console.log(userId);
        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();
        var role = $('#roleFld').val();
        var user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            role: role
        };

        userService
            .updateUser(userId, user)
            .then(findAllUsers);
    }

    function deleteUser(event) {
        var deleteBtn = $(event.currentTarget);
        var userId = deleteBtn
            .parent()
            .parent()
            .parent()
            .attr('id');

        userService
            .deleteUser(userId)
            .then(findAllUsers);
    }
})();