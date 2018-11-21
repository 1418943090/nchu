function  uploadSubmit(){
    document.getElementById("closemodal").click();
  setTimeout(function(){
      document.getElementById("upForm").submit();//js原生方式表单提交
     // $("#upForm").submit();//或者jQuery方式,二选一，不过现在已经没啥项目不引入jQuery了吧。。。
  },500);
    var url =  $(".active").attr("url");
    setTimeout(function(){
        $.ajax({
            url: url,
            async:false,
            success: function(data){
                $("#rightContainer").html(data);
            },
            error: function() {
                alert("一不小心就出错了^_^,请刷新试试嘻嘻,还不行的话,及时联系管理员哦");
            }
        });
    },1000);
}
function  updateSubmit(){
    document.getElementById("closeupdatemodal").click();
    setTimeout(function(){
        document.getElementById("updateForm").submit();//js原生方式表单提交
        //$("#updateForm").submit();//或者jQuery方式,二选一，不过现在已经没啥项目不引入jQuery了吧。。。
    },500);
    var url =  $(".active").attr("url");
    setTimeout(function(){
        $.ajax({
            url: url,
            async:false,
            success: function(data){
                $("#rightContainer").html(data);
            },
            error: function() {
                window.location.href="/error";
            }
        });
    },1000);
}
