<%@ include file="/WEB-INF/common/inhead.jsp" %>
<%@ include file="/WEB-INF/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>综合系统</title>
</head>
<body>
<div>
    <div class="layui-row layui-col-space10">
        <div class="layui-col-md3">
            <div class="layui-form-item">
                <label class="layui-form-label">菜单名称</label>
                <div class="layui-input-block">
                    <input type="text" id="menuName" name="menuName" placeholder="请输入菜单名称"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-col-md3">
            <div class="layui-form-item">
                <label class="layui-form-label">菜单类型</label>
                <div class="layui-input-block">
                    <select id="menuType" name="menuType" class="layui-input">
                        <option value="-1">全部</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-col-md3">
            <div class="layui-form-item">
                <label class="layui-form-label">菜单状态</label>
                <div class="layui-input-block">
                    <select id="menuStatus" name="menuStatus" class="layui-input">
                        <option value="-1">全部</option>
                        <option value="0">未锁定</option>
                        <option value="1">锁定</option>
                    </select>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-row layui-col-space10 button-group">
        <div class="layui-form-item">
            <input type="hidden" id="pid" name="pid" value="${menuPid}">
            <button class="layui-btn layui-btn-radius layui-btn-normal" onclick="searchData()">查询</button>
            <button class="layui-btn layui-btn-radius" onclick="saveData()">新增</button>
        </div>
    </div>
</div>

<div>
    <table id="menuTable" lay-filter="menuTable"></table>
</div>

<%--新增数据表单--%>
<div style="display: none" id="saveDialog">
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">菜单名称</label>
            <div class="layui-input-block">
                <input type="text" id="menuNameSave" name="menuNameSave" placeholder="请输入菜单名称" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">菜单类型</label>
            <div class="layui-input-block">
                <select id="menuTypeSave" name="menuTypeSave">
                    <option value="-1">请选择</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">菜单动作</label>
            <div class="layui-input-block">
                <input type="text" id="menuActionSave" name="menuActionSave" placeholder="请输入菜单动作"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">显示顺序</label>
            <div class="layui-input-inline">
                <input type="text" id="menuOrderSave" name="menuOrderSave" placeholder="请输入显示顺序"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">菜单备注</label>
            <div class="layui-input-block">
                <textarea id="menuRemarksSave" name="menuRemarksSave" placeholder="请输入内容"
                          class="layui-textarea"></textarea>
            </div>
        </div>
    </form>
</div>

<%--更新数据表单--%>
<div style="display: none" id="updateDialog">
    <form class="layui-form" action="">
        <input id="updateId" name="updateId" type="hidden" disabled="disabled">
        <div class="layui-form-item">
            <label class="layui-form-label">菜单名称</label>
            <div class="layui-input-block">
                <input type="text" id="menuNameUpdate" name="menuNameUpdate" placeholder="请输入菜单名称"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">菜单类型</label>
            <div class="layui-input-block">
                <select id="menuTypeUpdate" name="menuTypeUpdate" disabled="disabled">
                    <option value="-1">请选择</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">菜单动作</label>
            <div class="layui-input-block">
                <input type="text" id="menuActionUpdate" name="menuActionUpdate" placeholder="请输入菜单动作"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">显示顺序</label>
            <div class="layui-input-inline">
                <input type="text" id="menuOrderUpdate" name="menuOrderUpdate" placeholder="请输入显示顺序"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">菜单备注</label>
            <div class="layui-input-block">
                <textarea id="menuRemarksUpdate" name="menuRemarksUpdate" placeholder="请输入内容"
                          class="layui-textarea"></textarea>
            </div>
        </div>
    </form>
</div>

<script type="text/html" id="dataMenuBarDemo">
    <a class="layui-btn layui-btn-primary layui-border-green layui-btn-sm" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-primary layui-border-red layui-btn-sm" lay-event="del">删除</a>
