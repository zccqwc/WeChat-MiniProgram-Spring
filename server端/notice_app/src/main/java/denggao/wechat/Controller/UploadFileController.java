package denggao.wechat.Controller;

import denggao.wechat.Service.UploadFileService;
import denggao.wechat.util.Config;
import denggao.wechat.util.toResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class UploadFileController {
    private denggao.wechat.util.toResponse toResponse = new toResponse();
    @Autowired
    private UploadFileService uploadFileService;

//    @ApiOperation(value = "上传文件")
//    @IgnoreAuth
    @PostMapping("/PostUploadFile")
    public Object upload(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        System.out.println("执行upload");
        request.setCharacterEncoding("UTF-8");
//        logger.info("执行图片上传");
//        String user = request.getParameter("user");
//        String groupId = request.getParameter("groupId");
//        String isCoverImg = request.getParameter("isCoverImg");
//        System.out.println("isCoverImg: "+isCoverImg);
//        logger.info("user:"+user);
//        logger.info("groupId:"+groupId);
//        logger.info("isMain:"+isMain+"isMain.equals:"+isMain.equals("t"));
        if(!file.isEmpty()) {
//            logger.info("成功获取照片");
            String fileName = file.getOriginalFilename();
            System.out.println("fileName: "+fileName);
            String path = null;
            String type = null;
            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
//            logger.info("图片初始名称为：" + fileName + " 类型为：" + type);
            if (type != null) {
                if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())||"JPEG".equals(type.toUpperCase())) {
                    // 项目在容器中实际发布运行的根路径
//                    String realPath = request.getSession().getServletContext().getRealPath("/");
                    String realPath = "C:\\Users\\22844\\Desktop\\wxshop\\platform_static\\platform_static\\static\\images\\test\\uploads\\";
//                    String realPath = Config.UploadsPath;
                    // 自定义的文件名称
//                    String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
                    String trueFileName = fileName;
                    // 设置存放图片文件的路径
                    path = realPath + "" + trueFileName;
//                    logger.info("存放图片文件的路径:" + path);
                    file.transferTo(new File(path));
                    // 将文件路径写入数据库
//                    tmsGroupBuyService.uploadImg(groupId, trueFileName);
//                    logger.info("isMain:"+isMain.equals("t"));
//                    logger.info("文件路径写入数据库成功");
//                    logger.info("文件成功上传到指定目录下");
                    System.out.println("上传成功");
                    return trueFileName;
                }else {
//                    logger.info("不是我们想要的文件类型,请按要求重新上传");
                    return toResponse.toResponsFail("不是我们想要的文件类型,请按要求重新上传");
                }
            }else {
//                logger.info("文件类型为空");
                return toResponse.toResponsFail("文件类型为空");
            }
        }else {
//            logger.info("没有找到相对应的文件");
            return toResponse.toResponsFail("没有找到相对应的文件");
        }
    }
}
