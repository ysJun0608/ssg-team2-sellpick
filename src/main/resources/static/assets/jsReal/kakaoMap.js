var mapContainer = document.getElementById('map');
var detailContainer = document.getElementById('detail');
var mapOptions = {
    center: new kakao.maps.LatLng(37.5665, 126.9780),
    level: 3
};
var map = new kakao.maps.Map(mapContainer, mapOptions);
var geocoder = new kakao.maps.services.Geocoder();

function showWarehouseMap(element) {
    var location = element.getAttribute('data-location');
    geocoder.addressSearch(location, function (result, status) {
        if (status === kakao.maps.services.Status.OK) {
            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
            var marker = new kakao.maps.Marker({
                map: map,
                position: coords
            });
            map.setCenter(coords);
            mapContainer.style.display = 'block';
            detailContainer.style.display = 'block';
            relayout();
        }
    });

    var rows = document.querySelectorAll('#warehouse-data tr');
    rows.forEach(function (row) {
        row.classList.remove('selected');
    });
    element.classList.add('selected');

    // 선택된 행의 데이터 가져오기
    var type = element.cells[0].textContent;
    var id = element.cells[1].textContent;
    var deliveryCompanyName = element.cells[2].textContent;
    var location = element.cells[3].textContent;
    var sectionType = element.getAttribute('data-section-type');
    var sectionTypes = sectionType.split(',').slice(0, 2);
    sectionType = sectionTypes.join(', ');

    // 상세 정보 업데이트
    var detailList = detailContainer.querySelector('.list-group');
    detailList.innerHTML = '';
    detailList.innerHTML += '<li class="list-group-item bg-transparent">창고대분류: ' + type + '</li>';
    detailList.innerHTML += '<li class="list-group-item bg-transparent">창고번호: ' + id + '</li>';
    detailList.innerHTML += '<li class="list-group-item bg-transparent">택배사: ' + deliveryCompanyName + '</li>';
    detailList.innerHTML += '<li class="list-group-item bg-transparent">창고주소: ' + location + '</li>';
    detailList.innerHTML += '<li class="list-group-item bg-transparent">창고소분류: ' + sectionType + '</li>';
}

function relayout() {
    // 지도를 표시하는 div 크기를 변경한 이후 지도가 정상적으로 표출되지 않을 수도 있습니다
    // 크기를 변경한 이후에는 반드시 map.relayout 함수를 호출해야 합니다
    // window의 resize 이벤트에 의한 크기변경은 map.relayout 함수가 자동으로 호출됩니다
    map.relayout();
}