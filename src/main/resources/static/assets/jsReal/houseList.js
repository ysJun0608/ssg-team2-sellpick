function resetFormAndQueryString() {
    document.getElementById("searchForm").reset();
    window.location.href = window.location.pathname; // 현재 페이지를 다시 로드하여 쿼리 문자열을 제거합니다.
}
