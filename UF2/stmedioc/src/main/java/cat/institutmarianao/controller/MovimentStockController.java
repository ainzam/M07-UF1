package cat.institutmarianao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cat.institutmarianao.service.MovimentStockService;

@Controller
public class MovimentStockController {

    @Autowired
    private MovimentStockService movimentStockService;

    @RequestMapping("/movimentestoc/M020/2/−1")
    public String process() {
        movimentStockService.processMovimentStock("M020", 2, -1);
        return "redirect:/medicaments";
    }
}
