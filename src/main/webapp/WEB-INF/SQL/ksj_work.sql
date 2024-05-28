show user;
-- USER이(가) "SEMI_ORAUSER4"입니다.

-- 제품 테이블
create table Product
(pIdx              NVARCHAR2(20)  not null        -- 제품번호
,pName             NVARCHAR2(100) not null        -- 제품명
,pEngName          NVARCHAR2(100) not null        -- 제품영문명
,pType             NVARCHAR2(10)  not null        -- 제품분류(레드,화이트,로제,스파클링)
,pHomeTown         NVARCHAR2(10)  not null        -- 원산지(칠레,미국,이탈리아,뉴질랜드,호주,스페인,프랑스)
,pPrice            NVARCHAR2(100) not null        -- 가격
,pPoint            NVARCHAR2(20)  not null        -- 적립금
,pBody             NVARCHAR2(1)   not null        -- 바디 (가벼움 약간가벼움 중간 약간무거움 무거움)
,pAcid             NVARCHAR2(1)   not null        -- 산도 (낮음 약간낮음 중간 약간높음 높음)
,pTannin           NVARCHAR2(1)   not null        -- 타닌 (약함 약간약함 중간 약간강함 강함)
,pAcl              NVARCHAR2(1)   not null        -- 도수(알코올) (낮음(~11%) 중간(12~13%) 높음(14%+))
,pDetail           NVARCHAR2(300) not null        -- 제품설명
,pImg              NVARCHAR2(100) not null        -- 제품이미지
,pStock            NVARCHAR2(20)  not null        -- 재고량
,constraint PK_Product_pIdx primary key(pIdx)
);
-- Table PRODUCT이(가) 생성되었습니다.

-- 제품 인덱스 시퀀스
create sequence seq_pIdx
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_PIDX이(가) 생성되었습니다.



-- 회원테이블
create table member 
(userid              VARCHAR2(20)   not null                    -- 회원아이디
,pwd                 VARCHAR2(20)   not null                    -- 암호
,name                NVARCHAR2(20)  not null                    -- 회원명
,email               VARCHAR2(100)  not null                    -- 이메일
,phone               VARCHAR2(20)   not null                    -- 연락처
,address             NVARCHAR2(100) not null                    -- 주소
,addressDetail       NVARCHAR2(100) not null                    -- 상세주소
,gender              NVARCHAR2(2)   not null                    -- 성별
,birthday            NVARCHAR2(20)  not null                    -- 생년월일
,point               NVARCHAR2(20)                              -- 포인트
,registerDay         NVARCHAR2(20)  default sysdate  not null   -- 가입일자
,pwdUpdateDay        NVARCHAR2(20)                              -- 암호변경일
,memberIdx           NVARCHAR2(1) not null                      -- 회원상태코드
,constraint PK_member_userid primary key(userid)
,constraint UQ_member_email  unique(email)
,constraint FK_member_fk_memberIdx foreign key(memberIdx) references memberIdx(memberIdx)
);
-- Table MEMBER이(가) 생성되었습니다.



-- 회원상태 테이블
create table memberIdx
(
memberIdx           NVARCHAR2(1)  not null        -- 회원상태코드
,status             NVARCHAR2(20) not null        -- 상태 (가입, 탈퇴, 휴면, 관리자, 팀원)
,constraint PK_memberIdx_memberIdx primary key(memberIdx)
);
-- Table MEMBERIDX이(가) 생성되었습니다.


-- 로그인 기록 테이블
create table Log
(
logIdx              NVARCHAR2(20) not null                          -- INDEX
,userid             VARCHAR2(20)  not null                          -- 회원아이디
,loginDate          NVARCHAR2(20) default sysdate  not null         -- 로그인날짜시각
,ipAddress          NVARCHAR2(20) not null                          -- 접속IP주소
,constraint PK_Log_logIdx primary key(logIdx)
,constraint FK_Log_fk_userid foreign key(userid) references member(userid)
);
-- Table LOG이(가) 생성되었습니다.


-- 로그인기록 인덱스 시퀀스
create sequence seq_logIdx
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_LOGIDX이(가) 생성되었습니다.




