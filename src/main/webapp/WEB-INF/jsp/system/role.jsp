<%@ include file="/WEB-INF/common/inhead.jsp" %>
<%@ include file="/WEB-INF/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>综合系统</title>
</head>
<body>
<div class="layui-row layui-col-space10">
    <div class="layui-col-md3">
        <div class="layui-form-item">
            <label class="layui-form-label">角色名称</label>
            <div class="layui-input-block">
                <input type="text" id="roleName" name="roleName" placeholder="请输入角色名称"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
</div>
<div class="layui-row layui-col-space10 button-group">
    <div class="layui-form-item">
        <button class="layui-btn layui-btn-radius layui-btn-normal" onclick="searchData()">查询</button>
        <button class="layui-btn layui-btn-radius" onclick="saveData()">新增</button>
    </div>
</div>

<div>
    <table id="roleTable" lay-filter="roleTable"></table>
</div>

<%--新增数据--%>
<div style="display: none" id="saveDialog">
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">角色名称</label>
            <div class="layui-input-block">
                <input type="text" id="roleNameSave" name="roleNameSave" placeholder="请输入角色名称" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
                <textarea id="remarksSave" name="remarksSave" placeholder="请输入内容"
                          class="layui-textarea"></textarea>
            </div>
        </div>
        <%--菜单列表--%>
        <div>
            <hr>
            <table class="layui-table">
                <thead>
                <tr>
                    <th>菜单列表</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td style="width: 50%">
                        <div id="menuTree"></div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </form>
</div>

<%--更新数据表单--%>
<div style="display: none" id="updateDialog">
    <form class="layui-form" action="">
        <input id="updateId" name="updateId" type="hidden" disabled="disabled">
        <div class="layui-form-item">
            <label class="layui-form-label">角色名称</label>
            <div class="layui-input-block">
                <input type="text" id="roleNameUpdate" name="roleNameUpdate" placeholder="请输入角色名称"
                       autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
                <textarea id="remarksUpdate" name="remarksUpdate" placeholder="请输入内容"
                          class="layui-textarea"></textarea>
            </div>
        </div>
        <%--菜单列表--%>
        <div>
            <hr>
            <table class="layui-table">
                <thead>
                <tr>
                    <th>菜单列表</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td style="width: 50%">
                        <div id="menuTree2"></div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </form>
</div>

<script type="text/html" id="dataRoleBarDemo">
    <a class="layui-btn layui-btn-primary layui-border-green layui-btn-sm" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-primary layui-border-red layui-btn-sm" lay-event="del">删除</a>
