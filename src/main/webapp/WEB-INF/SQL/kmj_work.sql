
-- Search창 와인 검색
select *
from product
where pname like '%' || '빈야드' || '%';


-- 페이징 처리한 상품 목록 보여주기 (정렬 포함)
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
        order by pprice desc
    ) V
) T
WHERE T.rno BETWEEN 9 AND 16;
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


select *
from member
where userid ='kmj0228';


select email
from member
where userid != kmj0228 and email = ?;


SELECT*
FROM tab;

select *
from PRODUCT
where pname = 'qwerqwer'

SELECT *
FROM all_sequences;

select SEQ_PINDEX.nextval AS pindex
from dual;

select *
from PRODUCTDETAILIMG


select userid, memberIdx
from member;

select *
from PRODUCT
where pname = '어린이가 좋아하는';

select *
from PRODUCTDETAILIMG
where pname

select *
from PRODUCTDETAILIMG

DELETE from PRODUCTDETAILIMG
where pindex = '134'

DELETE from product
where pindex = '134'

commit;

select pwd
from member
where userid = 'kmj0228' and pwd = '1fb5a7508bdf2a76e5b7f0ec8be6dda74816f32bbf5542721f4802f3859a414d';

9695b88a59a1610320897fa84cb7e144cc51f2984520efb77111d94b402a8382


WITH
O AS
(SELECT pIndex
FROM order 
WHERE fk_userid = 'kmj0228'
)
,
OD AS
(SELECT fk_odrcode, fk_pnum, oqty, odrprice
FROM tbl_orderdetail
)



---- 와인별 총 판매개수 ----

WITH
O AS
(SELECT pIndex
FROM order)
select ptype, count(*)
from O join product P
ON O.pindex = P.pindex
group by P.ptype;



