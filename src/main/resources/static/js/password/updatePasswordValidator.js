
$(function(){

    function validator_Init() {
        $('#UpdatePasswordForm').bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
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
                            message: '请输入确认密码'
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
            }
        });
    }
    validator_Init();
});
function form_check(){
    var bootstrapValidator = $('#UpdatePasswordForm').data('bootstrapValidator');
    bootstrapValidator.validate();
    if(bootstrapValidator.isValid()){//如果校验成功后执行的操作
            form_submit();
    }
}
function form_submit(){

    $.ajax({
        url: "/password/updatepassword",
        contentType: 'application/json;charset=utf-8',
        type: 'post',
        async:true,
        data: $("#firstpassword").val(),
        dataType:'text',
        success: function (data) {
            var jsonStr = JSON.parse(data);
            if(jsonStr.data=="")
                window.location.href="/password/success";
            toastr.error(jsonStr.data);
        },
        error: function () {
            toastr.error("操作失败");
        }
    });
}
