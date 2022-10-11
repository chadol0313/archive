CREATE OR REPLACE PROCEDURE getBestNewProduct(
    p_cur1 OUT SYS_REFCURSOR, 
    p_cur2 OUT SYS_REFCURSOR   )
IS
BEGIN
    OPEN p_cur1 FOR  
        SELECT * FROM new_pro_view;
    OPEN p_cur2 FOR  
        SELECT * FROM best_pro_view;
END;


create or replace procedure getMember(
    p_id IN member.id%type,
    p_curvar OUT SYS_REFCURSOR
)
IS
    result_cur SYS_REFCURSOR;
BEGIN
    OPEN result_cur For select * from member where id=p_id;
    p_curvar := result_cur;
END;




create or replace PROCEDURE insertMember(
    p_id IN member.id%TYPE ,
    p_pwd IN member.pwd%TYPE ,
    p_name IN member.name%TYPE ,
    p_email IN member.email%TYPE ,
    p_phone IN member.phone%TYPE,
    p_zip_num IN member.zip_num%TYPE,
    p_address1 IN member.address1%TYPE,
    p_address2 IN member.address2%TYPE,
    p_address3 IN member.address3%TYPE
)
IS
BEGIN
    insert into member( id, pwd, name, email, phone , zip_num, address1, address2, address3 )
    values( p_id, p_pwd, p_name, p_email, p_phone, p_zip_num, p_address1, p_address2, p_address3 );
    commit;
END;




create or replace PROCEDURE updateMember(
    p_id IN member.id%TYPE ,
    p_pwd IN member.pwd%TYPE ,
    p_name IN member.name%TYPE ,
    p_email IN member.email%TYPE ,
    p_phone IN member.phone%TYPE,
    p_zip_num IN member.zip_num%TYPE,
    p_address1 IN member.address1%TYPE,
    p_address2 IN member.address2%TYPE,
    p_address3 IN member.address3%TYPE
)
IS
BEGIN
    update member set pwd=p_pwd, name=p_name, email=p_email, phone=p_phone,
    zip_num = p_zip_num, address1=p_address1, address2=p_address2, address3=p_address3
    where id=p_id;
    commit;
END;



CREATE OR REPLACE PROCEDURE getKindList(
    p_kind IN product.kind%TYPE, 
    p_cur OUT SYS_REFCURSOR   )
IS
BEGIN
    OPEN p_cur FOR SELECT * FROM product where kind=p_kind;
END;





CREATE OR REPLACE PROCEDURE getProduct(
    p_pseq IN product.pseq%TYPE, 
    p_cur OUT SYS_REFCURSOR   )
IS
BEGIN
    OPEN p_cur FOR SELECT * FROM product where pseq=p_pseq;
END;





CREATE OR REPLACE PROCEDURE insertCart(
    p_id IN cart.id%TYPE,
    p_pseq  IN cart.pseq%TYPE,
    p_quantity  IN cart.quantity%TYPE )
IS
BEGIN
    insert into cart( cseq, id, pseq, quantity ) 
    values( cart_seq.nextVal, p_id, p_pseq, p_quantity );
    commit;    
END;



CREATE OR REPLACE PROCEDURE listCart(
    p_id IN cart.id%TYPE, 
    p_cur OUT SYS_REFCURSOR   )
IS
BEGIN
    OPEN p_cur FOR SELECT * FROM cart_view where id=p_id;
END;




CREATE OR REPLACE PROCEDURE deleteCart(
    p_cseq  IN cart.cseq%TYPE   )
IS
BEGIN
    delete from cart where cseq = p_cseq;
    commit;    
END;


CREATE OR REPLACE PROCEDURE insertOrder(
    p_id IN ORDERS.ID%TYPE,
    p_oseq OUT ORDERS.OSEQ%TYPE
)
IS
    temp_cur SYS_REFCURSOR;
    v_oseq ORDERS.OSEQ%TYPE;
    v_cseq CART.CSEQ%TYPE;
    v_pseq CART.PSEQ%TYPE;
    v_quantity CART.QUANTITY%TYPE;
BEGIN
   -- orders 테이블에 레코드 추가
   INSERT INTO ORDERS(oseq, id) VALUES( orders_seq.nextVal, p_id);
   -- orders 테이블에서 가장 큰 oseq 조회
   SELECT MAX(oseq) INTO v_oseq FROM ORDERS;
   -- cart  테이블에서  id  로 목록 조회
   OPEN temp_cur FOR 
   SELECT cseq, pseq, quantity FROM CART WHERE id=p_id AND result='1';
   -- 목록과  oseq  로 order_detail 테이블에 레코드 추가
   LOOP
       FETCH temp_cur INTO v_cseq, v_pseq, v_quantity;
       EXIT WHEN temp_cur%NOTFOUND;
       INSERT INTO ORDER_DETAIL( odseq, oseq, pseq, quantity )
       VALUES( order_detail_seq.nextVal, v_oseq, v_pseq, v_quantity );
       DELETE FROM CART WHERE cseq=v_cseq;
   END LOOP;
   COMMIT; 
   -- oseq 값을 OUT 변수에 저장
   p_oseq := v_oseq;
   
