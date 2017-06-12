'use strict';

/* App Module */

var springChat = angular.module('springChat', ['springChat.services',
                                               'springChat.directives','ui.router']);

// Declare app level module which depends on filters, and service
springChat.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

    $stateProvider
        .state('todo', {
            url: '/login',
            templateUrl: 'pages/login.html',
            controller: 'LoginController'
        }).state('chat', {
            url: '/chat',
            templateUrl: 'pages/chat.html',
            //params: { taskObj: null },
            controller: 'ChatController'
        });
    $urlRouterProvider.otherwise('/login');
}]);