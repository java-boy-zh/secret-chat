package com.itchat.wx.utils.msg;


import com.freewayso.image.combiner.ImageCombiner;
import com.freewayso.image.combiner.enums.OutputFormat;
import com.freewayso.image.combiner.enums.ZoomMode;
import com.itchat.wx.constant.QRCodeConstant;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.net.URL;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @create 2024年12月29日 12:15
 * @Description 图片合成工具
 * @Version V1.0
 */
public class ImageUtil {


    /**
     * 完整功能
     *
     * @param qrCodeUrl  二维码路径
     * @param headImgUrl 头像路径
     * @param wxId       微信号
     * @return 图片保存路径
     * @throws Exception
     */
    public static String MergeImage(String qrCodeUrl, String headImgUrl, String wxId) throws Exception {
        String bgImagePath = QRCodeConstant.DOWNLOAD_PATH_PREFIX + "bgimage.jpeg";                       //背景图（测试url形式）
        BufferedImage avatar = ImageIO.read(new URL(headImgUrl));           //头像
        BufferedImage bgImage = ImageIO.read(new FileInputStream(bgImagePath));

        //合成器和背景图（整个图片的宽高和相关计算依赖于背景图，所以背景图的大小是个基准）
        ImageCombiner combiner = new ImageCombiner(bgImage, OutputFormat.PNG);
        combiner.setBackgroundBlur(0);     //设置背景高斯模糊（毛玻璃效果）
        combiner.setCanvasRoundCorner(0); //设置整图圆角（输出格式必须为PNG）

        //头像（圆角设置一定的大小，可以把头像变成圆的）
        combiner.addImageElement(avatar, 500, 1200, 186, 186, ZoomMode.WidthHeight)
                .setRoundCorner(10)
                .setBlur(0);       //高斯模糊，毛玻璃效果

        //二维码（强制按指定宽度、高度缩放）
        combiner.addImageElement(ImageIO.read(new FileInputStream(qrCodeUrl)), 138, 1200, 186, 186, ZoomMode.WidthHeight).setRoundCorner(10);

        //执行图片合并
        combiner.combine();

        //保存文件
        String path = QRCodeConstant.DOWNLOAD_PATH_PREFIX + wxId + "_full.png";
        combiner.save(path);

        return path;
    }

}
