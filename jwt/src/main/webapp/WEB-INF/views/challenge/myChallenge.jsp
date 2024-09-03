<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>참여중인 챌린지 목록</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f7f7f7;
            margin: 0;
            padding: 20px;
        }
        .container {
            width: 100%;
            max-width: 1200px;
        }
        .challenge-list {
            display: flex;
            flex-wrap: wrap;
        }
        .challenge-box {
            width: 300px;
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            margin: 10px;
            overflow: hidden;
            display: flex;
            align-items: center;
        }
        .challenge-box .image-placeholder {
            width: 80px;
            height: 80px;
            background-color: #ccc;
            display: flex;
            justify-content: center;
            align-items: center;
            color: #777;
            font-size: 14px;
            font-weight: bold;
            margin: 10px;
        }
        .challenge-box .content {
            flex: 1;
            padding: 10px;
        }
        .challenge-box .title {
            font-weight: bold;
            margin: 5px 0;
            font-size: 14px;
            color: #333;
        }
        .challenge-box .details {
            display: flex;
            justify-content: space-between;
            align-items: center;
            font-size: 14px;
            color: #777;
        }
        .challenge-box .details .participant-count {
            font-size: 14px;
            color: #777;
        }
        .challenge-box .details .button {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 5px 10px;
            border-radius: 5px;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
        }
        .challenge-box .details .button:hover {
            background-color: #0056b3;
        }
    </style>

</head>
<body>
<div class="container">
    <h3>참여중인 챌린지 목록</h3>
    <p>안녕하세요, ${name}님!</p>
    <div class="challenge-list">
        <!-- Challenge Item 1 -->
        <div class="challenge-box">
            <div class="image-placeholder">이미지</div>
            <div class="content">
                <div class="title">챌린지 제목 1</div>
                <div class="details">
                    <div class="participant-count">참여 인원수 4명</div>
                    <a href="#" class="button">상세보기</a>
                </div>
            </div>
        </div>

        <!-- Challenge Item 2 -->
        <div class="challenge-box">
            <div class="image-placeholder">이미지</div>
            <div class="content">
                <div class="title">챌린지 제목 2</div>
                <div class="details">
                    <div class="participant-count">참여 인원수 4명</div>
                    <a href="#" class="button">상세보기</a>
                </div>
            </div>
        </div>

        <!-- Add more challenge items as needed -->
    </div>
</div>
</body>
</html>
