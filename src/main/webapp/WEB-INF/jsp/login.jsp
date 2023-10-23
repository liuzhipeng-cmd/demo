<%@ include file="/WEB-INF/common/inhead.jsp" %>
<%@ include file="/WEB-INF/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>综合系统</title>
    <style>
        .bodyClass {
            background-image: url(${ctx}/image/login.jpg);
            /*设置背景图片填满body标签*/
            background-size: 100% 100%;
            /*设置背景图片拉伸，而不是重复占满空间*/
            background-repeat: no-repeat;
        }
        .panelCss {
            margin: 15% 35% 0 35%;
            width: 30%;
            text-align: center;
            background-color: rgba(255,255,255,0.1);
            box-shadow: 10px 10px 15px -10px;
        }
        .layui-input {
            display: block;
            width: 80%;
            padding-left: 10px;
        }
        .layui-form-select .layui-edge {
            right: 25%;
        }
    </style>
</head>
<body class="bodyClass">
    <div class="layui-panel panelCss">
        <div style="margin-bottom: 5%;margin-top: 5%">
            <h1>综合系统</h1>
        </div>
        <form class="layui-form" action="${ctx}/login_in"  method="post">
            <c:if test="${errorText != null}">
                <span style="color: red">${errorText}</span>
            </c:if>
            <div class="layui-form-item">
                <label class="layui-form-label">账号</label>
                <div class="layui-input-block">
                    <input style="width: 80%" type="text" id="userName" name="userName" required  lay-verify="required" placeholder="请输入账号" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码框</label>
                <div class="layui-input-block">
                    <input style="width: 80%" type="password" id="userPassword" name="userPassword" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">验证码</label>
                <div class="box pos" style="display: flex">
                    <input style="width: 50%" type="text" id="verificationCode" name="verificationCode" class="layui-input" placeholder="验证码"/>
                    <img id="createImg" src="" onclick="refreshVerificationCode()">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button type="submit" class="layui-btn" lay-submit lay-filter="formDemo">登录</button>
                    <button onclick="resetting()" type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form>
    </div>
</body>
<script type="text/javascript">
    $(function () {
        // 页面加载验证码图片
        refreshVerificationCode();
    })
    // 获取验证码图片
    function refreshVerificationCode () {
        $('#createImg').prop("src",ctx + "/createImg?time=" + new Date().getTime());
    }
    // 重置数据
    function resetting () {
        $('#userName').val('');
        $('#userPassword').val('');
        $('#verificationCode').val('');
    }
</script>
</html>
