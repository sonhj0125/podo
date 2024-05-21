show user;
-- USER이(가) "SEMI_ORAUSER4"입니다.

select * from tab;

select to_char(sysdate, 'yyyy-mm-dd')
from dual;

select *
from member;

select *
from memberidx;



select userid, name, email, phone, gender, status
from member 
join memberidx 
on member.memberidx = memberidx.memberidx
where memberidx.memberidx != 9 and name like '%'|| '테' ||'%' 
order by registerday desc ;


select ceil(count(*)/10)
from member 
join memberidx 
on member.memberidx = memberidx.memberidx
where memberidx.memberidx != 9;



select userid, name, email, phone, gender, status
from member 
join memberidx 
on member.memberidx = memberidx.memberidx
where memberidx.memberidx != 9 and name like '%'|| '테' ||'%' 
order by registerday desc ;


SELECT rno, userid, name, email, gender 
FROM 
( 
    select rownum AS RNO, userid, name, email, gender
    from 
    (
        select userid, name, email, gender
        from member
        where userid != 'admin' and name like '%'|| '테' ||'%'
        order by registerday desc
    ) V 
) T
WHERE T.rno BETWEEN 1 AND 10;



SELECT rno, userid, name, email, phone, gender, status
FROM 
( 
    select rownum AS RNO, userid, name, email, phone, gender, status
    from 
    (
        select userid, name, email, phone, gender, status
        from member join memberidx on member.memberidx = memberidx.memberidx
        where memberidx.memberidx != 9 and name like '%'|| '테' ||'%'
        order by registerday desc
    ) 
) T
WHERE T.rno BETWEEN 1 AND 20;


select count(*)
from tbl_member
where userid != 'admin'
and name like '%'|| '아' ||'%'

select count(*)
from member join memberidx on member.memberidx = memberidx.memberidx
where memberidx.memberidx != 9 and name like '%' || '테' || '%';


update member set memberidx = 9
where userid = 'ejss0125';
-- 1 행 이(가) 업데이트되었습니다.

commit;
-- 커밋 완료.






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




commit;
--커밋 완료.






