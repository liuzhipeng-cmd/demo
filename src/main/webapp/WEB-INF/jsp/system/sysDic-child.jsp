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
                <label class="layui-form-label">字典名称</label>
                <div class="layui-input-block">
                    <input type="text" id="dicName" name="dicName" placeholder="请输入字典名称"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
    </div>
    <div class="layui-row layui-col-space10 button-group">
        <div class="layui-form-item">
            <input type="hidden" id="pid" name="pid" value="${sysDicPid}">
            <button class="layui-btn layui-btn-radius layui-btn-normal" onclick="searchData()">查询</button>
            <button class="layui-btn layui-btn-radius" onclick="saveData()">新增</button>
        </div>
    </div>
</div>

<div>
    <table id="sysDicTable" lay-filter="sysDicTable"></table>
</div>

<%--新增数据表单--%>
<div style="display: none" id="saveDialog">
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">字典编码<i class="asterisk">*</i></label>
            <div class="layui-input-block">
                <input type="text" id="dicCodeSave" name="dicCodeSave" placeholder="请输入字典编码" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">字典名称<i class="asterisk">*</i></label>
            <div class="layui-input-block">
                <input type="text" id="dicNameSave" name="dicNameSave" placeholder="请输入字典名称" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">显示顺序</label>
            <div class="layui-input-inline">
                <input type="text" id="dicOrderSave" name="dicOrderSave" placeholder="请输入显示顺序" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">菜单备注</label>
            <div class="layui-input-block">
                <textarea id="remarksSave" name="remarksSave" placeholder="请输入内容"
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
            <label class="layui-form-label">字典编码<i class="asterisk">*</i></label>
            <div class="layui-input-block">
                <input type="text" id="dicCodeUpdate" name="dicCodeUpdate" placeholder="请输入字典编码"
                       autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">字典名称<i class="asterisk">*</i></label>
            <div class="layui-input-block">
                <input type="text" id="dicNameUpdate" name="dicNameUpdate" placeholder="请输入字典名称"
                       autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">显示顺序</label>
            <div class="layui-input-inline">
                <input type="text" id="dicOrderUpdate" name="dicOrderUpdate" placeholder="请输入显示顺序"
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
    </form>
</div>

<script type="text/html" id="dataSysDicBarDemo">
    <a class="layui-btn layui-btn-primary layui-border-green layui-btn-sm button-group-child" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-primary layui-border-red layui-btn-sm button-group-child" lay-event="del">删除</a>
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
                elem: '#sysDicTable'
                , height: 550
                , url: '${ctx}/listSysDicChildPage' //数据接口
                , where: {
                    dicName: $('#dicName').val(),
                    dicType: $('#dicType').val(),
                    pid: $('#pid').val()
                }
                , page: true //开启分页
                , cols: [[ //表头
                    {field: 'id', title: 'ID', width: 80, sort: true, fixed: 'left', align: "center"}
                    , {field: 'dicCode', title: '字典编码', width: 110, align: "center"}
                    , {field: 'dicName', title: '字典名称', width: 120, align: "center"}
                    , {field: 'dicOrder', title: '显示顺序', width: 110, align: "center"}
                    , {
                        field: 'creator',
                        title: '创建人',
                        width: 100,
                        align: "center",
                        templet: "<span>{{realNameEcho(d.creator)}}</span>"
                    }
                    , {field: 'createTime', title: '创建时间', width: 200, align: "center"}
                    , {
                        field: 'updator',
                        title: '更新人',
                        width: 100,
                        align: "center",
                        templet: "<span>{{realNameEcho(d.updator)}}</span>"
                    }
                    , {field: 'updateTime', title: '更新时间', width: 200, align: "center"}
                    , {field: 'remarks', title: '备注', width: 200, align: "center"}
                    , {title: '操作', width: 260, align: 'center', toolbar: '#dataSysDicBarDemo', fixed: 'right'}
                ]]
            });
            //触发单元格工具事件
            table.on('tool(sysDicTable)', function (obj) { // 双击 toolDouble
                if (obj.event === 'del') {
                    layer.confirm('真的删除行么', function (index) {
                        $.ajax({
                            url: '${ctx}/deleteSysDic',
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
                            var dicCodeUpdate = $('#dicCodeUpdate').val();
                            var dicNameUpdate = $('#dicNameUpdate').val();
                            var dicOrderUpdate = $('#dicOrderUpdate').val();
                            var remarksUpdate = $('#remarksUpdate').val();
                            // 字段校验
                            var validation = formValidation(dicNameUpdate, dicCodeUpdate);
                            if (!validation) {
                                $.ajax({
                                    url: ctx + '/updateDataSysDic',
                                    method: 'post',
                                    data: {
                                        id: updateId,
                                        dicCode: dicCodeUpdate,
                                        dicName: dicNameUpdate,
                                        dicOrder: dicOrderUpdate,
                                        remarks: remarksUpdate
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
                var dicCodeSave = $('#dicCodeSave').val();
                var dicNameSave = $('#dicNameSave').val();
                var dicOrderSave = $('#dicOrderSave').val();
                var remarksSave = $('#remarksSave').val();
                var pid = $('#pid').val();
                // 字段校验
                var validation = formValidation(dicNameSave, dicCodeSave);
                if (!validation) {
                    $.ajax({
                        url: ctx + '/saveDataSysDic',
                        method: 'post',
                        data: {
                            dicCode: dicCodeSave,
                            dicName: dicNameSave,
                            remarks: remarksSave,
                            dicOrder: dicOrderSave,
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
    function formValidation(dicName, dicCode) {
        var flag = false;
        if (!dicName) { // 字典名称
            flag = true;
            layer.alert("字典名称不能为空");
            return flag;
        }
        if (!dicCode) { // 字典编码
            flag = true;
            layer.alert("字典编码不能为空");
            return flag;
        }
        return flag;
    }

    // 新增数据时清空表单数据
    function clearFormData() {
        $('#dicCodeSave').val(''); // 字典编码
        $('#dicNameSave').val(''); // 字典名称
        $('#dicOrderSave').val(''); // 显示顺序
        $('#remarksSave').val(''); // 备注
        form.render(); //更新全部
    }

    // 修改数据时回显数据
    function echoData(obj) {
        $('#updateId').val(obj.data.id);
        $('#dicCodeUpdate').val(obj.data.dicCode);
        $('#dicNameUpdate').val(obj.data.dicName);
        $('#remarksUpdate').val(obj.data.remarks);
        $('#dicOrderUpdate').val(obj.data.dicOrder);

        form.render(); //更新全部
    }
</script>
</body>
</html>
