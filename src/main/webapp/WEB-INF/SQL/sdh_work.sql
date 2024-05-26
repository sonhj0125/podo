select userid, name, email, phone, address, addressdetail, gender, birthday, point, registerday, pwdupdateday, memberidx
from MEMBER where userid = 'test001';

SELECT *
FROM SEMI_ORAUSER4.PRODUCT t
ORDER BY PINDEX DESC;

select *
from PRODUCT
where PINDEX=50;

select pname, pengname, ptype, phometown, pprice, pdetail, pimg
from product
where pname like '%'|| '엠' || '%';

select USERID,CVOLUME,PNAME,PTYPE,PHOMETOWN,PPRICE from CART join PRODUCT on CART.PINDEX = PRODUCT.PINDEX where USERID='redtree2379';

select CVOLUME,PNAME,PTYPE,PHOMETOWN,PPRICE,PIMG,CART.PINDEX as pindex, CINDEX
from CART join PRODUCT on CART.PINDEX = PRODUCT.PINDEX
where USERID = 'redtree2379';

select *
from
(select pindex,count(pindex)as coun from LIKEIT group by pindex order by coun desc) l join PRODUCT on l.PINDEX=PRODUCT.PINDEX
order by  coun desc;

select USERID,name,EMAIL,PHONE,GENDER,STATUS
from MEMBER join MEMBERIDX on MEMBER.MEMBERIDX = MEMBERIDX.MEMBERIDX
where USERID='redtree2379'
order by  REGISTERDAY desc;

select *
from CART where CINDEX IN (26, 27, 28);

select MEMBER.USERID, COUPON.CONAME, COTYPE, CODISCOUNT, CODATE, COREGISTERDAY,COINDEX
from COUPON join MYCOUPON on COUPON.CONAME = MYCOUPON.CONAME join MEMBER on MYCOUPON.USERID = MEMBER.USERID
where MEMBER.USERID = 'redtree2379';

select COTYPE,CODISCOUNT
from COUPON where coname = ?;

select CVOLUME, PPOINT, PPRICE, USERID
from cart join PRODUCT on cart.PINDEX = PRODUCT.PINDEX
where CINDEX = 61;

UPDATE MEMBER
SET POINT = point + 100
WHERE USERID = 'redtree2379';



UPDATE MEMBER SET POINT = POINT - ? WHERE USERID = 'redtree2379';