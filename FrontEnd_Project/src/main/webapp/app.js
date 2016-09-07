	var App = angular.module('App',['ngRoute','ngResource']);

	app.config(function($routeProvider){
		$routeProvider
		
		.when('/',{
		templateUrl : 'UserRegistration.html',
		controller: 'user_controller'
		})
	});