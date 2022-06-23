
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/../../../quiz/static/css/bootstrap4.0.min.css" >
    <script src="/../../../quiz/static/js/jquery.slim.min.js" ></script>
    <script src="/../../../quiz/static/js/popper.min.js" ></script>
    <script src="/../../../quiz/static/js/bootstrap4.0.min.js"></script>
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
    <form action="/quiz/v/edit/do" method="post">
        <input type="hidden" value="${earthquake.number}" name="number">
        <div class="form-group">
            <label for="volcano_name">volcano_name</label>
            <input type="text" class="form-control" id="volcano_name"
                   name="volcano_name" placeholder="volcano_name" value="${earthquake.volcanoName}">
        </div>
        <div class="form-group">
            <label for="country">country</label>
            <input type="text" class="form-control" id="country"
                   name="country" placeholder="country" value="${earthquake.country}">
        </div>
        <div class="form-group">
            <label for="region">region</label>
            <input type="text" class="form-control" id="region"
                   name="region" placeholder="region" value="${earthquake.region}">
        </div>
        <div class="form-group">
            <label for="latitude">latitude</label>
            <input type="text" class="form-control" id="latitude"
                   name="latitude" placeholder="latitude" value="${earthquake.latitude}">
        </div>
        <div class="form-group">
            <label for="longitude">longitude</label>
            <input type="text" class="form-control" id="longitude"
                   name="longitude" placeholder="longitude" value="${earthquake.longitude}">
        </div>
        <div class="form-group">
            <label for="elev">elev</label>
            <input type="text" class="form-control" id="elev"
                   name="elev" placeholder="elev" value="${earthquake.elev}">
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
