//
//
//     function edit_form_validator_Init() {
//         $('#editForm').bootstrapValidator({
//             feedbackIcons: {
//                 valid: 'glyphicon glyphicon-ok',
//                 invalid: 'glyphicon glyphicon-remove',
//                 validating: 'glyphicon glyphicon-refresh'
//             },
//             fields: {
//                 editSchool: {
//                     validators: {
//                         notEmpty: {
//                             message: '你还没有输入学校名称哦'
//                         },
//                         stringLength: {
//                             min: 3,
//                             max: 30,
//                             message: '这个学校名好像不存在哦，要写全名哦'
//                         }
//                     }
//                 },
//                 editEmail: {
//                     validators: {
//                         notEmpty: {
//                             message: '你还没输入邮箱哦'
//                         },
//                         emailAddress: {
//                             message: '邮箱格式错误'
//                         }
//                     }
//                 },
//                 editTel:{
//                     validators:{
//                         notEmpty:{
//                             message:'别忘记输入电话号码哦'
//                         },
//                         regexp: {
//                             regexp: /^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\d{8}$/,
//                             message: '请输入合法的电话号码'
//                         }
//
//                     }
//                 },
//                 editResearch_Direct:{
//                     validators:{
//                         notEmpty:{
//                             message:'别忘记输入研究方向哦'
//                         },
//                         stringLength: {
//                             max: 50,
//                             message: '研究方向简单写下就行了哦'
//                         },
//
//                     }
//                 },
//                 editSelf_Introduction:{
//                     validators:{
//                         notEmpty:{
//                             message:'别忘记自我介绍下哦'
//                         },
//                         stringLength: {
//                             max: 120,
//                             message: '不是警察办案，不用太详细哦'
//                         },
//
//                     }
//                 }
//             }
//         })
//     }
//
//
//     function fedit(user) {
//         document.getElementById("editSchool").value = user.school;
//         document.getElementById("editEmail").value = user.email;
//         document.getElementById("editTel").value = user.tel;
//         document.getElementById("editResearch_Direct").value = user.research_direct;
//         if (user.identity == "teacher") {
//             $("#editTeacher").attr("checked", true);
//         }
//         if (user.identity == "student") {
//             $("#editStudent").attr("checked", true);
//         }
//         document.getElementById("details").value = user.self_introduction;
//
//         edit_form_validator_Init();
//         $("#show_basic_Information_Edit_Modal").modal('show');
//
//     }
//
//
// function edit_check(){
//     var bootstrapValidator = $('#editForm').data('bootstrapValidator');
//     bootstrapValidator.validate();
//     if(bootstrapValidator.isValid()){//如果校验成功后执行的操作
//         var user = [[${user_info}]];
//         var username = user.username;
//         var school =  document.getElementById("editSchool").value;
//         var email  =  document.getElementById("editEmail").value;
//         var tel    =  document.getElementById("editTel").value;
//         var research_direct =  document.getElementById("editResearch_Direct").value;
//         var self_introduction =  document.getElementById("details").value;
//         var identity = $('input:radio[name="editIdentity"]:checked').val();
//
//         var data = {
//             "editUsername":username,
//             "editSchool":school,
//             "editEmail":email,
//             "editTel":tel,
//             "editResearch_Direct":research_direct,
//             "editSelf_Introduction":self_introduction,
//             "editIdentity":identity
//         }
//
//         $.ajax
//         ({
//             url: '/update/basic_information',
//             contentType: 'application/json;charset=utf-8',
//             type: 'post',
//             async:true,
//             data: JSON.stringify(data),
//             dataType:"text",
//             success: function (a) {
//                 var jsonStr = JSON.parse(a);
//                 var obj = document.getElementById("error_data");
//
//                 if(jsonStr.data!=""){
//                     obj.innerText= jsonStr.data;
//                     $("#error").attr("class","alert alert-danger col-sm-5 alert-dismissible");
//                 }else{
//
//                     setTimeout(function(){
//                         $("#show_basic_Information_Edit_Modal").modal('hide');
//                     },500);
//                     setTimeout(function(){
//                         $.ajax({
//                             url: /basic_information/+username,
//                             async:true,
//                             success: function(data){
//                                 $("#rightContainer").html(data);
//                             },
//                             error: function() {
//                                 alert("一不小心就出错了^_^,请刷新试试嘻嘻,还不行的话,及时联系管理员哦");
//                             }
//                         });
//                     },1000);
//                 }
//
//             },
//             error : function() {
//                 alert("一不小心就出错了^_^,请刷新试试嘻嘻,还不行的话,及时联系管理员哦");
//             }
//         });
//     }
// }