(function () {
    var $usernameFld, $phoneFld;
    var $emailFld, $roleFld, $dobFld;
    var $updateBtn, $logoutBtn;
    var uid;
    var userService = new UserServiceClient();
    $(main);

    function main() {

        var url = new URL(window.location.href);
        uid = url.searchParams.get("uid");

        $usernameFld = $('#username');
        $phoneFld = $('#phoneFld');
        $emailFld = $('#emailFld');
        $roleFld = $('#roleFld');
        $dobFld = $('#dobFld');

        $updateBtn = $('#updateBtn');
        $logoutBtn = $('#logoutBtn');

        $updateBtn.click(update);
        $logoutBtn.click(logout);

        userService
            .findUserById(uid)
            .then(function (value) {
                $usernameFld.val(value.username);
                $phoneFld.val(value.phone);
                $emailFld.val(value.email);
                $roleFld.val(value.role);
                $dobFld.val(value.dateOfBirth.substr(0,10));
            });
    }

    function update() {
        var user = {
            username: $usernameFld.val(),
            phone: $phoneFld.val(),
            email: $emailFld.val(),
            dateOfBirth: $dobFld.val(),
            role: $roleFld.val()
        };

        userService
            .updateProfile(uid, user)
            .then(function () {
                alert('user updated');
            });
    }
    function logout() {
        window.location.replace("../login/login.template.client.html");
    }
})();
