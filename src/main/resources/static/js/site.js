$(document).ready(function(){
	
	$('#password-error-message').hide();
	$('#confirm-password-error-message').hide();
	//$('#register-submit').prop('disabled', 'true');
	
	var file_element = '<div class="form-group"><input type="file" class="form-control" name="pics" /></div>';
	
	$('#register_password, #register_confirm_password').on('input', function(){
		var pwd = $('#register_password').val();
		var confirm_pwd = $('#register_confirm_password').val();
		
		if(pwd !== confirm_pwd){
			$('#password-error-message').show();
			$('#confirm-password-error-message').show();
		}
		else {
			$('#password-error-message').hide();
			$('#confirm-password-error-message').hide();
			//$('#register-submit').removeAttr('disabled');
		}
	});
	
	$('#upload-extra-file-btn').on('click', function(){
		$(file_element).insertBefore('#upload-another-file-div');
	});
});