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

document.addEventListener("DOMContentLoaded", function() {
    const submenus = document.querySelectorAll('.collapse__menu');

    // 서브메뉴를 감추는 함수
    function hideSubmenus() {
        submenus.forEach(submenu => {
            submenu.style.display = 'none';
        });
    }

    // 페이지 로드시 초기화
    hideSubmenus();

    // 메뉴에 마우스가 올라갔을 때 서브메뉴를 보여주는 이벤트 핸들러 추가
    document.querySelectorAll('.nav__link').forEach(link => {
        link.addEventListener('mouseover', function() {
            const submenu = this.nextElementSibling;
            if (submenu) {
                hideSubmenus();
                submenu.style.display = 'block';
            }
        });
    });

    // 하위 메뉴 영역을 마우스가 벗어났을 때 메뉴를 닫지 않도록 설정
    document.querySelectorAll('.collapse__menu').forEach(submenu => {
        submenu.addEventListener('mouseleave', function() {
            this.style.display = 'none';
        });
    });
});

document.addEventListener("DOMContentLoaded", function() {
    const dropdownItems = document.querySelectorAll('.drop-deli');
    const deleSpan = document.querySelector('.dele');

    // 각 드롭다운 항목에 클릭 이벤트 핸들러 추가
    dropdownItems.forEach(item => {
        item.addEventListener('click', function() {
            const selectedText = this.textContent.trim();
            deleSpan.textContent = selectedText; // 선택된 택배사 이름으로 span 요소 내용 변경
        });
    });
});


document.addEventListener("DOMContentLoaded", function() {
    const dropdownItems = document.querySelectorAll('.drop-shop');
    const deleSpan = document.querySelector('.shop');

    // 각 드롭다운 항목에 클릭 이벤트 핸들러 추가
    dropdownItems.forEach(item => {
        item.addEventListener('click', function() {
            const selectedText = this.textContent.trim();
            deleSpan.textContent = selectedText; // 선택된 택배사 이름으로 span 요소 내용 변경
        });
    });
});



// warehouseCreate 에서 연동 택배사 쇼핑몰에 대한 자바스크립트

const courierDropdownItems = document.querySelectorAll('.drop-deli');
const courierButton = document.getElementById('courier-button');
const shopDropdownItems = document.querySelectorAll('.drop-shop');
const shopButton = document.getElementById('shop-button');

courierDropdownItems.forEach(item => {
    item.addEventListener('click', (event) => {
        event.preventDefault();
        courierButton.textContent = event.target.textContent;
    });
});

shopDropdownItems.forEach(item => {
    item.addEventListener('click', (event) => {
        event.preventDefault();
        shopButton.textContent = event.target.textContent;
    });
});



document.addEventListener("DOMContentLoaded", function() {
    const sectionCountSelect = document.getElementById("section-count");
    const sectionFieldsContainer = document.getElementById("section-fields");

    // 섹션 개수 선택에 따라 섹션 입력 필드를 동적으로 생성하는 함수
    function createSectionFields() {
        const sectionCount = parseInt(sectionCountSelect.value);
        sectionFieldsContainer.innerHTML = ""; // 기존의 섹션 입력 필드를 초기화

        for (let i = 1; i <= sectionCount; i++) {
            const sectionDiv = document.createElement("div");

            const nameLabel = document.createElement("label");
            nameLabel.textContent = `섹션 ${i} 이름: `;
            sectionDiv.appendChild(nameLabel);

            const nameSelect = document.createElement("select");
            nameSelect.name = `section${i}-name`;

            // 각 섹션의 종류 옵션 추가
            const option1 = document.createElement("option");
            option1.value = "가공";
            option1.textContent = "가공";
            nameSelect.appendChild(option1);

            const option2 = document.createElement("option");
            option2.value = "건조";
            option2.textContent = "건조";
            nameSelect.appendChild(option2);

            const option3 = document.createElement("option");
            option3.value = "냉동";
            option3.textContent = "냉동";
            nameSelect.appendChild(option3);

            const option4 = document.createElement("option");
            option4.value = "냉장";
            option4.textContent = "냉장";
            nameSelect.appendChild(option4);

            sectionDiv.appendChild(nameSelect);

            sectionFieldsContainer.appendChild(sectionDiv); // 섹션 입력 필드를 추가
        }
    }

    // 페이지 로드시 초기화
    createSectionFields();

    // 섹션 개수 선택이 변경될 때마다 섹션 입력 필드를 다시 생성
    sectionCountSelect.addEventListener("change", createSectionFields);

    // 폼 제출 이벤트 핸들러
    // document.getElementById("warehouse-form").addEventListener("submit", function(event) {
    //     event.preventDefault(); // 기본 제출 동작 방지
    //     window.location.href = "다음 페이지 URL";
    //     // 폼 데이터를 가져와서 처리하는 코드를 작성하세요.
    //     // 이 부분에서 선택된 섹션의 정보와 창고 주소를 가져와서 서버로 전송하거나 다른 작업을 수행할 수 있습니다.
    // });
});



