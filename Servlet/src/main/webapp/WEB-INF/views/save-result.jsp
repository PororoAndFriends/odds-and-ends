<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
<%--    기존에 배운 방식대로 하려면 이런 식으로 Object 타입으로 나오는 Attribute를 캐스팅하여 사용해야 함--%>
<%--    <li>id=<%=((Member) request.getAttribute("member")).getId()%></li>--%>
<%--    <li>username=<%=((Member) request.getAttribute("member")).getUsername()%></li>--%>
<%--    <li>age=<%=((Member) request.getAttribute("member")).getAge()%></li>--%>

<%--    그런데 이런 식의 표현을 jsp에서 지원해줌 자동으로 member.get~~ 로 접근해줌(Property 접근법)--%>
    <li>id=${member.id}</li>
    <li>username=${member.username}</li>
    <li>age=${member.age}</li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
