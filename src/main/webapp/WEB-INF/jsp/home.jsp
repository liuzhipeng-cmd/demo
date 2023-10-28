<%@ include file="/WEB-INF/common/inhead.jsp" %>
<%@ include file="/WEB-INF/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>综合系统</title>
</head>
<body>
<div id="test1"></div>
<button onclick="abc()">点击触发</button>

<script>
    var tree;
    layui.use('tree', function(){
        tree = layui.tree;

        //渲染
        var inst1 = tree.render({
            elem: '#test1'  //绑定元素
            ,showCheckbox: true // 是否显示复选框
            ,id: 'demo1' // 是否显示复选框
            ,data: [{
                id: '1',
                title: '江西' //一级菜单
                ,children: [{
                    id: '11',
                    title: '南昌' //二级菜单
                    ,children: [{
                        title: '高新区' //三级菜单
                        //…… //以此类推，可无限层级
                    }]
                }]
            },{
                id: '2',
                title: '陕西' //一级菜单
                ,children: [{
                    id: '22',
                    title: '西安' //二级菜单
                }]
            }]
        });
    });

    function abc () {
        var checkData = tree.getChecked('demo1');
        tree.setChecked('demo1',1);
    }
</script>
</body>
</html>
