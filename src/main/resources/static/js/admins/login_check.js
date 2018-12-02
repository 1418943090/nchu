
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
                        message: '你还没有输入用户名哦'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '别忘记输入密码哦'
                    }
                }
            }
        }
    })

});

function login_check(){
    var bootstrapValidator = $('#loginForm').data('bootstrapValidator');
    bootstrapValidator.validate();
    if(bootstrapValidator.isValid()){//如果校验成功后执行的操作
        submit();
    }

    function submit(){
       // document.getElementById("loginForm").submit();//js原生方式表单提交
       // $("#loginForm").submit();

        $("#loginForm").ajaxSubmit(function(message) {
            if(message=='success')
                window.location.href="/index";
           else{
             //  toastr.warning(message);
              swal({
                title: message,
                text: "",
                icon: "warning",
                button: "确定",
            });
           }
        });
    }
}
