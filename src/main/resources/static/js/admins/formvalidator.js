
$(function(){
    function validator_Init() {
        $('#upForm').bootstrapValidator({
            feedbackIcons: {
                 valid: 'glyphicon glyphicon-ok',
                 invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                title: {
                    validators: {
                        notEmpty: {
                            message: '你还没有输入论文标题哦'
                        },
                        stringLength: {
                            min: 3,
                            max: 30,
                            message: '论文标题长度在3~30个字符哦'
                        }
                    }
                },
                file: {
                    validators: {
                        notEmpty: {
                            message: '你还没选择文件哦!'
                        },
                        regexp: {
                            regexp: /^.*\.pdf$/,
                            message: '论文格式必须是PDF格式的哦'
                        }
                    }
                }
            }
        })
    }
    $('.card').on('click','.btn-add',function() {
        validator_Init();
        $('#myModal').modal('show');
    });

});

function upload_check(){
    // $("#upForm").data('bootstrapValidator').destroy();
    // $('#upForm').data('bootstrapValidator', null);
    var bootstrapValidator = $('#upForm').data('bootstrapValidator');
    bootstrapValidator.validate();
    if(bootstrapValidator.isValid()){//如果校验成功后执行的操作
       uploadSubmit();
    }
}
