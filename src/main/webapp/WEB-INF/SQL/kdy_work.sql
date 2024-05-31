
-- Search창 와인 검색
select pname, pengname, ptype, phometown, pprice, pdetail, pimg, pindex
from product
where pname like '%' || '빈야드' || '%';


select pname, pengname, ptype, phometown, pprice, pdetail, pimg, pindex
from product
where pengname like '%' || UPPER('rose') || '%';


-- 페이징 처리한 상품 목록 보여주기 (정렬, 검색 포함)
SELECT RNO, PNAME, PENGNAME, PTYPE, PHOMETOWN, PPRICE,
       PPOINT, PBODY, PACID, PTANNIN, PACL, PDETAIL, PIMG, PSTOCK, PINDEX
FROM
( 
    select rownum AS RNO, pname, pengname, ptype, phometown, pprice,
           ppoint, pbody, pacid, ptannin, pacl, pdetail, pimg, pstock, pindex
    from
    (
        select pname, pengname, ptype, phometown, to_number(PPRICE) as pprice,
               ppoint, pbody, pacid, ptannin, pacl, pdetail, pimg, pstock, pindex
        from product
        where ptype in('레드', '로제') and
--              (10000 <= to_number(pprice) and to_number(pprice) < 50000) and
              phometown like '%' ||  '' || '%' and
			  pbody like '%' ||  '' || '%' and
			  pacid like '%' ||  '' || '%' and
			  ptannin like '%' ||  '' || '%'
        order by pindex desc
    ) V
) T
WHERE T.rno BETWEEN 1 AND 8;
/*
    === 페이징처리의 공식 ===
    where RNO between (조회하고자하는페이지번호 * 한페이지당보여줄행의개수) - (한페이지당보여줄행의개수 - 1) and (조회하고자하는페이지번호 * 한페이지당보여줄행의개수);
    
    where RNO between (1 * 10) - (10 - 1) and (1 * 10);
    where RNO between (10) - (9) and (10);
    where RNO between 1 and 10;
*/

update member set email = 'XMByj/WdlvOCzIA9UGrvEs7RpKFT7SJegnITc7aSoKg='
where userid = 'test002';

commit;

-- 비밀번호찾기(이메일인증)
select userid
from member
where memberidx = 1 and name = '김다영' and userid = 'test002' and email = 'XMByj/WdlvOCzIA9UGrvEs7RpKFT7SJegnITc7aSoKg=';

select to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss')
from dual;


-- 페이징 처리를 위해 검색이 있는 또는 검색이 없는 상품에 대한 총 페이지 수 알아오기
select ceil(count(*)/8)
from product
where ptype in('레드', '로제') and
--    (50000 <= to_number(pprice) and to_number(pprice) < 150000) and
      phometown in('미국') and
      pbody like '%' ||  '' || '%' and
	  pacid like '%' ||  '' || '%' and
	  ptannin like '%' ||  '' || '%';




-- 인기순 정렬 + 페이징
SELECT rno, pindex, popular, pname, pengname, ptype, phometown,
       pprice, ppoint, pbody, pacid, ptannin, pacl, pdetail, pimg, pstock
FROM
(
    select rownum AS RNO, pindex, popular, pname, pengname, ptype, phometown,
           pprice, ppoint, pbody, pacid, ptannin, pacl, pdetail, pimg, pstock
    from
    (
        SELECT P.*, popular
        FROM product P
        LEFT JOIN
        (select pindex, count(pindex) as popular 
         from LIKEIT
         group by pindex
        ) L
        ON L.pindex = P.pindex
        order by CASE 
                 WHEN popular IS NULL THEN 1 
                 ELSE 0 
                 END, 
                 popular desc
    )         
) V
WHERE V.rno BETWEEN 1 AND 8;


-- 인기순 정렬 + 페이징 + 검색
SELECT rno, pindex, popular, pname, pengname, ptype, phometown,
       pprice, ppoint, pbody, pacid, ptannin, pacl, pdetail, pimg, pstock
FROM
(
    SELECT rownum as RNO, V1.*
    FROM
    (
        WITH
        P AS (
            select *
            from product
            where ptype in('레드', '로제') and
                  (10000 <= to_number(pprice) and to_number(pprice) < 50000) and
--                phometown in('미국', '프랑스') and
                  pbody like '%' ||  '' || '%' and
                  pacid like '%' ||  '' || '%' and
                  ptannin like '%' ||  '' || '%'
        ),
        L AS (
            select pindex, count(pindex) as popular 
            from likeit
            group by pindex
        )
        SELECT P.*, popular
        FROM P LEFT JOIN L
        ON P.pindex = L.pindex
        order by CASE 
                 WHEN popular IS NULL THEN 1 
                 ELSE 0 
                 END, 
                 popular desc
    ) V1
) V2
WHERE V2.rno between 1 and 8;




select *
from log
order by logindex desc;


-- 주문번호 500 주문상태 '배송완료'로 바꾸기
update orders set ostatus = 4
where oindex = 500;
update orders set oardate = '2024-05-23 14:26:00'
where oindex = 500;
commit;


