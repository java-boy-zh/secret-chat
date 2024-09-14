package com.itchat.controller;

import com.baidu.aip.speech.AipSpeech;
import com.itchat.result.GraceJSONResult;
import com.itchat.utils.BaiduAISDK;
import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.InputStream;
import java.util.HashMap;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @ClassName BaiduSpeechController.java
 * @create 2024年09月14日 上午10:37
 * @Description 语音处理器
 * @Version V1.0
 */
@RestController
@RequestMapping("/speech")
public class BaiduSpeechController {

    @PostMapping(value = "/uploadVoice")
    public GraceJSONResult uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        byte[] pcmbytedata = mp3ConvertToPcm(file.getInputStream());
        HashMap<String, Object> options = new HashMap<String, Object>();
        int dev_id = 1537;
        options.put("dev_pid", dev_id);
        JSONObject jsonfrombaidu = basicBydata(pcmbytedata, "pcm", options);
        System.out.println(jsonfrombaidu);
        JSONArray jsonArray = jsonfrombaidu.getJSONArray("result");
        String result = jsonArray.getString(0);
        System.out.println(result); //解析完的结果

        return GraceJSONResult.ok(result);
    }

    /**
     * MP3转换PCM
     *
     * @param inputStream MP3输入流
     * @throws Exception
     */
    public static byte[] mp3ConvertToPcm(InputStream inputStream) throws Exception {
        //转换PCM audioInputStream 数据
        AudioInputStream audioInputStream = getPcmAudioInputStream(inputStream);
        byte[] pcmBytes = IOUtils.toByteArray(audioInputStream);
        return pcmBytes;
    }

    /**
     * 获取PCM AudioInputStream 数据
     *
     * @param inputStream MP3输入流
     * @return AudioInputStream PCM输入流
     */
    private static AudioInputStream getPcmAudioInputStream(InputStream inputStream) {
        AudioInputStream audioInputStream = null;
        AudioFormat targetFormat = null;
        try {
            AudioInputStream in = null;
            MpegAudioFileReader mp = new MpegAudioFileReader();
            in = mp.getAudioInputStream(inputStream);
            AudioFormat baseFormat = in.getFormat();
            targetFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16,
                    baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
            audioInputStream = AudioSystem.getAudioInputStream(targetFormat, in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return audioInputStream;
    }

    // 语音识别（来自文件）
    public static JSONObject basicBydata(byte[] voicedata, String fileType, HashMap<String, Object> options) {
        AipSpeech client = getClient();
        return client.asr(voicedata, fileType, 16000, options);
    }

    // 获取AipSpeech对象
    public static AipSpeech getClient() {
        // TODO 请务必替换为自己的appid和秘钥
        AipSpeech client = new AipSpeech(BaiduAISDK.APP_ID,
                BaiduAISDK.API_KEY,
                BaiduAISDK.SECRET_KEY);
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        return client;
    }

}

