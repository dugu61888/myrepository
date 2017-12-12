package com.apcompany.api.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.apcompany.user.utils.TipUtil;
import com.hazelcast.com.eclipsesource.json.JsonObject;

@Controller
public class UploadController {
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "text/html;charset=UTF-8") 
	@ResponseBody
    public Object upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {  
  
        System.out.println("开始");  
        String path = request.getSession().getServletContext().getRealPath("/picture");   
        String fileName = file.getOriginalFilename();  
//        String fileName = new Date().getTime()+".jpg";  
        System.out.println(path);  
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
  
        //保存  
        try {  
            file.transferTo(targetFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        JsonObject jsonObject=new JsonObject();
        jsonObject.add("fileUrl", request.getContextPath()+"/picture/"+fileName);
        
        return TipUtil.success(request.getContextPath()+"/picture/"+fileName);  
    } 

}
