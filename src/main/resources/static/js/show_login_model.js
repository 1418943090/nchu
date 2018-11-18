//开启登录model 同时清除错误信息 从头开始
function show_login_model(){
    $("#login_error").attr("class","alert alert-danger alert-dismissible hidden");
    $('#login_model').modal('show');
}