</script>
<script>
    var table;
    var form;
    $(function () {
        dataList();
    })

    //  点击查询数据
    function searchData() {
        dataList()
    }

    // 查询主数据
    function dataList() {
        layui.use(['table', 'form'], function () {
            table = layui.table;
            form = layui.form;
            //第一个实例
            table.render({
                elem: '#menuTable'
                , height: 550
                , url: '${ctx}/listMenuInfoChildPage' //数据接口
                , where: {
                    menuName: $('#menuName').val(),
                    menuType: $('#menuType').val(),
                    menuStatus: $('#menuStatus').val(),
                    pid: $('#pid').val()
                }
                , page: true //开启分页
                , cols: [[ //表头
                    {field: 'id', title: 'ID', width: 80, sort: true, fixed: 'left', align: "center"}
                    , {field: 'menuName', title: '菜单名称', width: 120, align: "center"}
                    , {field: 'menuType', title: '菜单类型', width: 120, align: "center"}
                    , {field: 'menuAction', title: '菜单动作', width: 120, align: "center"}
                    , {field: 'creatorName', title: '创建人', width: 120, align: "center"}
                    , {field: 'updatorName', title: '更新人', width: 120, align: "center"}
                    , {field: 'menuOrder', title: '显示顺序', width: 120, align: "center"}
                    , {field: 'menuRemarks', title: '菜单备注', width: 150, align: "center"}
                    , {
                        field: 'menuStatus', title: '菜单状态', width: 120, align: "center", templet: function (d) {
                            if (d.menuStatus == 1) {
                                return '<a style="color: green" onclick="statusOpenClose(\'' + d.id + '\',0)">启用</a>'
                            } else {
                                return '<a style="color: red" onclick="statusOpenClose(\'' + d.id + '\',1)">锁定</a>'
                            }
                        }
                    }
                    , {title: '操作', width: 260, align: 'center', toolbar: '#dataMenuBarDemo', fixed: 'right'}
                ]]
            });
            //触发单元格工具事件
            table.on('tool(menuTable)', function (obj) { // 双击 toolDouble
                if (obj.event === 'del') {
                    layer.confirm('真的删除行么', function (index) {
                        $.ajax({
                            url: '${ctx}/deleteMenu',
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
                            var menuNameUpdate = $('#menuNameUpdate').val();
                            var menuActionUpdate = $('#menuActionUpdate').val();
                            var menuOrderUpdate = $('#menuOrderUpdate').val();
                            var menuRemarksUpdate = $('#menuRemarksUpdate').val();
                            // 字段校验
                            var validation = formValidation(menuNameUpdate, menuTypeUpdate, menuActionUpdate, menuOrderUpdate);
                            if (!validation) {
                                $.ajax({
                                    url: ctx + '/updateDataMenu',
                                    method: 'post',
                                    data: {
                                        id: updateId,
                                        menuName: menuNameUpdate,
                                        menuAction: menuActionUpdate,
                                        menuOrder: menuOrderUpdate,
                                        menuRemarks: menuRemarksUpdate
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
                var menuNameSave = $('#menuNameSave').val();
                var menuTypeSave = $('#menuTypeSave').val();
                var menuActionSave = $('#menuActionSave').val();
                var menuOrderSave = $('#menuOrderSave').val();
                var menuRemarksSave = $('#menuRemarksSave').val();
                var pid = $('#pid').val();
                // 字段校验
                var validation = formValidation(menuNameSave, menuTypeSave, menuActionSave, menuOrderSave);
                if (!validation) {
                    $.ajax({
                        url: ctx + '/saveDataMenu',
                        method: 'post',
                        data: {
                            menuName: menuNameSave,
                            menuType: menuTypeSave,
                            menuAction: menuActionSave,
                            menuOrder: menuOrderSave,
                            menuRemarks: menuRemarksSave,
                            pid: pid
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
    }

    // 表单验证
    function formValidation(menuName, menuType, menuAction, menuOrder) {
        var flag = false;
        if (!menuName) { // 菜单名称
            flag = true;
            layer.alert("菜单名称不能为空");
            return flag;
        }
        if (!menuType) { // 菜单类型
            flag = true;
            layer.alert("菜单类型不能为空");
            return flag;
        }
        if (!menuAction) { // 菜单动作
            flag = true;
            layer.alert("菜单动作不能为空");
            return flag;
        }
        if (!menuOrder) { // 显示顺序
            flag = true;
            layer.alert("显示顺序不能为空");
            return flag;
        }
        return flag;
    }

    // 新增数据时清空表单数据
    function clearFormData() {
        $('#menuNameSave').val(''); // 菜单名称
        $('#menuTypeSave').val('-1'); // 菜单类型
        $('#menuActionSave').val(''); // 菜单动作
        $('#menuOrderSave').val(''); // 显示顺序
        $('#menuRemarksSave').val(''); // 菜单备注
        $('#menuActionDiv').show(); // 显示菜单动作
        form.render(); //更新全部
    }

    // 修改数据时回显数据
    function echoData(obj) {
        $('#updateId').val(obj.data.id);
        $('#menuNameUpdate').val(obj.data.menuName);
        $('#menuTypeUpdate').val(obj.data.menuType);
        $('#menuActionUpdate').val(obj.data.menuAction);
        $('#menuOrderUpdate').val(obj.data.menuOrder);
        $('#menuRemarksUpdate').val(obj.data.menuRemarks);

        form.render(); //更新全部
    }

    // 更新菜单状态
    function statusOpenClose(id, type) {
        var str = ""
        if (type == 1) {
            str = "锁定"
        } else if (type == 0) {
            str = "启用"
        }
        layer.confirm('您确认想要' + str + '吗？', function (index) {
            $.ajax({
                url: ctx + '/updateMenuStatus',
                method: 'post',
                data: {
                    id: id,
                    type: type
                }
            })
            layer.close(index);
            dataList();
        });
    }
</script>
</body>
</html>
