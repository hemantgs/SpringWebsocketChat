'use strict';

/* Controllers */
springChat.controller('ChatController', 
	['$scope', '$location', '$interval', 'ChatSocket',function($scope, $location, $interval, chatSocket){
// angular.module('springChat.controllers', ['toaster'])
// 	.controller('ChatController', ['$scope', '$location', '$interval', 'toaster', 'ChatSocket', function($scope, $location, $interval, toaster, chatSocket) {
		  
		var typing = undefined;
		
		$scope.username     = '';
		$scope.sendTo       = 'everyone';
		$scope.participants = [{'username':'hemant'}];
		$scope.messages     = [];
		$scope.newMessage   = ''; 
		  
		$scope.sendMessage = function() {
			var destination = "/Chat/newMessage";
			
			
			 	$scope.messages.unshift({text: $scope.newMessage, author: $scope.username });
			
			
			chatSocket.send(destination, {}, JSON.stringify({text: $scope.newMessage,author: 'you'}));
			$scope.newMessage = '';
		};
		
		$scope.startTyping = function() {
			// Don't send notification if we are still typing or we are typing a private message
	        if (angular.isDefined(typing) || $scope.sendTo != "everyone") return;
	        
	        typing = $interval(function() {
	                $scope.stopTyping();
	            }, 500);
	        
	        chatSocket.send("/topic/chat.typing", {}, JSON.stringify({username: $scope.username, typing: true}));
		};
		
		$scope.stopTyping = function() {
			if (angular.isDefined(typing)) {
		        $interval.cancel(typing);
		        typing = undefined;
		        
		        chatSocket.send("/topic/chat.typing", {}, JSON.stringify({username: $scope.username, typing: false}));
			}
		};
		
		$scope.privateSending = function(username) {
				$scope.sendTo = (username != $scope.sendTo) ? username : 'everyone';
		};
			
		var initStompClient = function() {
			 // var socket = new SockJS('/Chat/newMessage');
    //         chatSocket = Stomp.over(socket);
			chatSocket.init('/Chat/newMessage');
			
			chatSocket.connect(function(frame) {
				  
				//$scope.username = frame.headers['user-name'];

				chatSocket.subscribe("/topic/newMessage", function(message) {
					//console.log(JSON.parse(message.body).body)
					$scope.messages = JSON.parse(message.body).body;
				});
				  
				
			}, function(error) {
				
				//toaster.pop('error', 'Error', 'Connection error ' + error);
				
		    });
		};
		  
		initStompClient();
	}]);
	