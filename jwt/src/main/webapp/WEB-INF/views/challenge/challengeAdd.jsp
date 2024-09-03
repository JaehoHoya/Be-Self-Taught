<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>챌린지 등록</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #f7f7f7;
        }
        .container {
            width: 90%;
            max-width: 700px;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            background-color: #fff;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }
        .form-group input[type="text"],
        .form-group input[type="date"],
        .form-group input[type="number"],
        .form-group select {
            width: calc(100% - 22px);
            padding: 10px;
            box-sizing: border-box;
        }
        .form-group img {
            width: 100%;
            height: 150px;
            object-fit: cover;
        }
        .file-buttons {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .file-buttons input[type="file"] {
            display: none;
        }
        .category-other {
            display: none;
        }
        .row {
            display: flex;
            align-items: center;
            gap: 10px;
        }
        .row .form-group {
            flex: 1;
        }
        .form-actions {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }
        .datepicker {
            position: relative;
        }
        .datepicker button {
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <h3>챌린지 등록</h3>
    ${name}
    <form action="/your-submit-url" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="title">제목</label>
            <input type="text" id="title" name="title" placeholder="챌린지 제목" autofocus>
        </div>
        <div class="form-group">
            <img id="preview" src="" alt="미리보기" style="display: none;">
            <div class="file-buttons">
                <input type="file" accept="image/png, image/jpeg" id="imageFile" name="challengeImage">
                <button type="button" id="fileButton">챌린지 표지 등록</button>
                <button type="button" id="changeFileButton" style="display: none;">파일 변경</button>
            </div>
        </div>
        <div class="form-group">
            <div class="row">
                <button type="button" id="categoryButton">챌린지 카테고리 선택</button>
                <select name="category" id="categorySelect" style="display: none;">
                    <option value="title">3kg 감량</option>
                    <option value="author">바디프로필</option>
                    <option value="other">기타</option>
                </select>
                <div class="category-other">
                    <input type="text" name="categoryOther" placeholder="기타 카테고리 입력">
                </div>
            </div>
        </div>
        <div class="form-group">
            <label>챌린지 기간</label>
            <div class="datepicker">
                <input type="date" name="startDate" id="startDate" placeholder="시작 날짜">
                <input type="date" name="endDate" id="endDate" placeholder="종료 날짜">
                <button type="button" id="setDateRange">날짜 범위 설정</button>
            </div>
        </div>
        <div class="form-group">
            <div class="row">
                <button type="button" id="participantButton">인원수 제한</button>
                <input type="number" id="participantLimit" name="participantLimit" placeholder="참여 가능한 인원 수" style="display: none;">
            </div>
        </div>
        <div class="form-group">
            <div class="row">
                <button type="button" id="certificationButton">인증 횟수 선택</button>
                <select name="certificationFrequency" id="certificationFrequency" style="display: none;">
                    <option value="3회">주3회</option>
                    <option value="4회">주4회</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="description">챌린지 내용</label>
            <input type="text" id="description" name="description" placeholder="챌린지 내용">
        </div>
        <div class="form-actions">
            <button type="submit">등록</button>
            <button type="button" onclick="window.history.back();">뒤로가기</button>
        </div>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#fileButton").on("click", function() {
            $("#imageFile").click();
        });

        $("#imageFile").on("change", function(event) {
            var file = event.target.files[0];
            if (file) {
                var reader = new FileReader();
                reader.onload = function(e) {
                    $("#preview").attr("src", e.target.result).show();
                    $("#fileButton").hide();
                    $("#changeFileButton").show();
                }
                reader.readAsDataURL(file);
            }
        });

        $("#changeFileButton").on("click", function() {
            $("#imageFile").click();
        });

        $("#categoryButton").on("click", function() {
            $("#categorySelect").toggle();
        });

        $("#categorySelect").on("change", function() {
            if ($(this).val() === "other") {
                $(".category-other").show();
            } else {
                $(".category-other").hide();
            }
        });

        $("#participantButton").on("click", function() {
            $("#participantLimit").toggle();
        });

        $("#certificationButton").on("click", function() {
            $("#certificationFrequency").toggle();
        });

        $("#setDateRange").on("click", function() {
            var startDate = $("#startDate").val();
            var endDate = $("#endDate").val();
            if (startDate && endDate) {
                alert("선택된 기간: " + startDate + "부터 " + endDate + "까지");
            } else {
                alert("시작 날짜와 종료 날짜를 모두 선택해 주세요.");
            }
        });
    });
</script>
</body>
</html>