</script>
<script type="text/javascript">
    var table;
    var form;
    var laydate;
    var tree;
    var menuList; // 菜单列表
    var menuIdList; // 菜单id
    $(function () {
        dataList(); // 加载表格数据
        getMenuTreeData(); // 获得菜单树的数据
    })

    //  点击查询数据
    function searchData() {
        dataList()
    }

    // 查询主数据
    function dataList() {
        layui.use(['table', 'form', 'laydate', 'tree'], function () {
            table = layui.table;
            form = layui.form;
            laydate = layui.laydate;
            tree = layui.tree;
            //第一个实例
            table.render({
                elem: '#roleTable'
                , height: 550
                , url: '${ctx}/listRoleInfoPage' //数据接口
                , where: {
                    roleName: $('#roleName').val()
                }
                , page: true //开启分页
                , cols: [[ //表头
                    {field: 'id', title: 'ID', width: 80, sort: true, fixed: 'left', align: "center"}
                    , {field: 'roleName', title: '角色名称', width: 120, align: "center"}
                    , {field: 'creatorName', title: '创建人', width: 120, align: "center"}
                    , {field: 'createTime', title: '创建时间', width: 200, align: "center"}
                    , {field: 'updatorName', title: '更新人', width: 200, align: "center"}
                    , {field: 'updateTime', title: '更新时间', width: 200, align: "center"}
                    , {field: 'remark', title: '备注', width: 200, align: "center"}
                    , {title: '操作', width: 200, align: 'center', toolbar: '#dataRoleBarDemo', fixed: 'right'}
                ]]
            });
            //触发单元格工具事件
            table.on('tool(roleTable)', function (obj) { // 双击 toolDouble
                if (obj.event === 'del') {
                    layer.confirm('真的删除行么', function (index) {
                        $.ajax({
                            url: '${ctx}/deleteRole',
                            method: 'post',
                            data: {
                                id: obj.data.id
                            }
                        })
                        layer.close(index);
                        dataList();
                    });
                } else if (obj.event === 'edit') {
                    // 回显数据
                    echoData(obj);
                    layer.open({
                        title: '编辑',
                        type: 1,
                        area: ['50%', '70%'],
                        content: $('#updateDialog'),
                        btn: ['确定', '取消'],
                        btnAlign: 'c',
                        btn1: function (index) {
                            var updateId = $('#updateId').val();
                            var roleNameUpdate = $('#roleNameUpdate').val();
                            var remarksUpdate = $('#remarksUpdate').val();
                            var menuTreeData2 = tree.getChecked('menuTreeData2');
                            // 字段校验
                            var validation = formValidation(roleNameUpdate);
                            if (!validation) {
                                $.ajax({
                                    url: ctx + '/updateDataRole',
                                    method: 'post',
                                    data: {
                                        id: updateId,
                                        roleName: roleNameUpdate,
                                        remarks: remarksUpdate,
                                        menuTreeData: JSON.stringify(menuTreeData2)
                                    },
                                    success: function (res) {
                                        if (res.code == 200) {
                                            layer.alert(res.msg);
                                            layer.close(index);
                                            dataList();
                                        } else {
                                            layer.alert(res.msg);
                                        }
                                    }
                                });
                            }
                        }
                    });
                    // 加载菜单列表
                    var inst2 = tree.render({
                        elem: '#menuTree2'  //绑定元素
                        , showCheckbox: true // 是否显示复选框
                        , id: 'menuTreeData2'
                        , data: menuList
                    });
                    tree.setChecked('menuTreeData2', menuIdList); // 回显菜单
                }
            });
        });
    }

    // 新增数据
    function saveData() {
        clearFormData();
        layer.open({
            title: '新增',
            type: 1,
            area: ['50%', '70%'],
            content: $('#saveDialog'),
            btn: ['确定', '取消'],
            btnAlign: 'c',
            btn1: function (index) {
                var roleNameSave = $('#roleNameSave').val();
                var remarksSave = $('#remarksSave').val();
                var menuTreeData = tree.getChecked('menuTreeData');
                // 字段校验
                var validation = formValidation(roleNameSave);
                if (!validation) {
                    $.ajax({
                        url: ctx + '/saveDataRole',
                        method: 'post',
                        data: {
                            roleName: roleNameSave,
                            remarks: remarksSave,
                            menuTreeData: JSON.stringify(menuTreeData)
                        },
                        success: function (res) {
                            if (res.code == 200) {
                                layer.alert(res.msg);
                                layer.close(index);
                                dataList();
                            } else {
                                layer.alert(res.msg);
                            }
                        }
                    });
                }
            }
        });
        // 加载菜单列表
        var inst1 = tree.render({
            elem: '#menuTree'  //绑定元素
            , showCheckbox: true // 是否显示复选框
            , id: 'menuTreeData'
            , data: menuList
        });
    }

    // 新增数据时清空表单数据
    function clearFormData() {
        $('#roleNameSave').val('');
        $('#remarksSave').val('');
    }

    // 校验参数
    function formValidation(roleName) {
        var flag = false;
        if (!roleName) { // 角色名称
            flag = true;
            layer.alert("角色名称不能为空");
            return flag;
        }

        return flag;
    }

    // 修改数据时回显数据
    function echoData(obj) {
        $('#updateId').val(obj.data.id);
        $('#roleNameUpdate').val(obj.data.roleName);
        $('#remarksUpdate').val(obj.data.remarks);
        // 菜单id
        menuIdList = obj.data.menuId;
        form.render(); //更新全部
    }

    // 获得菜单树的数据
    function getMenuTreeData() {
        $.ajax({
            url: ctx + '/menuTreeData',
            method: 'get',
            success: function (res) {
                menuList = res;
            }
        });
    }
</script>
</body>
</html>
