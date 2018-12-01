
$(function(){



    function validator_Init() {
        $('#RegistryForm').bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                username: {
                    validators: {
                        notEmpty: {
                            message: '请输入用户名'
                        },
                        stringLength: {
                            min: 3,
                            max: 30,
                            message: '用户名格式不正确'
                        }
                    }
                },
                firstpassword: {
                    validators: {
                        notEmpty: {
                            message: '请输入密码'
                        },
                        stringLength: {
                            min: 6,
                            max: 30,
                            message: '密码长度为6~30个字符'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_\.]+$/,
                            message: '密码由数字字母下划线和.组成'
                        }
                    }
                },
                secondpassword: {
                    validators: {
                        notEmpty: {
                            message: '请输入密码'
                        },
                        stringLength: {
                            min: 6,
                            max: 30,
                            message: '密码长度为6~30个字符'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_\.]+$/,
                            message: '密码由数字字母下划线和.组成'
                        },
                        identical: {
                            field: 'firstpassword',
                            message: '两次密码不一样'
                        }

                    }
                },
                email: {
                    validators: {
                        notEmpty: {
                            message: '请输入邮箱地址'
                        },
                        emailAddress: {
                            message: '邮箱格式错误'
                        }
                    }
                }
            }
        });
    }
    validator_Init();
});

function form_check(val,i){
    var bootstrapValidator = $('#RegistryForm').data('bootstrapValidator');
    bootstrapValidator.validate();
    if(bootstrapValidator.isValid()){//如果校验成功后执行的操作

        if(i==1)
        getCode(val);
        else if(i==2){
            form_submit();
        }
    }
}
var countdown=60;

function getCode(val) {

    var email = $("#email").val();
    settime(val,0);
        $.ajax({
            url: "/registry/email/code",
            contentType: 'application/json;charset=utf-8',
            type: 'post',
            async:true,
            data: email,
            success: function (data) {
                toastr.success("验证码已发送至你的邮箱");
            },
            error: function () {
                toastr.error("操作失败");
            }
        });

}
function settime(val,flag) {
    if (countdown == 0) {
        val.removeAttribute("disabled");
        val.value="获取验证码";
        countdown = 60;
        flag++;
    } else {
        val.setAttribute("disabled", true);
        val.value="重新发送(" + countdown + ")";
        countdown--;
    }
    if(flag<=0) {

        setTimeout(function () {
            settime(val,flag)
        }, 1000)
    }
}







function form_submit(){

    var data = {
      "username":$("#username").val(),
      "firstpassword":$("#firstpassword").val(),
      "secondpassword":$("#secondpassword").val(),
      "email":$("#email").val(),
      "vcode":$("#vcode").val()
    };
    $.ajax({
        url: "/registry/step1",
        contentType: 'application/json;charset=utf-8',
        type: 'post',
        async:true,
        data: JSON.stringify(data),
        dataType:'text',
        success: function (data) {
            var jsonStr = JSON.parse(data);
            if(jsonStr.data=="")
                window.location.href="/registry/step2";
            toastr.error(jsonStr.data);
        },
        error: function () {
            toastr.error("操作失败");
        }
    });



}