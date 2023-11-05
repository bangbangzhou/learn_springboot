<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>语法学习</title>
</head>
<body>
<h2>遍历List集合</h2>
<table border="1">
    <tr>
        <td>序号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>钱包</td>
        <td>生日</td>
    </tr>
    <#list stus as stu>
        <#if stu.name=='小红'>
            <tr style="color: red">
                <td>${stu_index+1}</td>
                <td>${stu.name}</td>
                <td>${stu.age}</td>
                <td>${stu.money}</td>
                <td>${stu.birthday?string("yyyy年MM月dd日")}</td>
            </tr>
        <#else>
            <tr>
                <td>${stu_index+1}</td>
                <td>${stu.name}</td>
                <td>${stu.age}</td>
                <td>${stu.money}</td>
                <td>${stu.birthday?string("yyyy年MM月dd日")}</td>
            </tr>
        </#if>

    </#list>
</table>
</body>
</html>