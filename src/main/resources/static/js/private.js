$(document).ready(function() {
	var socket = new SockJS("/ws");
	var stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame1) {
		stompClient.subscribe('/user/queue/notifications', function(frame2) {
			var msg = JSON.parse(frame2.body);	
			var body = msg.body;
			var author = msg.author;
			var created = msg.created;
			$('#messageList').prepend(created + ' - @' + author + ': ' + body + '\n\n');
		});
	}, function(error) {
		console.log('Chat Error: ', error);
	});
	
	userToChatWith:function(e){
		
	}

	$('#sendMessageBtn').click(function(e) {
		e.preventDefault();
		var text = $('#currentUserMessage').val().trim();
		if(text === ''){
			$('#currentUserMessage').val('');
			return;
		}
		var selectedUsers = $('#activeUserList').val();
		if(selectedUsers.length === 0){
			alert("Select User!")
			return;
		}
		stompClient.send('/app/private', {}, JSON.stringify({
			'body': text,
			'recipients': selectedUsers
			}));
		$('#currentUserMessage').val('');
	});
});