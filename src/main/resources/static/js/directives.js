/* Directives */

angular.module('springChat.directives', [])
	.directive('printMessage', function () {
	    return {
	    	restrict: 'A',
	        template: '<strong>{{message.author}}{{message.text}}<br/>'
	       
	    };
});