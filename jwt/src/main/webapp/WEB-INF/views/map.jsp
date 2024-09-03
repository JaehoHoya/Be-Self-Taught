<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>MAP</title>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=be5e9a482ef1bcad0ca4d349481aac7a" ></script>
</head>
<body>
<h1>MAP</h1>
<div id="map" style="width: 700px; height: 600px;"></div>


<script type="text/javascript">
    // 사용자 위치 정보를 가져옵니다.
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            // 사용자의 현재 위치입니다.
            var lat = position.coords.latitude;  // 위도
            var lng = position.coords.longitude; // 경도

            var container = document.getElementById('map'); // 지도를 담을 영역의 DOM 레퍼런스
            var options = { // 지도를 생성할 때 필요한 기본 옵션
                center: new kakao.maps.LatLng(lat, lng), // 지도의 중심좌표를 사용자의 현재 위치로 설정합니다.
                level: 3 // 지도의 레벨(확대, 축소 정도)
            };

            // 지도 생성 및 객체 리턴
            var map = new kakao.maps.Map(container, options);

            // 사용자의 현재 위치에 마커를 표시합니다.
            var marker = new kakao.maps.Marker({
                position: new kakao.maps.LatLng(lat, lng),
                map: map
            });

            // 마커에 인포윈도우를 표시합니다.
            var infowindow = new kakao.maps.InfoWindow({
                content: '<div style="padding:5px;">현재 위치</div>'
            });
            infowindow.open(map, marker);

        }, function (error) {
            console.error("Error Code: " + error.code + ", Message: " + error.message);
        });
    } else {
        alert('GPS를 지원하지 않습니다.');
    }
</script>
</body>
</html>