-- 주문 값 임의로 넣기
insert into orders(oindex, ototalprice, opoint, odate, ostatus, oardate, ovolume, userid, pindex)
values(501, '45000', '2250', '2024-05-22 19:15:00', 4, '2024-05-23 14:26:00', 1, 'test002', 29);
commit;
insert into orders(oindex, ototalprice, opoint, odate, ostatus, oardate, ovolume, userid, pindex)
values(502, '190000', '9500', '2024-05-23 11:02:38', 4, '2024-05-24 08:13:28', 1, 'test002', 15);
commit;
insert into orders(oindex, ototalprice, opoint, odate, ostatus, oardate, ovolume, userid, pindex)
values(503, '12900', '645', '2024-05-23 11:02:38', 4, '2024-05-24 08:13:28', 1, 'test002', 7);
commit;
insert into orders(oindex, ototalprice, opoint, odate, ostatus, oardate, ovolume, userid, pindex)
values(504, '29000', '1450', '2024-05-23 14:02:38', 4, '2024-05-24 11:13:28', 1, 'test002', 46);
commit;
insert into orders(oindex, ototalprice, opoint, odate, ostatus, oardate, ovolume, userid, pindex)
values(505, '190000', '9500', '2024-05-23 14:02:38', 4, '2024-05-24 11:13:28', 1, 'test002', 15);
commit;


-- 리뷰 관리 페이지 : 배송완료인 상품 목록 띄우기
SELECT pindex, pname, pengname, pprice, pimg, V.oindex, ototalprice,
       ostatus, odate, ovolume, NVL(rindex, 0) AS rindex, rdate
FROM
(
    select P.pindex, pname, pengname, to_number(pprice) as pprice, pimg,
           to_number(ototalprice) as ototalprice, ostatus, odate, ovolume, oindex
    from product P JOIN orders O
    ON P.pindex = O.pindex
    where O.userid = 'test002' and O.ostatus = 4
) V
LEFT JOIN REVIEW R
ON V.oindex = R.oindex
ORDER BY oindex desc;




-- 리뷰 작성 페이지 : 주문 인덱스에 대한 상품 정보 받아오기
select P.*
from product P JOIN orders O
ON P.pindex = O.pindex
where oindex = 501 and userid = 'test002';

-- 리뷰 작성 페이지 : oindex에 대한 리뷰가 존재하는지 확인하기
select *
from review
where oindex = 502;

/*
update member
set point = point + 500
where userid = 'test002';
*/

-- 리뷰 수정 페이지 : rindex에 대한 리뷰가 존재하는지 확인하기
select *
from review R JOIN orders O
on R.oindex = O.oindex
JOIN product P
ON O.pindex = P.pindex
where R.rindex = 50 and O.userid = 'test002';

/*
update review
set rstar = ?, rdetail = ?, rdate = to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss')
where rindex = ?;
*/




-- shop 페이지 리뷰 바 : pindex에 대한 리뷰 목록 불러오기
select R.*, O.userid, P.pindex
from product P JOIN orders O
ON P.pindex = O.pindex
JOIN review R
ON O.oindex = R.oindex
where P.pindex = 15
order by rindex desc;


-- 마이페이지 : 작성할 리뷰 개수 알아오기
SELECT count(*) as CNT
FROM
(
    select P.pindex, pname, pengname, to_number(pprice) as pprice, pimg,
           to_number(ototalprice) as ototalprice, ostatus, odate, ovolume, oindex
    from product P JOIN orders O
    ON P.pindex = O.pindex
    where O.userid = 'test002' and O.ostatus = 4
) V
LEFT JOIN REVIEW R
ON V.oindex = R.oindex
where rindex is null;



/*
update member set memberidx = 9
where userid = 'test002';
commit;
*/


-- 주문내역조회 페이지 : 회원이 주문한 상품 목록 받아오기
select P.pname, pimg, O.ototalprice, ovolume, odate, P.pindex, oindex
from product P JOIN orders O
ON P.pindex = O.pindex
where O.userid = 'redtree2379'
order by oindex desc;



-- 주문내역조회 페이지 : 주문 인덱스에 대한 상품, 주문, 배송 정보 받아오기
select P.pindex, pname, pengname, pimg, ptype, phometown, pprice, 
       O.oindex, ototalprice, odate, ostatus, oardate, ovolume, 
       dnumber, dname, demail, dphone, dmsg, daddress, daddressdetail
from product P JOIN orders O
ON P.pindex = O.pindex
JOIN delivery D
ON O.oindex = D.oindex
where O.oindex = 27 and userid = 'redtree2379';





update orders set ostatus = 4, oardate = '2024-05-29 14:26:00'
where oindex = 50;
commit;

update delivery set dnumber = '1515-6565-15124'
where dindex = 31;
commit;





-- 와인종류별 판매량
WITH
O AS (select pindex
from orders)
SELECT ptype, count(ptype)
FROM O JOIN product P
ON O.pindex = P.pindex
group by P.ptype;



select pindex, ovolume
from orders;