EXCEPTION WHEN OTHERS THEN
    ROLLBACK;
END;

select * from cart;




CREATE OR REPLACE PROCEDURE listOrderByOseq(
    p_oseq IN orders.oseq%TYPE, 
    p_cur OUT SYS_REFCURSOR   )
IS
BEGIN
    OPEN p_cur FOR SELECT * FROM order_view where oseq=p_oseq;
END;

CREATE OR REPLACE PROCEDURE insertOrderOne(
    p_id IN ORDERS.ID%TYPE ,
    p_pseq IN ORDER_DETAIL.PSEQ%TYPE ,
    p_quantity IN ORDER_DETAIL.QUANTITY%TYPE ,
    p_oseq OUT  ORDERS.OSEQ%TYPE 
)
IS
    v_oseq ORDERS.OSEQ%TYPE;
BEGIN
    INSERT INTO ORDERS(oseq, id) VALUES( orders_seq.nextVal, p_id);
    SELECT MAX(oseq) INTO v_oseq FROM ORDERS;
    INSERT INTO ORDER_DETAIL( odseq, oseq, pseq, quantity)
    VALUES( order_detail_seq.nextVal, v_oseq, p_pseq, p_quantity);
    p_oseq := v_oseq;
    COMMIT;
EXCEPTION WHEN OTHERS THEN
    ROLLBACK;
END;



CREATE OR REPLACE PROCEDURE listOrderByIdIng(
    p_id IN ORDERS.ID%TYPE,
    p_rc OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN p_rc FOR
    SELECT DISTINCT oseq FROM ORDER_VIEW WHERE id=p_id AND result='1' ORDER BY OSEQ DESC;
END;




CREATE OR REPLACE PROCEDURE listOrderByIdAll(
    p_id IN ORDERS.ID%TYPE,
    p_rc OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN p_rc FOR
    -- SELECT DISTINCT oseq FROM ORDER_VIEW WHERE id=p_id ORDER BY OSEQ DESC;
    SELECT DISTINCT oseq FROM 
    (SELECT oseq, id FROM ORDER_VIEW ORDER BY result, oseq desc)
    WHERE id=p_id;
END;

select * from order_view


CREATE OR REPLACE PROCEDURE getAllCount(
    p_id IN QNA.ID%TYPE,
    p_cnt OUT NUMBER
)
IS
    v_cnt NUMBER;
BEGIN
    SELECT COUNT(*) INTO v_cnt FROM QNA WHERE id=p_id;
    p_cnt := v_cnt;
END;

select * from qna;


CREATE OR REPLACE PROCEDURE listQna (
    p_id IN   QNA.ID%TYPE,
    p_startNum IN NUMBER,
    p_endNum IN NUMBER,
    p_rc   OUT     SYS_REFCURSOR )
IS
BEGIN
    OPEN p_rc FOR
    SELECT * FROM (
    SELECT * FROM (
    SELECT ROWNUM AS rn, q.* FROM ((SELECT * FROM QNA WHERE id=p_id ORDER BY qseq desc)q) 
    ) WHERE rn>= p_startNum
    ) WHERE rn<= p_endNum;
END;




CREATE OR REPLACE PROCEDURE insertQna(
    p_id IN qna.id%TYPE,
    p_subject  IN qna.subject%TYPE,
    p_content  IN qna.content%TYPE )
IS
BEGIN
    insert into qna(qseq, id, subject, content) 
    values( qna_seq.nextVal, p_id, p_subject, p_content );
    commit;    
END;



CREATE OR REPLACE PROCEDURE getQna (
    p_qseq IN   Qna.qseq%TYPE,
    p_rc   OUT     SYS_REFCURSOR )
IS
BEGIN
    OPEN p_rc FOR
        SELECT * FROM qna WHERE qseq=p_qseq;
END;


CREATE OR REPLACE PROCEDURE getAdmin(
    p_id IN   worker.id%TYPE,
    p_rc   OUT     SYS_REFCURSOR )
IS
BEGIN
    OPEN p_rc FOR
        select * from worker where id=p_id;
END;



CREATE OR REPLACE PROCEDURE getProductList(
     p_rc   OUT     SYS_REFCURSOR
)
IS
BEGIN
    OPEN p_rc FOR
    SELECT * FROM PRODUCT ORDER BY PSEQ DESC;
END;














