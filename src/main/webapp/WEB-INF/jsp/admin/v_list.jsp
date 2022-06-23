
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Quiz1</title>
    <link rel="stylesheet" href="/../../quiz/static/css/bootstrap4.0.min.css" >
    <script src="/../../quiz/static/js/jquery.slim.min.js" ></script>
    <script src="/../../quiz/static/js/popper.min.js" ></script>
    <script src="/../../quiz/static/js/bootstrap4.0.min.js"></script>
    <script src="/../../quiz/static/js/layer.js"></script>
</head>
<body>
<div>
    <header id="header">
        <nav>
            <div class="my-info" onmouseover="hiddeewm()" onmouseout="hiddeewm()">
                <figure></figure>
                <span>UTA</span>
                <div id="hiddenewm" hidden="true" >
                    <img src="/img/me.jpg" width="200px" height="200px" >
                    <p></p>
                </div>
            </div>
        </nav>
    </header>
    <div id="bg" >
        <p>
            1001989820
            <br>
            Kun Tian
        </p>
    </div>
</div>
<nav class="navbar navbar-expand-lg navbar-light bg-light" >
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <a class="navbar-brand text-success" href="/quiz/v/list">List</a>

    <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <li class="nav-item">
                <!-- Example single danger button -->
                <div class="btn-group">
                    <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        add
                    </button>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="javascript:void(0);" onclick="fullScreen('add','/quiz/v/add')">earthquake</a>
                        <!-- <a class="dropdown-item" href="#">评论</a>-->
                    </div>
                </div>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="/quiz/v/list">list</a>
            </li>

        </ul>
        <form class="form-inline my-2 my-lg-0" action="/quiz/v/search" method="GET">
            <input class="form-control mr-sm-2" type="number" placeholder="input number" aria-label="Search" name="word">
            <button class="btn btn-outline-success my-2 my-sm-0 btn-sm" type="submit">search RDB</button>
        </form>
        <form class="form-inline my-2 my-lg-0" action="/quiz/v/searchCache" method="GET">
            <input class="form-control mr-sm-2" type="number" placeholder="input number" aria-label="Search" name="word">
            <button class="btn btn-outline-success my-2 my-sm-0 btn-sm" type="submit">search Cache</button>
        </form>
        <form class="form-inline my-2 my-lg-0" action="/quiz/v/randomN" method="GET">
            <input class="form-control mr-sm-2" type="number" placeholder="input random n" aria-label="Random N" name="n">
            <button class="btn btn-outline-success my-2 my-sm-0 btn-sm" type="submit">Random N</button>
        </form>

        <form class="form-inline my-2 my-lg-0" action="/quiz/v/searchRange" method="GET">
            <input class="form-control mr-sm-2" type="number" placeholder="input min" aria-label="minSeq" name="minSeq">
            <input class="form-control mr-sm-2" type="number" placeholder="input max" aria-label="maxSeq" name="maxSeq">
            <button class="btn btn-outline-success my-2 my-sm-0 btn-sm" type="submit">Search Sequence</button>
        </form>
        <%--<a class="btn btn-outline-danger btn-sm" href="/admin/logout" role="button">exit</a>
--%>    </div>
</nav>
<br/>
<p>time elspsed：${time} ms</p>
<table class="table table-sm">
    <thead>
    <tr class="table-info">
        <th>number</th>
        <th>volcano_name</th>
        <th>country</th>
        <th>region</th>
        <th>latitude</th>
        <th>longitude</th>
        <th>elev</th>
        <th>edit RDB</th>
        <th>delete RDB</th>
        <th>edit Cache</th>
        <th>delete Cache</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${earthquakeList}" var="earthquake">
        <tr>
            <td>${earthquake.number}</td>
            <td>${earthquake.volcanoName}</td>
            <td>${earthquake.country}</td>
            <td>${earthquake.region}</td>
            <td>${earthquake.latitude}</td>
            <td>${earthquake.longitude}</td>
            <td>${earthquake.elev}</td>
            <td><button type="button" class="btn btn-outline-primary btn-sm" onclick="fullScreen('《${earthquake.number}》|edit','/quiz/v/edit?number=${earthquake.number}')">edit</button></td>
            <td><button type="button" class="btn btn-outline-danger btn-sm" onclick="ifdelete('${earthquake.number}') ">delete</button></td>
            <td><button type="button" class="btn btn-outline-primary btn-sm" onclick="fullScreenCache('add','/quiz/v/addCache/do?number=${earthquake.number}')">add to Cache</button></td>
            <td><button type="button" class="btn btn-outline-danger btn-sm" onclick="ifdeleteCache('${earthquake.number}') ">delete</button></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<nav aria-label="Page navigation example" style="position: absolute;bottom: 10px;left: 42%">
    <ul class="pagination justify-content-center">
        <li class="page-item  <c:if test="${pageInfo.pageNum==1}">disabled</c:if> ">
            <a class="page-link" href="/quiz/v/list?page=1" >&laquo;</a>
        </li>
        <c:forEach begin="1" end="${pageInfo.pages}" step="1" var="pageNo">
            <li class="page-item <c:if test="${pageInfo.pageNum==pageNo}">active</c:if>"><a class="page-link" href="/v/list?page=${pageNo}">${pageNo}</a></li>
        </c:forEach>
        <li class="page-item  <c:if test="${pageInfo.pageNum==pageInfo.pages}">disabled</c:if> ">
            <a class="page-link" href="/quiz/v/list?page=${pageInfo.pages}">&raquo;</a>
        </li>
    </ul>
</nav>
<script src="/../../quiz/static/js/jquery-3.2.1.min.js"></script>
<script>
    function fullScreen(title,url){
        var index = layer.open({
            type: 2,
            title: title,
            area: ['70%', '70%'],
            content: url,
            maxmin: true
        });
        layer.full(index);
    }

    function fullScreenCache(title,url){
        var index = layer.open({
            type: 2,
            title: title,
            area: ['70%', '70%'],
            content: url,
            maxmin: true
        });
        layer.full(index);
    }

    function ifdelete(number) {
        layer.confirm('delete?', {
            btn: ['yes','no'] //按钮
        }, function(){
            $.ajax({
                type: 'POST',
                url: '/quiz/v/del',
                datatype:'json',
                data:{"number" : number},
                success: function(data){
                    if(data['stateCode']==1){
                        layer.msg('delete success!',{icon:1,time:1000});
                        setTimeout("window.location.reload()",1000);
                    }
                    else {
                        layer.msg('delete failed',{icon:5,time:1000});
                    }
                },
                error:function(data) {
                    console.log('error...');
                },
            });
        }, function(){

        });


    }

    function ifdeleteCache(number) {
        layer.confirm('delete?', {
            btn: ['yes','no'] //按钮
        }, function(){
            $.ajax({
                type: 'POST',
                url: '/quiz/v/delCache',
                datatype:'json',
                data:{"number" : number},
                success: function(data){
                    if(data['stateCode']==1){
                        layer.msg('delete success!',{icon:1,time:1000});
                        setTimeout("window.location.reload()",1000);
                    }
                    else {
                        layer.msg('delete failed',{icon:5,time:1000});
                    }
                },
                error:function(data) {
                    console.log('error...');
                },
            });
        }, function(){

        });


    }

</script>
</body>
</html>
