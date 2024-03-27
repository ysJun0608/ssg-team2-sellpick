// 검색 입력 필드에 'input' 이벤트 리스너를 추가합니다.
document.getElementById('searchInput').addEventListener('input', function(event) {
    // 입력한 검색어를 소문자로 변환하여 저장합니다.
    var searchValue = event.target.value.toLowerCase();
    // '.dropdown-item' 클래스를 가진 모든 메뉴 항목을 선택합니다.
    var menuItems = document.querySelectorAll('.dropdown-item');
    // 자동완성 목록을 나타내는 요소를 선택합니다.
    var autocompleteList = document.getElementById('autocompleteList');
    // 자동완성 목록의 내용을 초기화합니다.
    autocompleteList.innerHTML = '';

    // 검색어가 빈 문자열인 경우 자동완성 목록을 숨깁니다.
    if (searchValue.trim() === '') {
        autocompleteList.style.display = 'none';
        return;
    }

    // 검색어와 일치하는 메뉴 항목을 저장할 배열을 초기화합니다.
    var matchingItems = [];
    // 각 메뉴 항목을 반복하면서 검색어와 일치하는 항목을 찾습니다.
    menuItems.forEach(function(item) {
        var itemText = item.textContent.toLowerCase();
        if (itemText.includes(searchValue)) {
            matchingItems.push(item);
        }
    });

    // 일치하는 항목이 없는 경우 자동완성 목록을 숨깁니다.
    if (matchingItems.length === 0) {
        autocompleteList.style.display = 'none';
        return;
    }

    // 일치하는 항목을 자동완성 목록에 추가합니다.
    matchingItems.forEach(function(item, index) {
        var listItem = document.createElement('li');
        listItem.textContent = item.textContent;
        listItem.classList.add('autocomplete-item');
        // 첫 번째 항목에 'selected' 클래스를 추가하여 선택된 상태로 표시합니다.
        if (index === 0) {
            listItem.classList.add('selected');
        }
        // 자동완성 항목을 클릭했을 때 해당 메뉴 항목으로 이동하도록 클릭 이벤트를 추가합니다.
        listItem.addEventListener('click', function() {
            event.target.value = item.textContent;
            autocompleteList.innerHTML = '';
            autocompleteList.style.display = 'none';
            item.click();
        });
        autocompleteList.appendChild(listItem);
    });

    // 자동완성 목록을 화면에 표시합니다.
    autocompleteList.style.display = 'block';
});

// 검색 입력 필드에 'keydown' 이벤트 리스너를 추가합니다.
document.getElementById('searchInput').addEventListener('keydown', function(event) {
    var autocompleteList = document.getElementById('autocompleteList');
    var selectedItem = autocompleteList.querySelector('.selected');

    // 아래 화살표 키를 눌렀을 때
    if (event.key === 'ArrowDown') {
        event.preventDefault();
        // 선택된 항목이 있고 다음 항목이 있는 경우, 선택된 클래스를 다음 항목으로 이동합니다.
        if (selectedItem && selectedItem.nextElementSibling) {
            selectedItem.classList.remove('selected');
            selectedItem.nextElementSibling.classList.add('selected');
        } else if (!selectedItem && autocompleteList.firstElementChild) {
            // 선택된 항목이 없고 자동완성 목록에 항목이 있는 경우, 첫 번째 항목을 선택합니다.
            autocompleteList.firstElementChild.classList.add('selected');
        }
    } else if (event.key === 'ArrowUp') {
        event.preventDefault();
        // 위 화살표 키를 눌렀을 때
        // 선택된 항목이 있고 이전 항목이 있는 경우, 선택된 클래스를 이전 항목으로 이동합니다.
        if (selectedItem && selectedItem.previousElementSibling) {
            selectedItem.classList.remove('selected');
            selectedItem.previousElementSibling.classList.add('selected');
        }
    } else if (event.key === 'Enter') {
        event.preventDefault();
        // 엔터 키를 눌렀을 때
        // 선택된 항목이 있는 경우, 해당 항목을 클릭하여 페이지 이동을 수행합니다.
        if (selectedItem) {
            selectedItem.click();
        }
    }
});