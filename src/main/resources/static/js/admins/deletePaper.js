function deletePaper(){
  var obj = document.getElementsByClassName("cb");
  var username;
  var check_id = [];
  var k;
  for(k in obj){
      if(obj[k].checked && obj[k].id!=undefined)
      {
          check_id.push(obj[k].id);
      }
  }
  if(check_id.length==0)
  {
      alert("你还没有选择要删除的论文哦");
  }else {
      username = $("#user").attr("username");
      var data = {
          "userName": username,
          "listPaperId": check_id
      };
      $.ajax
      ({
          url: '/delete/paper',
          contentType: 'application/json;charset=utf-8',
          type: 'post',
          async:false,
          data: JSON.stringify(data),
          //dataType:"text",
          success: function (data) {
              $("#rightContainer").html(data);
          },
          error : function() {
              alert("一不小心就出错了^_^,请刷新试试嘻嘻,还不行的话,及时联系管理员哦");
          }
      });
  }
}

