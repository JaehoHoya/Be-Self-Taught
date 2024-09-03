<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>챌린지 목록</title>
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
        .challenge-box {
            width: 220px;
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            margin: 10px;
            overflow: hidden;
            text-align: left;
            display: inline-block;
        }
        .challenge-box .image-placeholder {
            width: 100%;
            height: 150px;
            background-color: #ccc; /* Placeholder background color */
            display: flex;
            justify-content: center;
            align-items: center;
            color: #777;
            font-size: 16px;
            font-weight: bold;
        }
        .challenge-box .content {
            padding: 10px;
        }
        .challenge-box .title {
            font-weight: bold;
            margin: 5px 0;
            font-size: 16px;
            color: #333;
        }
        .challenge-box .user-info {
            display: flex;
            align-items: center;
            margin: 5px 0;
        }
        .challenge-box .user-info img {
            width: 30px;
            height: 30px;
            border-radius: 50%;
            margin-right: 10px;
            background-color: #ddd; /* Placeholder for profile image */
        }
        .challenge-box .user-id {
            color: #555;
        }
        .challenge-box .details {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-top: 10px;
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
        .challenge-box .date-range {
            font-size: 14px;
            color: #777;
            margin-top: 5px;
        }
        .challenge-list {
            display: flex;
            flex-wrap: wrap;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="challenge-list">
        <!-- Challenge Item 1 -->
        <div class="challenge-box">
            <div class="image-placeholder">이미지 공간</div>
            <div class="content">
                <div class="title">챌린지 제목 1</div>
                <div class="user-info">
                    <img src="" alt="프로필 이미지"> <!-- Placeholder for profile image -->
                    <div class="user-id">User123</div>
                </div>
                <div class="date-range">기간: 2024-01-01 ~ 2024-12-31</div>
                <div class="details">
                    <div class="participant-count">참여 인원수 4명</div>
                    <a href="#" class="button">참여하기</a>
                </div>
            </div>
        </div>

        <!-- Challenge Item 2 -->
        <div class="challenge-box">
            <div class="image-placeholder">이미지 공간</div>
            <div class="content">
                <div class="title">챌린지 제목 2</div>
                <div class="user-info">
                    <img src="" alt="프로필 이미지"> <!-- Placeholder for profile image -->
                    <div class="user-id">User456</div>
                </div>
                <div class="date-range">기간: 2024-02-01 ~ 2024-11-30</div>
                <div class="details">
                    <div class="participant-count">참여 인원수 4명</div>
                    <a href="#" class="button">참여하기</a>
                </div>
            </div>
        </div>

        <!-- Add more challenge items as needed -->
    </div>
</div>
</body>
</html>
