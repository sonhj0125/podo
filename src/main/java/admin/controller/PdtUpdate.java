package admin.controller;

import java.io.File;
import java.util.Calendar;
import java.util.Collection;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.json.JSONObject;

import common.controller.AbstractController;
import member.domain.MemberDTO;
import shop.domain.ProductDTO;
import shop.model.ProductDAO;
import shop.model.ProductDAO_imple;

public class PdtUpdate extends AbstractController {

	ProductDAO pdao = null;

	public PdtUpdate() {
		pdao = new ProductDAO_imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		MemberDTO loginUser = (MemberDTO)session.getAttribute("loginUser");
		
		if(super.checkLogin(request) && "9".equals(loginUser.getMemberIdx())) {
			// 관리자로 로그인 한 경우
			
			String method = request.getMethod();
			
			if(!"POST".equalsIgnoreCase(method)) {
				// GET 방식일 경우
				
				String pindex = request.getParameter("pindex");
				
				try {
					ProductDTO pdto = pdao.getproduct(Integer.parseInt(pindex));
					String pdImgName = pdao.getProductDetailImg(Integer.parseInt(pindex));
					
					if(pdto == null) {
						super.setRedirect(true);
						super.setViewPage(request.getContextPath() + "/member/admin/adminProductUpdate.wine");
						return;
					}
					
					request.setAttribute("pdto", pdto);
					request.setAttribute("pdImgName", pdImgName);
					
					super.setRedirect(false);
					super.setViewPage("/WEB-INF/member/admin/pdtUpdate.jsp");
					
				} catch (NumberFormatException e) {
					super.setRedirect(true);
					super.setViewPage(request.getContextPath() + "/member/admin/adminProductUpdate.wine");
					return;
				}
				
			} else {
				// POST 방식일 경우
				
				int pindex = Integer.parseInt(request.getParameter("pindex")); // 제품 인덱스
				String pname = request.getParameter("pname"); // 제품한글명
				String pengname = request.getParameter("pengname"); // 제품영어명
				String ptype = request.getParameter("ptype"); // 제품타입(예: 레드, 화이트)
				String phometown = request.getParameter("phometown"); // 제품원산지(예: 미국, 칠레)
				String pprice = request.getParameter("pprice"); // 제품 정가
				String ppoint = request.getParameter("ppoint"); // 제품 적립금
				String pbody = request.getParameter("pbody"); // 제품 바디
				String pacid = request.getParameter("pacid"); // 제품 산도
				String ptannin = request.getParameter("ptannin"); // 제품 타닌
				String pacl = request.getParameter("pacl"); // 제품 도수
				String pstock = request.getParameter("pstock"); // 제품 재고량
				String pdetail = request.getParameter("pdetail"); // 제품설명
				
				// pdetail XSS 공격 방지
				pdetail = pdetail.replaceAll("<", "$lt;");
				pdetail = pdetail.replaceAll(">", "$gt;");
				pdetail = pdetail.replaceAll("\r\n", "<br>");

				ProductDTO pdto = new ProductDTO();
                pdto.setPname(pname);
                pdto.setPengname(pengname);
                pdto.setPtype(ptype);
                pdto.setPhometown(phometown);
                pdto.setPprice(pprice);
                pdto.setPpoint(ppoint);
                pdto.setPbody(pbody);
                pdto.setPacid(pacid);
                pdto.setPtannin(ptannin);
                pdto.setPacl(pacl);
                pdto.setPdetail(pdetail);
                pdto.setPstock(pstock);
                pdto.setPindex(pindex);
                
                // 제품 수정하기
				int result = pdao.updateProduct(pdto);

				JSONObject jsonObj = new JSONObject();
				jsonObj.put("result", result);

				String json = jsonObj.toString(); // 문자열로 변환
				request.setAttribute("json", json);
				
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/jsonview.jsp");

			}
						
		} else {
			// 로그인을 하지 않았거나 관리자가 아니라면
			
			String msg = "관리자만 접근 가능합니다.";
			String loc = request.getContextPath() + "/index.wine";
	
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
	
			super.setViewPage("/WEB-INF/msg.jsp");
		
		}
	}

}
