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
            <label class="layui-form-label">账号</label>
            <div class="layui-input-block">
                <input type="text" id="userName" name="userName" placeholder="请输入账号"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-col-md3">
        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-block">
                <input type="text" id="realName" name="realName" placeholder="请输入姓名"
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
    <table id="userTable" lay-filter="userTable"></table>
</div>

<%--新增数据--%>
<div style="display: none" id="saveDialog">
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">账号<i class="asterisk">*</i></label>
            <div class="layui-input-block">
                <input type="text" id="userNameSave" name="userNameSave" placeholder="请输入账号" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">姓名<i class="asterisk">*</i></label>
            <div class="layui-input-block">
                <input type="text" id="realNameSave" name="realNameSave" placeholder="请输入姓名" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-inline">
                <select id="userGenderSave" name="userGenderSave">
                    <option value="-1">请选择</option>
                    <c:forEach var="item" items="${genderTypeList}">
                        <option value="${item.dicCode}">${item.dicName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">出生日期</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" id="userBirthdaySave" name="userBirthdaySave"
                       placeholder="yyyy-MM-dd">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机号</label>
            <div class="layui-input-block">
                <input type="text" id="userPhoneSave" name="userPhoneSave" placeholder="请输入手机号"
                       autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">角色分配</label>
            <div class="layui-input-block">
                <select id="roleIdSave" name="roleIdSave">
                    <option value="-1">请选择</option>
                    <c:forEach var="item" items="${roleList}">
                        <option value="${item.id}">${item.roleName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
                <textarea id="remarkSave" name="remarkSave" placeholder="请输入内容"
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
            <label class="layui-form-label">账号<i class="asterisk">*</i></label>
            <div class="layui-input-block">
                <input type="text" id="userNameUpdate" name="userNameUpdate" placeholder="请输入账号" autocomplete="off"
                       class="layui-input" disabled="disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">姓名<i class="asterisk">*</i></label>
            <div class="layui-input-block">
                <input type="text" id="realNameUpdate" name="realNameUpdate" placeholder="请输入姓名" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-inline">
                <select id="userGenderUpdate" name="userGenderUpdate">
                    <option value="-1">请选择</option>
                    <c:forEach var="item" items="${genderTypeList}">
                        <option value="${item.dicCode}">${item.dicName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">出生日期</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" id="userBirthdayUpdate" name="userBirthdayUpdate"
                       placeholder="yyyy-MM-dd">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机号</label>
            <div class="layui-input-block">
                <input type="text" id="userPhoneUpdate" name="userPhoneUpdate" placeholder="请输入手机号"
                       autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">角色分配</label>
            <div class="layui-input-block">
                <select id="roleIdUpdate" name="roleIdUpdate">
                    <option value="-1">请选择</option>
                    <c:forEach var="item" items="${roleList}">
                        <option value="${item.id}">${item.roleName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
                <textarea id="remarkUpdate" name="remarkUpdate" placeholder="请输入内容"
                          class="layui-textarea"></textarea>
            </div>
        </div>
    </form>
</div>

<script type="text/html" id="dataUserBarDemo">
    <a class="layui-btn layui-btn-primary layui-border-green layui-btn-sm button-group-child" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-primary layui-border-red layui-btn-sm button-group-child" lay-event="del">删除</a>
</script>
<script type="text/javascript">
    var table;
    var form;
    var laydate;
    $(function () {
        dataList();
    })

    //  点击查询数据
    function searchData() {
        dataList()
    }

    // 查询主数据
    function dataList() {
        layui.use(['table', 'form', 'laydate'], function () {
            table = layui.table;
            form = layui.form;
            laydate = layui.laydate;
            //第一个实例
            table.render({
                elem: '#userTable'
                , height: 550
                , url: '${ctx}/listUserInfoPage' //数据接口
                , where: {
                    realName: $('#realName').val(),
                    userName: $('#userName').val()
                }
                , page: true //开启分页
                , cols: [[ //表头
                    {field: 'id', title: 'ID', width: 80, sort: true, fixed: 'left', align: "center"}
                    , {field: 'userName', title: '账号', width: 120, align: "center"}
                    , {field: 'realName', title: '真实姓名', width: 100, align: "center"}
                    , {field: 'userGender', title: '性别', width: 80, align: "center",templet: "<span>{{dataDictionaryEcho(d.userGender,'GENDER_TYPE')}}</span>"}
                    , {field: 'userBirthday', title: '生日', width: 100, align: "center"}
                    , {field: 'userPhone', title: '手机号', width: 100, align: "center"}
                    , {field: 'roleName', title: '角色名称', width: 120, align: "center"}
                    , {field: 'createTime', title: '创建时间', width: 180, align: "center"}
                    , {field: 'updateTime', title: '更新时间', width: 180, align: "center"}
                    , {field: 'remark', title: '备注', width: 120, align: "center"}
                    , {
                        field: 'isLock', title: '是否锁定', width: 100, align: "center", templet: function (d) {
                            if (d.isLock == 0) {
                                return '<a style="color: red" onclick="statusOpenClose(\'' + d.id + '\',1)">锁定</a>'
                            } else {
                                return '<a style="color: green" onclick="statusOpenClose(\'' + d.id + '\',0)">启用</a>'
                            }
                        }
                    }
                    , {title: '操作', width: 200, align: 'center', toolbar: '#dataUserBarDemo', fixed: 'right'}
                ]]
            });
            //触发单元格工具事件
            table.on('tool(userTable)', function (obj) { // 双击 toolDouble
                if (obj.event === 'del') {
                    layer.confirm('真的删除行么', function (index) {
                        $.ajax({
                            url: '${ctx}/deleteUser',
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
                            var userNameUpdate = $('#userNameUpdate').val();
                            var realNameUpdate = $('#realNameUpdate').val();
                            var userBirthdayUpdate = $('#userBirthdayUpdate').val();
                            var userPhoneUpdate = $('#userPhoneUpdate').val();
                            var remarkUpdate = $('#remarkUpdate').val();
                            var roleIdUpdate = $('#roleIdUpdate').val();
                            var userGenderUpdate = $('#userGenderUpdate').val();
                            console.log(roleIdUpdate);
                            // 字段校验
                            var validation = formValidation(userNameUpdate, realNameUpdate,userPhoneUpdate);
                            if (!validation) {
                                $.ajax({
                                    url: ctx + '/updateDataUser',
                                    method: 'post',
                                    data: {
                                        id: updateId,
                                        userName: userNameUpdate,
                                        realName: realNameUpdate,
                                        userBirthday: userBirthdayUpdate,
                                        userPhone: userPhoneUpdate,
                                        remark: remarkUpdate,
                                        roleId: roleIdUpdate,
                                        userGender: userGenderUpdate
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
            laydate.render({
                elem: '#userBirthdaySave'
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
                var userNameSave = $('#userNameSave').val();
                var realNameSave = $('#realNameSave').val();
                var userBirthdaySave = $('#userBirthdaySave').val();
                var userPhoneSave = $('#userPhoneSave').val();
                var remarkSave = $('#remarkSave').val();
                var roleIdSave = $('#roleIdSave').val();
                var userGenderSave = $('#userGenderSave').val();
                // 字段校验
                var validation = formValidation(userNameSave, realNameSave,userPhoneSave);
                if (!validation) {
                    $.ajax({
                        url: ctx + '/saveDataUser',
                        method: 'post',
                        data: {
                            userName: userNameSave,
                            realName: realNameSave,
                            userBirthday: userBirthdaySave,
                            userPhone: userPhoneSave,
                            remark: remarkSave,
                            roleId: roleIdSave,
                            userGender: userGenderSave
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

    // 新增数据时清空表单数据
    function clearFormData() {
        $('#userNameSave').val('');
        $('#realNameSave').val('');
        $('#userBirthdaySave').val('');
        $('#userPhoneSave').val('');
        $('#remarkSave').val('');
        $('#roleIdSave').val('');
        $('#userGenderSave').val('');
    }

    // 校验参数
    function formValidation(userName, realName,userPhone) {
        var flag = false;
        if (!userName) { // 账号
            flag = true;
            layer.alert("账号不能为空");
            return flag;
        }
        if (!realName) { // 姓名
            flag = true;
            layer.alert("姓名不能为空");
            return flag;
        }
        if (userPhone) {
            var phoneFlag =  verifyPhoneNumber(userPhone);
            if (!phoneFlag) {
                flag = true;
                layer.alert("手机号格式不正确");
                return flag;
            }
        }

        return flag;
    }

    function statusOpenClose(id, type) {
        var str = ""
        if (type == 1) {
            str = "锁定"
        } else if (type == 0) {
            str = "启用"
        }
        layer.confirm('您确认想要' + str + '吗？', function (index) {
            $.ajax({
                url: ctx + '/updateUserStatus',
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

    // 修改数据时回显数据
    function echoData(obj) {
        $('#updateId').val(obj.data.id);
        $('#userNameUpdate').val(obj.data.userName);
        $('#realNameUpdate').val(obj.data.realName);
        $('#userBirthdayUpdate').val(obj.data.userBirthday);
        $('#userPhoneUpdate').val(obj.data.userPhone);
        $('#remarkUpdate').val(obj.data.remark);
        $('#roleIdUpdate').val(obj.data.roleId);
        $('#userGenderUpdate').val(obj.data.userGender);

        form.render(); //更新全部
    }
</script>
</body>
</html>
