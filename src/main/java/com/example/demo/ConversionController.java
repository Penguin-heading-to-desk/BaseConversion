package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConversionController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");
		mav.addObject("msg", "変換したい数字を半角で入力してください\n例）10,　00000101,　0xff");
		return mav;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView convarsion(@RequestParam("num") String num, ModelAndView mav) {
		int dec = 0;
		int n = 0;
		String bin, hex;

//		ブランク確認
		if (num.equals("")) {
			mav.setViewName("index");
			mav.addObject("msg", "変換したい数字を半角で入力してください\n例）10,　00000101,　0xff");
		} else {
			// 進数変換の計算
			int len = String.valueOf(num).length();
			if (len == 8) {
				n = 2;
			} else if (num.contains("0x")) {
				num = num.replaceAll("0x", "");
				n = 16;
			} else {
				n = 10;
			}
			dec = Integer.parseInt(num, n);// n進数
			bin = Integer.toBinaryString(dec);// 2進数
			hex = Integer.toHexString(dec);// 16進数

			// 値セット
			mav.addObject("msg", "10進数：" + dec + "　2進数：" + bin + "　16進数：" + hex);
			mav.addObject("value", num);
			mav.setViewName("index");
		}
		return mav;

	}
}
