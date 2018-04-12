angular.module('mainModule')
    .controller('HomeCtrl', function ($scope, AccountService) {
        AccountService.getAll(function (isSuccess, data) {
            if (isSuccess) {
                $scope.accounts = data;
            }
        });
        
        $scope.add = function () {
            AccountService.add($scope.newAccount, function (isSuccess, account) {
                if (isSuccess) {
                    $scope.accounts.push(account)
                }
            });
        };


        $scope.edit = function (index, account) {
            $scope.editAccountIndex = index;
            $scope.editAccount = angular.copy(account);
        };

        $scope.applyEdit = function () {
            AccountService.editAccount($scope.editAccount, function (isSuccess, account) {
                if (isSuccess) {
                    $scope.accounts.splice($scope.editAccountIndex , 1, account)
                }
            });
        };

        $scope.delete = function (index, account) {
            AccountService.delete(account.id, function (isSuccess, isDeleted) {
                if (isSuccess && isDeleted) {
                    $scope.accounts.splice(index, 1)
                }
            });
        }
    });

