package com.apcompany.learn.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apcompany.learn.pojo.Stepping;
import com.apcompany.learn.pojo.THits;
import com.apcompany.learn.pojo.TKeyWords;
import com.apcompany.learn.pojo.TTranslations;
import com.apcompany.learn.pojo.Vedio;
import com.apcompany.learn.service.HitsService;
import com.apcompany.learn.service.SteppingService;
import com.apcompany.learn.service.TransLateService;
import com.apcompany.learn.service.VedioService;
import com.apcompany.user.utils.JsonUtil;

@Controller
@RequestMapping("/learn")
public class LearnController {
	@Autowired
	private VedioService vedioService;

	@Autowired
	private TransLateService transLateService;

	@Autowired
	private SteppingService steppingService;

	@Autowired
	private HitsService hitsService;

	@RequestMapping(value = "/vedio/{keypointId}", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getVedio(@PathVariable("keypointId") Integer keypointId) {
		List<Vedio> vedios = vedioService.getAll(keypointId);
		String outcome = JsonUtil.ObjectToJson(vedios);
		return outcome;
	}

	@RequestMapping(value = "/translation/{questionId}", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getTtranslation(@PathVariable("questionId") Integer questionId) {
		TTranslations tTranslations = transLateService.getTranslationsByQuestionId(questionId);
		String outcome = JsonUtil.ObjectToJson(tTranslations);
		return outcome;
	}

	@RequestMapping(value = "/keyword/{wordId}", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getKeyWord(@PathVariable("wordId") Integer wordId) {
		TKeyWords tKeyWords = transLateService.getKeyWords(wordId);
		String outcome = JsonUtil.ObjectToJson(tKeyWords);
		return outcome;
	}

	@RequestMapping(value = "/step/{questiongId}/{stepId}", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getOneStep(@PathVariable("questiongId") Integer questiongId, @PathVariable("stepId") Integer stepId) {
		Stepping stepping = steppingService.getNextStep(questiongId, stepId);
		String outcome = JsonUtil.ObjectToJson(stepping);
		return outcome;
	}

	@RequestMapping(value = "/step/{questiongId}", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getAllStep(@PathVariable("questiongId") Integer questiongId) {
		List<Stepping> steppinglist = steppingService.getAllStep(questiongId);
		String outcome = JsonUtil.ObjectToJson(steppinglist);
		return outcome;
	}

	@RequestMapping(value = "/step/valid", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getValid(HttpServletRequest request) {
		String input = (String) request.getParameter("input");
		String questiongId = (String) request.getParameter("questiongId");
		String step = (String) request.getParameter("step");
		boolean outcome = steppingService.valid(input, Integer.valueOf(questiongId), Integer.valueOf(step));
		return outcome ? "true" : "false";
	}

	@RequestMapping(value = "/hit/{questiongId}/{stepId}", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getHits(@PathVariable("questiongId") Integer questiongId, @PathVariable("stepId") Integer stepId) {
		THits tHits = hitsService.getTHits(questiongId, stepId);
		String outcome = JsonUtil.ObjectToJson(tHits);
		return outcome;
	}

	@RequestMapping(value = "/hit/{questiongId}", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getOneHits(@PathVariable("questiongId") Integer questiongId) {
		List<THits> hitslist = hitsService.getTHitsByQuestionId(questiongId);
		String outcome = JsonUtil.ObjectToJson(hitslist);
		return outcome;
	}

}
