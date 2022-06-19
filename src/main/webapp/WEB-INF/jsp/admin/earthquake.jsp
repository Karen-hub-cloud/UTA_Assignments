
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Quiz1</title>
    <link rel="stylesheet" href="/css/bootstrap4.0.min.css" >
    <script src="/js/jquery.slim.min.js" ></script>
    <script src="/js/popper.min.js" ></script>
    <script src="/js/bootstrap4.0.min.js"></script>
    <script src="/js/layer.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light" >
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <a class="navbar-brand text-success" href="/admin/main">List</a>

    <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <li class="nav-item">
                <!-- Example single danger button -->
                <div class="btn-group">
                    <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        add
                    </button>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="javascript:void(0);" onclick="fullScreen('addUser','/admin/dataDemo/add')">user</a>
                        <!-- <a class="dropdown-item" href="#">评论</a>-->
                    </div>
                </div>
            </li>
            <li class="nav-item ">
                <a class="nav-link" href="/admin/main">index</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="/admin/dataDemo/list">list</a>
            </li>

        </ul>

        <a class="btn btn-outline-danger btn-sm" href="/admin/logout" role="button">exit</a>
    </div>
</nav>
<form class="form-inline my-2 my-lg-0" action="/blogwar/compareTwoPlace" method="GET">
    longitude1<input class="form-control mr-sm-2" type="search" placeholder="search" aria-label="Search" name="longitude1">
    latitude1<input class="form-control mr-sm-2" type="search" placeholder="search" aria-label="Search" name="latitude1">
    longitude2<input class="form-control mr-sm-2" type="search" placeholder="search" aria-label="Search" name="longitude2">
    latitude2<input class="form-control mr-sm-2" type="search" placeholder="search" aria-label="Search" name="latitude2">
    <button class="btn btn-outline-success my-2 my-sm-0 btn-sm" type="submit">search</button>
</form>
<form class="form-inline my-2 my-lg-0" action="/blogwar/searchLargestN" method="GET">
    net<input class="form-control mr-sm-2" type="search" placeholder="search" aria-label="Search" name="net">
    min<input class="form-control mr-sm-2" type="search" placeholder="search" aria-label="Search" name="min">
    max<input class="form-control mr-sm-2" type="search" placeholder="search" aria-label="Search" name="max">
    <button class="btn btn-outline-success my-2 my-sm-0 btn-sm" type="submit">search</button>
</form>

<form class="form-inline my-2 my-lg-0" action="/blogwar/searchScale" method="GET">
    time(eg:6-12)<input class="form-control mr-sm-2" type="search" placeholder="search" aria-label="Search" name="time">
    <button class="btn btn-outline-success my-2 my-sm-0 btn-sm" type="submit">search</button>
</form>
<br/>
<script src="/js/jquery-3.2.1.min.js"></script>
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

    function ifdelete(id,title) {
        layer.confirm('delete?', {
            btn: ['yes','no'] //按钮
        }, function(){
            $.ajax({
                type: 'POST',
                url: '/api/dataDemo/del',
                datatype:'json',
                data:{"id":id},
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
