package servlet;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GoodsDao;
import factory.DAOFactory;

import java.util.Base64.Decoder;
import java.util.Base64;


@WebServlet("/AddPhotoServlet")
public class AddPhotoServlet  extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddPhotoServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); 
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		
		String imgBase = request.getParameter("imgBase");
		String imgFormat = request.getParameter("imgFormat");
		String lookIndex = request.getParameter("lookIndex");
		String gid = request.getParameter("Gid");
		
		Decoder decoder = Base64.getDecoder();
        OutputStream out1 = null;
        OutputStream out2 = null;
        
        GoodsDao goodsDao;
        try {
        	goodsDao = DAOFactory.getGoodsServiceInstance();
        	String Path1 = "G:\\课程\\网络应用开发\\实验\\second_hand_shop\\WebRoot\\images";
        	String Path2 = request.getSession().getServletContext().getRealPath("/images");
        	
        	String imgName = gid + lookIndex + "." + imgFormat;
        	String imgFilePath1 = Path1 + "\\" + imgName;
        	String imgFilePath2 = Path2 + "\\" + imgName;
        	String partSeparator = ",";
        	String encodedImg = null;
        	if (imgBase.contains(partSeparator)){
        	  encodedImg = imgBase.split(partSeparator)[1];
        	}
            out1 = new FileOutputStream(imgFilePath1);
            out2 = new FileOutputStream(imgFilePath2);
            // Base64解码
            byte[] b = decoder.decode(encodedImg);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            out1.write(b);
            out2.write(b);
            
            String Yname = goodsDao.queryPho(Integer.parseInt(gid));
            String ImgName = "nophoto.jpg";
    		if (Yname.equals("nophoto.jpg")) {
    			ImgName = imgName;
    		} else {
    			ImgName = Yname + "&" + imgName;
    		}
            goodsDao.addPho(Integer.parseInt(gid), ImgName);
            
        } catch (FileNotFoundException e) {
          
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        } catch (Exception e) {
		
			e.printStackTrace();
		} finally {
            out1.flush();
            out1.close();
            out2.flush();
            out2.close();
        }
        
        }

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	public void init() throws ServletException {
		
	}

}
