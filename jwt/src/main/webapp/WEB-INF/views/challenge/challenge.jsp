<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false"%>
<c:set var="loginId" value="${pageContext.request.getSession(false)==null ? '' : pageContext.request.session.getAttribute('id')}"/>
<c:set var="loginOutLink" value="${loginId=='' ? '/login/login' : '/login/logout'}"/>
<c:set var="loginOut" value="${loginId=='' ? '로그인' : 'ID='+=loginId}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>챌린지</title>

</head>
<body>

<h3>챌린지</h3>

</form>
<a href="/challenge/list"><button> 목록</button></a>
<a href="/challenge/add"><button>챌린지 등록</button></a>
<a href="/challenge/mychall"><button>참여 중인 챌린지</button></a>
</body>
</html>
