'use strict';
 
App.controller('UserController', ['$scope', 'User', function($scope, User) {
          var self = this;
          self.user= new User();
           
          self.users=[];
               
          self.fetchAllUsers = function(){
              self.users = User.query();
          };
            
          self.createUser = function(){
              self.user.$save(function(){
                  self.fetchAllUsers();
              });
          };
           
          self.updateUser = function(){
              self.user.$update(function(){
                  self.fetchAllUsers();
              });
          };
 
         self.deleteUser = function(identity){
             var user = User.get({id:identity}, function() {
                  user.$delete(function(){
                      console.log('Deleting user with id ', identity);
                      self.fetchAllUsers();
                  });
             });
          };
 
          self.fetchAllUsers();
 
          self.submit = function() {
                
                  self.createUser();
            
          };
               self.edit=function(id)
               {
            	   console.log('id to be edited' ,id);
            	   if(self.user.id==id)
            		   {
            		   self.user=angular.copy(self.users[i]);
            		  
            		   }
               }
     
      }]);