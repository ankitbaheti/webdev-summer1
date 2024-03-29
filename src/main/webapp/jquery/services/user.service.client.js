function UserServiceClient() {

    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.findUserById = findUserById;
    this.register = register;
    this.login = login;
    this.updateProfile = updateProfile;

    this.url =
        '/api/user';
    var self = this;

    function findUserById(userId) {
        return fetch(self.url + '/' + userId)
            .then(function (response) {
                return response.json();
            });
    }

    function updateProfile(userId, user) {
        return fetch('/api/profile' + '/' + userId, {
            method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        })
            .then(function(response){
                if(response.bodyUsed) {
                    return response.json();
                } else {
                    return null;
                }
            });
    }
    
    function login(user) {
        return fetch('/api/login', {
            method: 'post',
                body: JSON.stringify(user),
                headers: {
                    'content-type': 'application/json'
                }
        });
    }

    function register(user) {
        return fetch('/api/register/', {
            method: 'post',
                body: JSON.stringify(user),
                headers: {
                'content-type': 'application/json'
                }
        });
    }

    function deleteUser(userId) {
        return fetch(self.url + '/' + userId, {
            method: 'delete'
        });
    }

    function updateUser(userId, user) {
        return fetch(self.url + '/' + userId, {
            method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        })
            .then(function(response){
                if(response.bodyUsed) {
                    return response.json();
                } else {
                    return null;
                }
            });
    }

    function findAllUsers() {
        return fetch(self.url)
            .then(function (response) {
                return response.json();
            });
    }

    function createUser(user) {
        return fetch(self.url, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }
}