package servlet;

import dao.GoodsDao;
import factory.DAOFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Base64.Decoder;

@WebServlet("/AddPhotoServlet")
public class AddPhotoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddPhotoServlet() {
        super();
    }

    public void destroy() {
        super.destroy();

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");

        String imgBase = request.getParameter("imgBase");
        String imgFormat = request.getParameter("imgFormat");
        String lookIndex = request.getParameter("lookIndex");
        String gid = request.getParameter("Gid");

        Decoder decoder = Base64.getMimeDecoder();

        OutputStream out = null;

        try {
            GoodsDao goodsDao = DAOFactory.getGoodsServiceInstance();
            if(lookIndex.equals("0")) goodsDao.addPho(Integer.parseInt(gid), "nophoto.jpg");

            String Path = request.getSession().getServletContext().getRealPath("/images");

            if(imgFormat.equals("jpeg"))imgFormat="jpg";
            String imgName = gid + lookIndex + "." + imgFormat;
            String imgFilePath = Path + "\\" + imgName;
            String partSeparator = ",";

            if (imgBase.contains(partSeparator)) {
                String encodedImg = imgBase.split(partSeparator)[1];

                out = new FileOutputStream(imgFilePath);
                // Base64解码
                byte[] b = decoder.decode(encodedImg);
                for (int i = 0; i < b.length; ++i) {
                    if (b[i] < 0) {// 调整异常数据
                        b[i] += 256;
                    }
                }
                out.write(b);
            }

            String Yname = goodsDao.queryPho(Integer.parseInt(gid));
            String ImgName;
            if (Yname.equals("nophoto.jpg")) {
                ImgName = imgName;
            } else {
                ImgName = Yname + "&" + imgName;
            }
            goodsDao.addPho(Integer.parseInt(gid), ImgName);

            String jsonStr = "{\"isok\":\"1\", \"ind\": \"" + lookIndex + "\"}";
            response.getWriter().print(jsonStr);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(out != null) {
                out.flush();
                out.close();
            }
        }

    }


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        this.doPost(request, response);
    }

    public void init() {

    }

}
