package com.bitc.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitc.vo.SampleVO;

@Controller
public class SampleController {

	@GetMapping("sampleTest")
	@ResponseBody
	public SampleVO sampleTest(SampleVO vo) {
		System.out.println("sampleTest vo : " + vo);
		return vo;
	}
	
	@GetMapping("getSampleList")
	@ResponseBody
	public List<SampleVO> getSampleList(SampleVO vo) {
		System.out.println("Get SampleList ");
		List<SampleVO> list = new ArrayList<>();
		for (int i = 1; i <= 10; i++ ) {
			SampleVO sample = new SampleVO();
			sample.setName("KDH-"+i);
			sample.setAge(50+i);
			list.add(sample);
		}
		list.add(vo);
		return list;
	}
}
