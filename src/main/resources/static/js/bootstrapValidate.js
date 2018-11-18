
$(function(){
	$('#RegisteredForm').bootstrapValidator({
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
			registered_username: {
	                validators: {
	                	notEmpty: {
	                		message: '用户名称不能为空！'
	                	},
	                	stringLength:{
	                		min:6,
	                		max:30,
	                		message:'用户名长度必须在6到30之间'
	                	}
	                }
			},
			registered_password:{
				 validators: {
					 notEmpty: {
						 message: '用户密码不能为空！'
					 },
					 stringLength:{
						 min:6,
						 max:60,
						 message:'密码长度必须在6到30之间'
					 },
					 regexp:{
						 regexp:/^[a-zA-Z0-9_\.]+$/,
						 message:'密码由数字字母下划线和.组成'
					 }
	              }
			},
			registered_confirmpassword:{
				 message:'密码无效',
				 validators: {
					 notEmpty: {
						 message: '用户密码不能为空！'
					 },
					 identical:{
						 filed: 'registered_password',
						 message:'两次密码不一样'
					 }

	              }
			},
			registered_email:{
				 validators: {
					 notEmpty: {
						 message: 'Email不能为空！'
					 }
	              }
			},
			registered_name:{
				 validators: {
					 notEmpty: {
						 message: '姓名不能为空！'
					 }
	              }
			},
			registered_tel:{
				 validators: {
					 notEmpty: {
						 message: '电话号码不能为空！'
					 }
	              }
			}
			
		}
	})

});

function registered_check(){
	var bootstrapValidator = $('#RegisteredForm').data('bootstrapValidator');
	bootstrapValidator.validate();
	if(bootstrapValidator.isValid()){//如果校验成功后执行的操作
		loginCheck();
	}
};