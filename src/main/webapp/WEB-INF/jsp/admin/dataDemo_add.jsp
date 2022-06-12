
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/css/bootstrap4.0.min.css" >
    <script src="/js/jquery.slim.min.js" ></script>
    <script src="/js/popper.min.js" ></script>
    <script src="/js/bootstrap4.0.min.js"></script>
    <script type="text/javascript" src="/js/wangEditor.min.js"></script>
</head>
<body>
<div style="position: relative;top: 10%">
    <c:if test="${!empty succ}">
        <div class="alert alert-success" role="alert">
                ${succ}
        </div>
    </c:if>
    <c:if test="${!empty error}">
        <div class="alert alert-danger" role="alert">
            ${error}
        </div>
    </c:if>
</div>
<div class="container">
    <form action="/admin/dataDemo/add/do" method="post">
        <div class="form-group">
            <label for="name">name</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="Name">
        </div>
        <div class="form-group">
            <label for="num">num</label>
            <input type="text" class="form-control" id="num" name="num" placeholder="Num">
        </div>
        <div class="form-group">
            <label for="keywords">Keywords</label>
            <input type="text" class="form-control" id="keywords" name="keywords" placeholder="Keywords">
        </div>
        <div class="form-group">
            <label for="picture">Picture</label>
            <textarea class="form-control" id="picture" rows="3" name="picture" placeholder="Picture"></textarea>
        </div>
        <input type="submit" value="submit" />
    </form>

            <script type="text/javascript">
                var E = window.wangEditor
                var editor = new E('#div1')
                var $text1 = $('#content')
                editor.customConfig.onchange = function (html) {
                    // 监控变化，同步更新到 textarea
                    $text1.val(html)
                }
                editor.create()
                // 初始化 textarea 的值
                $text1.val(editor.txt.html())
            </script>
</div>
</body>
</html>
