<%@ include file="/WEB-INF/common/inhead.jsp" %>
<%@ include file="/WEB-INF/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>综合系统</title>
    <style>

    </style>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo layui-hide-xs layui-bg-black">综合系统</div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item layui-hide layui-show-md-inline-block">
                <a href="javascript:;">
                    <img src="//tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg"
                         class="layui-nav-img">
                    管理员
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">Your Profile</a></dd>
                    <dd><a href="">Settings</a></dd>
                    <dd><a href="">Sign out</a></dd>
                </dl>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <c:forEach items="${menuList}" var="item">
                    <li class="layui-nav-item">
                        <c:if test="${item.menu_action != ''}">
                            <a class="site-demo-active" href="javascript:;" data-id="${item.id}"
                               data-url="${ctx}${item.menu_action}" data-title="${item.menu_name}">${item.menu_name}</a>
                        </c:if>
                        <c:if test="${item.menu_action == ''}">
                            <a href="javascript:;">${item.menu_name}</a>
                        </c:if>
                        <c:if test="${item.is_child_node != 1}">
                            <dl class="layui-nav-child">
                                <c:forEach items="${item.childList}" var="child">
                                    <dd><a class="site-demo-active" href="javascript:;" data-id="${child.id}"
                                           data-url="${ctx}${child.menu_action}"
                                           data-title="${child.menu_name}">${child.menu_name}</a>
                                    </dd>
                                </c:forEach>

                            </dl>
                        </c:if>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div class="layui-tab" lay-filter="demo" lay-allowclose="true">
            <ul class="layui-tab-title"></ul>
            <div class="layui-tab-content"></div>
        </div>
    </div>

    <div class="layui-footer" style="text-align: center;">
        <div style="letter-spacing: 5px">
            <span>联系电话：12345678900</span>
            <span>网址：www.baidu.com</span>
        </div>
    </div>
</div>
<script type="text/javascript">
    layui.use(['element', 'layer', 'util'], function () {
        var element = layui.element
            , layer = layui.layer
            , util = layui.util
            , $ = layui.$;
        // 配置tab实现在下面无法获取到菜单元素
        $('.site-demo-active').on('click', function () {
            var dataid = $(this);
            // 这时会判断右侧layui-tab-title属性下的lay-id属性的li的数目，即已经打开的tab项数目
            if ($('.layui-tab-title li[lay-id]').length <= 0) {
                // 如果比零小则直接打开新的tab页
                // active.tabAdd(dataid.attr('data-url'),dataid.attr('data-id'),dataid.attr('data-title'));
                active.tabAdd(dataid.attr('data-url'), dataid.attr('data-id'), dataid.attr('data-title'));
            } else {
                // 否则判断该tab项是否以及存在
                var isData = false; // 初始化一个标志，为false说明未打开tab项，为true则说名已有
                $.each($('.layui-tab-title li[lay-id]'), function () {
                    // 如果点击左侧的菜单栏传入的id在右侧的tab项中的lay-id属性可以找到，则说明tab项已经打开
                    if ($(this).attr('lay-id') == dataid.attr('data-id')) {
                        isData = true;
                    }
                })
                if (isData == false) {
                    // 标志false，新增一个tab项
                    active.tabAdd(dataid.attr('data-url'), dataid.attr('data-id'), dataid.attr('data-title'));
                }
            }
            // 最后不管是否新增tab，最后都转到打开的选项的页面上
            active.tabChange(dataid.attr('data-id'));
        });
        var active = {
            // 在这里给active绑定上几个事件，后面可通过active调用这些事件
            tabAdd: function (url, id, name) {
                // 新增一个项传入三个参数，分别对应标题、tab页的地址、绑定的id，是标签中的data-id的属性值
                // 关于tabAdd的方法所传入的参数可看layui的开发文档中基础方法部分
                element.tabAdd('demo', {
                    title: name,
                    content: '<iframe data-frameid="' + id + '" scrolling="auto" frameborder="0" src="' + url + '" style="width: 100%;height: 99%"></iframe>',
                    id: id // 绑定好的id
                });
                FrameWH(); // 计算iframe大小
            },
            tabChange: function (id) {
                // 切换到指定的Tab项
                element.tabChange('demo', id); // 根据传入的id传入到指定的tab项
            },
            tabDelete: function (id) {
                element.tabDelete('demo', id); // 删除
            }
        };

        // 默认打开首页
        active.tabAdd('home', 2, '主页');
        active.tabChange(2);

        function FrameWH() {
            var h = $(window).height();
            $('iframe').css('height', h + 'px')
        }
    });
</script>
</body>
</html>
