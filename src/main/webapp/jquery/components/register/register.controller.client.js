(function () {

    var $usernameFld, $passwordFld, $verifyPasswordFld;
    var $registerBtn;
    var userService = new UserServiceClient();
    $(main);

    function main() {
        $usernameFld = $('#username');
        $passwordFld = $('#password');
        $verifyPasswordFld = $('#vpassword');

        $registerBtn = $('#registerUser');

        $registerBtn.click(register);
    }

    function register() {
        var uId;
        if($verifyPasswordFld.val() === $passwordFld.val()){
            var user = new User($usernameFld.val(), $passwordFld.val());
            userService
                .register(user)
                .then(function (promise) {
                    var value = promise.status;
                    if(value === 200){
                        promise.json().then(function (response) {
                            uId = response.id;
                            window.location.replace("../profile/profile.template.client.html?uid=" + uId);
                        });
                    }
                    else
                        alert("Username already exist");
                });
        }
        else
            alert("password is not same!!");
    }
})();
