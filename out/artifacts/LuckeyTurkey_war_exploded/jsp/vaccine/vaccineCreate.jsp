<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%--    <meta name="viewport"--%>
    <%--          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">--%>
    <%--    <meta http-equiv="X-UA-Compatible" content="ie=edge">--%>
    <title>Document</title>
    <link rel="stylesheet" type="text/css" href="vaccine.css">
</head>
<body>
<div class="topRectangle">dd</div>
    <form action="/vaccination/create" method="post">
        <div class="form-group">
            <label for="inoculationName" class="">백신 이름</label>
            <input type="text" id="inoculationName" name="inoculationName" required>
        </div>
        <div class="form-group">
            <label for="totalDoses">총 접종 횟수</label>
            <input type="number" id="totalDoses" name="totalDoses" required>
        </div>
        <button type="submit">Submit</button>
    </form>
</body>
</html>

