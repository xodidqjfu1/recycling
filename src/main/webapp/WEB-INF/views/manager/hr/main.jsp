<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

<script type="text/javascript">
	$(function() {
		$("#listDel").click(function() {

			var len = $("input[name=chkBox]:checked").length;
			var chk = new Array();

			$("input:checkbox[name=chkBox]").each(function() {
				if ($(this).is(":checked") == true) {
					chk.push($(this).attr('id'));
				}
			})

			console.log(len);
			console.log(chk);

			if (len == 0) {
				alert("삭제할 게시물 선택해주세요.");
			} else {
				$.ajax({
					url : "./emplistdelete",
					type : "post",
					data : {
						chBox : chk
					},
					success : function(res) {
						if (res <= 0) {
							alert("삭제 실패");
						} else {
							alert("삭제 성공");
							location.reload();
						}
					},
					error : function() {
						console.log("error");
					}
				})
			}

		})
	})
</script>

</head>
<body>
	<div class = "full" >
		<div class="wrap">		
			<div class="page">
				<h1>전체사원 조회</h1>
			</div>
					<div class = "section">
						<table border="1">
							<thead>
								<tr>
									<th>V</th>
									<th>사원번호</th>
									<th>부서명</th>
									<th>직급</th>
									<th>이름</th>
									<th>핸드폰 번호</th>
									<th>이메일</th>
									<th>입사일</th>
									<th>상세조회</th>
								</tr>
							</thead>
				
							<tbody>
								<c:forEach var="select" items="${select }">
									<tr>
										<td><input type="checkbox" id="${select.mgrCode }"name="chkBox"></td>
										<td>${select.mgrCode }</td>
										<td>${select.dName }</td>
										
										<td>
											<c:choose>
												<c:when test="${select.deptno == 10 }">
													최고관리자
												</c:when>
												<c:when test="${select.deptno == 20 }">
													HR매니저
												</c:when>
												<c:when test="${select.deptno == 30 }">
													SLS매니저
												</c:when>
												<c:when test="${select.deptno == 40 }">
													CS매니저
												</c:when>
											</c:choose>
										</td>
										<td>${select.mgrName }</td>
										<td>${select.mgrPhone }</td>
										<td>${select.mgrEmail }</td>
										<td>
											<!-- 데이터 타입이 Date일 경우 사용 -->
											<%-- 	<fmt:formatDate value="${manager.mgrEntDate }" pattern="yyyy-MM-dd HH:mm:ss"/> --%>
					
												<!-- 데이터 타입이 String일 경우 사용 --> 
												<fmt:parseDate value="${select.mgrEntDate }" var="mgrEntDate" pattern="yyyy-MM-dd" />
												<fmt:formatDate value="${mgrEntDate }" pattern="yyyy-MM-dd" />
										</td>
					
										<td><a href="./empdetail?mgrCode=${select.mgrCode }">
												<button>상세정보 보기</button>
										</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
		</div>
	</div>

	<div>
		<div>
			<a href="./empform"><button>사원정보 입력</button></a>
		</div>
	
		<div>
			<button id="listDel">삭제하기</button>
		</div>
	</div>


</body>
</html>