


$(function() {
    // 菜单事件
    $(".blog-menu .list-group-item").on('click',function() {
        var url = $(this).attr("url");
        // 先移除其他的点击样式，再添加当前的点击样式
        $(".blog-menu .list-group-item").removeClass("active");
        $(this).addClass("active");
        // 加载其他模块的页面到右侧工作区
        $.ajax({
            url: url,
            async:true,
            success: function(data){
                $("#rightContainer").html(data);
            },
            error: function() {
                //toastr.error("一不小心就出错了^_^,请刷新试试嘻嘻,还不行的话,及时联系管理员哦");
                swal({
                    title: "Error!",
                    text: '服务器处理错误',
                    icon: "error",
                    button: "确定",
                });
            }
        });
    });
    // 选中菜单第一项
    $(".blog-menu .list-group-item:first").trigger("click");
});
