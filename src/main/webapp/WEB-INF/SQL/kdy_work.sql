
-- Search창 와인 검색
select *
from product
where pname like '%' || '빈야드' || '%';


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
      phometown like '%' ||  '' || '%' and
      pbody like '%' ||  '' || '%' and
	  pacid like '%' ||  '' || '%' and
	  ptannin like '%' ||  '' || '%';






