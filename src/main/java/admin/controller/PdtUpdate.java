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

    private String extractFileName(String partHeader) {
        for(String cd : partHeader.split("\\;")) {
           if(cd.trim().startsWith("filename")) {
              String fileName = cd.substring(cd.indexOf("=") + 1).trim().replace("\"", ""); 
              int index = fileName.lastIndexOf(File.separator); // File.separator 란? OS가 Windows 이라면 \ 이고, OS가 Mac, Linux, Unix 이라면 / 를 말하는 것이다.
              return fileName.substring(index + 1);
           }
        }
        
        return null;
        
     }// end of private String extractFileName(String partHeader)-------------------

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

				// 1. 첨부된 파일을 업로드 할 디스크 경로 설정
				ServletContext svlCtx = session.getServletContext();
				String uploadFileDir = "C:/NCS/podo/src/main/webapp/images/product";

				// ==== >>> 파일 업로드하기 <<< ==== //
				String pimg = null;
				String pdimg = null;

				Collection<Part> parts = request.getParts();
				//  getParts()를 사용하여 form 태그로부터 넘어온 데이터들을 각각의 Part로 하나하나씩 받는다.

				for (Part part : parts) {

					if (part.getHeader("Content-Disposition").contains("filename=")) {
						// form 태그에서 전송되어 온 것이 파일일 경우

						String fileName = extractFileName(part.getHeader("Content-Disposition"));

						if (part.getSize() > 0) {

							// 서버에 저장할 새로운 파일명을 만든다.
							// 서버에 저장할 새로운 파일명이 동일한 파일명이 되지 않고 고유한 파일명이 되도록 하기 위해
							// 현재의 년월일시분초에다가 현재 나노세컨즈nanoseconds 값을 결합하여 확장자를 붙여서 만든다.
							String newFilename = fileName.substring(0, fileName.lastIndexOf(".")); // 확장자를 뺀 파일명 알아오기

							newFilename += "_"
									+ String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS", Calendar.getInstance());
							newFilename += System.nanoTime();
							newFilename += fileName.substring(fileName.lastIndexOf(".")); // 확장자 붙이기

							// >>> 파일을 지정된 디스크 경로에 저장하기 <<<
							part.write(uploadFileDir + File.separator + newFilename);

							// >>> 임시저장된 파일 데이터를 제거해준다. <<<
							part.delete();

							if ("pimg".equals(part.getName())) {
								pimg = newFilename;
							}

							else if ("pdimg".equals(part.getName())) {
								pdimg = newFilename;
							}

						}

					} // end of if(part.getHeader("Content-Disposition").contains("filename=")) ------------------------------
					
					else { // form 태그에서 전송되어 온 것이 파일이 아닐 경우
						String formValue = request.getParameter(part.getName());
						pimg = request.getParameter("origin_pimg");
						pdimg = request.getParameter("origin_pdimg");
					}

				} // end of for(Part part : parts)------------------------

				// === 이미지 파일을 제외한 제품 정보 ===
				int pindex = Integer.parseInt(request.getParameter("pname")); // 제품 인덱스
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
                
                pdto.setPimg(pimg);   
                pdto.setPdimg(pdimg); 

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
