$(document).ready(function() {
	var socket = new SockJS("/ws");
	var stompClient = Stomp.over(socket);

	stompClient.connect({}, function(frame1) {
		stompClient.subscribe('/chat', function(frame2) {
			var msg = frame2.body;			
			$('#messageList').prepend(msg + '\n\n');
		});
	}, function(error) {
		console.log('Chat Error: ', error);
	});

	$('#sendMessageBtn').click(function(e) {
		e.preventDefault();
		var text = $('#currentUserMessage').val().trim();
		if(text === ''){
			$('#currentUserMessage').val('');
			return;
		}
		var selectedUsers = $('#activeUserList').val();
		stompClient.send('/app/send', {}, JSON.stringify({
			'body': text,
			'recipients': selectedUsers
			}));
		$('#currentUserMessage').val('');
	});
});