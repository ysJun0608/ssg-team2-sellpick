/* EXPANDER MENU */
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
/* 하위 메뉴 닫기 */
const hideSubmenu = (submenu) => {
    submenu.classList.remove('show');
};

/* 사이드바 메뉴에 마우스를 가져다 대면 하위 메뉴 열기 */
const showSubmenu = (submenu) => {
    submenu.classList.add('show');
};


/* 메뉴 항목에서 마우스 벗어나면 하위 메뉴가 닫히지 않도록 설정 */
const handleSubmenuMouseEvents = () => {
    const navLinks = document.querySelectorAll('.has-submenu');
    navLinks.forEach((link) => {
        const submenu = link.nextElementSibling;

        link.addEventListener('mouseenter', () => {
            showSubmenu(submenu);
        });

        // 하위 메뉴 영역에서 마우스가 나가면 닫히지 않도록 설정
        submenu.addEventListener('mouseleave', () => {
            hideSubmenu(submenu);
        });
    });
};

/* 초기화 함수 */
const init = () => {
    showMenu('nav-toggle', 'navbar', 'body-pd');
    handleSidebarMouseEvents();
    handleSubmenuMouseEvents(); // 하위 메뉴 닫기 이벤트 핸들러 등록
};

/* 페이지 로드시 초기화 실행 */
window.onload = init;