-- 리뷰테이블
create table Review
(
rIdx              NVARCHAR2(20) not null                     -- 작성번호(PK)
,pIdx             NVARCHAR2(20) not null                     -- 제품번호(FK)
,userid           VARCHAR2(20)  not null                     -- 회원아이디(FK)
,rStar            NVARCHAR2(1)  not null                     -- 별점
,rDetail          NVARCHAR2(300)                             -- 리뷰내용
,rDate            NVARCHAR2(20) default sysdate  not null    -- 작성일자
,constraint PK_Review_rIdx primary key(rIdx)
,constraint FK_Review_fk_pIdx foreign key(pIdx) references Product(pIdx)
,constraint FK_Review_fk_userid foreign key(userid) references member(userid)
);
-- Table REVIEW이(가) 생성되었습니다.


-- 리뷰 인덱스 시퀀스
create sequence seq_rIdx
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_RIDX이(가) 생성되었습니다.



-- 장바구니 테이블
create table Cart
(
cIdx              NVARCHAR2(20)  not null                      -- 장바구니번호(PK)
,pIdx             NVARCHAR2(20)  not null                      -- 제품번호(FK)
,userid           VARCHAR2(20)   not null                      -- 회원아이디(FK)
,cVolume          NVARCHAR2(20)  not null                      -- 주문량
,constraint PK_Cart_cIdx primary key(cIdx)
,constraint FK_Cart_fk_pIdx foreign key(pIdx) references Product(pIdx)
,constraint FK_Cart_fk_userid foreign key(userid) references member(userid)
);
-- Table CART이(가) 생성되었습니다.


-- 장바구니 인덱스 시퀀스
create sequence seq_cIdx
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_CIDX이(가) 생성되었습니다.


commit;
--커밋 완료.



select *
from member;


desc point;

select *
from point
order by podate desc;


select R.*, O.userid, P.pindex 
from product P JOIN orders O 
ON P.pindex = O.pindex 
JOIN review R 
ON O.oindex = R.oindex 
where P.pindex = '20'
order by rindex desc


SELECT POINCOME, PODETAIL, PODATE 
FROM POINT 
WHERE USERID = 'ksj1024sj' 
ORDER BY PODATE DESC


SELECT NVL(SUM(TO_NUMBER(POINCOME)), '0') AS AvailablePoints 
     , NVL(SUM(TO_NUMBER(POINCOME)), '0') AS UsedPoints 
	 , NVL(SUM(TO_NUMBER(POINCOME)), '0') + NVL(SUM(TO_NUMBER(POINCOME)), '0') AS TotalPoints 
FROM point 
WHERE USERID = 'ksj1024sj'

select CODISCOUNT, CONAME, CODETAIL, COTYPE, CODATE, COREGISTERDAY
from coupon 
where COCODE = 'QXWRTYPLMNZA'

select COINDEX, USERID, CONAME, COSTATUS
from mycoupon 
where userid = 'ksj1024sj'

    update coupon set cocode = 'QXWRTYPLMNZA'
    where userid = 'ksj1024sj';

desc coupon;

desc mycoupon;

 select *
 from user_sequences

    update coupon set COCODE = 'VHNFGXZLOUAW'
    where codate = '2024-12-31'
    
    commit;


 select last_number  -- 다음번에 들어올 시퀀스 값을 미리 알려주는 것이다.
 from user_sequences
 where sequence_name = 'SEQ_COINDEX';


INSERT INTO mycoupon (COINDEX, USERID, CONAME, COSTATUS)
SELECT SEQ_COINDEX.nextval, 'ksj1024sj', CONAME, 1
FROM coupon
WHERE COCODE = 'QXWRTYPLMNZA';

select *
from coupon



INSERT INTO mycoupon (COINDEX, USERID, CONAME, COSTATUS)
SELECT SEQ_COINDEX.nextval, 'ksj1024sj', CONAME, 1
FROM coupon c
WHERE c.COCODE = 'QXWRTYPLMNZA'
AND NOT EXISTS (
    SELECT 1
    FROM mycoupon mc
    WHERE mc.CONAME = c.CONAME
);

select COINDEX, USERID, CONAME, COSTATUS
from mycoupon 
where userid = 'ksj1024sj'

