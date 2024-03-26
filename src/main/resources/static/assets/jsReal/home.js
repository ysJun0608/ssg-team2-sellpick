// 'searchInput' 요소에 'keyup' 이벤트에 대한 이벤트 리스너를 추가합니다.
document.getElementById('searchInput').addEventListener('keyup', function(event) {
    // 이벤트를 발생시킨 키가 'Enter'인지 확인합니다.
    if (event.key === 'Enter') {
        // 입력 필드의 현재 값을 소문자로 변환하여 저장합니다.
        var searchValue = event.target.value.toLowerCase();
        // 'dropdown-item' 클래스를 가진 모든 요소를 선택하여 'menuItems'에 저장합니다.
        var menuItems = document.querySelectorAll('.dropdown-item');

        // 'menuItems'의 각 항목에 대해 반복합니다.
        menuItems.forEach(function(item) {
            // 현재 항목의 텍스트 내용을 소문자로 저장합니다.
            var itemText = item.textContent.toLowerCase();
            // 항목의 텍스트 내용에 검색 값이 포함되어 있는지 확인합니다.
            if (itemText.includes(searchValue)) {
                // 항목의 텍스트 내용에 검색 값이 포함되어 있다면 해당 항목을 클릭한 것처럼 시뮬레이션합니다.
                item.click();
            }
        });
    }
});
