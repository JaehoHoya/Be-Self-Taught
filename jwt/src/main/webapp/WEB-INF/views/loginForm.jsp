<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
</head>
<body>

<form action="/login" method="post">
    <h3 id="title">Login</h3>
    <input type="text" name="username" placeholder="아이디 입력" required>
    <input type="password" name="password" placeholder="비밀번호" required>
    <button type="submit">로그인</button>
</form>
<a href="/signUp"><button>회원가입</button></a>
<c:if test="${param.error == 'true'}">
    <p style="color: red;">로그인에 실패했습니다. 아이디와 비밀번호를 확인해주세요.</p>
</c:if>

</body>
</html>