select *
from coupon 

select CODISCOUNT, CONAME, CODETAIL, COTYPE, CODATE, COREGISTERDAY
from coupon 
where COCODE = 'QXWRTYPLMNZA'

delete from mycoupon
where userid = 'ksj1024sj'
commit;


select *
from coupon



select TRUNC(SYSDATE)
from dual

select COINDEX, USERID, CONAME, COSTATUS
from mycoupon 
where userid = 'ksj1024sj'


INSERT INTO mycoupon (COINDEX, USERID, CONAME, COSTATUS) 
SELECT SEQ_COINDEX.nextval, 'ksj1024sj', C.CONAME, 1 
FROM coupon C 
WHERE C.COCODE = 'QXWRTYPLMNZA'
AND TO_DATE(C.CODATE, 'YYYY-MM-DD') >= TO_DATE(TO_CHAR(SYSDATE, 'YYYY-MM-DD'), 'YYYY-MM-DD')
AND NOT EXISTS ( 
             SELECT 1 
             FROM mycoupon MC 
             WHERE MC.CONAME = C.CONAME AND MC.USERID = 'ksj1024sj'
);


SELECT *
FROM coupon
WHERE COCODE = 'QXWRTYPLMNZA' AND TO_DATE(CODATE, 'YYYY-MM-DD') <= TRUNC(SYSDATE);



SELECT *
FROM coupon
WHERE COCODE = 'QXWRTYPLMNZA' AND TO_DATE(CODATE, 'YYYY-MM-DD') >= TRUNC(SYSDATE);



VHNFGXZLOUAW

SELECT rno, USERID, POINCOME, PODETAIL, PODATE
FROM  
( 
select rownum as rno, USERID, POINCOME, PODETAIL, PODATE
 from   
 (  
 select USERID, POINCOME, PODETAIL, PODATE
from point 
where userid = ? 
) V 
) T  
 WHERE T.rno BETWEEN ? AND ?
 
 
 
 select CODISCOUNT, CONAME, CODETAIL, COTYPE, COMIN, CODATE, COREGISTERDAY, COCODE
 from coupon
 where coname = v.coname
 
 
 
 SELECT 
    T.rno, 
    T.COINDEX, 
    T.USERID, 
    T.CONAME, 
    T.COSTATUS, 
    C.CODISCOUNT, 
    C.CODETAIL, 
    C.COTYPE, 
    C.COMIN, 
    C.CODATE, 
    C.COREGISTERDAY, 
    C.COCODE
FROM (
    SELECT 
        rownum AS rno, 
        COINDEX, 
        USERID, 
        CONAME, 
        COSTATUS
    FROM (
        SELECT 
            COINDEX, 
            USERID, 
            CONAME, 
            COSTATUS
        FROM 
            mycoupon
        WHERE 
            userid = 'ksj1024sj'
    ) V
) T
JOIN coupon C ON T.CONAME = C.CONAME
WHERE T.rno BETWEEN 1 AND 5


desc coupon


select ceil(count(*)/10) 
from point 
where userid = 'ksj1024sj'

select *
from point;

desc point

select *
from product 

select NVL(count(*), 0) as count
from LIKEIT
where pindex = 33



SELECT rno, USERID, POINCOME, PODETAIL, PODATE
 FROM 
 ( 
     select rownum as rno, USERID, POINCOME, PODETAIL, PODATE 
     from  
     ( 
      select USERID, POINCOME, PODETAIL, PODATE 
      from point 
      where userid = ?
order by PODATE desc 
     ) V 
 ) T 
 WHERE T.rno BETWEEN ? AND ?


desc point

  insert into point(USERID, POINCOME, PODETAIL, PODATE )
  values('ksj1024sj', '4400', '상품 구매 립', '2024-05-27 12:25:00');
  
  commit;
  
  
SELECT NVL(TO_CHAR(SUM(TO_NUMBER(POINCOME))), '0') AS AvailablePoints, PODETAIL, PODATE 
FROM POINT 
WHERE PODETAIL LIKE '%적립' AND USERID = 'ksj1024sj'
GROUP BY PODETAIL, PODATE
ORDER BY PODATE DESC;