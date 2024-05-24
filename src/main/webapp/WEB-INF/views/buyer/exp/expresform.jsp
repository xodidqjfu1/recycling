<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

</head>
<body>
<!-- 모달 버튼 -->
					<button type="button" id="btnPostcode" data-bs-toggle="modal" data-bs-target="#expformModal">
						체험 날짜/시간 예약
					</button>
					
					<!-- 모달창-->
					<div class="modal fade" id="expformModal" tabindex="-1" aria-labelledby="expformModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">

								<!-- 모달 헤드 -->
								<div class="modal-header">
									<h1 class="modal-title fs-5" id="expformModalLabel">
									체험 가능 날짜/시간등록</h1>
									<button type="button" class="btn-close" data-bs-dismiss="modal"aria-label="Close"></button>
								</div>
								
								<!-- 모달 바디 -->
								<div class="modal-body">
									<h3>체험 마감일</h3>
									<input type="date" name="schDate" placeholder="종료일">

									<h3>시간</h3>
									<!-- 시작시간 -->
									<c:set var="startTimeHour" value="7" />
									<!-- 종료시간 -->
									<c:set var="endTimeHour" value="18"/>
									<c:set var="interval" value="30" />
									<c:forEach var="hour" begin="${startTimeHour}" end="${endTimeHour}">
										<c:forEach var="minute" begin="0" end="59" step="${interval}">
											<input type="button" name="schTime" class="hourBtnClick" value="${hour < 10 ? '0' : ''}${hour}:${minute < 10 ? '0' : ''}${minute}" readonly="readonly">
										</c:forEach>
									</c:forEach>
									
									<h3>인원</h3>
									<input type="text" name="schCnt" placeholder="인원">
								</div>
								
								<!-- 모달 푸터 -->
								<div class="modal-footer">
									<button type="button" id="applyBtn" data-bs-dismiss="modal">
										추가
									</button>
									<button type="button" class="btn btn-footer" data-bs-dismiss="modal">
										닫기																			
									</button>
								</div>
							</div>
						</div>
					</div>
</body>
</html>