angular.module('mainModule')
    .service('AccountService', function ($http, $location) {
        var service = this;

        var contextPath = $location.protocol() + "://" + $location.host() + ":" + $location.port() + '/';

        service.getAll = function (callback) {
            var req = {
                method: 'GET',
                url: contextPath + 'api/account/all'
            };

            $http(req).then(function (resp) {
                callback(true, resp.data)
            }, function () {
                callback(false)
            });

        };

        service.add = function (account, callback) {
            var req = {
                method: 'POST',
                url: contextPath + 'api/account/add',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: account
            };

            $http(req).then(function (resp) {
                callback(true, resp.data)
            }, function () {
                callback(false)
            });
        };

        service.editAccount = function (account, callback) {
            var req = {
                method: 'POST',
                url: contextPath + 'api/account/edit',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: account
            };

            $http(req).then(function (resp) {
                callback(true, resp.data)
            }, function () {
                callback(false)
            });
        };

        service.delete = function (id, callback) {
            var req = {
                method: 'POST',
                url: contextPath + 'api/account/delete/' + id
            };

            $http(req).then(function (resp) {
                callback(true, resp.data)
            }, function () {
                callback(false)
            });
        };

        service.testFunc = function(){};

    });

