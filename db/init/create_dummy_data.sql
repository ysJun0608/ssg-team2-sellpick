USE SELLPICK;

INSERT INTO SHOPPING_MALL (ID, NAME)
VALUES
    (1, '신세계'),
    (2, '롯데'),
    (3, '현대'),
    (4, '이마트'),
    (5, '홈플러스'),
    (6, '쿠팡'),
    (7, '11번가'),
    (8, 'G마켓'),
    (9, '옥션'),
    (10, '티몬'),
    (11, '위메프'),
    (12, '인터파크');

INSERT INTO BRAND (ID, NAME)
VALUES
    (1, '현대'),
    (2, '삼성'),
    (3, 'LG'),
    (4, 'SK'),
    (5, 'KT');

INSERT INTO DELIVERY_CMP (ID, NAME)
VALUES
    (1, '대한통운'),
    (2, '한진택배'),
    (3, '로젠택배'),
    (4, 'CJ대한통운'),
    (5, '우체국택배'),
    (6, '로젠'),
    (7, '일양로지스'),
    (8, '한의사랑택배'),
    (9, '롯데택배'),
    (10, 'EMS');

INSERT INTO BUSINESS_OWNER (ID, EMAIL, PASSWORD, NAME, PHONE, ADDRESS)
VALUES
    (1, 'TEST01@TEST.COM', '1111', '최문석', '010-1111-1111', '서울시 강남구'),
    (2, 'TEST02@TEST.COM', '2222', '백정훈', '010-2222-2222', '서울시 송파구'),
    (3, 'TEST03@TEST.COM', '3333', '문지환', '010-3333-3333', '서울시 강북구'),
    (4, 'TEST04@TEST.COM', '4444', '양성준', '010-4444-4444', '서울시 동작구'),
    (5, 'TEST05@TEST.COM', '5555', '이도엽', '010-5555-5555', '서울시 서초구'),
    (6, 'TEST06@TEST.COM', '6666', '김현수', '010-6666-6666', '서울시 강동구');

INSERT INTO CUSTOMER (ID, EMAIL, PASSWORD, NAME, PHONE, ADDRESS)
VALUES
    (1, 'CUSTOMER01@TEST.COM', '1111', '김철수', '010-1111-1111', '서울시 강남구'),
    (2, 'CUSTOMER02@TEST.COM', '2222', '박영희', '010-2222-2222', '서울시 송파구'),
    (3, 'CUSTOMER03@TEST.COM', '3333', '이철민', '010-3333-3333', '서울시 강북구'),
    (4, 'CUSTOMER04@TEST.COM', '4444', '김미영', '010-4444-4444', '서울시 동작구'),
    (5, 'CUSTOMER05@TEST.COM', '5555', '박미영', '010-5555-5555', '서울시 서초구');
