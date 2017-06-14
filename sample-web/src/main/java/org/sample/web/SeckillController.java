package org.sample.web;

import org.sample.dto.Exposer;
import org.sample.model.APIResult;
import org.sample.model.Pagination;
import org.sample.enums.ResultCode;
import org.sample.exception.APIException;
import org.sample.model.Seckill;
import org.sample.model.SeckillRecord;
import org.sample.service.RemoteSeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("seckill")
public class SeckillController {

    @Autowired
    private RemoteSeckillService service;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        Pagination<Seckill> result = service.search(1).getData();
        model.addAttribute("list", result.getList());
        return "list";
    }

    @RequestMapping(value = "api/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Seckill> list(@RequestParam(value = "index", required = false, defaultValue = "1") Integer index) {
        System.out.println(index);
        Pagination<Seckill> result = service.search(index).getData();
        return result.getList();
    }

    @RequestMapping(value = "{seckillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable Integer seckillId, Model model) {
        if (seckillId == null)
            return "redirect:/seckill/list";
        Seckill seckill = service.detail(seckillId).getData();
        if (seckill == null)
            return "forward:/seckill/list";
        model.addAttribute(seckill);
        return "detail";
    }

    @RequestMapping(value = "{seckillId}/exposer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public APIResult<Exposer> exposer(@PathVariable Integer seckillId) {
        APIResult<Exposer> result = service.exposer(seckillId);
        return result;
    }

    @RequestMapping(value = "{seckillId}/{md5}/execution", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public APIResult<SeckillRecord> execute(@PathVariable Integer seckillId, @PathVariable String md5,
                                            @CookieValue(value = "userMobile", required = false) String userMobile) {
        if (userMobile == null)
            throw new APIException(ResultCode.S400);
        APIResult<SeckillRecord> result = service.execute(seckillId, md5, userMobile);
        return result;
    }

    @RequestMapping(value = "time/now", method = RequestMethod.GET)
    @ResponseBody
    public long currentTime() {
        return System.currentTimeMillis();
    }

}