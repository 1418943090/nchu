
$(function(){




    function validator_Init() {
        $('#BasicInformationForm').bootstrapValidator({
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
                password: {
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
                birthDate:{
                   trigger:"click",
                    validators:{
                        notEmpty:{
                            message:'请输入出生日期'
                        },
                        callback: {
                            message: '开始时间必须小于结束时间',
                            callback: function (value, validator, $field) {
                                var   d=new   Date(Date.parse(value .replace(/-/g,"/")));
                                var   curDate=new   Date();
                                if(d <=curDate){
                                    return true;
                                }else{
                                    return false;
                                }
                            }
                        }

                    }
                }, name:{
                    validators: {
                        notEmpty: {
                            message: '请输入姓名'
                        },
                        stringLength: {
                            min: 2,
                            max: 4,
                            message: '请输入合法的中文姓名'
                        }
                    }
                },tel:{
                    validators:{
                        notEmpty:{
                            message:'别忘记输入电话号码哦'
                        },
                        regexp: {
                            regexp: /^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\d{8}$/,
                            message: '请输入合法的电话号码'
                        }

                    }
                },birthplace:{

                    validators: {
                        notEmpty: {
                            message: '请输入籍贯'
                        },
                        stringLength: {
                            min: 2,
                            max: 10,
                            message: '请输入正确的籍贯:(江西 南昌)'
                        }
                    }
                },
                school:{
                    validators: {
                        notEmpty: {
                            message: '请输入所在学校名称'
                        },
                        stringLength: {
                            min: 4,
                            max: 20,
                            message: '请输入正确的学校全称'
                        }
                    }
                },research_direct:{
                    validators: {
                        notEmpty: {
                            message: '请输入研究方向'
                        },
                        stringLength: {
                            min: 2,
                            max: 20,
                            message: "格式错误"
                        }
                    }
                },file:{
                    validators: {
                        notEmpty: {
                            message: '你还没选择文件哦!'
                        },
                        regexp: {
                            regexp: /^.*?(png|jpg)$/,
                            message: '图片必须是png或jpg格式的哦'
                        }
                    }
                },self_introduction:{
                    validators: {
                        notEmpty: {
                            message: '请简单简介下你自己'
                        },
                        stringLength: {
                            min: 50,
                            max: 400,
                            message: "自我简介字数要适当哦"
                        }
                    }
                }

            }
        });
    }
    validator_Init();

    // $('#birthDate').change(function() {
    //     alert("change");
    //     $('#BasicInformationForm').data('bootstrapValidator').updateStatus('birthDate',
    //         'NOT_VALIDATED',null).validateField('birthDate');
    // });


});
//
// function fa() {
//     alert("change");
//     $('#BasicInformationForm').data('bootstrapValidator').updateStatus('birthDate',
//         'NOT_VALIDATED',null).validateField('birthDate');
// }


function form_check(){
    var bootstrapValidator = $('#BasicInformationForm').data('bootstrapValidator');
    bootstrapValidator.validate();
    if(bootstrapValidator.isValid()){//如果校验成功后执行的操作
            submit()
    }
}
 function submit() {

        $("#BasicInformationForm").ajaxSubmit(function(message) {
            if(message=='success')
                window.location.href="/registry/step3";
            toastr.error(message);
        });
        return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
    }
