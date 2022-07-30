/**
 * 
 */

var markers = [];

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = {
		center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
		level: 8
		// 지도의 확대 레벨
	};
var center = new kakao.maps.LatLng(37.566826, 126.9786567);
// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption);

// 장소 검색 객체를 생성합니다
var ps = new kakao.maps.services.Places();

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({
	zIndex: 1
});
$(function() {
	mapList();
});
function mapList() {
	var url = '/customer/board/map/list';
	$.get(url, function(response) {
		if (response.result == 'fail') {
			console.log(response.message);
			return;
		}
		$('#map').empty();
		map = new kakao.maps.Map(mapContainer, mapOption);
		// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
		var zoomControl = new kakao.maps.ZoomControl();
		map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
		for(var i=0;i<=$(".addr").length - 1;i++){
		// 주소로 좌표를 검색합니다
			geocoder.addressSearch($("#addr_"+i).val(), function(result, status) {
	
			    // 정상적으로 검색이 완료됐으면 
			     if (status === kakao.maps.services.Status.OK) {
	
			        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
	
			        // 결과값으로 받은 위치를 마커로 표시합니다
			        var marker = new kakao.maps.Marker({
			            map: map,
			            position: coords,
			            clickable: true
			        });
					/* 
			        // 인포윈도우로 장소에 대한 설명을 표시합니다
			        var infowindow = new kakao.maps.InfoWindow({
			            content: `<div style="width:150px;text-align:center;padding:6px 0;"></div>`
			        });
			        infowindow.open(map, marker);
					 */
			        /* 
			        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
			        map.setCenter(coords); 
					 */
					 var content = 
						 `
					 	<div class="overlaybox"> 
					        <div class="boxtitle">for index${i}</div> 
					        <div class="first"> 
					            <div class="triangle text">1</div> 
					            <div class="movietitle text">드래곤 길들이기2</div>      
					        </div>
					        <ul>
					            <li class="up">
					                <span class="number">2</span>
					                <span class="title">명량</span>
					                <span class="arrow up"></span>
					                <span class="count">2</span>
					            </li>
					            <li>
					                <span class="number">3</span>
					                <span class="title">해적(바다로 간 산적)</span>
					                <span class="arrow up"></span>
					                <span class="count">6</span>
					            </li>
					            <li>
					                <span class="number">4</span>
					                <span class="title">해무</span>
					                <span class="arrow up"></span>
					                <span class="count">3</span>
					            </li>
					            <li>
					                <span class="number">5</span>
					                <span class="title">안녕, 헤이즐</span>
					                <span class="arrow down"></span>
					                <span class="count">1</span>
					            </li>
					        </ul>
					    </div>
					    `;

				     // 커스텀 오버레이를 생성합니다
				    var overlay = new kakao.maps.CustomOverlay({
				        position: coords,
				        content: content,
				        xAnchor: 0.3,
				        yAnchor: 0.91
				    });
				 	// 마커에 click 이벤트를 등록합니다
				    kakao.maps.event.addListener(marker, 'click', function() {

				        // 클릭된 마커가 없고, click 마커가 클릭된 마커가 아니면
				        // 마커의 이미지를 클릭 이미지로 변경합니다
				        if (!selectedMarker || selectedMarker !== marker) {

				            // 클릭된 마커 객체가 null이 아니면
				            // 클릭된 마커의 이미지를 기본 이미지로 변경하고
				            !!selectedMarker && selectedMarker.setImage(overlay.setMap(null));

				            // 현재 클릭된 마커의 이미지는 클릭 이미지로 변경합니다
				            marker.setImage(overlay.setMap(map));
				        }

				        // 클릭된 마커를 현재 클릭된 마커 객체로 설정합니다
				        selectedMarker = marker;
				    });
				 	// 마커에 클릭이벤트를 등록합니다
				    /* kakao.maps.event.addListener(marker, 'click', function() {
				        // 마커 위에 커스텀 오버레이를 표시합니다
				        overlay.setMap(map);
				 	});
				    kakao.maps.event.addListener($(".boxtitle"), 'click', function() {
				    	    
				 	}); */
				    
			    } 
			});
		}
	});
}

