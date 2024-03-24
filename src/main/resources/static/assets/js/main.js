/* EXPANDER MENU */
// 사이드바 마우스 오버/아웃 이벤트 핸들러
const handleSidebarMouseEvents = () => {
    const navbar = document.getElementById('navbar');
    const bodypadding = document.getElementById('body-pd');

    // 마우스가 사이드바 위에 있을 때
    navbar.addEventListener('mouseenter', () => {
        navbar.classList.add('expander');
        bodypadding.classList.add('body-pd');
        document.body.style.overflow = 'hidden';
    });

    // 마우스가 사이드바를 벗어날 때
    navbar.addEventListener('mouseleave', () => {
        navbar.classList.remove('expander');
        bodypadding.classList.remove('body-pd');
        document.body.style.overflow = 'auto';
    });
};

// 사이드바 열고 닫는 함수
const showMenu = (toggleId, navbarId, bodyId) => {
    const toggle = document.getElementById(toggleId),
        navbar = document.getElementById(navbarId),
        bodypadding = document.getElementById(bodyId);

    if (toggle && navbar) {
        toggle.addEventListener('click', () => {
            navbar.classList.toggle('expander');
            bodypadding.classList.toggle('body-pd');

            if (navbar.classList.contains('expander')) {
                document.body.style.overflow = 'hidden';
            } else {
                document.body.style.overflow = 'auto';
            }
        });
    }
};

// 초기화 함수
const init = () => {
    showMenu('nav-toggle', 'navbar', 'body-pd');
    handleSidebarMouseEvents();
};

// 페이지 로드시 초기화 실행
window.onload = init;

showMenu('nav-toggle', 'navbar', 'body-pd')

/* LINK ACTIVE */
const linkColor = document.querySelectorAll('.nav__link')
function colorLink() {
    linkColor.forEach(l=> l.classList.remove('active'))
    this.classList.add('active')
}
linkColor.forEach(l=> l.addEventListener('click', colorLink))

/* COLLAPSE MENU */
const linkCollapse = document.getElementsByClassName('collapse__link')
var i

for(i=0;i<linkCollapse.length;i++) {
    linkCollapse[i].addEventListener('click', function(){
        const collapseMenu = this.nextElementSibling
        collapseMenu.classList.toggle('showCollapse')

        const rotate = collapseMenu.previousElementSibling
        rotate.classList.toggle('rotate')
    });
}

// 스크롤 내리는 기능 추가
window.addEventListener('scroll', function() {
    const navbar = document.getElementById('navbar');
    const toggle = document.getElementById('nav-toggle');
    const bodypadding = document.getElementById('body-pd');

    // 스크롤이 발생했을 때 사이드바가 열려있다면 닫습니다.
    if (navbar.classList.contains('expander')) {
        navbar.classList.remove('expander');
        bodypadding.classList.remove('body-pd');
        document.body.style.overflow = 'auto';
        toggle.classList.remove('toggle');
    }
});