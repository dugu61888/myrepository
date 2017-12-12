package com.apcompany.content.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.apcompany.content.service.CatalogService;
import com.apcompany.content.service.KeypointService;

@Controller
@RequestMapping("/show")
public class ContentController {

	@Autowired
	private CatalogService catalogService;
	@Autowired
	private KeypointService keypointService;

	@RequestMapping(value="/content/list", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Object getContentCata() {

		List<Map<String, Object>> outcome = catalogService.selectALl();
		String jsString=JSON.toJSONString(outcome);
		return jsString;
//		return TipUtil.success("successful!!", outcome);

	}
	
	@RequestMapping(value="/keypoint/{catalogId}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Object getKeyPointCata(@PathVariable("catalogId") Integer catalogId) {
		List<Map<String, Object>> outcome = keypointService.selectALl(catalogId);
		String jsString=JSON.toJSONString(outcome);
		return jsString;
//		return TipUtil.success("successful!!", outcome);

	}

}
