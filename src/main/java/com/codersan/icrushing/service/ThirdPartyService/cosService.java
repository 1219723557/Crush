package com.codersan.icrushing.service.ThirdPartyService;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.UploadResult;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.Upload;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * 本Service用于对腾讯云对象存储进行操作
 * 包括 存储，删除
 */
@Service
public class cosService {
    //初始化密钥信息
    private String SecretKey ="41ufQ7teTF3lMtQyUtJcnw4AawBLSWfj";
    private String SecretId = "AKIDFapuBa8hzosmYFu1HNJSQH1n6Vq36pK9";
    //初始化Bucket信息
    private String bucketName = "icrushing-1253873931";
    COSCredentials cosCredentials = new BasicCOSCredentials(SecretId,SecretKey);
    //设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
    ClientConfig clientConfig = new ClientConfig(new Region("ap-beijing"));
    //生成 cos 客户端
    COSClient cosclient = new COSClient(cosCredentials, clientConfig);

    ExecutorService threadPool = Executors.newFixedThreadPool(32);
    // 传入一个 threadpool, 若不传入线程池, 默认 TransferManager 中会生成一个单线程的线程池。
    TransferManager transferManager = new TransferManager(cosclient, threadPool);
    //初始化密钥信息

    //上传用户头像
    public String upload(MultipartFile file, String key) throws InterruptedException, IOException {
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,key,new ByteArrayInputStream(file.getBytes()),new ObjectMetadata());
        //上传文件
        Upload upload = transferManager.upload(putObjectRequest);
        // 等待传输结束（如果想同步的等待上传结束，则调用 waitForCompletion）
        UploadResult uploadResult = upload.waitForUploadResult();
        return uploadResult.getKey();
    }
}
