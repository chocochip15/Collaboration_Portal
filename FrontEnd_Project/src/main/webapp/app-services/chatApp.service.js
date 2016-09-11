angular.module("chatApp.services").service("CharService",function($q,$timeout){

var service ={} ,listner = $q.defer() ,socket={
	
	client:null,
	stomp:null
}, messageIds=[];

service.RECONNECT_TIMEOUT = 30000;
service.SOCKET_URL="/chatappbackend/chat";
service.CHAT_TOPIC="/topic/message";
service.CHAT_BROKER="/app/chat"

service.recive=function(){
	return listener.promise;
};
serivce.send=function(message){
	var id=Math.floor(Math.random()* 1000000);
	socket.stomp.send(service.CHAT_BROKER,{
	priority:9
	}, JSON.stringify({
	message:message,
	id:id
	}));
	messageids.push(id);
};

var reconnect = function(){
	$timeout(function(){
	initialize();
	},this.RECONNECT_TIMEOUT);
};

var getMessage = function(data){
	var message = JSON.parse(data) , out={};

	out.message=message.message;
	out.time=new Date(message.time);
if(_.contains(messageIds, message.id))
{
	out.self=true;
	messageIds= _.remove(messageIds, message.id);
}
return out;
};

var startListner = function() {
	socket.stomp.subcribe(service.CHAT_TOPIC ,function(data){
	listner.notify(getMessage(data.body));
	});
};

var initialize = function() {
	socket.client=new SockJS(service.SOCKET_URL);
	socket.stomp=stomp.over(socket.client);
	socket.stomp.connect({}, startListner);
	socket.stomp.onclose = reconnect;
	
};
});
