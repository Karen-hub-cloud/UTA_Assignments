
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
    <script type="text/javascript" src="/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" src="/js/ueditor/ueditor.all.js"> </script>
    <script type="text/javascript"  src="/js/lang/zh-cn/zh-cn.js"></script>
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
    <form action="/admin/dataDemo/edit/do" method="post">
        <input type="hidden" value="${dataDemo.id}" name="id">
        <div class="form-group">
            <label for="name">Name</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="Name" value="${dataDemo.name}">
        </div>
        <div class="form-group">
            <label for="num">Num</label>
            <input type="text" class="form-control" id="num" name="num" placeholder="Num" value="${dataDemo.num}">
        </div>
        <div class="form-group">
            <label for="picture">Picture</label>
            <textarea class="form-control" id="picture" rows="3" name="picture" placeholder="Picture">${dataDemo.picture}</textarea>
        </div>
        <div class="form-group">
            <label for="keywords">Keywords</label>
            <input type="text" class="form-control" id="keywords" name="keywords" placeholder="Keywords" value="${dataDemo.keywords}">
        </div>
            <input type="submit" />
    </form>
    <script>
                $(function(){
                    var ue = UE.getEditor('editor');
                    ue.ready(function() {
                        ue.setContent($("#cont").html());
                    });
                });
            </script>
        </div>
</body>
</html>