if (response.result == 'success') {
	// 마을 전체 리스트를 돌면서 마을 위치마다 마커 찍기
	$.each(response.data, function(index, value) {
		// DB에서 주소를 불러와 geocoder를 이용해 좌표로 변환
		geocoder.addressSearch(response.data[index].townAddr, function(result, status) {
			// 정상적으로 검색이 완료됐으면 
			if (status === kakao.maps.services.Status.OK) {
				var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

				// 마커가 표시될 위치입니다
				var markerPosition = new kakao.maps.LatLng(coords.Ha, coords.Ga);

				// 마커를 생성합니다
				var marker = new kakao.maps.Marker({
					image: clamImage,
					position: markerPosition
				});

				// 커스텀 오버레이를 닫는 함수입니다.
				var closeOverlay = function() {
					overlay.setMap(null);
				};

				// 커스텀 오버레이에 표시할 컨텐츠 입니다
				// 커스텀 오버레이는 아래와 같이 사용자가 자유롭게 컨텐츠를 구성하고 이벤트를 제어할 수 있기 때문에
				// 별도의 이벤트 메소드를 제공하지 않습니다
				var $box = $('<div class="box"/>');
				var $wrap = $('	<div class="wrap"/>');
				var $info = $('	 <div class="info"/>');
				var $title = $('	  <div class="title"/>').text(response.data[index].townName);
				var $body = $('<div class="body"/>');
				var $contents =
					$('<div class="img">' +
						'		<img src="http://cfile181.uf.daum.net/image/250649365602043421936D" width="73" height="70">' +
						'</div>' +
						'<div class="desc"">' +
						'	<div class="ellipsis">' + response.data[index].townAddr + '</div>' +
						'	<div>' +
						'		<a href="${pageContext.request.contextPath}/town/' + response.data[index].townURL +
						'		" target="_blank" class="link">/' + response.data[index].townURL + '</a>' +
						'	</div>' +
						'</div>');

				$box.append($wrap);
				$wrap.append($info);
				$info.append($title).append($body);
				$body.append($contents);

				// 커스텀 오버레이 내용
				var content = $box[0];

				// 마커와 커스텀 오버레이를 감싸는 영역
				var wrap = $wrap[0];

				// 커스텀 오버레이 생성
				var overlay = new kakao.maps.CustomOverlay({
					content: content,
					map: map,
					position: marker.getPosition()
				});

				// 마커에 마우스를 올렸을 때 커스텀 오버레이를 표시
				kakao.maps.event.addListener(marker, 'mouseover', function() {
					// 기존에 표시된 커스텀 오버레이가 존재할 경우
					if (selectedOverlay != null && selectedMarker != null) {
						selectedOverlay.setMap(null);			// 기존 커스텀 오버레이를 닫는다
						selectedMarker.setImage(clamImage);		// 기존 마커를 기존 마커 이미지로 변경
						selectedMarker.setZIndex(1);			// 기존 마커를 지도 상에서 아래로 내린다
					}
					// 현재 마우스오버 이벤트가 발생한 마커와 커스텀 오버레이를 변수에 담아 맵에 표시한다
					selectedOverlay = overlay;
					selectedMarker = marker;

					// z-index 변경 : 선택된 마커와 커스텀 오버레이를 지도 상에서 가장 위로 올린다
					selectedOverlay.setZIndex(10);
					selectedMarker.setZIndex(10);

					// 선택된 마커의 커스텀 오버레이를 표시한다
					selectedOverlay.setMap(map);

					// 선택된 마커를 새로운 마커 이미지로 변경한다
					selectedMarker.setImage(clamImage_selected);

					// 커스텀 오버레이의 내용을 감싸는 영역인 wrap에 이벤트 등록
					// wrap 영역에서 마우스가 벗어나면
					wrap.addEventListener('mouseout', function() {
						// 커스텀 오버레이가 사라진다
						selectedOverlay.setMap(null);

						// 선택된 마커의 이미지를 원래 이미지로 변경
						selectedMarker.setImage(clamImage);
					}, true);

				});

				// 펼쳐진 커스텀 오버레이 닫기
				closeOverlay();

				// 마커가 지도 위에 표시되도록 설정합니다
				marker.setMap(map);

				// 생성된 마커를 배열에 추가
				markers.push(marker);
			}
		});
	})
}

