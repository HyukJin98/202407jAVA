
<%@page import="board.BoardDTO"%>
<%@page import="board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%
request.setCharacterEncoding("UTF-8");

// num 파라미터 가져오기
String numStr = request.getParameter("num");

if (numStr != null) {
    int num = Integer.parseInt(numStr);

    // BoardDAO 객체 생성 및 게시물 데이터 가져오기
    BoardDAO dao = new BoardDAO();
    BoardDTO dto = dao.getOne(num);

%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <style>
        table { width:680px; text-align:center; }
        th    { width:100px; background-color:cyan; }
        input[type=text], textarea { width:100%; }
    </style>
</head>
<body>

<form method="post" action="update.jsp">
<input type="hidden" name="num" value="<%=dto.getNum() %>">
    <table>
        <tr>
            <th>제목</th>
            <td><input type="text" name="title"  maxlength="80"
                       value="<%=dto.getTitle()%>">
            </td>
        </tr>
        <tr>
            <th>작성자</th>
            <td><input type="text" name="writer" maxlength="20"
                       value="<%=dto.getWriter()%>">
            </td>
        </tr>
        <tr>
            <th>내용</th>
            <td><textarea name="content" rows="10"><%=dto.getContent()%></textarea>
            </td>
        </tr>
    </table>
<%
}
%>
    <br>
    <input type="submit" value="저장">
    <input type="button" value="취소" onclick="history.back()">
</form>

</body>
</html>