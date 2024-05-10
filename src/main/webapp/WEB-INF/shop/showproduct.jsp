<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>

<jsp:include page="../header.jsp" />

<div class="container">

<!-- Product detail-->
<section class="py-5">
  <div class="container px-4 px-lg-5 my-5">
      <div class="row gx-4 gx-lg-5 align-items-center" >
          <div class="col-md-5"><img class="card-img-top mb-5 mb-md-0" src="../images/product/디아블로 데블스 카나발 카베르네.png" /></div>
          <div class="col-md-6">
              <h1 style="font-weight: bold;">디아블로데블 카나발카베르네</h1>
            <p class="card-text" style="font-size: 12pt; font-weight: bold; color:rgba(59, 59, 59, 0.877);" >Diablo Devil Canaval Cabernet</p>
            <div class="fs-5 mb-5">
                <input type="hidden" id="price" name="price">
                <span class="text-decoration-none">14,900원</span>
            </div>
            <hr class="my-4">

            <div class="mb-3">
              <span class="badge rounded-pill p-2" style="background-color: #ff8080;">레드</span>
              <span class="badge rounded-pill p-2" style="background-color: #ff8080;">칠레산</span>
              <span class="badge rounded-pill p-2" style="background-color: #ff8080;">카베르네 소비뇽</span>
            </div>

            <form>
            <div class="input-group fs-5 mb-5">
                <div class="input-group-prepend">
                    <input type="hidden" id="stockquantity" name="stockquantity">
                    <span class="input-group-text">주문 수량</span>
                </div>
                <input class="form-control text-center me-3" id="count" name="count" type="number" value="1" start="1" min="0" max="100" style="max-width: 5rem" />
            </div>
            </form>

            <br>

            <div class="d-flex">
                <form class="d-flex" method="post">
                    <!--
                    <button class="btn btn-outline-dark flex-shrink-0 fw-semibold pt-3 me-3" type="submit">
                      <img src="../images/heart.png" style="width: 3rem;"/>
                    </button>
                     -->
                    <button class="btn btn-outline-dark flex-shrink-0 fw-semibold pt-3 px-4 py-3" type="submit">
                        BUY IT NOW
                    </button>
                </form>
                &nbsp;&nbsp; 
                <button class="btn btn-outline-light ms-5 flex-shrink-0 fw-semibold btn-lg me-5" style="background-color: #9999ff;" type="button">
                    CART
                 </button>
             </div>
         </div>
     </div>

	</div>
</section>

<div class="accordion" id="accordionExample">
      <div class="accordion-item">
        <h2 class="accordion-header">
          <button style="font-weight: bold; font-size: 14pt;" class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
            PRODUCT
          </button>
        </h2>
        <div id="collapseOne" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">
          <div class="accordion-body" style="font-size: 9pt;">
            <strong><구매예약 안내></strong><br>
            · <span style="color:red;">상품 이미지에 표시된 빈티지는 상품 이해를 돕기 위한 샘플 이미지입니다. 빈티지가 상품명에 별도 표기되지 않은 상품은 특정 빈티지로 입고되지 않습니다.</span><br>
            · <span style="color:red;">주류통신 판매에 대한 명령위임 고시에 따라 주류상품은 온라인 결제는 가능하나 배송은 불가합니다.</span><br>
            · <span style="font-weight: bold;">일부 제품의 경우 수입사의 사정에 따라 취소 및 환불처리가 될 수 있습니다.</span><br>
            · 인터넷PC와 모바일앱(App)을 통해 신용카드 결제 또는 간편 결제중 하나를 선택하고<br> 
            &nbsp;<span style="color:red;">원하시는 수령 매장과 일자를 지정한 후 해당일에 방문 수령 가능합니다.</span><br> 
            · 주류가 아닌 상품(악세사리 등)은 온라인 구매와 배송이 가능합니다. (주류가 아닌 상품의 배송 별도 안내)<br><br>
            <strong>* 주류상품의 매장 방문수령</strong><br>
            · 수령은 예약 당사자에 한하여 가능하며, 제3자 위임은 불가합니다.<br> 
            · 온라인 구매 예약을 통한 방문 수령시 각 매장의 샵마스터는 상품 수령을 돕고자 아래의 항목을 확인합니다.<br>  
            &nbsp; ① 수령자 본인 확인 (회원가입시 등록된 이름, 연락처 뒷번호 4자리, 본인확인 및 성인인증을 위한 신분증)<br>  
            &nbsp; ② 결제 완료시 발송되는 주문번호와 주문내용 SMS 확인<br><br>
            <strong>* 구매예약시 유의사항</strong><br>
            · 구매예약 상품의 매장별 재고, 상세 빈티지 확인은 해당 매장에 문의주시기 바랍니다.<br>
            · 구매예약한 상품은 수령 지정일로부터 5일 이내에만 해당 매장에서만 수령이 가능하며,<br> 
            &nbsp;지정일로부터 5일 이후 해당 매장에서 수령이 필요하신 경우 해당 매장에 문의하여 수령 일정을 조정 하여 주시기 바랍니다.<br><hr>
            <strong><주류상품></strong><br>
            · 교환 및 반품은 상품 수령 후 7일 이내 가능합니다.<br>  
            · 해당 예약 상품은 타상품으로 교환이 불가능 합니다.<br> 
            · 증정품이 있을 경우 증정품을 사용한 경우 반품(환불)이 불가합니다.<br>
            · 주류상품의 교환 및 반품은 와인나라 직영 매장에서만 가능합니다.<br> 
            · 상품(또는 케이스) 택(tag)제거,전면부 라벨 손상(오염),개봉으로 상품 가치 훼손 시에는 상품수령후 7일 이내라도 교환 및 반품이 불가능합니다.<br>
            · 일부 상품은 신규 빈티지 출시, 수입 가격 변동 등 제조사 사정으로 가격이 변동될 수 있습니다.
          </div>
        </div>
      </div>
      <div class="accordion-item">
        <h2 class="accordion-header">
          <button style="font-weight: bold; font-size: 14pt;" class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
            RELATED PRODUCT
          </button>
        </h2>
        <div id="collapseTwo" class="accordion-collapse collapse" data-bs-parent="#accordionExample">
          <div class="accordion-body">
            <strong>등록된 정보가 없습니다.</strong>
          </div>
        </div>
      </div>
      <div class="accordion-item">
        <h2 class="accordion-header">
          <button style="font-weight: bold; font-size: 14pt;" class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
            REVIEW
          </button>
        </h2>
        <div id="collapseThree" class="accordion-collapse collapse" data-bs-parent="#accordionExample">
          <div class="accordion-body">
            <strong>등록된 정보가 없습니다.</strong>
          </div>
        </div>
      </div>
    </div>


</div>

<jsp:include page="../footer.jsp" />