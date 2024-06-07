<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    // 각 행 클릭 이벤트 리스너 추가
    $(".dataRow").on("click", function() {
        // 클릭된 행의 데이터 속성을 가져와서 삽입
        var sCode = $(this).data("s-code");
        var cmpNo = $(this).data("cmp-no");
        var cmpName = $(this).data("cmp-name");
        var cmpCEO = $(this).data("cmp-ceo");
        var cmpAddr = $(this).data("cmp-addr");

        // 폼 필드에 데이터 삽입
        document.getElementsByName("sCode")[0].value = sCode;
        document.getElementsByName("cmpName")[0].value = cmpName;
        // 나머지 필드도 동일한 방식으로 삽입

        alert("입력되었습니다.");
    });
});


</script>
</script>
<style type="text/css">
/* 전체 기본 설정 */
* {
	margin: 0;
	padding: 0;
	border: 0;
	vertical-align: baseline;
	box-sizing: border-box;
	font: inherit;
	font-size: 100%;
	line-height: 1.5;
	color: #333;
}

/* 외부 레이아웃 설정 */
.full {
	width: 1200px;
	border: 1px solid #ccc;
	margin: 0 auto;
	display: flex;
	border-radius: 8px;
	overflow: hidden;
}

aside {
	width: 300px;
	background-color: #f1f1f1;
	border-right: 1px solid #ddd;
	box-shadow: inset 0 0 10px rgba(0, 0, 0, 0.05);
}

.wrap {
	flex: 1;
	display: flex;
	flex-direction: column;
	padding: 20px;
}

/* 상단 페이지 */
.page {
	margin-bottom: 20px;
	font-size: 24px;
	font-weight: bold;
	display: flex;
	align-items: center;
	color: #007BFF;
	border-bottom: 2px solid #007BFF;
	padding-bottom: 10px;
}

/* [우]하단 페이지 */
.btn_bot_style {
	display: flex;
	float: right;
	margin: 0 0 20px 0;
}

/* ------------------------------------- */

/* 섹션 스타일 */
.section {
	padding-top: 20px;
	width: 500px;
	margin: 0 auto;
}

.section input[type="date"] {
	padding: 12px;
	text-align: center;
	width: 100%;
}

.section input[type="text"] {
	border: 1px solid #ccc;
}

.section div {
	margin-bottom: 20px;
}

.section textarea {
	border: 1px solid #ccc;
	width: 100%;
}

.btn_modal_wrap {
	display: flex;
}

label {
	color: #373f57;
	font-size: 16px;
	font-weight: bold;
}

.section input[type="text"] {
	padding: 12px;
	height: 46px;
	width: 100%;
}

.section .hyphen {
	height: 46px;
	padding: 10px 10px;
	font-weight: bold;
	vertical-align: middle;
	margin: 0px;
	border: 0px;
}

/* 파일버튼 디자인 */
.filebox {
	display: flex;
	justify-content: flex-end;
}

.filebox input[type="file"] {
	width: 0;
	height: 0;
	padding: 0;
	overflow: hidden;
	border: 0;
}

.filebox .upload_name
, .filebox .profile_name {
	display: inline-block;
	height: 46px;
	padding: 0 10px;
	vertical-align: middle;
	width: 79%;
	color: #999999;
}

.filebox label {
	display: inline-block;
	width: 21%;
	padding: 10px 20px;
	color: #fff;
	vertical-align: middle;
	background-color: #999999;
	cursor: pointer;
	height: 46px;
}

.phone_box {
	display: flex;
}

.phone_box input[type="text"] {
	width: calc(1/ 3);
	text-align: center;
}

.email_box {
	display: flex;
}

/* 모달1버튼 */
#btnSearchCmp {
	padding: 6px 12px;
	background-color: #007BFF;
	color: #fff;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	white-space: nowrap;
}

#btnSearchCmp:hover {
	background-color: #0056b3;
}

.modal-header {
	margin-bottom: 20px;
	font-size: 24px;
	font-weight: bold;
	display: flex;
	align-items: center;
	color: #007BFF;
	border-bottom: 2px solid #007BFF;
	padding-bottom: 10px;
}

/* 모달2버튼 */
.btnPostcode_wrap {
	width: 230px;
	margin: 0 auto;
}

#postcodeWrap {
	margin: 0 auto;
	display: flex;
	border-radius: 8px;
	overflow: auto;
}

#btnPostcode {
	padding: 12px 25px;
	background-color: #007BFF;
	color: #fff;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	white-space: nowrap;
}

#btnPostcode:hover {
	background-color: #0056b3;
}

#schDate {
	border: 1px solid #ccc;
}

.modal_time_con {
	display: flex;
	flex-direction: column;
	align-items: center;
}

.modal_time_section {
	margin: 10px 0;
}

.modal_time_section h2 {
	margin: 0;
	font-size: 1.2em;
	color: #333;
}

.modal_time_buttons {
	display: flex;
	flex-wrap: wrap;
	justify-content: center;
}

.modal_time-buttons input {
	margin: 5px;
	padding: 10px 20px;
	font-size: 1em;
	background-color: #f0f0f0;
	border: 1px solid #ccc;
	border-radius: 5px;
	cursor: pointer;
	transition: background-color 0.3s;
}

.modal_time-buttons input:hover {
	background-color: #ddd;
}

.modal_body_title {
	margin-bottom: 5px;
	font-size: 20px;
	font-weight: bold;
	display: flex;
	align-items: center;
	border-bottom: 2px solid #007BFF;
	padding-bottom: 10px;
}


#applyBtn, #time_modal_cencle{
	padding: 10px 20px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-size: 16px;
}

.applyBtn {
	background-color: #4CAF50;
	color: white;
}

.applyBtn:hover {
	background-color: #45a049;
}

.time_modal_cencle {
	background-color: #f44336;
	color: white;
	margin-left: 10px;
}

.time_modal_cencle:hover {
	background-color: #da190b;
}


/* 버튼 세팅 */
.btn_inform {
	border: 0;
	width: 106px;
	margin: 0 auto;
}

.btn_bot_join, .btn_bot_cencle {
	padding: 10px 20px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-size: 16px;
}

.btn_bot_join {
	background-color: #4CAF50;
	color: white;
}

.btn_bot_join:hover {
	background-color: #45a049;
}

.btn_bot_cencle {
	background-color: #f44336;
	color: white;
	margin-left: 10px;
}

.btn_bot_cencle:hover {
	background-color: #da190b;
}

</style>

</style>
</head>
<body>

	<div class="section">
		<table>
			<thead>
				<tr>
					<th>기업</th>
					<th>상호명</th>
					<th>대표자명</th>
					<th>사업장 주소</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="selList" items="${selList}">
	                <c:if test="${selList.S_CHK eq 'Y'}">
	                    <tr class="dataRow"
	                        data-s-code="${selList.S_CODE}"
	                        data-cmp-no="${selList.CMP_NO}"
	                        data-cmp-name="${selList.CMP_NAME}"
	                        data-cmp-ceo="${selList.CMP_CEO}"
	                        data-cmp-addr="${selList.CMP_ADDR}">
	                        <td>${selList.CMP_NO}</td>
	                        <td>${selList.CMP_NAME}</td>
	                        <td>${selList.CMP_CEO}</td>
	                        <td>${selList.CMP_ADDR}</td>
	                    </tr>
	                </c:if>
	            </c:forEach>

			</tbody>
		</table>

	</div>





</body>
</html>