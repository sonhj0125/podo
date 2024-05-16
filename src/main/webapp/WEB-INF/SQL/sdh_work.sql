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