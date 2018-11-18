
$(function(){
	$('#loginForm').bootstrapValidator({
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
		    username: {
	                validators: {
	                	notEmpty: {
	                		message: '用sddddffjjj名称不能为空！'
	                	},
	                	stringLength:{
	                		min:6,
	                		max:30,
	                		message:'用户名长度必须在6到30之间'
	                	}
	                }
			},
			  password:{
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
			}
		}
	})

});

function upload_check(){
	var bootstrapValidator = $('#loginForm').data('bootstrapValidator');
	bootstrapValidator.validate();
	if(bootstrapValidator.isValid()){//如果校验成功后执行的操作
		loginCheck();
	}
};

function loginCheck(){
	var name = document.forms['loginForm'].elements['name'].value;
	var password_md5 = document.getElementById('password_md5');
	
	var password = document.forms['loginForm'].elements['password'].value;
	 password_md5.value = hex_md5(password);
	// password =  password_md5.value;   
	 //var data='{"name":"'+name+'","password":"'+password+'"}'; 
	 var arr= {
			 "name":name ,
			 "password":password
	 };
     //var data = '{"name":name,"password":password}';
	// alert(data);
	 var data = JSON.stringify(arr);
	 
	 alert(data);
	 $.ajax
       ({
           url:'login.action',
           contentType:'application/json;charset=utf-8',
           type:'post',
           data: data, 
       success:function(data)
        { 
    	    
        }
       });
}