<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>语法学习</title>
</head>
<body>

<h2>遍历Map集合</h2>
<table border="1">
    <tr>
        <td>姓名</td>
        <td>年龄</td>
        <td>钱包</td>
        <td>生日</td>
    </tr>
    <#list stuMap?keys as key>
        <tr>
            <td>${stuMap[key].name}</td>
            <td>${stuMap[key].age}</td>
            <td>${stuMap[key].money}</td>
            <td>${stuMap[key].birthday?string("yyyy年MM月dd日")}</td>
        </tr>
    </#list>


</table>
</body>
</html>