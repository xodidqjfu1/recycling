<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<link rel="stylesheet" href="/resources/css/manager/cs/list.css">
</head>
<body>

	<div class="full">
		<aside>
		</aside>
		<div class="wrap">
			<div class="page">
				구매자 리스트
			</div>
			
			<div class="search">
				<form action="./buyerlist" method="get">
					<input type="hidden" name="sCtg" value="UP">
					<input type="text" id="uppersearch" name="search" placeholder="검색어를 입력해주세요." class="search">
					<button>검색</button>
				</form>
			</div>
			
			<div class="section">	
				
				<table>
					<thead>
						<tr>
	                        <th>구매자 코드</th>
	                        <th>구매자 분류 코드</th>
	                        <th>아이디</th>
							<th>이름</th>
	                        <th>상세조회</th>
						</tr>
					</thead>
		
					<tbody>
					    <c:forEach var="buyer" items="${buyerList}">
					        <c:if test="${buyer.bOut != 'Y'}">
					            <tr>
					                <td>${buyer.bCode }</td>
					                <td>${buyer.bCtCode }</td>
					                <td>${buyer.bId }</td>
					                <td>${buyer.bName }</td>
					                <td>                                
	                                	<a href="/manager/cs/buyerdetail?bCode=${buyer.bCode }">
		                                   <button class="btn">상세조회</button>
		                               </a>                                   
	                                </td>
					            </tr>
					        </c:if>
					    </c:forEach>
					</tbody>
				</table>
			</div>
			<c:import url="/WEB-INF/views/layout/upperpaging.jsp"/>
		</div>
	</div>
</body>
</html>
