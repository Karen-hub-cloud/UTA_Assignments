
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../../quiz/static/css/bootstrap4.0.min.css" >
    <script src="../../quiz/static/js/jquery.slim.min.js" ></script>
    <script src="../../quiz/static/js/popper.min.js" ></script>
    <script src="../../quiz/static/js/bootstrap4.0.min.js"></script>
    <script type="text/javascript" src="../../quiz/static/wangEditor.min.js"></script>
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
    <form action="/quiz/v/add/do" method="post">
        <div class="form-group">
            <label for="number">number</label>
            <input type="text" class="form-control" id="number" name="number" placeholder="number">
        </div>
        <div class="form-group">
            <label for="volcano_name">volcano_name</label>
            <input type="text" class="form-control" id="volcano_name" name="volcano_name" placeholder="volcano_name">
        </div>
        <div class="form-group">
            <label for="country">country</label>
            <input type="text" class="form-control" id="country" name="country" placeholder="country">
        </div>
        <div class="form-group">
            <label for="region">region</label>
            <input type="text" class="form-control" id="region" name="region" placeholder="region">
        </div>
        <div class="form-group">
            <label for="latitude">latitude</label>
            <input type="text" class="form-control" id="latitude" name="latitude" placeholder="latitude">
        </div>
        <div class="form-group">
            <label for="longitude">longitude</label>
            <input type="text" class="form-control" id="longitude" name="longitude" placeholder="longitude">
        </div>
        <div class="form-group">
            <label for="elev">elev</label>
            <input type="text" class="form-control" id="elev" name="elev" placeholder="elev">
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
