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
        if($verifyPasswordFld.val() === $passwordFld.val()){
            var user = new User($usernameFld.val(), $passwordFld.val());
            userService
                .register(user)
                .then(function (value) {
                    if(value === 200){
                        console.log("success");
                        window.location.replace("../profile/profile.template.client.html");
                    }
                    else
                        alert("Username already exist");
                });
        }
        else
            alert("password is not same!!");
    }
})();
