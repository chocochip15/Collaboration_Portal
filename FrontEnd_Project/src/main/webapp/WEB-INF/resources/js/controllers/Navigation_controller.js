var app = angular.moduel('App',['ngRoute']);

app.config(function($routeProvider){
	$routeProvider
	
	.when('/',{
	templateUrl : 'UserRegistration.html',
	controller: 'user_controller'
	})
});